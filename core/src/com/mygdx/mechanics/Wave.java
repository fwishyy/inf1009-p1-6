package com.mygdx.mechanics;

public class Wave {
	private int initialEnemies = 0;
	private float interval = 0f;
	private float multiplier = 0f; 
	
	private boolean stop = false;
	private boolean waveEnded = false;
	private float timer = 0f;
	private int enemyCount = 1;
	private int waveCount = 0;
	
	public Wave() {}
	
	public Wave(int initialEnemies, float interval, float multiplier) {
		this.setInitialEnemies(initialEnemies);
		this.setInterval(interval);
		this.setMultiplier(multiplier);
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

	public boolean isStop() {
		return stop;
	}

	public void stop() {
		this.stop = true;
	}
	
	public void start() {
		this.stop = false;
		this.waveEnded = false;
	}

	public boolean isWaveEnded() {
		return waveEnded;
	}
	
	public void waveEnded() {
		this.waveEnded = true;
		this.enemyCount = 0;
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

	public int getWaveCount() {
		return waveCount;
	}

	public void setWaveCount(int currWave) {
		this.waveCount = currWave;
	}	
}
