package com.mygdx.engine.core;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.SceneManager;

// Contains the Core of the engine
// TODO: maybe turn this into a dependency injector
public class GameContainer {
    private final Game parent;
    private final EntityManager entityManager;
    private final SceneManager sceneManager;
    private final InputManager inputManager;
    private final PlayerControlManager playerControlManager;
    private final CollisionManager collisionManager;
    private final AudioManager audioManager;

    // Instantiate classes here so we can plug and play whenever we need
    public GameContainer(Game game) {
        parent = game;
        entityManager = new EntityManager();
        collisionManager = new CollisionManager();
        sceneManager = new SceneManager();
        inputManager = new InputManager(Gdx.input);
        playerControlManager = new PlayerControlManager();
        audioManager = new AudioManager();
    }

    public Game getParent() {
        return parent;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public PlayerControlManager getPlayerControlManager() {

        return playerControlManager;
    }

    public AudioManager getAudioManager() {

        return audioManager;
    }
}
