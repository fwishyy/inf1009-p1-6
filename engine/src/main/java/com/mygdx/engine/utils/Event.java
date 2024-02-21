package com.mygdx.engine.utils;

public abstract class Event {
    public static synchronized void addListener(Class<? extends Event> eventType, EventListener<? extends Event> listener) {
        EventBus.addListener(eventType, listener);
    }

    public static synchronized void addEvent(Event e) {
        EventBus.addEvent(e);
    }
}
