package com.mygdx.engine.core;

import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.input.PointerEvent;
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

    protected void addPointerListener(EventListener<PointerEvent> listener) {
        listeners.add(listener);
        PointerEvent.addPointerListener(listener);
    }

    protected void addKeyListener(EventListener<KeyEvent> listener) {
        listeners.add(listener);
        KeyEvent.addKeyListener(listener);
    }
}
