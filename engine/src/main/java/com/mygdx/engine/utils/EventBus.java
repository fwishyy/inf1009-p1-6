package com.mygdx.engine.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {
    private static final ConcurrentHashMap<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class<? extends Event>, List<Event>> eventsMap = new ConcurrentHashMap<>();

    public static synchronized <T extends Event> void addListener(Class<? extends Event> eventType, EventListener<? extends Event> listener) {
        List<EventListener<? extends Event>> listenerList = listenerMap.getOrDefault(eventType, new CopyOnWriteArrayList<>());
        listenerList.add(listener);
        listenerMap.put(eventType, listenerList);
    }

    public static synchronized void removeListener(EventListener<? extends Event> listener) {
        for (Map.Entry<Class<? extends Event>, List<EventListener<? extends Event>>> entry : listenerMap.entrySet()) {
            List<EventListener<? extends Event>> listenerList = entry.getValue();
            if (listenerList.contains(listener)) {
                listenerList.remove(listener);
                break;
            }
        }
    }

    public static synchronized void addEvent(Event event) {
        List<Event> eventsList = eventsMap.getOrDefault(event.getClass(), new CopyOnWriteArrayList<>());
        eventsList.add(event);
        eventsMap.put(event.getClass(), eventsList);
    }

    public static synchronized List<Event> getEventByType(Class<? extends Event> eventType) {
        return eventsMap.getOrDefault(eventType, new CopyOnWriteArrayList<>());
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
