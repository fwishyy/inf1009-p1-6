package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Character extends Entity implements IDamageable, IMoveable{
	
	protected Sprite sprite;
	protected float hp;
	
	protected Character(String texture, float x, float y, float speed, String type) {
		super(texture, x, y, speed, type);
		sprite = new Sprite(super.getTexture(), (int)super.getWidth(), (int)super.getHeight());
		sprite.setPosition(x, y);
	}
	
	@Override
	public float getHealth() {
		return this.hp;
	}
	
	@Override
	public void setHealth(float hp) {
		this.hp = hp;
	}
	
	@Override
	public void moveLeft() {
		float move = this.getX() - this.getSpeed() * Gdx.graphics.getDeltaTime();
		this.setX(move);
	}
	
	@Override
	public void moveRight() {
		float move = this.getX() + this.getSpeed() * Gdx.graphics.getDeltaTime();
		this.setX(move);
	}
	
	@Override
	public void moveUp() {
		float move = this.getY() + this.getSpeed() * Gdx.graphics.getDeltaTime();
		this.setX(move);
	}
	
	@Override
	public void moveDown() {
		float move = this.getY() - this.getSpeed() * Gdx.graphics.getDeltaTime();
		this.setX(move);
	}
	
	@Override
	protected void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

}
