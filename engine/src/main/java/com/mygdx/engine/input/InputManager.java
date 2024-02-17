package com.mygdx.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager extends InputAdapter {
    // InputMultiplexer is used to handle multiple input processors
    private final InputMultiplexer multiplexer;
    private final int mouseButtonOffset = 1001;

    public InputManager(Input input) {
        multiplexer = new InputMultiplexer();
        input.setInputProcessor(multiplexer);
        addInputProcessor(this);
    }

    public void addInputProcessor(InputProcessor processor) {
        multiplexer.addProcessor(0, processor);
    }

    public void removeInputProcessor(InputProcessor processor) {
        multiplexer.removeProcessor(processor);
    }

    public void processAllEvents() {
        PointerEvent.processPointerEvents();
        KeyEvent.processKeyEvents();
    }

    // TODO: add keyboard support
    @Override
    public boolean keyDown(int keyCode) {
        // Method is called when a key is pressed
        if ((keyCode < 0 || keyCode > 255) && keyCode < 1000) {
            // Check if special keys are being pressed
            return false;
        } else {
            KeyEvent.addKeyEvent(new KeyEvent(keyCode, true));
            return true;
        }

    }

    @Override
    public boolean keyUp(int keyCode) {
        // Method is called when a key is released
        if ((keyCode < 0 || keyCode > 255) && keyCode < 1000) {
            // Check if special keys are being pressed
            return false;
        } else {
            KeyEvent.addKeyEvent(new KeyEvent(keyCode, true));
            return true;
        }
    }

    // *************
    // *** Mouse ***
    // *************

    // TODO: maybe check for coordinates here
    // Currently not checked because InputManager then requires some knowledge of the coordinates, which may not be very friendly
    // There's also no way to distinguish between clicking buttons and clicking (to shoot), we add a custom mouse offset to define it in our keybindings
    public synchronized boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PointerEvent.addPointerEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.DOWN, button));
        KeyEvent.addKeyEvent(new KeyEvent(button + mouseButtonOffset, true));
        return true;
    }

    public synchronized boolean touchUp(int screenX, int screenY, int pointer, int button) {
        PointerEvent.addPointerEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.UP, button));
        KeyEvent.addKeyEvent(new KeyEvent(button + mouseButtonOffset, false));
        return true;
    }

    public synchronized boolean mouseMoved(int screenX, int screenY) {
        PointerEvent.addPointerEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.HOVER, -1));
        return true;
    }
}
