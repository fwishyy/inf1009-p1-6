package com.mygdx.engine.utils;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.scenes.SceneManager;

// Contains the Core of the engine
// TODO: maybe turn this into a dependency injector
public class GameContainer {
    private final Game parent;
    private final SceneManager sceneManager;
    private final InputManager inputManager;

    // Instantiate classes here so we can plug and play whenever we need
    public GameContainer(Game game) {
        parent = game;
        sceneManager = new SceneManager();
        inputManager = new InputManager(Gdx.input);
    }

    public Game getParent() {
        return parent;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
