package com.mygdx.engine.input;

import com.mygdx.engine.utils.Event;

public class PointerEvent extends Event {
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

    public float getScreenX() {
        return this.screenX;
    }

    public float getScreenY() {
        return this.screenY;
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {
        DOWN, UP, HOVER
    }
}
