package com.mygdx.engine.core;

import com.mygdx.engine.entity.EntityDisposedEvent;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.physics.CollisionEvent;
import com.mygdx.engine.utils.EventListener;

public abstract class Manager {
    // Holds all events that a manager is subscribed to

    public Manager() {
    }

    protected void addPointerListener(EventListener<PointerEvent> listener) {
        PointerEvent.addListener(PointerEvent.class, listener);
    }

    protected void addKeyListener(EventListener<KeyEvent> listener) {
        KeyEvent.addListener(KeyEvent.class, listener);
    }

    protected void addCollisionListener(EventListener<CollisionEvent> listener) {
        CollisionEvent.addListener(CollisionEvent.class, listener);
    }

    protected void addEntityDisposedListener(EventListener<EntityDisposedEvent> listener) {
        EntityDisposedEvent.addListener(EntityDisposedEvent.class, listener);
    }
}
