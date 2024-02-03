package com.mygdx.engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected float speed;
	protected String type;
	
	public abstract void draw(SpriteBatch batch);
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public float getWidth() {
		return this.width;
	}
	
	public float getHeight() {
		return this.height;
	}
	
	public String getType() {
		return this.type;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
