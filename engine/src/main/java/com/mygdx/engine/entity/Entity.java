package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Sprite sprite;
    private TextureRegion texture;
    private Vector2 vector2;
    private float width;
    private float height;
    private String type;
    
    // animation settings
    private boolean isAnimation;
    private int frameCountColumn; // Number of frames in a column
    private int frameCountRow; // Number of frames in a row
    private float stateTime; // time elapsed since the last frame update
    private float frameDuration; // Duration between each frame (in seconds)
    private TextureRegion[][] frames; // to hold sliced frames
    private int currentFrame;

    protected Entity() {
        this.texture = null;
        this.vector2 = new Vector2();
        this.width = 0;
        this.height = 0;
        this.type = "";
        this.sprite = null;
        // animation settings
        this.isAnimation = false;
        this.frameCountColumn = 0;
        this.frameCountRow = 0;
        this.stateTime = 0;
        this.frameDuration = 0;
        this.frames = null;
    }

    protected Entity(String texture, float x, float y, String type) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = type;
        this.sprite = new Sprite(this.texture);
        // animation settings
        this.isAnimation = false;
        this.frameCountColumn = 0;
        this.frameCountRow = 0;
        this.stateTime = 0;
        this.frameDuration = 0;
        this.frames = null;
    }
    
    protected Entity(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
    	if(this.texture != null)
    		this.texture.getTexture().dispose();
    	this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
    	this.vector2 = new Vector2(x, y);
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = type;
        this.sprite = new Sprite(new Texture(Gdx.files.internal(texture)), (int) this.width, (int) this.height);
     // animation settings
        this.isAnimation = true;
        this.frameCountColumn = frameCountColumn;
        this.frameCountRow = frameCountRow;
        this.stateTime = 0;
        this.frameDuration = frameDuration;
        this.frames = new TextureRegion[this.frameCountRow][this.frameCountColumn];
        this.currentFrame = 0;
        this.prepAnimation();
        
    }

    protected Entity(float x, float y, String type) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = 0;
        this.height = 0;
        this.type = type;
        this.sprite = null;
        // animation settings
        this.isAnimation = false;
        this.frameCountColumn = 0;
        this.frameCountRow = 0;
        this.stateTime = 0;
        this.frameDuration = 0;
        this.frames = null;
    }

    protected Entity(float x, float y) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = null;
        // animation settings
        this.isAnimation = false;
        this.frameCountColumn = 0;
        this.frameCountRow = 0;
        this.stateTime = 0;
        this.frameDuration = 0;
        this.frames = null;
    }

    protected Entity(String texture) {
    	this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2();
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = new Sprite(this.texture);
        // animation settings
        this.isAnimation = false;
        this.frameCountColumn = 0;
        this.frameCountRow = 0;
        this.stateTime = 0;
        this.frameDuration = 0;
        this.frames = null;
    }

    protected void update() {
//    	this.vector2.x = this.sprite.getX();
//    	this.vector2.y = this.sprite.getY();
    }

    public abstract void collide(Collider other);

    protected void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.vector2.x, this.vector2.y);
    }

    public TextureRegion getTextureRegion() {
        return texture;
    }

    public void setTextureRegion(String texture) {
        this.texture.getTexture().dispose();
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        setWidth(this.texture.getRegionWidth());
        setHeight(this.texture.getRegionHeight());
        this.sprite = null;
        this.sprite = new Sprite(this.texture);
    }

    public float getX() {
        return this.vector2.x;
    }

    public void setX(float x) {
        this.vector2.x = x;
        this.sprite.setX(x);
    }

    public float getY() {
        return this.vector2.y;
    }

    public void setY(float y) {
        this.vector2.y = y;
        this.sprite.setY(y);
    }

    public Vector2 getVector2() {
        return this.vector2;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
        this.sprite.setSize(width, this.height);
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
        this.sprite.setSize(this.width, height);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setPosition(Vector2 vector2) {
        this.vector2 = vector2;
        this.sprite.setPosition(vector2.x, vector2.y);
    }

    public void setPosition(float x, float y) {
        this.vector2 = null; // Release previous object
        this.vector2 = new Vector2(x, y);
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        this.sprite.setSize(width, height);
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
    
    public void setFrameCount(int frameCount) {
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
    
    private void prepAnimation() {
    	
//    	System.out.println("frame len: " + this.frames[0].length);
    	
    	int cols = this.frameCountColumn;
    	int rows = this.frameCountRow;
    	int width = (int)this.width;
    	int height = (int)this.height;
    	TextureRegion tex = this.texture;
    	
    	// split texture into frames
    	int frameWidth = width / cols;
    	int frameHeight = height / rows;	
        this.frames = TextureRegion.split(tex.getTexture(), frameWidth, frameHeight);

        this.texture = new TextureRegion(frames[0][0]);
    }
    
    public void dispose() {
        EntityDisposedEvent.addEvent(new EntityDisposedEvent(this));
        this.sprite.getTexture().dispose();
        if(this.isAnimation) {
        	for(int row = 0; row < frames.length; row++) {
        		for(int col = 0; col < frames[row].length; col++)
        			frames[row][col].getTexture().dispose();
        	}
        }
        this.texture.getTexture().dispose();
    }
}
