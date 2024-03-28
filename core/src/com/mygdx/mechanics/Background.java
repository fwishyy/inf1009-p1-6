package com.mygdx.mechanics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Background {
	
	private Texture texture = null;
	private Vector2 startPos = null;
	private Vector2 maxPos = null;
	private Vector2 minPos = null;
	
	private HashMap<Integer, Vector2> gridMap;
	private boolean useGrid = true;
	
	public Background(String texture, boolean useGrid) {
		this.texture = new Texture(Gdx.files.internal(texture));
		this.startPos =  new Vector2(-(this.texture.getWidth() / 2), -(this.texture.getHeight() / 2));
		this.useGrid = useGrid;
		
		if(this.useGrid) {
			this.gridMap = new HashMap<>();
			createGrid();
			this.maxPos = new Vector2(this.startPos.x + this.texture.getWidth()*2, this.startPos.y + this.texture.getHeight()*2);
			this.minPos = new Vector2(this.startPos.x - this.texture.getWidth(), this.startPos.y - this.texture.getHeight());
		}else {
			this.maxPos = new Vector2(this.startPos.x + this.texture.getWidth(), this.startPos.y + this.texture.getHeight());
			this.minPos = new Vector2(this.startPos.x , this.startPos.y);
		}
		
	}
	
	//for specifying start position of texture
	public Background(String texture, float x, float y) {
		this.texture = new Texture(Gdx.files.internal(texture));
		this.startPos =  new Vector2(x, y);
		this.gridMap = new HashMap<>();
		createGrid();
		this.maxPos = new Vector2(this.startPos.x + this.texture.getWidth()*2, this.startPos.y + this.texture.getHeight()*2);
		this.minPos = new Vector2(this.startPos.x - this.texture.getWidth(), this.startPos.y - this.texture.getHeight());
	}
	
	public void update(SpriteBatch batch) {
		/* Background by drawn in a grid of 9
		 * For your visualisation
			    1|2|3
			   --+-+--
		        4|5|6
		       --+-+--
		        7|8|9
		 */
		
		batch.begin();
		if(useGrid) {
			//Draw Background using coordinates of the grid
			for(int i = 1; i < 10; i++) {
				float x = gridMap.get(i).x;
				float y = gridMap.get(i).y;
				batch.draw(texture, x, y);
			}
		}else {
			batch.draw(texture, startPos.x, startPos.y);
		}
		
		batch.end();
	}
	
	public Vector2 getMaxPos() {
		return this.maxPos;
	}
	
	public Vector2 getMinPos() {
		return this.minPos;
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
}
