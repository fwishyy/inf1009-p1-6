package com.mygdx.engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private float x;
	private float y;
	protected Vector2 vector2;
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
	
	public Vector2 getVector2() {
		return this.vector2;
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
	
	public void setVector2(Vector2 vector2) {
		this.vector2 = vector2;
		this.x = vector2.x;
		this.y = vector2.y;
	}
	
	public void setVector2(float x, float y) {
		this.vector2 = new Vector2(x, y);
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
