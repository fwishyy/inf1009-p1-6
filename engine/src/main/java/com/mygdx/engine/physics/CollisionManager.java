package com.mygdx.engine.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
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

    private void handleEntityDisposed(EntityDisposedEvent e) {
        Entity entity = e.getEntity();
        Collider collider = colliderMap.get(entity);
        if(collider != null){
            collider.dispose();
            colliderMap.remove(entity);
        }
    }

    public void dispose() {
        for (Collider col : colliderMap.values())
            col.dispose();
        colliderMap.clear();
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

    public void drawCollider(ShapeRenderer shapeRenderer, Color color) {
        List<Collider> colliderList = new ArrayList<>(colliderMap.values());

        for (Collider col : colliderList)
            col.drawCollider(shapeRenderer, color);
    }
}
