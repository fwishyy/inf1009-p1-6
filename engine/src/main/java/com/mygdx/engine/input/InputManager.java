package com.mygdx.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.engine.utils.EventBus;

import java.util.LinkedHashMap;

// TODO: add dragged events maybe
public class InputManager extends InputAdapter {
    // InputMultiplexer is used to handle multiple input processors
    private final InputMultiplexer multiplexer;
    private final int mouseButtonOffset = 1001;
    private ControllerHandler controllerHandler;
    private LinkedHashMap<Integer, Boolean> keyState;

    public InputManager(Input input) {
        multiplexer = new InputMultiplexer();

        input.setInputProcessor(multiplexer);
        addInputProcessor(this);

        keyState = new LinkedHashMap<>();
    }

    public void addInputProcessor(InputProcessor processor) {
        multiplexer.addProcessor(0, processor);
    }

    public void removeInputProcessor(InputProcessor processor) {
        multiplexer.removeProcessor(processor);
    }

    public void processAllEvents() {
        EventBus.processEvents(PointerEvent.class);
    }

    @Override
    public boolean keyDown(int keyCode) {
        keyState.put(keyCode, true);
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        keyState.put(keyCode, false);
        return true;
    }

    public boolean getKeyState(int keyCode) {
        return keyState.getOrDefault(keyCode, false);
    }

    // *************
    // *** Mouse ***
    // *************

    // TODO: maybe check for coordinates here
    // Currently not checked because InputManager then requires some knowledge of the coordinates, which may not be very friendly
    // There's also no way to distinguish between clicking buttons and clicking (to shoot), we add a custom mouse offset to define it in our keybindings

    @Override
    public synchronized boolean touchDragged(int screenX, int screenY, int pointer) {
        PointerEvent.addEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.HOVER, -1));
        return true;
    }

    @Override
    public synchronized boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PointerEvent.addEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.DOWN, button));
        keyState.put(button + mouseButtonOffset, true);
        return true;
    }

    @Override
    public synchronized boolean touchUp(int screenX, int screenY, int pointer, int button) {
        PointerEvent.addEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.UP, button));
        keyState.put(button + mouseButtonOffset, false);
        return true;
    }

    @Override
    public synchronized boolean mouseMoved(int screenX, int screenY) {
        PointerEvent.addEvent(new PointerEvent(screenX, screenY, PointerEvent.Type.HOVER, -1));
        return true;
    }
}
