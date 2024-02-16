package com.mygdx.engine.audio;


public class AudioTrack {
	protected String name;
	protected String path;
	protected float volume;
	protected boolean isLoop;
	protected boolean isFX;
	
	// Constructor
	protected AudioTrack(String name, String path, float volume, boolean isLoop, boolean isFX) {
		this.name = name;
		this.path = path;
		this.volume = volume;
		this.isLoop = isLoop;
		this.isFX = isFX;
	}
	
	// Getter Methods
	protected String getName() {
		return name;
	}
	
    protected String getPath() {
        return path;
    }

    protected float getVolume() {
        return volume;
    }

    protected boolean isLoop() {
        return isLoop;
    }

    protected boolean isFX() {
        return isFX;
    }
    
    // Setter Methods
    protected void setName(String name) {
        this.name = name;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    protected void setVolume(float volume) {
        this.volume = volume;
    }

    protected void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    protected void setIsEnabled(boolean isFX) {
        this.isFX = isFX;
    }

}
