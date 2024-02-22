package com.mygdx.engine.core;

import com.mygdx.engine.entity.EntityDisposedEvent;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.physics.CollisionEvent;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;

import java.util.ArrayList;
import java.util.List;

public abstract class Manager {
    // Holds all events that a manager is subscribed to
    private List<EventListener<? extends Event>> listeners;

    public Manager() {
        listeners = new ArrayList<>();
    }

    protected void addPointerListener(EventListener<PointerEvent> listener) {
        listeners.add(listener);
        PointerEvent.addListener(PointerEvent.class, listener);
    }

    protected void addKeyListener(EventListener<KeyEvent> listener) {
        listeners.add(listener);
        KeyEvent.addListener(KeyEvent.class, listener);
    }

    protected void addCollisionListener(EventListener<CollisionEvent> listener) {
        listeners.add(listener);
        CollisionEvent.addListener(CollisionEvent.class, listener);
    }

    protected void addEntityDisposedListener(EventListener<EntityDisposedEvent> listener) {
        listeners.add(listener);
        EntityDisposedEvent.addListener(EntityDisposedEvent.class, listener);
    }

    protected void clearAllListeners() {
        for (EventListener<? extends Event> listener : listeners) {
            EventBus.removeListener(listener);
        }
    }
}
