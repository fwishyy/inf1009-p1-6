package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.camera.Camera;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.controls.KeyCodes;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
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
import com.mygdx.mechanics.Boundary;
import com.mygdx.mechanics.SpawnSystem;
import com.mygdx.ui.Cursor;
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
    private AudioManager am;
    //CONCRETE GAME LAYER FOR DEMO PURPOSES
    private HealthBar hbar;
    private Player p1;
    private Enemy skull;
    private Pickup healthPotion;
    private Pickup maxHealthPotion;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private Cursor crosshair;

    //Camera
    private Camera camera;

    //Spawn
    private SpawnSystem enemySpawn;
    private BackGround bg;

    private Boundary bound;
    //Damage Indicators
    private BitmapFont font;

    public GameScene(GameContainer container) {
        this.container = container;
        em = container.getEntityManager();
        cm = container.getCollisionManager();
        pm = container.getPlayerControlManager();
        bm = container.getBehaviourManager();
        im = container.getInputManager();
        sm = container.getSceneManager();
        am = container.getAudioManager();
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
        crosshair = new Cursor("mouse/crosshair.png");

        //Create entities to spawn here
        // TODO: move all this into player
        em.createEntity(1, Player.class, "characters/Mage_Fire/Idle.png", 0, 0, "player1", 1, 7, 0.1f);

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
        ActionMap playerControls = new ActionMap("controls");
        playerControls.addMoveAction(KeyCodes.W, KeyCodes.A, KeyCodes.S, KeyCodes.D);
        playerControls.addInputAction("attack", KeyCodes.MOUSE1);
        pm.setActionMap(p1, playerControls);

        bg = new BackGround("bg/new_bg.png", false);

        // create new camera and center it
        camera = new Camera();
        camera.setOffset(p1.getWidth() / 2, p1.getHeight() / 2);
        camera.setBoundary(bg.getMinPos(), bg.getMaxPos());

        // Retrieve the instance of CharacterSelectionScene
        CharacterSelectionScene characterSelectionScene = CharacterSelectionScene.getInstance();
        boolean isMageSelected = characterSelectionScene.isMageSelected();
        boolean isArcherSelected = characterSelectionScene.isSkeletonSelected();

        System.out.println(isMageSelected);

        // create spawn system and set interval to spawn
        enemySpawn = new SpawnSystem(container, 2, 1.5f, 2);
        enemySpawn.setBoundary(bg.getMinPos(), bg.getMaxPos());
//        enemySpawn.getWave().setBossWave(5);
//        enemySpawn.getWave().setBossCount(2);
//        enemySpawn.nextWave(new Wave(10, 2, 1.5f), 1);

        bound = new Boundary(p1, bg.getMinPos(), bg.getMaxPos());
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);

        bg.update(batch);
        em.update();
        em.draw(batch, shapeRenderer);

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

        bound.update();

        // cursor update
        crosshair.update();

        // spawn system
        enemySpawn.update(deltaTime);
        enemySpawn.updateDisplay(batch, camera.getCamera());
    }

    public void handlePointerEvent(PointerEvent e) {
        // update where player is facing here
        if (e.getType() == PointerEvent.Type.HOVER) {
            Vector2 cursorPos = new Vector2(e.getScreenX(), e.getScreenY());
            Vector3 cursorWorldPos = camera.getCamera().unproject(new Vector3(cursorPos.x, cursorPos.y, 0));
            Vector2 target = new Vector2(cursorWorldPos.x, cursorWorldPos.y);
            p1.setCrosshairPosition(target);
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
        font.dispose();

        EventBus.removeListener(winEventListener);
        EventBus.removeListener(loseEventListener);
        EventBus.removeListener(pointerEventListener);

        shapeRenderer = null;
        p1 = null;
    }
}
