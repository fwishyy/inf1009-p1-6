package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.backgroundsprite.Enemy;
import com.mygdx.camera.Camera;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.controls.KeyCodes;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;
import com.mygdx.mechanics.BackGround;
import com.mygdx.mechanics.Boundary;
import com.mygdx.mechanics.SpawnSystem;
import com.mygdx.mechanics.Wave;
import com.mygdx.player.Player;
import com.mygdx.player.SeekBehaviour;

import java.util.Random;

public class GameScene extends Scene {

    EventListener<WinEvent> winEventListener;
    EventListener<LoseEvent> loseEventListener;
    //ENGINE
    private GameContainer container;
    private EntityManager em;
    private CollisionManager cm;
    private InputManager im;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    private SceneManager sm;
    //CONCRETE GAME LAYER FOR DEMO PURPOSES
    private Player p1;
    private SeekBehaviour seek;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    
    //Camera
    private Camera camera;
    
    //Spawn
    private SpawnSystem enemySpawn;
    private BackGround bg;
    
    private Boundary bounds;

    public GameScene(GameContainer container) {
        this.container = container;
        em = container.getEntityManager();
        cm = container.getCollisionManager();
        pm = container.getPlayerControlManager();
        bm = container.getBehaviourManager();
        im = container.getInputManager();
        sm = container.getSceneManager();
    }

    @Override
    public void show() {
        super.show();

        winEventListener = new EventListener<WinEvent>() {
            public void onSignal(Event e) {
                onWin();
            }
        };
        loseEventListener = new EventListener<LoseEvent>() {
            public void onSignal(Event e) {
                onLose();
            }
        };

        WinEvent.addListener(WinEvent.class, winEventListener);
        LoseEvent.addListener(LoseEvent.class, loseEventListener);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
        //Create entities to spawn here
        em.createEntity(1, Player.class, "sprite/Converted_Vampire/Run.png", 0, 0, "player1", 1, 8, 0.1f);
        // assignment of unique entities
        p1 = (Player) em.getEntity("player1");
        // add colliders to entities that need collision logic
        // in this case, remember that the current player using a spritesheet, so we have to calculate frame size of the 
        cm.addCollider(p1, p1.getWidth() / 2, p1.getHeight() / 2);
        // attempt at centering
        cm.setOffset(
                new Vector2(p1.getWidth() / 2 - cm.getCollider(p1).getWidth() / 2,
                        p1.getHeight() / 2 - cm.getCollider(p1).getHeight() / 2),
                p1);

        // player control mapping 
        ActionMap playerControls = new ActionMap();
        playerControls.add2DMovementBindings(KeyCodes.W, KeyCodes.A, KeyCodes.S, KeyCodes.D);
        pm.setActionMap(p1, playerControls);
        
        
        bg = new BackGround("bg/bg.png");
        
        // create new camera and center it
        camera = new Camera();
        camera.setOffset(p1.getWidth()/2, p1.getHeight()/2);
        camera.setBoundary(bg.getMinPos(), bg.getMaxPos());
        
        // create spawn system and set interval to spawn 1 enemy/4s
        enemySpawn = new SpawnSystem(container, 1, 1.5f, 10);
        enemySpawn.setBoundary(bg.getMinPos(), bg.getMaxPos());
        
        // simple seeking behaviour towards unique entity player1 with a speed of 50
//        seek = new SeekBehaviour(em.getEntity("player1"), 50);
//        bm.addBehaviour(lich, seek);
//        for (Entity entity : em.getEntities("goblin")) {
//            bm.addBehaviour(entity, seek);
//        }
        
        bounds = new Boundary(p1, bg.getMinPos(), bg.getMaxPos());
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        bounds.update();
        batch.begin();
        bg.update(batch);
        em.update();
        em.draw(batch);
        cm.update();
        bm.update(Gdx.graphics.getDeltaTime());
        batch.end();
        pm.update();
        
        // draw collider for debugging purposes
        cm.drawCollider(shapeRenderer, Color.RED);

        EventBus.processEvents(WinEvent.class);
        EventBus.processEvents(LoseEvent.class);
        
        // camera updates
        camera.cameraUpdate(deltaTime, p1.getVector2());
        camera.batchUpdate(batch);
        camera.shapeUpdate(shapeRenderer);
        
        // spawn system
        enemySpawn.update(deltaTime);
//        if(enemySpawn.getWave().isWaveEnded()) {
//        	enemySpawn.nextWave(new Wave(20, 2f, 1.2f));
//        }
        
    }

    private void onWin() {
        System.out.println("WIN");
        sm.setScene(new MainMenuScene(container));
    }

    private void onLose() {
        System.out.println("LOSE");
        sm.setScene(new LoseScene(container));
    }

    @Override
    public void dispose() {
        cm.dispose();
        em.dispose();
        batch.dispose();
        camera.dispose();

        EventBus.removeListener(winEventListener);
        EventBus.removeListener(loseEventListener);
        
        camera = null;
        shapeRenderer = null;
        seek = null;
        p1 = null;
    }
}
