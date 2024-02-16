package com.mygdx.engine.entity;

public abstract class Collidable{
	
//	protected Rect rect = new Rect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
	
//	protected boolean isCollide(Rect otherRect) {
//		return rect.overlaps(otherRect);
//	}
//	
	protected void dispatchCollisionSignal() {
		
	}
	
	protected abstract void onCollide();
	
//	public Rect getCollisionBound() {
//		return this.rect;
//	}
//	
//	public boolean isCollide(Rect otherRect) {
//		float[] opposing_bound = otherRect.getCollisionBound();
//		float[] my_bound = this.rect.getCollisionBound();
// 		if(opposing_bound[0] >= my_bound[0] && opposing_bound[1] <= my_bound[1])
// 			if(opposing_bound[2] >= my_bound[2] && opposing_bound[3] <= my_bound[3])
// 				return true;
// 		
// 		return false;
//	}
}
