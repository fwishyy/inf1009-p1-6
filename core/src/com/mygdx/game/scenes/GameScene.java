package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.game.player.Player;

public class GameScene extends Scene {
    
	private EntityManager entityManager = new EntityManager();
	private BehaviourManager behaviourManager;
	private SpriteBatch batch;
	private Player monster = new Player();
    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        entityManager.createEntity(new Player());
        
        monster.setType("monster");
        monster.setVector2(400, 400);
        monster.setSpeed(100);
        entityManager.createEntity(monster);
        
        behaviourManager = new BehaviourManager(entityManager.getEntities());
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(1, 0.5f, 0.5f, 1);
        batch.begin();
        entityManager.draw(batch);
        behaviourManager.updateMovement();
        batch.end();
    	
    }
}
