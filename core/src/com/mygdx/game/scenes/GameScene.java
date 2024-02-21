package com.mygdx.game.scenes;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.backgroundsprite.BGSprite;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.controls.KeyCodes;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.player.Player;
import com.mygdx.player.SeekBehaviour;

public class GameScene extends Scene {

	//ENGINE
    private GameContainer container;
    private EntityManager em;
    private SpriteBatch batch;
    private CollisionManager cm;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    
    //CONCRETE GAME LAYER FOR DEMO PURPOSES
    private Player p1;
    private Player p2;
    private Player lich;
    private Player birdSkull;
    private BGSprite crystal;
    private BGSprite deadArm;
    private SeekBehaviour seek;


    public GameScene(GameContainer container) {
        this.container = container;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();

        em = container.getEntityManager();
        cm = container.getCollisionManager();
        pm = container.getPlayerControlManager();
        bm = container.getBehaviourManager();
        
        Random random = new Random();
        
        // Manual creation
        for(int i = 0; i < 5; i++) {
        	float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
            deadArm = new BGSprite("bg/PNG/Objects_separately/Dead_arm_shadow3_1.png", x, y, "arm");
        	em.addEntity(deadArm);
        }
        
        // Dynamic creation
        em.createEntity(5, BGSprite.class, "bg/PNG/Objects_separately/Crystal_shadow1_1.png", 0, 0, "crystal");
        em.createEntity(1, Player.class, "bg/PNG/Objects_separately/Lich_shadow_scaledDown.png", 300, 300, "lich");
        em.createEntity(4, Player.class, "bg/PNG/Objects_separately/Bones_shadow1_17.png", 200, 200, "birdSkull");
        em.createEntity(1, Player.class, "sprite/Converted_Vampire/Hurt.png", 0, 0, "player1");
        em.createEntity(1, Player.class, "sprite/Countess_Vampire/Attack_3.png", 0, 400, "player2");
        
        
        
        // Identify Entities to randomly set their position
        for(Entity entity: em.getEntities("crystal")) {
        	float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
        	entity.setPosition(new Vector2(x, y));
        }
        
        for(Entity entity: em.getEntities("birdSkull")) {
        	float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
        	entity.setPosition(new Vector2(x, y));
        }
        
        // assignment of unique entities
        p1 = (Player) em.getEntity("player1");
        p2 = (Player) em.getEntity("player2");
        lich = (Player) em.getEntity("lich");
        
        // add colliders to entities that need collision logic
        cm.addCollider(p1);
        cm.addCollider(p2);
        cm.addCollider(lich);
        for(Entity entity: em.getEntities("birdSkull")) {
        	cm.addCollider(entity);
        }
        
        // player control mapping 
        ActionMap playerControls = new ActionMap();
        playerControls.add2DMovementBindings(KeyCodes.W, KeyCodes.A, KeyCodes.S, KeyCodes.D);
        ActionMap playerControls2 = new ActionMap();
        playerControls2.add2DMovementBindings(KeyCodes.UP, KeyCodes.LEFT, KeyCodes.DOWN, KeyCodes.RIGHT);
        pm.setActionMap(p1, playerControls);
        pm.setActionMap(p2, playerControls2);
        
        // simple seeking behaviour towards unique entity player1 with a speed of 50
        seek = new SeekBehaviour(em.getEntity("player1"), 50);
        bm.addBehaviour(lich, seek);
        for(Entity entity: em.getEntities("birdSkull")) {
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
    }
}
