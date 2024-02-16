package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.player.Player;

import com.mygdx.engine.entity.*;

public class GameScene extends Scene {
    
	private EntityManager em;
	private SpriteBatch batch;
    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        em = new EntityManager();
        em.createEntity(1, Player.class, "badlogic.jpg", 300, 300, 100, "player");
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
        em.draw(batch);
        batch.end();
    	
    }
}
