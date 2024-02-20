package com.mygdx.engine.entity;

import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;

import java.util.ArrayList;

public class EntityDisposedEvent {
    private Entity entity;

    // Default Constructor
    public EntityDisposedEvent(Entity entity) {
        this.entity = entity;
    }

    // Signal Related Stuff
    private final static Signal<EntityDisposedEvent> disposedSignal = new Signal<>();
    private final static ArrayList<EntityDisposedEvent> disposedEvents = new ArrayList<>();

    public static void addDisposedListener(Listener<EntityDisposedEvent> listener) {
        disposedSignal.add(listener);
    }

    public static void removeDisposedListener(Listener<EntityDisposedEvent> listener) {
        disposedSignal.remove(listener);
    }

    public static synchronized void addDisposedEvent(EntityDisposedEvent event) {
        disposedEvents.add(event);
    }

    public static synchronized void processDisposedEvents() {
        for (EntityDisposedEvent e : disposedEvents) {
            disposedSignal.dispatch(e);
        }
        // Clear the list
        disposedEvents.clear();
    }

    // Getters
    public Entity getEntity() {
        return this.entity;
    }
}
