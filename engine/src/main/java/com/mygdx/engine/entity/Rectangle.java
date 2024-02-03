package com.mygdx.engine.entity;

public class Rectangle {
	private float width = 0;
	private float height = 0;
	private float x = 0;
	private float y = 0;
	
	public Rectangle() {}
	
	public Rectangle(float width, float height, float x, float y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public Rectangle(float width, float height) {
		this.width = width;
		this.height = height;
	}
	
	public float[] getCollisionBound() {
		float result[] = {0,0,0,0};
		result[0] = x - width/2; //start of width
		result[1] = x + width/2; //end of width
		result[2] = y - height/2; //start of height
		result[3] = y + height/2; //end of height
		return result;
	}
	
	// ----------------------------------------------
	/*
	 * GET METHODS
	 */
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
	
	/*
	 * SET METHODS
	 */
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
}
