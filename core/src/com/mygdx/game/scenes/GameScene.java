package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.backgroundsprite.BGSprite;
import com.mygdx.backgroundsprite.bgField;
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
import com.mygdx.player.HealthBar;
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
    private HealthBar hbar;
    private Player p1;
    private bgField lich;
    private bgField field;
    private BGSprite skull;
    private SeekBehaviour seek;
    private SpriteBatch batch;       
    private ShapeRenderer shapeRenderer;

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

        Random random = new Random();

        field = new bgField("bg/bg.png", -Gdx.graphics.getWidth() / 2, -Gdx.graphics.getHeight() / 2, "field");
        em.addEntity(field);
        
        hbar = new HealthBar();
        em.addEntity(hbar);

        // Dynamic Creation Showcase
        em.createEntity(5, bgField.class, "bg/PNG/Objects_separately/Crystal_shadow1_1.png", 0, 0, "crystal");
        em.createEntity(1, bgField.class, "bg/PNG/Objects_separately/Lich_shadow_scaledDown.png", 300, 300, "lich");
        em.createEntity(4, BGSprite.class, "monsters/Goblin/Attack3.png", 200, 200, "goblin", 1, 12, 0.1f);
        em.createEntity(1, Player.class, "sprite/Converted_Vampire/Run.png", 0, 0, "player1", 1, 8, 0.1f);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        // Manual Creation Showcase
        field = new bgField("bg/bg.png", -screenWidth / 2, -screenHeight / 2, "field");
        em.addEntity(field);
        skull = new BGSprite("bg/PNG/Animation5.png", 0, 0, "skull", 3, 6, 0.1f);
        em.addEntity(skull);
        skull = new BGSprite("bg/PNG/Animation5.png", 0, screenHeight - 125, "skull", 3, 6, 0.1f);
        em.addEntity(skull);
        skull = new BGSprite("bg/PNG/Animation5.png", screenWidth - 100, screenHeight - 125, "skull", 3, 6, 0.1f);
        em.addEntity(skull);
        skull = new BGSprite("bg/PNG/Animation5.png", screenWidth - 100, 0, "skull", 3, 6, 0.1f);
        em.addEntity(skull);
        skull = new BGSprite("bg/PNG/Animation5.png", screenWidth / 2 - 50, screenHeight / 2 - 50, "skull", 3, 6, 0.1f);
        em.addEntity(skull);

        // Batch Filtering Showcase
        // Identify Entities to randomly set their position
        for (Entity entity : em.getEntities("crystal")) {
            float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
            entity.setPosition(new Vector2(x, y));
        }

        for (Entity entity : em.getEntities("goblin")) {
            float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
            entity.setPosition(new Vector2(x, y));
            cm.addCollider(entity, entity.getWidth() / 2, entity.getHeight() / 2);
            cm.setOffset(
                    new Vector2(cm.getCollider(entity).getWidth() / 2, cm.getCollider(entity).getHeight() / 2),
                    entity);
        }

        // assignment of unique entities
        p1 = (Player) em.getEntity("player1");
        p1.setIsAnimation(false);

        lich = (bgField) em.getEntity("lich");
        cm.addCollider(lich);

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

        // simple seeking behaviour towards unique entity player1 with a speed of 50
        seek = new SeekBehaviour(em.getEntity("player1"), 50);
        bm.addBehaviour(lich, seek);
        for (Entity entity : em.getEntities("goblin")) {
            bm.addBehaviour(entity, seek);
        }


    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
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

        EventBus.removeListener(winEventListener);
        EventBus.removeListener(loseEventListener);

        shapeRenderer = null;
        seek = null;
        field = null;
        skull = null;
        lich = null;
        p1 = null;
    }
}
