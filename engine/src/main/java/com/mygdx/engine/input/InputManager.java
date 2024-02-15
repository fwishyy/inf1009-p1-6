package com.mygdx.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager extends InputAdapter {
	// InputMultiplexer is used to handle multiple input processors
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
    	// Method is called when a key is pressed
    	if ((keyCode < 0 || keyCode > 255) && keyCode < 1000) {
    		// Check if special keys are being pressed
    		System.out.println("Special keys being pressed");
            return false;
    	} else {
            KeyEvent.addKeyEvent(new KeyEvent(keyCode, true));
    		System.out.println("Key Pressed");
    		return true;
    	}
        
    }

    @Override
    public boolean keyUp(int keyCode) {
    	// Method is called when a key is released
    	if ((keyCode < 0 || keyCode > 255) && keyCode < 1000) {
    		// Check if special keys are being pressed
    		System.out.println("Special keys being released");
            return false;
    	} else {
            KeyEvent.addKeyEvent(new KeyEvent(keyCode, true));
    		System.out.println("Key released");
    		return true;
    	}
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
