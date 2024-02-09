package com.mygdx.engine.utils;

public interface Listener<T> {
    void onSignal(Signal<T> signal, T object);
}
