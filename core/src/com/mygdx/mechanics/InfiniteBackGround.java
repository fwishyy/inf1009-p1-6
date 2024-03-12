package com.mygdx.mechanics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class InfiniteBackGround {
	
	private OrthographicCamera camera = null;
	private Texture texture = null;
	private Vector2 startPos = null;
	private float screenHeight = Gdx.graphics.getHeight();
	private float screenWidth = Gdx.graphics.getWidth();
	HashMap<Integer, Vector2> gridMap;
	
	public InfiniteBackGround(OrthographicCamera camera, String texture) {
		this.camera = camera;
		this.texture = new Texture(Gdx.files.internal(texture));
		this.startPos =  new Vector2(-(this.texture.getWidth() / 2), -(this.texture.getHeight() / 2));
		this.gridMap = new HashMap<>();
		createGrid();
	}
	
	public void update(SpriteBatch batch) {
		/* Simulate infinite background by drawing the background in a grid of 9
		 * For your visualisation
			    1|2|3
			   --+-+--
		        4|5|6
		       --+-+--
		        7|8|9
		 */
		
		int currGrid = getCurrentGrid(); // current grid should always be 5 so we create 
		//System.out.println(currGrid);
		if(currGrid != 5) {
			// create a new grid once camera crosses into a new grid
			startPos = null;
			startPos = new Vector2(gridMap.get(currGrid));
			createGrid();
		}
		
		//Draw Background using coordinates of the grid
		for(int i = 1; i < 10; i++) {
			float x = gridMap.get(i).x;
			float y = gridMap.get(i).y;
			batch.draw(texture, x, y);
		}
	}
	
	private void createGrid() {
		
		/* For your visualisation
		    1|2|3
		   --+-+--
            4|5|6
           --+-+--
            7|8|9
		 */
		
		float textureWidth = texture.getWidth();
		float textureHeight = texture.getHeight();
		//System.out.println("New Grid");
		for(int i = 1; i < 10; i++) {
			if(gridMap.get(i) == null)
				gridMap.put(i, newGrid(i, startPos, textureWidth, textureHeight));
			else
				gridMap.replace(i, newGrid(i, startPos, textureWidth, textureHeight));
			//System.out.println("grid " + i + ": " + gridMap.get(i));
		}
	}
	
	private Vector2 newGrid(int gridNum, Vector2 pos, float width, float height) {
		
		Vector2 ret = null;
		
		switch(gridNum) {
			case 1:
				ret = new Vector2(pos.x - width, pos.y + height);
				break;
			case 2:
				ret = new Vector2(pos.x, pos.y + height);
				break;
			case 3:
				ret = new Vector2(pos.x + width, pos.y + height);
				break;
			case 4:
				ret = new Vector2(pos.x - width, pos.y);
				break;
			case 5:
				ret = new Vector2(pos.x, pos.y);
				break;
			case 6:
				ret = new Vector2(pos.x + width, pos.y);
				break;
			case 7:
				ret = new Vector2(pos.x - width, pos.y - height);
				break;
			case 8:
				ret = new Vector2(pos.x, pos.y - height);
				break;
			case 9:
				ret = new Vector2(pos.x + width, pos.y - height);
				break;
			default:
				break;
		}
		
		return ret;
	}
	
	private int getCurrentGrid() {
		for(int gridNum: gridMap.keySet()) {
			Vector2 currPos = new Vector2(camera.position.x, camera.position.y);
			if(isWithinTexture(currPos, gridMap.get(gridNum)))
				return gridNum;
		}
		
		return 0;
	}
	
	
	private boolean isWithinTexture(Vector2 curr, Vector2 other) {
		if(curr.x <= other.x + texture.getWidth() && curr.x >= other.x)
			if(curr.y <= other.y + texture.getHeight() && curr.y >= other.y)
				return true;
		return false;
	}
}
