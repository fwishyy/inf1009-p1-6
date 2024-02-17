package com.mygdx.engine.input;

import java.util.ArrayList;

import com.mygdx.engine.utils.Listener;
import com.mygdx.engine.utils.Signal;

public class KeyEvent {
    private final int keyCode;
    private final boolean pressed;

    // Default Constructor
    public KeyEvent(int keyCode, boolean pressed) {
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    // Signal Related Stuff
    private final static Signal<KeyEvent> keyEventSignal = new Signal<>();
    private final static ArrayList<KeyEvent> keyEvents = new ArrayList<>();

    public static void addKeyListener(Listener<KeyEvent> listener) {
        keyEventSignal.add(listener);
    }

    public static void removeKeyListener(Listener<KeyEvent> listener) {
        keyEventSignal.remove(listener);
    }

    protected static synchronized void addKeyEvent(KeyEvent event) {
        keyEventSignal.dispatch(event);
    }

    protected static synchronized void processKeyEvents() {
        for (KeyEvent k : keyEvents) {
            keyEventSignal.dispatch(k);
        }
        // Clear the list
        keyEvents.clear();
    }

    // Getters
    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }
}