package com.mygdx.engine.actions;

// These new InputActions are used to provide an abstraction of the InputManager and PlayerControlManager from the Entities themselves
public class InputAction {
    protected int binding;
    protected boolean isTriggered;

    public InputAction() {

    }

    public InputAction(int keyCode) {
        this.binding = keyCode;
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
    }

    public int getBinding() {
        return binding;
    }

    public void setBinding(int binding) {
        this.binding = binding;
    }
}
