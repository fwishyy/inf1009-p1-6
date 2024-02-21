package com.mygdx.engine.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class EventBus {

    private static final LinkedHashMap<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new LinkedHashMap<>();
    private static final LinkedHashMap<Class<? extends Event>, List<Event>> eventsMap = new LinkedHashMap<>();

    public static <T extends Event> void addListener(Class<? extends Event> eventType, EventListener<? extends Event> listener) {
        List<EventListener<? extends Event>> listenerList = listenerMap.getOrDefault(eventType, new ArrayList<>());
        listenerList.add(listener);
        listenerMap.put(eventType, listenerList);
    }

    public static synchronized void addEvent(Event event) {
        List<Event> eventsList = eventsMap.getOrDefault(event.getClass(), new ArrayList<>());
        eventsList.add(event);
        eventsMap.put(event.getClass(), eventsList);
    }

    public static synchronized List<Event> getEventByType(Class<? extends Event> eventType) {
        return eventsMap.getOrDefault(eventType, new ArrayList<>());
    }

    public static synchronized void processEvents(Class<? extends Event> eventType) {
        List<Event> events = eventsMap.get(eventType);
        List<EventListener<? extends Event>> listenerList = listenerMap.get(eventType);

        if (events != null && listenerList != null) {
            for (Event event : events) {
                for (EventListener<? extends Event> listener : listenerList) {
                    listener.onSignal(event);
                }
            }
            events.clear();
        }
    }
}
