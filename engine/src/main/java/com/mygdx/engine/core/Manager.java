package com.mygdx.engine.core;

import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.engine.entity.EntityDisposedEvent;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.physics.CollisionEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Listener;

public abstract class Manager {
    // Holds all events that a manager is subscribed to
    private final SnapshotArray<EventListener<?>> listeners;

    public Manager() {
        listeners = new SnapshotArray<>();
    }

    protected void addPointerListener(EventListener<PointerEvent> listener) {
        listeners.add(listener);
        PointerEvent.addPointerListener(listener);
    }

    protected void addKeyListener(EventListener<KeyEvent> listener) {
        listeners.add(listener);
        KeyEvent.addKeyListener(listener);
    }

    protected void addCollisionListener(Listener<CollisionEvent> listener) {
        CollisionEvent.addCollisionListener(listener);
    }

    protected void addEntityDisposedListener(Listener<EntityDisposedEvent> listener) {
        EntityDisposedEvent.addDisposedListener(listener);
    }
}
