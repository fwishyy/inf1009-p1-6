package com.mygdx.engine;

import com.badlogic.gdx.ApplicationListener;

import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.GameContainer;

// generic instance of a game
public class Game implements ApplicationListener {

    protected SceneManager sceneManager;

    // TODO: maybe rely on an injector to place variables here
    // Container here contains all the dependencies that the game needs
    public Game(GameContainer container) {
        this.sceneManager = container.getSceneManager();
    }

    @Override
    public void create() {

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