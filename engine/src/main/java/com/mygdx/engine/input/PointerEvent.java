package com.mygdx.engine.input;

import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;

import java.util.ArrayList;

public class PointerEvent {
    public enum Type {
        DOWN, UP, HOVER
    }

    private final float screenX;
    private final float screenY;
    private final Type type;
    private final int button;
    private boolean handled;

    public PointerEvent(float x, float y, Type type, int button) {
        this.screenX = x;
        this.screenY = y;
        this.type = type;
        this.button = button;
        this.handled = false;
    }

    protected void handle() {
        this.handled = true;
    }

    // Static Members
    private final static Signal<PointerEvent> pointerSignal = new Signal<PointerEvent>();

    private static ArrayList<PointerEvent> pointerEvents = new ArrayList<>();

    public static void addPointerListener(Listener<PointerEvent> listener) {
        pointerSignal.add(listener);
    }

    public static void removePointerListener(Listener<PointerEvent> listener) {
        pointerSignal.remove(listener);
    }

    public static synchronized void addPointerEvent(PointerEvent event) {
        pointerEvents.add(event);
    }

    public static synchronized void processPointerEvents() {
        // dispatch pointer events
        for (PointerEvent p : pointerEvents) {
            pointerSignal.dispatch(p);
        }
        // clear any backlog
        pointerEvents.clear();
    }

    public float getScreenX() {
        return this.screenX;
    }

    public float getScreenY() {
        return this.screenY;
    }

    public Type getType() {
        return this.type;
    }
}
