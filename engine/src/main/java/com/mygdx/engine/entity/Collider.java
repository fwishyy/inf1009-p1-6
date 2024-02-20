package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.physics.CollisionEvent;

public class Collider {
	
	protected Rectangle rect;
	private boolean isCollidable;
	private Entity entity;
	
	public Collider(Entity entity) {
		this.entity = entity;
		rect =  new Rectangle(this.entity.getX(), this.entity.getY(), this.entity.getWidth(), this.entity.getHeight());
		this.isCollidable = true;
	}
	
	public Collider(Entity entity, boolean isCollidable) {
		rect =  new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		this.isCollidable = isCollidable;
	}
	
	public void onCollide(Collider other) {
			CollisionEvent.addCollisionEvent(new CollisionEvent(this.entity, other.getEntity()));
	}
	
	public boolean isCollide(Collider other) {
		Rectangle _curr = this.rect;
		Rectangle _other = other.getRect();
		
		if(Intersector.overlaps(_curr, _other)) {
			System.out.println("Collision detected");
			return true;
		}
		return false;
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
	
	public boolean getIsCollidable() {
		return this.isCollidable;
	}
	
	public float getX() {
		return this.rect.getX();
	}
	
	public float getY() {
		return this.rect.getY();
	}
	
	public float getWidth() {
		return this.rect.getWidth();
	}
	
	public float getHeight() {
		return this.rect.getHeight();
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void setWidth(float width) {
		this.rect.width = width;
	}
	
	public void setHeight(float height) {
		this.rect.height = height;
	}
	
	public void setIsCollidable(boolean tf) {
		this.isCollidable = tf;
	}
	
	public void setX(float x) {
		this.rect.setX(x);
	}
	
	public void setY(float y) {
		this.rect.setY(y);
	}
	
	public void setPosition(Vector2 v2) {
		this.rect.setPosition(v2);
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public void update() {
		this.rect.x = this.entity.getX();
		this.rect.y = this.entity.getY();
		//System.out.println("X: " + this.rect.x + " Y: " + this.rect.y);
	}
	
	public void dispose() {
		this.entity = null;
		this.rect = null;
	}
}
