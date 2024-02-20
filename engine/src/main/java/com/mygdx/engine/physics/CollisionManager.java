package com.mygdx.engine.physics;

import java.util.*;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;
import com.mygdx.engine.entity.*;

public class CollisionManager extends Manager {

    HashMap<Entity, Collider> colliderMap;

    public CollisionManager() {
        addCollisionListener(new EventListener<CollisionEvent>() {
            @Override
            public void onSignal(Signal<CollisionEvent> signal, CollisionEvent e) {
                resolveCollision(e);
            }
        });

        addEntityDisposedListener(new EventListener<EntityDisposedEvent>() {
            @Override
            public void onSignal(Signal<EntityDisposedEvent> signal, EntityDisposedEvent e) {
                handleEntityDisposed(e);
            }
        });

        colliderMap = new HashMap<>();
    }

    private void resolveCollision(CollisionEvent e) {
        //TODO: resolve collisions here
//    	System.out.println("A: " + e.getEntityA().getType() + " B: " + e.getEntityB().getType());
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
    }

    public void dispose() {
        for (Collider col : colliderMap.values())
            col.dispose();
        colliderMap.clear();
    }

    private void handleEntityDisposed(EntityDisposedEvent e) {
        Entity entity = e.getEntity();
        Collider collider = colliderMap.get(entity);
        collider.dispose();
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
                    if (CollisionEvent.checkRedundant(curr.getEntity(), other.getEntity()) == false)
                        curr.onCollide(other);
            }
        }
    }
}
