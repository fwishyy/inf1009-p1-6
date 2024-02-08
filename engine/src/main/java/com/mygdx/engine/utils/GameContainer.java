package com.mygdx.engine.utils;

import com.mygdx.engine.scenes.SceneManager;

// Contains the Core of the engine
public class GameContainer {
    private final SceneManager sceneManager;

    public GameContainer() {
        sceneManager = new SceneManager();
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}
