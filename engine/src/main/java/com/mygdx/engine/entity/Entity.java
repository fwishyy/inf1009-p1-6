package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	private Texture texture;
	private Vector2 vector2;
	private float width;
	private float height;
	private float speed;
	private String type;
	
	protected Entity() {
		this.texture = null;
		this.vector2 = new Vector2();
		this.width = 0;
		this.height = 0;
		this.speed = 0;
		this.type = "";
	}
	
	protected Entity(String texture, float x, float y, float speed, String type) {
		this.texture = new Texture(Gdx.files.internal(texture));
		this.vector2 = new Vector2(x, y);
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.speed = speed;
		this.type = type;
	}
	
	protected Entity(float x, float y, float speed, String type) {
		this.texture = null;
		this.vector2 = new Vector2(x, y);
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.speed = speed;
		this.type = type;
	}
	
	protected Entity(float x, float y, String type) {
		this.texture = null;
		this.vector2 = new Vector2(x, y);
		this.width = 0;
		this.height = 0;
		this.speed = 0;
		this.type = type;
	}
	
	protected Entity(float x, float y, float speed) {
		this.texture = null;
		this.vector2 = new Vector2(x, y);
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		this.speed = speed;
		this.type = "";
	}
	
	protected Entity(float x, float y) {
		this.texture = null;
		this.vector2 = new Vector2(x, y);
		this.width = 0;
		this.height = 0;
		this.speed = 0;
		this.type = "";
	}
	
	protected Entity(String texture) {
		this.texture = new Texture(Gdx.files.internal(texture));
		this.vector2 = new Vector2();
		this.width = 0;
		this.height = 0;
		this.speed = 0;
		this.type = "";
	}
	
	protected abstract void move();
	
	protected void draw(SpriteBatch batch) {
		batch.draw(this.texture, this.vector2.x, this.vector2.y);
	}
	
	public Texture getTexture() {
		return texture;
	}

	protected float getX() {
		return this.vector2.x;
	}
	
	protected float getY() {
		return this.vector2.y;
	}
	
	protected Vector2 getVector2() {
		return this.vector2;
	}
	
	protected float getWidth() {
		return this.width;
	}
	
	protected float getHeight() {
		return this.height;
	}
	
	protected String getType() {
		return this.type;
	}
	
	protected float getSpeed() {
		return this.speed;
	}
	
	public void setTexture(String texture) {
		this.texture = new Texture(Gdx.files.internal(texture));
	}
	
	protected void setX(float x) {
		this.vector2.x = x;
	}
	
	protected void setY(float y) {
		this.vector2.y = y;
	}
	
	protected void setVector2(Vector2 vector2) {
		this.vector2 = vector2;
	}
	
	protected void setVector2(float x, float y) {
		this.vector2 = null; // Release previous object
		this.vector2 = new Vector2(x, y);
	}
	
	protected void setWidth(float width) {
		this.width = width;
	}
	
	protected void setHeight(float height) {
		this.height = height;
	}
	
	protected void setType(String type) {
		this.type = type;
	}
	
	protected void setSpeed(float speed) {
		this.speed = speed;
	}
}
