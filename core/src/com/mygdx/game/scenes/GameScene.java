package com.mygdx.game.scenes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.actions.AttackAction;
import com.mygdx.aicontrol.SeekBehaviour;
import com.mygdx.camera.Camera;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.controls.KeyCodes;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.Pickup;
import com.mygdx.entity.Player;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;
import com.mygdx.mechanics.BackGround;
import com.mygdx.mechanics.SpawnSystem;
import com.mygdx.ui.DamageIndicator;
import com.mygdx.ui.HealthBar;

public class GameScene extends Scene {

    EventListener<WinEvent> winEventListener;
    EventListener<LoseEvent> loseEventListener;
    EventListener<PointerEvent> pointerEventListener;
    //ENGINE
    private GameContainer container;
    private EntityManager em;
    private CollisionManager cm;
    private InputManager im;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    private SceneManager sm;
    //CONCRETE GAME LAYER FOR DEMO PURPOSES
    private HealthBar hbar;
    private Player p1;
    private Enemy skull;
    private Pickup healthPotion;
    private Pickup maxHealthPotion;
    private SeekBehaviour seek;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    //Camera
    private Camera camera;

    //Spawn
    private SpawnSystem enemySpawn;
    private BackGround bg;
    
    //Damage Indicators
    private List<DamageIndicator> damageIndicators = new ArrayList<>();

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
        pointerEventListener = new EventListener<PointerEvent>() {
            public void onSignal(Event e) {
                PointerEvent pointerEvent = (PointerEvent) e;
                handlePointerEvent(pointerEvent);
            }
        };

        WinEvent.addListener(WinEvent.class, winEventListener);
        LoseEvent.addListener(LoseEvent.class, loseEventListener);
        PointerEvent.addListener(PointerEvent.class, pointerEventListener);

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //Create entities to spawn here
        // TODO: move all this into player
        em.createEntity(1, Player.class, "characters/Mage_Fire/Idle.png", 0, 0, "player1", 1, 7, 0.1f);

        // pickup stuff
//        healthPotion = new Pickup("sprite/health_potion.png", 30, 180, "healthPotion");
//        em.addEntity(healthPotion);
//        cm.addCollider(healthPotion);
//        maxHealthPotion = new Pickup("sprite/max_hp_potion.png", 100, 180, "maxHealthPotion");
//        em.addEntity(maxHealthPotion);
//        cm.addCollider(maxHealthPotion);

        // assignment of unique entities
        p1 = (Player) em.getEntity("player1");

        // TODO: also move this into player
        p1.addAnimation("characters/Mage_Fire/Run.png", "run", 8);

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
        playerControls.addNewBinding(KeyCodes.MOUSE1, new AttackAction());
        pm.setActionMap(p1, playerControls);


        bg = new BackGround("bg/bg.png");

        // create new camera and center it
        camera = new Camera();
        camera.setOffset(p1.getWidth() / 2, p1.getHeight() / 2);
        camera.setBoundary(bg.getMinPos(), bg.getMaxPos());

//        // create spawn system and set interval to spawn 1 enemy/4s
        enemySpawn = new SpawnSystem(container, 4, 1.5f, 10);
        enemySpawn.setBoundary(bg.getMinPos(), bg.getMaxPos());
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);

        bg.update(batch);
        em.update();
        em.draw(batch, shapeRenderer);
        for (Entity entity : em.getEntities()) {
            if (entity instanceof Enemy) {
                Enemy bgSprite = (Enemy) entity;
                if (bgSprite.isDead()) {
                    bgSprite.potionDrop(); // Ensure you pass the EntityManager instance
                }
            }
        }
        
        Iterator<DamageIndicator> iterator = damageIndicators.iterator();
        while (iterator.hasNext()) {
            DamageIndicator indicator = iterator.next();
            indicator.update(deltaTime);
            if (indicator.isFinished()) {
                iterator.remove(); // Remove the indicator if it's finished
            } else {
                indicator.draw(batch); // Otherwise, draw the indicator
            }
        }

        cm.update();
        bm.update(Gdx.graphics.getDeltaTime());
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
    }

    public void handlePointerEvent(PointerEvent e) {
        // TODO: still need to fix this to make sure that it's targeting properly
        // update where player is facing here
        if (e.getType() == PointerEvent.Type.HOVER) {
            Vector2 cursorPos = new Vector2(e.getScreenX(), e.getScreenY());
            Vector3 cursorWorldPos = camera.getCamera().unproject(new Vector3(cursorPos.x, cursorPos.y, 0));
            Vector2 target = new Vector2(cursorWorldPos.x, cursorWorldPos.y);
            p1.setTarget(target);
        }
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
        camera = null;
        cm.dispose();
        em.dispose();
        batch.dispose();

        EventBus.removeListener(winEventListener);
        EventBus.removeListener(loseEventListener);
        EventBus.removeListener(pointerEventListener);

        shapeRenderer = null;
        seek = null;
        p1 = null;
    }
}
