package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.physics.CollisionEvent;

public class Collider {
	
	protected Rectangle rect;
	private boolean isCollidable;
	private Entity entity;
	
	protected Collider(Entity entity) {
		this.entity = entity;
		rect =  new Rectangle(this.entity.getX(), this.entity.getY(), this.entity.getWidth(), this.entity.getHeight());
		this.isCollidable = false;
	}
	
	protected Collider(Entity entity, boolean isCollidable) {
		rect =  new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
		this.isCollidable = isCollidable;
	}
	
	public void onCollide(Entity otherEntity) {
		
		if(CollisionEvent.checkRedundant(this.getEntity(), otherEntity) == false) {
			System.out.println("add collision event " + this.entity.getType() + ", " + otherEntity.getType());
			CollisionEvent.addCollisionEvent(new CollisionEvent(this.entity, otherEntity));
		}
			
	}
	
	public boolean isCollide(Entity other) {
		Rectangle _curr = this.entity.getCollider().getRect();
		Rectangle _other = other.getCollider().getRect();
		
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
	
	public void setIsCollidable(boolean tf) {
		this.isCollidable = tf;
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	
	protected void update() {
		this.rect.x = this.entity.getX();
		this.rect.y = this.entity.getY();
		this.rect.width = this.entity.getWidth();
		this.rect.height = this.entity.getHeight();
		
//		System.out.println("collider position: " + this.rect.x + "," + this.rect.y);
	}
}
