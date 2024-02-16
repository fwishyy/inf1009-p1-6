package com.mygdx.engine.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	public ObjectMap<String, AudioTrack> audioTrackMap;
	
	public AudioManager() {
		audioTrackMap = new ObjectMap<>();
	}
	
	public synchronized void addAudio(String key, AudioTrack audioTrack) {
		audioTrackMap.put(key, audioTrack);
	}
	
	public synchronized void loadAudioFile(String key, String path, float volume, boolean isLoop, boolean isFX) {
		AudioTrack audioTrack = new AudioTrack(key, path, volume, isLoop, isFX);
		if (audioTrack != null) {
			addAudio(key, audioTrack);
		}
	}
	
	public synchronized void unloadAudioFile(String key) {
		AudioTrack audioTrack = audioTrackMap.get(key);
		// TODO
	}
	
	public synchronized void startAudio(String key) {
		AudioTrack audioTrack = audioTrackMap.get(key);
		if (audioTrack.isFX()) {
			// Play FX 
			Sound fx = Gdx.audio.newSound(Gdx.files.internal(audioTrack.getPath()));
			fx.play();
		} else {
			Music music = Gdx.audio.newMusic(Gdx.files.internal(audioTrack.getPath()));
			music.setVolume(audioTrack.getVolume());
			if (audioTrack.isLoop()) {
				music.setLooping(true);
			}
			music.play();
		}
	}
	
	public synchronized void stopAudio(String key) {
		AudioTrack audioTrack = audioTrackMap.get(key);
		if (audioTrack.isFX()) {
			Sound fx = Gdx.audio.newSound(Gdx.files.internal(audioTrack.getPath()));
			fx.stop();
		} else {
			Music music = Gdx.audio.newMusic(Gdx.files.internal(audioTrack.getPath()));
			music.stop();
		}
	}
	
	public synchronized void dispose() {
		for (AudioTrack audioTrack : audioTrackMap.values()) {
			if (audioTrack.isFX()) {
				Sound fx = Gdx.audio.newSound(Gdx.files.internal(audioTrack.getPath()));
				fx.dispose();
			} else {
				Music music = Gdx.audio.newMusic(Gdx.files.internal(audioTrack.getPath()));
				music.dispose();
			}
		}
		audioTrackMap.clear();
	}
}
