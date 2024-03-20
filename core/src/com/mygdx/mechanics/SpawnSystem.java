package com.mygdx.mechanics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.mygdx.player.SeekBehaviour;

public class SpawnSystem {
	
	private EntityManager em = null;
	private CollisionManager cm = null;
	private BehaviourManager bm = null;
	private float screenHeight = Gdx.graphics.getHeight();
	private float screenWidth = Gdx.graphics.getWidth();
	private boolean isBoundarySet = false;
	private Vector2 minPos = null;
	private Vector2 maxPos = null;
	private Rectangle spawnArea = null;
	
	private Wave wave = null;
	
//	private boolean stop = false;
//	private float timer = 0f;
//	private float interval = 0f;
//	private float multiplier = 0f; 
//	private int initialEnemies = 0;
//	private int enemyCount = 0;
//	private int waveCount = 0;
//	private boolean waveEnded = false;
	
	public SpawnSystem(GameContainer container, float interval, float multiplier, int initialEnemies) {
		this.em = container.getEntityManager();
		this.cm = container.getCollisionManager();
		this.bm = container.getBehaviourManager();
		this.wave = new Wave(initialEnemies, interval, multiplier);
//		this.interval = interval;
//		this.multiplier = multiplier;
//		this.initialEnemies = initialEnemies;
		// set spawn area, basically an oversized rectangle bigger than current screen
		this.spawnArea = new Rectangle(-100, -100, screenWidth + 200, screenHeight + 200);
	}
	
	public void update(float deltaTime) {
		float timer = wave.getTimer() + deltaTime;
		float interval = wave.getInterval();
		int enemyCount = wave.getEnemyCount();
		int initialEnemies = wave.getInitialEnemies();
		int waveCount = wave.getWaveCount();
		
		// update timer
		wave.setTimer(timer);
		
		// center the spawn area
		Vector2 centerPos = em.getEntity("player1").getVector2();
		spawnArea.setCenter(centerPos);
		
		// enemy spawn logic -- spawn new enemy every interval seconds
		if(!wave.isStop()) {
			if(timer >= interval && enemyCount < initialEnemies) {
				spawn(getSpawnPosition());
				wave.setTimer(0f);
				System.out.println("Wave: " + waveCount + " " + "Max Enemies: " + initialEnemies + " " + "Current Enemies: " + enemyCount);
			}
			if(enemyCount >= initialEnemies) {
				wave.stop();
				wave.waveEnded();
				System.out.println("Wave stopped at: " + waveCount);
			}
		}
	}
	
	public void nextWave() {
		wave.start();
		wave.setInitialEnemies((int)(wave.getInitialEnemies() * 1.5));
		wave.setWaveCount(wave.getWaveCount() + 1);
	}
	
	public void nextWave(Wave wave) {
		// preserve wave count
		int waveCount = this.wave.getWaveCount();
		dispose();
		// continue next wave with new wave object
		this.wave = wave;
		this.wave.setWaveCount(waveCount);
		this.wave.start();
	}
	
	public Wave getWave() {
		return wave;
	}
	
	public void setWave(Wave wave) {
		this.wave = wave;
	}
	
	public void setBoundary(Vector2 minPos, Vector2 maxPos) {
		this.isBoundarySet = true;
		this.minPos = minPos;
		this.maxPos = maxPos;
	}
	
	public void removeBoundary() {
		this.isBoundarySet = false;
	}
	
	public void debug(ShapeRenderer shapeRenderer, Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(this.spawnArea.x, this.spawnArea.y, this.spawnArea.width, this.spawnArea.height); // x, y, width, height
        shapeRenderer.end();
    }
	
	private void spawn(Vector2 position) {
		
		int totalEnemyType = 1; // update this variable whenever enemy types are added in the switch case below
		int chooseEnemy = wave.getEnemyCount() % totalEnemyType;
		
		// add more as needed
		switch(chooseEnemy) {
		case 0:
			// create entities at the position
			Enemy goblin = new Enemy("monsters/Goblin/Attack3.png", position.x, position.y, "goblin", 1, 12, 0.1f);
			initSpawn(goblin);
			break;
		default:
			// create entities at the position
			Enemy defaultEnemy = new Enemy("monsters/Goblin/Attack3.png", position.x, position.y, "goblin", 1, 12, 0.1f);
			em.addEntity(defaultEnemy);
			cm.addCollider(defaultEnemy);
			SeekBehaviour defaultSeek = new SeekBehaviour(em.getEntity("player1"), 50);
			bm.addBehaviour(defaultEnemy, defaultSeek);
		}
		
		
		wave.setEnemyCount(wave.getEnemyCount() + 1);
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
		if (isBoundarySet) {
			if(withinBoundary(x,y))
				return new Vector2(x,y);
			else
				return getSpawnPosition();
		}
			
		return new Vector2(x,y);
	}
	
	private void initSpawn(Entity entity) {
		em.addEntity(entity);
		cm.addCollider(entity);
		SeekBehaviour seek = new SeekBehaviour(em.getEntity("player1"), 50);
		bm.addBehaviour(entity, seek);
	}
	
	private boolean withinBoundary(float x, float y) {
		return x > minPos.x && y > minPos.y && x < maxPos.x && y < maxPos.y;
	}
	
	public void dispose() {
		this.wave = null;
	}
	
}
