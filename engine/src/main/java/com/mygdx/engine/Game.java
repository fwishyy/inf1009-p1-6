package com.mygdx.engine;

import com.badlogic.gdx.ApplicationListener;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.SceneManager;

// generic instance of a game
public class Game implements ApplicationListener {

    protected GameContainer container;
    protected EntityManager entityManager;
    protected SceneManager sceneManager;
    protected InputManager inputManager;
    protected PlayerControlManager playerControlManager;
    protected CollisionManager collisionManager;
    protected AudioManager audioManager;
    protected BehaviourManager behaviourManager;
    @Override
    public void create() {
        // we create game dependencies here because input relies on LibGDX to be initialised
        // from here on we inject GameContainer to use the same management classes
        container = new GameContainer(this);
        this.entityManager = container.getEntityManager();
        this.collisionManager = container.getCollisionManager();
        this.sceneManager = container.getSceneManager();
        this.inputManager = container.getInputManager();
        this.playerControlManager = container.getPlayerControlManager();
        this.audioManager = container.getAudioManager();
        this.behaviourManager = container.getBehaviourManager();
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