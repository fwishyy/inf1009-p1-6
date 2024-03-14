package com.mygdx.mechanics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.backgroundsprite.Enemy;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.events.WinEvent;
import com.mygdx.player.SeekBehaviour;

public class SpawnSystem {
	
	private EntityManager em = null;
	private CollisionManager cm = null;
	private BehaviourManager bm = null;
	private float screenHeight = Gdx.graphics.getHeight();
	private float screenWidth = Gdx.graphics.getWidth();
	private Vector2 minPos = null;
	private Vector2 maxPos = null;
	private Rectangle spawnArea = null;
	
	private boolean stop = false;
	private float timer = 0f;
	private float interval = 4f;
	
	private List<String> monsterTypes = new ArrayList<>();
	
	public SpawnSystem(GameContainer container, float interval, Vector2 minPos, Vector2 maxPos) {
		this.em = container.getEntityManager();
		this.cm = container.getCollisionManager();
		this.bm = container.getBehaviourManager();
		this.interval = interval;
		// set spawn area, basically an oversized rectangle bigger than current screen
		this.spawnArea = new Rectangle(-100, -100, screenWidth + 200, screenHeight + 200);
		this.minPos = minPos;
		this.maxPos = maxPos;
	}
	
	public void update(float deltaTime) {
		// update timer
		timer += deltaTime;
		
		// center the spawn area
		Vector2 centerPos = em.getEntity("player1").getVector2();
		spawnArea.setCenter(centerPos);
		
		// enemy spawn logic -- spawn new goblin every interval seconds
		if(!stop) {
			if(timer >= interval) {
				Vector2 spawnPosition = getSpawnPosition();
				spawn(spawnPosition);
				timer -= interval;
			}
		}
		
	}
	
	public void stop() {
		this.stop = true; 
	}
	
	public void start() {
		this.stop = false;
	}
	
	public void setInterval(float interval) {
		this.interval = interval;
	}
	
	public List<String> getMonsterTypes(){
		return monsterTypes;
	}
	
	public void debug(ShapeRenderer shapeRenderer, Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(this.spawnArea.x, this.spawnArea.y, this.spawnArea.width, this.spawnArea.height); // x, y, width, height
        shapeRenderer.end();
    }
	
	private void spawn(Vector2 position) {
		// TODO find better way to abstract this
		// create entities at the position
//		em.createEntity(1, Enemy.class, "monsters/Goblin/Attack3.png", position.x, position.y, "goblin", 1, 12, 0.1f);
		Enemy goblin = new Enemy("monsters/Goblin/Attack3.png", position.x, position.y, "goblin", 1, 12, 0.1f);
		em.addEntity(goblin);
		
		cm.addCollider(goblin);
		
		SeekBehaviour seek = new SeekBehaviour(em.getEntity("player1"), 50);
		bm.addBehaviour(goblin, seek);
	}
	
	private Vector2 getSpawnPosition() {
		
		// Randomly determine the side from which the entity will spawn
        int side = MathUtils.random(3);
		float x,y;

		switch(side) {
			case 1: // top
				x = MathUtils.random(spawnArea.x, spawnArea.x + spawnArea.width);
				y = spawnArea.y + spawnArea.height;
				break;
			case 2: // bottom
				x = MathUtils.random(spawnArea.x, spawnArea.x + spawnArea.width);
				y = spawnArea.y;
				break;
			case 3: // left
				x = spawnArea.x;
				y = MathUtils.random(spawnArea.y, spawnArea.y + spawnArea.height);
				break;
			default: // right
				x = spawnArea.x + spawnArea.width;
				y = MathUtils.random(spawnArea.y, spawnArea.y + spawnArea.height);
				break;
		}
//		System.out.println("side: " + side);
//		System.out.println("pos: " + x + " , " + y);
		if(withinBoundary(x,y))
			return new Vector2(x,y);
		else
			return getSpawnPosition();
	}
	
	private boolean withinBoundary(float x, float y) {
		return x > minPos.x && y > minPos.y && x < maxPos.x && y < maxPos.y;
	}
	
}
