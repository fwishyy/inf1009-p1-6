package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimatedEntity extends Entity {
	
	// animation settings
	private Texture tex;
    private boolean isAnimation;
    private int frameCountColumn; // Number of frames in a column
    private int frameCountRow; // Number of frames in a row
    private float stateTime; // time elapsed since the last frame update
    private float frameDuration; // Duration between each frame (in seconds)
    private TextureRegion[][] frames; // to hold sliced frames
    private int currentFrame;
	
	protected AnimatedEntity(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
		this.tex = new Texture(Gdx.files.internal(texture));
    	this.texture = new TextureRegion();
    	this.vector2 = new Vector2(x, y);
    	this.type = type;
    	this.frameCountRow = frameCountRow;
        this.frameCountColumn = frameCountColumn;
        this.frameDuration = frameDuration;
        
        this.width = tex.getWidth() / frameCountColumn;
        this.height = tex.getHeight() / frameCountRow;
        
        this.isAnimation = true;
        this.stateTime = 0;
        this.frames = new TextureRegion[this.frameCountRow][this.frameCountColumn];
        this.currentFrame = 0;
        prepAnimation();
        
        
    }
	
	@Override
	public void draw(SpriteBatch batch) {

        int cols = this.getFrameCountColumn();
        int rows = this.getFrameCountRow();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();

        // split texture into frames
//        int frameWidth = width / cols;
//        int frameHeight = height / rows;

        // update statetime
        this.setStateTime(this.getStateTime() + Gdx.graphics.getDeltaTime());

        // Get the current frame based on the state time
        int currentFrame = (int) (this.getStateTime() / this.getFrameDuration()) % (cols * rows);

        TextureRegion currentFrameRegion = this.getFrames()[currentFrame / cols][currentFrame % cols];
        // Draw every frame
        batch.draw(currentFrameRegion, this.getX(), this.getY(), width, height);

    }
	
	private void prepAnimation() {
    	
//    	System.out.println("frame len: " + this.frames[0].length);
    	int cols = this.frameCountColumn;
    	int rows = this.frameCountRow;
    	int width = (int)this.width;
    	int height = (int)this.height;
    	
    	// split texture into frames
//    	System.out.println(frameWidth + " " + frameHeight);
        this.frames = TextureRegion.split(this.tex, width, height);
        
        Texture firstFrame = frames[0][0].getTexture();
		this.texture = new TextureRegion(firstFrame, (int)this.vector2.x, (int)this.vector2.y, width, height);
        this.sprite = new Sprite(this.texture);
        
    }
	
	public boolean getIsAnimation() {
    	return this.isAnimation;
    }
    
    public void setIsAnimation(boolean isAnimation) {
    	this.isAnimation = isAnimation;
    }
    
    public int getFrameCountColumn() {
    	return this.frameCountColumn;
    }
    
    public void setFrameCountColumn(int frameCount) {
    	this.frameCountColumn = frameCount;
    }
    
    public int getFrameCountRow() {
    	return this.frameCountRow;
    }
    
    public void setFrameCountRow(int frameCount) {
    	this.frameCountRow = frameCount;
    }
    
    public float getStateTime() {
    	return this.stateTime;
    }
    
    public void setStateTime(float stateTime) {
    	this.stateTime = stateTime;
    }
    
    public float getFrameDuration() {
    	return this.frameDuration;
    }
    
    public void setFrameDuration(float frameDuration) {
    	this.frameDuration = frameDuration;
    }
    
    public TextureRegion[][] getFrames() {
    	return this.frames;
    }
    
    public void setFrames(TextureRegion[][] frames) {
    	this.frames = frames;
    }
    
    public int getCurrentFrame() {
    	return this.currentFrame;
    }
    
    public void setCurrentFrame(int currentFrame) {
    	this.currentFrame = currentFrame;
    }
    
    
    
    @Override
    public void dispose() {
    	for(int row = 0; row < frames.length; row++) {
        	for(int col = 0; col < frames[row].length; col++)
        		frames[row][col].getTexture().dispose();
        	}
    	super.dispose();
    }
}

