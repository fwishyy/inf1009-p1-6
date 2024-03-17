package com.mygdx.engine.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioTrack {
    //protected String name;
    protected String path;
    protected float volume;
    protected boolean isLoop;
    protected boolean isFX;
    protected Music music;
    protected Sound sound;

    // Constructor
    protected AudioTrack(String path, float volume, boolean isLoop, boolean isFX) {
        //this.name = name;
        this.path = path;
        this.volume = volume;
        this.isLoop = isLoop;
        this.isFX = isFX;

        if (isFX) {
            this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
            this.sound.setLooping(0, isLoop);
            this.music = null;

        } else {
            this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
            this.music.setLooping(isLoop);
            this.sound = null;
        }

    }

    // Getter Methods
    //protected String getName() {
    //	return name;
    //}

    protected String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
        if (this.isFX) {
            this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
        } else {
            this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        }
    }

    protected float getVolume() {
        return volume;
    }

    protected void setVolume(float volume) {
        this.volume = volume;
        if (this.isFX) {
        	this.sound.setVolume(0, volume);
        } else {
        	this.music.setVolume(volume);
        }
    }

    protected Sound getSound() {
        return sound;
    }

    // Setter Methods
    //protected void setName(String name) {
    //    this.name = name;
    //}

    protected Music getMusic() {
        return music;
    }

    protected boolean isLoop() {
        return isLoop;
    }

    protected void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
        if (this.isFX) {
            this.sound.setLooping(0, isLoop);
        } else {
            this.music.setLooping(isLoop);
        }
    }

    protected void setIsEnabled(boolean isFX) {
        this.isFX = isFX;
    }

}
