package com.mygdx.engine.physics;

import java.util.ArrayList;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;

public class CollisionEvent {
    private Entity entityA;
    private Entity entityB;

    // Default Constructor
    public CollisionEvent(Entity entityA, Entity entityB) {
    }

    // Signal Related Stuff
    private final static Signal<CollisionEvent> collisionEventSignal = new Signal<>();
    private final static ArrayList<CollisionEvent> collisionEvents = new ArrayList<>();

    public static void addCollisionListener(Listener<CollisionEvent> listener) {
        collisionEventSignal.add(listener);
    }

    public static void removeCollisionListener(Listener<CollisionEvent> listener) {
        collisionEventSignal.remove(listener);
    }

    public static synchronized void addCollisionEvent(CollisionEvent event) {
        collisionEventSignal.dispatch(event);
    }

    public static synchronized void processCollisionEvents() {
        for (CollisionEvent k : collisionEvents) {
            collisionEventSignal.dispatch(k);
        }
        // Clear the list
        collisionEvents.clear();
    }

    // Getters
    public Entity getEntityA() {
        return entityA;
    }

    public Entity getEntityB() {
        return entityB;
    }
}