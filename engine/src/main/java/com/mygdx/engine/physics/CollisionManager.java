package com.mygdx.engine.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.engine.entity.EntityDisposedEvent;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollisionManager extends Manager {

    HashMap<Entity, Collider> colliderMap;

    public CollisionManager() {

        colliderMap = new HashMap<>();

        addEntityAddedListener(new EventListener<EntityAddedEvent>() {
            @Override
            public void onSignal(Event e) {
                handleEntityAdded((EntityAddedEvent) e);
            }
        });
        addEntityDisposedListener(new EventListener<EntityDisposedEvent>() {
            @Override
            public void onSignal(Event e) {
                handleEntityDisposed((EntityDisposedEvent) e);
            }
        });
        addCollisionListener(new EventListener<CollisionEvent>() {
            @Override
            public void onSignal(Event e) {
                if (e instanceof CollisionEvent)
                    resolveCollision((CollisionEvent) e);
            }
        });
    }

    private void resolveCollision(CollisionEvent e) {
        Collider colA = this.colliderMap.get(e.getEntityA());
        Collider colB = this.colliderMap.get(e.getEntityB());
        e.getEntityA().collide(colB);
        e.getEntityB().collide(colA);
    }

    public void addCollider(Entity entity) {
        Collider col = new Collider(entity);
        col.setEntity(entity);
        this.colliderMap.put(entity, col);
    }

    public void addCollider(Entity entity, float width, float height) {
        Collider col = new Collider(entity, width, height);
        col.setEntity(entity);
        this.colliderMap.put(entity, col);
    }

    public void removeCollider(Entity entity) {
        this.colliderMap.remove(entity);
    }

    public void replaceCollider(Entity entity, Collider collider) {
        colliderMap.replace(entity, collider);
    }

    public void update() {

        List<Collider> colliderList = new ArrayList<>(colliderMap.values());

        for (Collider col : colliderList)
            col.update();

        checkCollisions(colliderList);
        EventBus.processEvents(CollisionEvent.class);
    }

    private void handleEntityAdded(EntityAddedEvent e) {
        Entity entity = e.getEntity();
        // add collider if entity does not already have one
        if (!colliderMap.containsKey(entity)) {
            addCollider(entity);
        }
    }

    private void handleEntityDisposed(EntityDisposedEvent e) {
        Entity entity = e.getEntity();
        Collider collider = colliderMap.get(entity);
        if (collider != null) {
            collider.dispose();
            colliderMap.remove(entity);
        }
    }

    public void drawCollider(ShapeRenderer shapeRenderer, Color color) {
        List<Collider> colliderList = new ArrayList<>(colliderMap.values());

        for (Collider col : colliderList)
            col.drawCollider(shapeRenderer, color);
    }

    public void setOffset(Vector2 v2) {
        for (Entity entity : colliderMap.keySet()) {
            colliderMap.get(entity).setOffset(v2);
        }
    }

    public void setOffset(Vector2 v2, Entity entity) {
        colliderMap.get(entity).setOffset(v2);
    }

    public Collider getCollider(Entity entity) {
        return colliderMap.get(entity);
    }

    public ArrayList<Collider> getColliders() {
        ArrayList<Collider> colliderList = new ArrayList<>(colliderMap.values());
        for (Collider curr : colliderList) {
            colliderList.add(curr);
        }
        return colliderList;
    }

    public ArrayList<Collider> getColliders(String type) {
        ArrayList<Collider> colliderList = new ArrayList<>(colliderMap.values());
        for (Collider curr : colliderList) {
            if (curr.getEntity().getType() == type) {
                colliderList.add(curr);
            }
        }
        return colliderList;
    }

    public void dispose() {
        for (Collider col : colliderMap.values())
            col.dispose();
        colliderMap.clear();
    }

    public void dispose(Entity entity) {
        colliderMap.get(entity).dispose();
        colliderMap.remove(entity);
    }

    private void checkCollisions(List<Collider> colliderList) {
        for (Collider curr : colliderList) {
            for (Collider other : colliderList) {
                if (curr == other)
                    continue;
                // if either colliders are not collidable, no collision event should happen
                if (curr.getIsCollidable() == false || other.getIsCollidable() == false)
                    continue;
                if (curr.isCollide(other))
                    curr.onCollide(other);
            }
        }
    }
}
