package com.mygdx.engine.core;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.SceneManager;

// Contains the Core of the engine
// TODO: maybe turn this into a dependency injector
public class GameContainer {
    private final Game parent;
    private EntityManager entityManager;
    private SceneManager sceneManager;
    private InputManager inputManager;
    private PlayerControlManager playerControlManager;
    private CollisionManager collisionManager;
    private AudioManager audioManager;
    private BehaviourManager behaviourManager;

    // Instantiate classes here so we can plug and play whenever we need
    public GameContainer(Game game) {
        parent = game;
        entityManager = new EntityManager();
        collisionManager = new CollisionManager();
        sceneManager = new SceneManager();
        inputManager = new InputManager(Gdx.input);
        playerControlManager = new PlayerControlManager(this);
        audioManager = new AudioManager();
        behaviourManager = new BehaviourManager(entityManager);
    }

    public Game getParent() {
        return parent;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public void setCollisionManager(CollisionManager collisionManager) {
        this.collisionManager = collisionManager;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public PlayerControlManager getPlayerControlManager() {

        return playerControlManager;
    }

    public void setPlayerControlManager(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
    }

    public AudioManager getAudioManager() {

        return audioManager;
    }

    public void setAudioManager(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public BehaviourManager getBehaviourManager() {
        return behaviourManager;
    }

    public void setBehaviourManager(BehaviourManager behaviourManager) {
        this.behaviourManager = behaviourManager;
    }
}
