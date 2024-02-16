package com.mygdx.engine.utils;

import com.badlogic.gdx.utils.SnapshotArray;

public class Signal<T> {
    private SnapshotArray<Listener<T>> listeners;

    public Signal() {
        listeners = new SnapshotArray<>();
    }

    public void add(Listener<T> listener) {
        listeners.add(listener);
    }

    public void remove(Listener<T> listener) {
        listeners.removeValue(listener, true);
    }

    public void removeAllListeners() {
        listeners.clear();
    }

    public void dispatch(T object) {
        final Object[] items = listeners.begin();
        for (int i = 0; i < listeners.size; ++i) {
            Listener<T> listener = (Listener<T>) items[i];
            listener.onSignal(this, object);
        }
        listeners.end();
    }
}