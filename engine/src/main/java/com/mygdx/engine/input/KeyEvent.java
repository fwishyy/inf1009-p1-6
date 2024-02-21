package com.mygdx.engine.input;

import com.mygdx.engine.utils.Event;

public class KeyEvent extends Event {

    private int keyCode;
    private boolean pressed;

    public KeyEvent(int keyCode, boolean pressed) {
        this.keyCode = keyCode;
        this.pressed = pressed;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isPressed() {
        return pressed;
    }
}
