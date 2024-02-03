package com.mygdx.engine.entity;

public abstract class Collidable extends Entity{
	
	protected Rectangle rect = new Rectangle();
	
	public Rectangle getCollisionBound() {
		return this.rect;
	}
	
	public boolean checkCollisions(Rectangle opposing) {
		float[] opposing_bound = opposing.getCollisionBound();
		float[] my_bound = this.rect.getCollisionBound();
 		if(opposing_bound[0] >= my_bound[0] && opposing_bound[1] <= my_bound[1])
 			if(opposing_bound[2] >= my_bound[2] && opposing_bound[3] <= my_bound[3])
 				return true;
 		
 		return false;
	}
}
