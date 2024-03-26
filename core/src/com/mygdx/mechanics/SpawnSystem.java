package com.mygdx.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.aicontrol.SeekBehaviour;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.entity.Enemy;

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
    private boolean isSpawnBoss = true;

    public SpawnSystem(GameContainer container, float interval, float multiplier, int initialEnemies) {
        this.em = container.getEntityManager();
        this.cm = container.getCollisionManager();
        this.bm = container.getBehaviourManager();
        this.wave = new Wave(initialEnemies, interval, multiplier);
        // set spawn area, basically an oversized rectangle bigger than current screen
        this.spawnArea = new Rectangle(-100, -100, screenWidth + 200, screenHeight + 200);
    }

    public void update(float deltaTime) {

        // update timer
        float timer = wave.getTimer() + deltaTime;
        wave.setTimer(timer);

        // center the spawn area
        Vector2 centerPos = em.getEntity("player1").getVector2();
        spawnArea.setCenter(centerPos);

        // enemy spawn logic -- spawn new enemy every interval seconds
        if (!wave.isStop()) {
            int enemyCount = wave.getEnemyCount();
            int initialEnemies = wave.getInitialEnemies();
            float interval = wave.getInterval();

            if (timer >= interval && enemyCount < initialEnemies) {
                spawn(getSpawnPosition());
                wave.setTimer(0f);
                System.out.println("Wave: " + wave.getWaveCount() + " " + "Max Enemies: " + initialEnemies + " " + "Current Enemies: " + enemyCount);
            }

            if (enemyCount >= initialEnemies) {
                wave.stop();
                wave.waveEnded();
                System.out.println("Wave stopped at: " + wave.getWaveCount());
            }
        }

        // Boss spawner
        if (wave.getBossWave() != 0 && (wave.getBossWave() % wave.getWaveCount() == 0)) {
            if (isSpawnBoss) {
                for (int i = 0; i < wave.getBossCount(); i++) {
                    System.out.println("Spawn boss");
                    spawnBoss(getSpawnPosition());
                }
                isSpawnBoss = false;
            }
        }
    }

    public void nextWave() {
        wave.start();
        wave.setInitialEnemies((int) (wave.getInitialEnemies() * 1.5));
        wave.setWaveCount(wave.getWaveCount() + 1);
        isSpawnBoss = true;
    }

    public void nextWave(Wave wave) {
        // preserve wave count
        int waveCount = this.wave.getWaveCount();
        dispose();
        // continue next wave with new wave object
        this.wave = wave;
        this.wave.setWaveCount(waveCount + 1);
        this.wave.start();
        isSpawnBoss = true;
    }

    public void nextWave(Wave wave, int nextWaveCount) {
        dispose();
        // continue next wave with new wave object
        this.wave = wave;
        this.wave.setWaveCount(nextWaveCount);
        this.wave.start();
        isSpawnBoss = true;
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

    public void dispose() {
        this.wave = null;
    }

    private void spawn(Vector2 position) {
        // create entities at the position

        int totalEnemyType = 3; // update this variable whenever enemy types are added in the switch case below
        int chooseEnemy = wave.getEnemyCount() % totalEnemyType;
        SeekBehaviour seek = new SeekBehaviour(em.getEntity("player1"), 50);
        SeekBehaviour seekRanged = new SeekBehaviour(em.getEntity("player1"), 50, 250);
//		System.out.println("choose enemy:" + chooseEnemy);
        // add more as needed
        // create entities at the position
        switch (chooseEnemy) {
            case 0:
                Enemy skeletonWarrior = new Enemy("characters/Skeleton_Warrior/Attack_1.png", position.x, position.y, "skeletonWarrior", 1, 5, 0.1f);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonWarrior));
                bm.addBehaviour(skeletonWarrior, seek);
                break;
            case 1:
                Enemy skeletonArcher = new Enemy("characters/Skeleton_Archer/Shot_1.png", position.x, position.y, "skeletonArcher", 1, 15, 0.1f);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonArcher));
                bm.addBehaviour(skeletonArcher, seekRanged);
                break;
            case 2:
                Enemy skeletonSpearman = new Enemy("characters/Skeleton_Spearman/Attack_1.png", position.x, position.y, "skeletonSpearman", 1, 4, 0.1f);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonSpearman));
                bm.addBehaviour(skeletonSpearman, seek);
                break;
            default:
                // create entities at the position
                Enemy defaultEnemy = new Enemy("characters/Skeleton_Warrior/Attack_1.png", position.x, position.y, "goblin", 1, 5, 0.1f);
                EntityAddedEvent.addEvent(new EntityAddedEvent(defaultEnemy));
                SeekBehaviour defaultSeek = new SeekBehaviour(em.getEntity("player1"), 50);
                bm.addBehaviour(defaultEnemy, defaultSeek);
        }


        wave.setEnemyCount(wave.getEnemyCount() + 1);
    }

    private void spawnBoss(Vector2 position) {
        Enemy yokai = new Enemy("characters/Yokai_Yamabushi_Tengu/Walk.png", position.x, position.y, "yokai", 1, 8, 0.1f);
        EntityAddedEvent.addEvent(new EntityAddedEvent(yokai));
        SeekBehaviour seek = new SeekBehaviour(em.getEntity("player1"), 50);
        bm.addBehaviour(yokai, seek);
    }

    private Vector2 getSpawnPosition() {

        // Randomly determine the side from which the entity will spawn
        int side = MathUtils.random(3);
        float x, y;

        switch (side) {
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
            if (withinBoundary(x, y))
                return new Vector2(x, y);
            else
                return getSpawnPosition();
        }

        return new Vector2(x, y);
    }

    private boolean withinBoundary(float x, float y) {
        return x > minPos.x && y > minPos.y && x < maxPos.x && y < maxPos.y;
    }

}
