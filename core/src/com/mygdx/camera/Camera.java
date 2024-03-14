package com.mygdx.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Camera {
	
	private OrthographicCamera camera = null;
	private float offsetX = 0; 
	private float offsetY = 0;
	private boolean isFollowX = true;
	private boolean isFollowY = true;

	
	private boolean isBounded = false;
	private Vector2 maxBound = null;
	private Vector2 minBound = null;
	
	public Camera() {
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false);
	}
	
	public void cameraUpdate(float deltaTime, Vector2 targetPos) {
		
		float newPosX = targetPos.x + offsetX;
		float newPosY = targetPos.y + offsetY;
		
		if(isBounded) {
			String boundary = boundaryCheck();
//			System.out.println(boundary);
			// return camera
			if(boundary != null)
				returnCameraCheck(boundary, newPosX, newPosY, deltaTime);
		}
			
		
		if(isFollowX) {
			this.camera.position.set(newPosX, camera.position.y, 1);
			this.camera.update();
		}
		if(isFollowY) {
			this.camera.position.set(camera.position.x, newPosY, 1);
			this.camera.update();
		}
		
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
	
	public void setBoundary(Vector2 min, Vector2 max) {
		this.isBounded = true;
		this.minBound = min;
		this.maxBound = max;
	}
	
	public void removeBoundary() {
		this.isBounded = false;
		this.minBound = null;
		this.maxBound = null;
	}
	
	public void dispose() {
		this.camera = null;
	}
	
	private String boundaryCheck() {
		float minCamX = camera.position.x - camera.viewportWidth/2;
		float minCamY = camera.position.y - camera.viewportHeight/2;
		float maxCamX = camera.position.x + camera.viewportWidth/2;
		float maxCamY = camera.position.y + camera.viewportHeight/2;
		
		boolean boundaryL = minCamX <= minBound.x ? true : false;
		boolean boundaryR = maxCamX >= maxBound.x ? true : false;
		boolean boundaryT = maxCamY >= maxBound.y ? true : false;
		boolean boundaryB = minCamY <= minBound.y ? true : false;
		boolean boundaryTR = boundaryT && boundaryR ? true : false;
		boolean boundaryTL = boundaryT && boundaryL ? true : false;
		boolean boundaryBR = boundaryB && boundaryR ? true : false;
		boolean boundaryBL = boundaryB && boundaryL ? true : false;
		// top right
		if(boundaryTR) {
			isFollowY = false;
			isFollowX = false;
			return "TR";
		}
		// top left
		if(boundaryTL) {
			isFollowY = false;
			isFollowX = false;
			return "TL";
		}
		// bottom left
		if(boundaryBL) {
			isFollowY = false;
			isFollowX = false;
			return "BL";
		}
		// bottom right
		if(boundaryBR) {
			isFollowY = false;
			isFollowX = false;
			return "BR";
		}
		// left boundary
		if(boundaryL) {
			isFollowY = true;
			isFollowX = false;
			return "L";
		}
		// right boundary
		if(boundaryR) {
			isFollowY = true;
			isFollowX = false;
			return "R";
		}
		// bottom boundary
		if(boundaryB) {
			isFollowY = false;
			isFollowX = true;
			return "B";
		}
		// top boundary 
		if(boundaryT) {
			isFollowY = false;
			isFollowX = true;
			return "T";
		}
		
		return null;
	}
	
	private boolean returnCameraCheck(String boundary, float x , float y, float deltaTime) {
		switch(boundary) {
		case "TL":
			if(y <= camera.position.y)
				isFollowY = true;
			if(x >= camera.position.x)
				isFollowX = true;
			break;
		case "TR":
			if(y <= camera.position.y)
				isFollowY = true;
			if(x <= camera.position.x)
				isFollowX = true;
			break;
		case "BL":
			if(y >= camera.position.y)
				isFollowY = true;
			if(x >= camera.position.x)
				isFollowX = true;
			break;
		case "BR":
			if(y >= camera.position.y)
				isFollowY = true;
			if(x <= camera.position.x)
				isFollowX = true;
			break;
			case "L":
				if(x >= camera.position.x) {
					returnCamera(deltaTime, 1, new Vector2(x,y));
				}
				break;
			case "R":
				if(x <= camera.position.x) {
					returnCamera(deltaTime, 1, new Vector2(x,y));
				}
				break;
			case "B":
				if(y >= camera.position.y) {
					returnCamera(deltaTime, 1, new Vector2(x,y));
				}
				break;
			case "T":
				if(y <= camera.position.y) {
					returnCamera(deltaTime, 1, new Vector2(x,y));
				}
				break;
		}
		
		return false;
	}
	
	private void returnCamera(float deltaTime, float speed, Vector2 targetPos) {
		
		// Calculate the interpolation factor based on the camera speed
        float alpha = Math.min(1.0f, speed * deltaTime);
        // Interpolate the camera's position towards the target position
        camera.position.slerp(new Vector3(targetPos.x, targetPos.y, 0), alpha);
        isFollowX = true;
        isFollowY = true;
	}
}
