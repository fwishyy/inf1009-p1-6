package com.mygdx.mechanics;

import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.entity.Enemy;
import com.mygdx.events.CharacterDeathEvent;

public class Wave {

    public enum waveState {
        PAUSE, IN_PROGRESS, END
    }

    public static waveState state = waveState.PAUSE;

    private int initialEnemies = 0;
    private float interval = 0f; // controls rate of spawning
    private float multiplier = 0f;

    private boolean stop = false; // to stop spawning
    private boolean waveEnded = false; // to track end of wave
    private float timer = 0f;
    private int enemyCount = 0; // track enemies currently existing
    private int enemiesSpawned = 0; // track num of enemies spawned
    private int waveCount = 1;

    private int bossWave = 0;
    private int bossCount = 0;
    private int bossSpawned = 0;
    private EventListener<CharacterDeathEvent> characterDeathEventListener = new EventListener<CharacterDeathEvent>() {
        @Override
        public void onSignal(Event e) {
            handleEnemyDefeated((CharacterDeathEvent) e);
        }
    };

    public Wave(int initialEnemies, float interval, float multiplier) {
        this.initialEnemies = initialEnemies;
        this.interval = interval;
        this.multiplier = multiplier;
        CharacterDeathEvent.addListener(CharacterDeathEvent.class, characterDeathEventListener);
    }

    public Wave(int initialEnemies, float interval, float multiplier, int waveCount) {
        this.initialEnemies = initialEnemies;
        this.interval = interval;
        this.multiplier = multiplier;
        this.waveCount = waveCount;
        CharacterDeathEvent.addListener(CharacterDeathEvent.class, characterDeathEventListener);
    }

    public int getInitialEnemies() {
        return initialEnemies;
    }

    public void setInitialEnemies(int initialEnemies) {
        this.initialEnemies = initialEnemies;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public int getBossWave() {
        return bossWave;
    }

    public void setBossWave(int bossWave) {
        this.bossWave = bossWave;
    }

    public boolean isBossWave() {
        return this.getBossWave() != 0 && (this.getWaveCount() % this.getBossWave() == 0);
    }

    public int getBossCount() {
        return bossCount;
    }

    public void setBossCount(int bossCount) {
        this.bossCount = bossCount;
    }

    public int getBossSpawned() {
        return this.bossSpawned;
    }

    public void setBossSpawned(int bossSpawned) {
        this.bossSpawned = bossSpawned;
    }

    public boolean isStop() {
        return stop;
    }

    public void stop() {
        this.stop = true;
    }

    public void start() {
        this.stop = false;
        this.waveEnded = false;
        state = waveState.IN_PROGRESS;
    }

    public boolean isWaveEnded() {
        return waveEnded;
    }

    public void waveEnded() {
        this.waveEnded = true;
    }

    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public void handleEnemyDefeated(CharacterDeathEvent e) {
        if(e.getCharacter().getType() != "player1") {
            enemyCount -= 1;
        }
    }

    public int getEnemiesSpawned() {
        return enemiesSpawned;
    }

    public void setEnemiesSpawned(int enemiesSpawned) {
        this.enemiesSpawned = enemiesSpawned;
    }

    public int getWaveCount() {
        return waveCount;
    }

    public void setWaveCount(int currWave) {
        this.waveCount = currWave;
    }

    public void dispose() {
        EventBus.removeListener(characterDeathEventListener);
    }
}
