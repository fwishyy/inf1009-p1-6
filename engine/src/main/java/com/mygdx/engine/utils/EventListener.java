package com.mygdx.engine.utils;

public class EventListener<T> implements Listener<T> {
    private T event;

    public void onSignal(Signal<T> signal, T object) {
    }

    public T getEvent() {
        return event;
    }
}