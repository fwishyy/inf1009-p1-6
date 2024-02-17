package com.mygdx.game.scenes;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.player.Player;

import com.mygdx.engine.entity.*;
import com.mygdx.engine.physics.CollisionManager;

public class GameScene extends Scene {
    
	private EntityManager em;
	private SpriteBatch batch;
	private CollisionManager cm;
	private Player p1;
	private Player p2;
	
	
    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        em = new EntityManager();
        em.createEntity(1, Player.class, "badlogic.jpg", 0, 0, 200, "player1");
        em.createEntity(1, Player.class, "badlogic.jpg", 300, 300, 200, "player2");
        cm = new CollisionManager();
        cm.addCollider(em.getEntity("player1"));
        cm.addCollider(em.getEntity("player2"));
        
        p1 = (Player)em.getEntity("player1");
        p2 = (Player)em.getEntity("player2");
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
        em.draw(batch);
        em.update();
        cm.update();
        p1.move();
//        p2.move();
        batch.end();
    	
    }
}
