package com.mygdx.engine.physics;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;

import java.util.List;

public class CollisionEvent extends Event {

    private final Entity entityA;
    private final Entity entityB;

    public CollisionEvent(Entity entityA, Entity entityB) {
        this.entityA = entityA;
        this.entityB = entityB;
    }
    public static void addEvent(CollisionEvent newCollisionEvent) {
        List<Event> collisionEvents = EventBus.getEventByType(CollisionEvent.class);
        Entity A = newCollisionEvent.getEntityA();
        Entity B = newCollisionEvent.getEntityB();
        if (collisionEvents != null) {
            for (Event e : collisionEvents) {
                CollisionEvent collisionEvent = (CollisionEvent) e;
                if ((A == collisionEvent.getEntityA() && B == collisionEvent.getEntityB()) || (A == collisionEvent.getEntityB()) && B == collisionEvent.getEntityA()) {
                    return;
                }
            }
        }

        EventBus.addEvent(newCollisionEvent);
    }

    public Entity getEntityA() {
        return this.entityA;
    }

    public Entity getEntityB() {
        return this.entityB;
    }
}
