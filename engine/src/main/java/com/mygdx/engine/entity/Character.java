package com.mygdx.engine.entity;

public abstract class Character extends Collidable implements IDamageable, IMoveable{
	
	protected SpriteObject sprite = new SpriteObject("badlogic.jpg");
	protected float hp;
	
	@Override
	public float getHealth() {
		return this.hp;
	}
	@Override
	public void setHealth(float hp) {
		this.hp = hp;
	}
	@Override
	public void onDeath() {
		
	}
}
