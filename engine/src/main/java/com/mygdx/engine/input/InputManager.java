package com.mygdx.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager extends InputAdapter {
    private final InputMultiplexer multiplexer;

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
    }

    // TODO: add keyboard support
    @Override
    public boolean keyDown(int keyCode) {
        System.out.println("Key pressed");
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        System.out.println("Key released");
        return false;
    }

    // TODO: add mouse support
    // *************
    // *** Mouse ***
    // *************

    public synchronized boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    public synchronized boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    public synchronized boolean mouseMoved(int screenX, int screenY) {
        PointerEvent.addPointerEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.HOVER, -1));
        return true;
    }
}
