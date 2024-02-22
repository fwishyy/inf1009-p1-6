package com.mygdx.game.scenes;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.actions.Attack;
import com.mygdx.actions.RunAction;
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
import com.mygdx.player.AnimatedGirl;
import com.mygdx.player.Player;
import com.mygdx.player.SeekBehaviour;

public class GameScene extends Scene {

	//ENGINE
    private GameContainer container;
    private EntityManager em;
    private CollisionManager cm;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    
    //CONCRETE GAME LAYER FOR DEMO PURPOSES
    private Player p1;
    private Player p2;
    private Player lich;
    private AnimatedGirl girl;
    private BGSprite skull;
    private BGSprite field;
    private SeekBehaviour seek;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private RunAction runAction;



    public GameScene(GameContainer container) {
        this.container = container;
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        em = container.getEntityManager();
        cm = container.getCollisionManager();
        pm = container.getPlayerControlManager();
        bm = container.getBehaviourManager();
        
        Random random = new Random();
        
        field = new BGSprite("bg/bg.png", -Gdx.graphics.getWidth()/2, -Gdx.graphics.getHeight()/2, "field");
        em.addEntity(field);
        
        // Manual creation
        for(int i = 0; i < 5; i++) {
        	float x = random.nextInt(Gdx.graphics.getWidth() - 50);
            float y = random.nextInt(Gdx.graphics.getHeight() - 50);
            skull = new BGSprite("bg/PNG/Animation5.png", x, y, "skull", 3, 6, 0.1f);
        	em.addEntity(skull);
        }
        
        // Dynamic creation
        em.createEntity(5, BGSprite.class, "bg/PNG/Objects_separately/Crystal_shadow1_1.png", 0, 0, "crystal");
        em.createEntity(1, Player.class, "bg/PNG/Objects_separately/Lich_shadow_scaledDown.png", 300, 300, "lich");
        em.createEntity(4, Player.class, "bg/PNG/Objects_separately/Bones_shadow1_17.png", 200, 200, "birdSkull");
        em.createEntity(1, Player.class, "sprite/Converted_Vampire/Run.png", 0, 0, "player1", 1, 8, 0.1f);
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
        p1.setIsAnimation(false);
        p2 = (Player) em.getEntity("player2");
        lich = (Player) em.getEntity("lich");
        
        // add colliders to entities that need collision logic
        // in this case, remember that the current player using a spritesheet, so we have to calculate frame size of the 
        float width = p1.getWidth() / p1.getFrameCountColumn();
        float height = p1.getHeight() / p1.getFrameCountRow();
        cm.addCollider(p1, width/2, height/2);
        // attempt at centering
        cm.setOffset(new Vector2(width/2 - cm.getCollider(p1).getWidth()/2, height/2 - cm.getCollider(p1).getHeight()/2), p1);
        

        
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
        
        // animated sprite
        em.createEntity(1, AnimatedGirl.class, "sprite/Vampire_Girl/Walk.png", 350, 350, "girl", 1, 6, 0.2f);
        girl = (AnimatedGirl)em.getEntity("girl");
        
        
        runAction = new RunAction();
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
        // draw collider for debugging purposes
        cm.drawCollider(shapeRenderer, Color.RED);
        
        runAction.setIsRun(Gdx.input.isKeyPressed(Keys.SPACE));
        runAction.act(p1);
    }
    
    public void dispose() {
    	cm.dispose();
    	em.dispose();
    	batch.dispose();
    	
    	runAction = null;
    	shapeRenderer = null;
    	seek = null;
    	field = null;
    	skull = null;
    	girl = null;
    	lich = null;
    	p2 = null;
    	p1 = null;
    }
}
