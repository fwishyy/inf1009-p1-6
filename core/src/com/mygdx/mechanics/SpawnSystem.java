package com.mygdx.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.aicontrol.EnemyBehaviour;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.entity.*;

public class SpawnSystem {

    private EntityManager em = null;
    private BehaviourManager bm = null;
    private float screenHeight = Gdx.graphics.getHeight();
    private float screenWidth = Gdx.graphics.getWidth();
    private boolean isBoundarySet = false;
    private Vector2 minPos = null;
    private Vector2 maxPos = null;
    private Rectangle spawnArea = null;
    private Player p1;

    private Wave wave = null;

    private BitmapFont font = new BitmapFont();
    private GlyphLayout layout = new GlyphLayout();
    private String message = "Enemies Left: ";

    public SpawnSystem(GameContainer container, float interval, float multiplier, int initialEnemies) {
        this.em = container.getEntityManager();
        this.bm = container.getBehaviourManager();
        this.wave = new Wave(initialEnemies, interval, multiplier);
        // set spawn area, basically an oversized rectangle bigger than current screen
        this.spawnArea = new Rectangle(-100, -100, screenWidth + 200, screenHeight + 200);
    }

    public void update(float deltaTime) {

        this.p1 = (Player) em.getEntity("player1");

        // update timer
        float timer = wave.getTimer() + deltaTime;
        wave.setTimer(timer);

        // center the spawn area
        Vector2 centerPos = em.getEntity("player1").getVector2();
        spawnArea.setCenter(centerPos);

        float interval = wave.getInterval();

        // Boss spawner
        if (wave.isBossWave()) {
            if (timer >= interval && wave.getBossSpawned() < wave.getBossCount()) {
                System.out.println("Spawn boss");
                spawnBoss(getSpawnPosition());
                wave.setTimer(0f);
            }
        }

        // enemy spawn logic
        if (!wave.isStop()) {
            // normal spawner
            if (timer >= interval && wave.getEnemiesSpawned() < wave.getInitialEnemies()) {
                spawn(getSpawnPosition());
                wave.setTimer(0f);
                System.out.println("Wave: " + wave.getWaveCount() + " " + "Max Enemies: " + wave.getInitialEnemies() + " " + "Enemies spawned: " + wave.getEnemiesSpawned());
            }

            if (wave.getEnemiesSpawned() >= wave.getInitialEnemies()) {
                wave.stop();
                System.out.println("Wave stopped at: " + wave.getWaveCount());
            }
        }

        // wave ends after all enemies have been defeated
        if (wave.getEnemyCount() <= 0 && wave.isStop()) {
            wave.waveEnded();
        }
    }

    public void updateDisplay(SpriteBatch batch, OrthographicCamera camera) {
        batch.begin();
        font.setColor(Color.WHITE);
        font.draw(batch, message + wave.getEnemyCount(), camera.position.x + camera.viewportWidth / 2 - 150, camera.position.y + camera.viewportHeight / 2 - 20);
//        System.out.println(font.draw(batch, message, screenWidth, screenHeight));
        batch.end();
    }

    public void nextWave() {
        wave.setInitialEnemies((int) (wave.getInitialEnemies() * 1.5));
        wave.setWaveCount(wave.getWaveCount() + 1);
        wave.setEnemiesSpawned(0);
        wave.setEnemyCount(0);
        wave.start();
    }

    public void nextWave(Wave wave) {
        // preserve wave count
        int waveCount = this.wave.getWaveCount();
        dispose();
        // continue next wave with new wave object
        this.wave = wave;
        this.wave.setWaveCount(waveCount + 1);
        wave.setEnemiesSpawned(0);
        wave.setEnemyCount(0);
        ;
        this.wave.start();
    }

    public void nextWave(Wave wave, int nextWaveCount) {
        dispose();
        // continue next wave with new wave object
        this.wave = wave;
        this.wave.setWaveCount(nextWaveCount);
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

    public void dispose() {
        wave.dispose();
        this.wave = null;
    }

    private void spawn(Vector2 position) {
        // create entities at the position

        int totalEnemyType = 3; // update this variable whenever enemy types are added in the switch case below
        int chooseEnemy = wave.getEnemyCount() % totalEnemyType;
        EnemyBehaviour enemyBehaviour = new EnemyBehaviour(p1);
//		System.out.println("choose enemy:" + chooseEnemy);
        // add more as needed
        // create entities at the position
        switch (chooseEnemy) {
            case 0:
                SkeletonSpearman skeletonWarrior = new SkeletonSpearman();
                skeletonWarrior.setPosition(position.x, position.y);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonWarrior));
                bm.addBehaviour(skeletonWarrior, enemyBehaviour);
                break;
            case 1:
                Enemy skeletonArcher = new SkeletonArcher();
                skeletonArcher.setPosition(position.x, position.y);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonArcher));
                bm.addBehaviour(skeletonArcher, enemyBehaviour);
                break;
            case 2:
                SkeletonSpearman skeletonSpearman = new SkeletonSpearman();
                skeletonSpearman.setPosition(position.x, position.y);
                EntityAddedEvent.addEvent(new EntityAddedEvent(skeletonSpearman));
                bm.addBehaviour(skeletonSpearman, enemyBehaviour);
                break;
            default:
                // create entities at the position
                Enemy defaultEnemy = new SkeletonWarrior();
                defaultEnemy.setPosition(position.x, position.y);
                EntityAddedEvent.addEvent(new EntityAddedEvent(defaultEnemy));
                bm.addBehaviour(defaultEnemy, enemyBehaviour);
        }

        wave.setEnemiesSpawned(wave.getEnemiesSpawned() + 1);
        wave.setEnemyCount(wave.getEnemyCount() + 1);
    }

    private void spawnBoss(Vector2 position) {
        Enemy yokai = new Enemy("characters/Yokai_Yamabushi_Tengu/Walk.png", position.x, position.y, "yokai", 1, 8, 0.1f);
        EntityAddedEvent.addEvent(new EntityAddedEvent(yokai));
        EnemyBehaviour seek = new EnemyBehaviour(p1);
        bm.addBehaviour(yokai, seek);

        wave.setEnemyCount(wave.getEnemyCount() + 1);
        wave.setBossSpawned(wave.getBossSpawned() + 1);
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
