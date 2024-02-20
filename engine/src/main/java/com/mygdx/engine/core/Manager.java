package com.mygdx.engine.core;

import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.engine.entity.EntityDisposedEvent;
import com.mygdx.engine.physics.CollisionEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;
import jdk.internal.event.Event;

public abstract class Manager {
    // Holds all events that a manager is subscribed to
    private final SnapshotArray<EventListener<?>> listeners;

    public Manager() {
        listeners = new SnapshotArray<>();
    }

    protected void addCollisionListener(Listener<CollisionEvent> listener) {
        CollisionEvent.addCollisionListener(listener);
    }

    protected void addEntityDisposedListener(Listener<EntityDisposedEvent> listener) {
        EntityDisposedEvent.addDisposedListener(listener);
    }
}