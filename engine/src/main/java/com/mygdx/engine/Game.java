package com.mygdx.engine;

import com.badlogic.gdx.ApplicationListener;

import com.mygdx.engine.scenes.SceneManager;

// generic instance of a game
public class Game implements ApplicationListener {

    protected SceneManager sceneManager;

    @Override
    public void create() {
        sceneManager = new SceneManager();
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