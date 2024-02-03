package com.mygdx.engine.entity;

public interface IDamageable {
	public float getHealth();
	public void setHealth(float hp);
	public void onDeath();
}
