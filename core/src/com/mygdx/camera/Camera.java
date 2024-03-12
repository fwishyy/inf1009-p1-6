package com.mygdx.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Camera {
	
	private OrthographicCamera camera = null;
	private float offsetX = 0; 
	private float offsetY = 0;
	
	public Camera() {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
	}
	
	public void cameraUpdate(float deltaTime, Vector2 position) {
		
		float newPosX = position.x + offsetX;
		float newPosY = position.y + offsetY;
		
		this.camera.position.set(newPosX, newPosY, 1);
		this.camera.update();
//		System.out.println("Camera Position: " + this.camera.position);
	}
	
	public void setOffset(float x, float y) {
		offsetX = x;
		offsetY = y;
	}
	
	public void setOffset(Vector2 position) {
		offsetX = position.x;
		offsetY = position.y;
	}
	
	public void batchUpdate(SpriteBatch batch) {
		batch.setProjectionMatrix(this.camera.combined);
	}
	
	public void shapeUpdate(ShapeRenderer shape) {
		shape.setProjectionMatrix(this.camera.combined);
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	public void dispose() {
		this.camera = null;
	}
}
