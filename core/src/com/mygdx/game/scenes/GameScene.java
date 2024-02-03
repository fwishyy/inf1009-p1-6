package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.player.Player;

public class GameScene extends Scene {
    
	private EntityManager entityManager = new EntityManager();
	private SpriteBatch batch;
	
    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        entityManager.createEntity(new Player());
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
        entityManager.draw(batch);
        batch.end();
    	
    }
}
