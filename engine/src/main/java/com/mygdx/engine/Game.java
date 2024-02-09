package com.mygdx.engine;

import com.badlogic.gdx.ApplicationListener;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.GameContainer;

// generic instance of a game
public class Game implements ApplicationListener {

    protected GameContainer container;
    protected SceneManager sceneManager;
    protected InputManager inputManager;

    @Override
    public void create() {
        // we create game dependencies here because input relies on LibGDX to be initialised
        // from here on we inject GameContainer to use the same management classes
        container = new GameContainer(this);
        sceneManager = container.getSceneManager();
        inputManager = container.getInputManager();
    }

    @Override
    public void render() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}