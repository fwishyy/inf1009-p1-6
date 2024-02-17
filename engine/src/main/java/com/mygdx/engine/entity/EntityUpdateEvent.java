package com.mygdx.engine.entity;

import java.util.ArrayList;

import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;

public class EntityUpdateEvent {
    private GameAction gameAction;

    public EntityUpdateEvent(GameAction gameAction) {
        this.gameAction = gameAction;
    }

    // Signal Related Stuff
    private final static Signal<EntityUpdateEvent> entityUpdateSignal = new Signal<>();
    private final static ArrayList<EntityUpdateEvent> entityUpdateEvents = new ArrayList<>();

    public static void addEntityUpdateListener(Listener<EntityUpdateEvent> listener) {
        entityUpdateSignal.add(listener);
    }

    public static void removeEntityUpdateListener(Listener<EntityUpdateEvent> listener) {
        entityUpdateSignal.remove(listener);
    }

    public static synchronized void addEntityUpdateEvent(EntityUpdateEvent event) {
        entityUpdateEvents.add(event);
    }

    public static synchronized void processEntityUpdateEvents() {
        for (EntityUpdateEvent e : entityUpdateEvents) {
            entityUpdateSignal.dispatch(e);
        }
        // Clear the list
        entityUpdateEvents.clear();
    }

    // Getters
    public GameAction getAction() {
        return gameAction;
    }
}
