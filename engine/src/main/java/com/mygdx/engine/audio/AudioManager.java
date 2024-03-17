package com.mygdx.engine.audio;

import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.engine.core.Manager;

import java.util.Timer;
import java.util.TimerTask;

/* [INSTRUCTIONS]
 *
 * -Sequence-
 * Create AudioManager > Add audio files > Play/Loop/changePath/changeVolume/etc > Dispose
 *
 * -Basic Usage-
 * 1: create instance of audiomanager: audiomanager = new AudioManager();
 *
 * 2a: add soundFX files you want to play: audiomanager.addSound("hitsound", "mysound.mp3");
 * 										or
 * 2b: add music files you want to play: audiomanager.addMusic("bgm", "mysong.mp3");
 * 		*The string "hitsound" is provided by you for easier reference to the same sound in the future
 *		*The string "mysound.mp3" is the filepath
 *
 * 3: Play the audio track by: audiomanager.play("hitsound")
 * 		*Since no arguments like volume, loop, or if this is sound or music is given, it will default to
 *		*volume: 1.0 (Max Volume), isLoop = false
 *
 * 4a: Dispose single audio: audiomanager.remove("hitsound");
 * 4b: Dispose everything: audiomanager.dispose();
 *
 *
 * -Looping-
 * There are several ways you can loop a sound. The purpose is to allow flexibility in how/when you want to loop
 *
 * [1]
 * audiomanager.addSound("waterdrop", "water_drop.wav"); // Use default constructor
 * audiomanager.setLoop("waterdrop", true); // Set loopint to true
 * audiomanager.play("waterdrop") // Play audio
 *
 * [2]
 * audiomanager.addSound("waterdrop", "water_drop.wav", (float) 1, true; // Use parameterized constructor
 * audiomanager.play("waterdrop") // Play audio
 *
 * [3]
 * audiomanager.addSound("waterdrop", "water_drop.wav"); // Use default constructor
 * audiomanager.loop("waterdrop") // Play using loop command which will ensure isLoop is true
 */

public class AudioManager extends Manager {

    /* audiolist keeps track of all audio files that have been played
     * This prevents having to create a new instance of sound/music every time its played
     * String = user given name eg. "hitsound"
     * AudioTrack = instance of AudioTrack class, which holds info such as filepath, volume, etc */
    private ObjectMap<String, AudioTrack> audiolist;

    // Might be messy if AudioManager keeps printing after every method, set false to disable printing
    private boolean debug;

    // Default Constructor
    public AudioManager() {
        audiolist = new ObjectMap<>();
        debug = true;
    }

    // Debug Constructor
    public AudioManager(boolean printornot) {
        audiolist = new ObjectMap<>();
        debug = printornot;
    }

    // Create an instance of sound with default parameters
    public void addSound(String name, String filepath) {

        // Check first if audio already exsists
        if (audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' already exist");
            }

            // If not, create a new instance
        } else {
            AudioTrack new_sound = new AudioTrack(filepath, (float) 1, false, true);
            audiolist.put(name, new_sound);
            if (debug) {
                System.out.println("AudioManager: Sound '" + name + "' added");
            }
        }
    }

    // [Method Overloading] Create an instance of sound with volume and loop arguments
    public void addSound(String name, String filepath, float volume, boolean isLoop) {

        // Check first if audio already exists
        if (audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' already exist");
            }

            // If not, create a new instance
        } else {
            AudioTrack new_sound = new AudioTrack(filepath, volume, isLoop, true);
            audiolist.put(name, new_sound);
            if (debug) {
                System.out.println("AudioManager: Sound '" + name + "' added");
            }
        }
    }

    // Create an instance of Music with default parameters
    public void addMusic(String name, String filepath) {

        // Check first if audio already exsists
        if (audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' already exist");
            }

            // If not, create a new instance
        } else {
            AudioTrack new_sound = new AudioTrack(filepath, (float) 1, false, false);
            audiolist.put(name, new_sound);
            if (debug) {
                System.out.println("AudioManager: Music '" + name + "' added");
            }
        }
    }

    // [Method Overloading] Create an instance of music with volume and loop arguments
    public void addMusic(String name, String filepath, float volume, boolean isLoop) {

        // Check first if audio already exsists
        if (audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' already exist");
            }

            // If not, create a new instance
        } else {
            AudioTrack new_sound = new AudioTrack(filepath, volume, isLoop, false);
            audiolist.put(name, new_sound);
            if (debug) {
                System.out.println("AudioManager: Music '" + name + "' added");
            }
        }
    }

    // Remove and dispose single Sound or Music
    public void removeAudio(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }

            // If exsists, remove it
        } else {

            // Call dispose with correct Sound or Music method
            AudioTrack audio_to_remove = audiolist.get(name);
            if (audio_to_remove.isFX) {
                audio_to_remove.getSound().dispose();
            } else {
                audio_to_remove.getMusic().dispose();
            }

            // Once disposed remove from audiolist
            audiolist.remove(name);
            if (debug) {
                System.out.println("AudioManager: '" + name + "' removed");
            }
        }
    }

    public void updatePath(String name, String new_filepath) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }

            // If exsists, update the filepath
        } else {
            AudioTrack audio_to_update = audiolist.get(name);
            audio_to_update.setPath(new_filepath);
            if (debug) {
                System.out.println("AudioManager: filepath for '" + name + "' has been updated to '" + new_filepath + "'");
            }
        }
    }

    public void setLoop(String name, boolean isLoop) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }

            // If exsists, update the value of isLoop
        } else {
            AudioTrack audio_to_update = audiolist.get(name);
            audio_to_update.setLoop(isLoop);
            if (debug) {
                System.out.println("AudioManager: '" + name + "' Loop set to " + isLoop);
            }
        }
    }

    public void setVolume(String name, float new_volume) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }

            // If exsists, update the value of isLoop
        } else {
            AudioTrack audio_to_update = audiolist.get(name);
            audio_to_update.setVolume(new_volume);
            if (debug) {
                System.out.println("AudioManager: '" + name + "' Volume set to " + new_volume);
            }
        }
    }
    
    public float getVolume(String name) {
    	float audioVolume = 0;
    	if (!audiolist.containsKey(name)) {
    		if (debug) {
    			System.out.println("AudioManager: '" + name + "' does not exist");
    		}
    	} else {
    		AudioTrack getAudioTrack = audiolist.get(name);
    		audioVolume = getAudioTrack.getVolume();
    	} 
    	return audioVolume;
    }

    public void setFX(String name, boolean isFX) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }

            // If exsists, update the value of isLoop
        } else {
            AudioTrack audio_to_update = audiolist.get(name);
            audio_to_update.setIsEnabled(isFX);
            if (debug) {
                if (isFX) {
                    System.out.println("AudioManager: '" + name + "' is now FX ");
                } else {
                    System.out.println("AudioManager: " + name + " is now Music ");
                }
            }
        }
    }

    public synchronized void play(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);
            if (audiotrack.isFX) {
                if (audiotrack.isLoop) {
                    audiotrack.getSound().loop(audiotrack.getVolume());
                } else {
                    audiotrack.getSound().play();
                }

            } else {
                audiotrack.getMusic().stop(); // Playback from start
                audiotrack.getMusic().play();
            }
        }
    }

    public synchronized void loop(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);
            if (audiotrack.isFX) {
                audiotrack.getSound().loop(audiotrack.getVolume());
            } else {
                audiotrack.getMusic().stop(); // Playback from start
                audiotrack.setLoop(true); // Since loop is requested, ensure loop is set true
                audiotrack.getMusic().play();
            }
        }
    }

    public synchronized void stop(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);
            if (audiotrack.isFX) {
                audiotrack.getSound().stop();
            } else {
                audiotrack.getMusic().stop();
            }
        }
    }

    public synchronized void pause(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);
            if (audiotrack.isFX) {
                audiotrack.getSound().pause();
            } else {
                audiotrack.getMusic().pause();
            }
        }
    }

    public synchronized void resume(String name) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);
            if (audiotrack.isFX) {
                audiotrack.getSound().resume();
            } else {
                audiotrack.getMusic().play();
            }
        }
    }

    public synchronized void setPosition(String name, float playback_position) {

        // Check first if audio exsist
        if (!audiolist.containsKey(name)) {
            if (debug) {
                System.out.println("AudioManager: '" + name + "' does not exist");
            }
        } else {
            AudioTrack audiotrack = audiolist.get(name);

            // Ensure audio is Music as Sound does not have setPosition method
            if (!audiotrack.isFX) {
                audiotrack.getMusic().setPosition(playback_position);
            } else {
                if (debug) {
                    System.out.println("AudioManager: '" + name + "' is not Music, cannot set position");
                }
                ;
            }
        }
    }

    public void dispose() {

        // Iterate over each audio track
        for (AudioTrack audiotrack : audiolist.values()) {

            if (audiotrack.isFX) {
                audiotrack.getSound().dispose();
            } else {
                audiotrack.getMusic().dispose();
            }
        }
        audiolist.clear();

    }

    public void setTimerStop(int miliseconds, String name) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("The timer has finished");
                pause(name);
            }
        }, miliseconds); // 5000 milliseconds = 5 seconds

    }

    public void setTimerPlay(int miliseconds, String name) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("The timer has finished");
                play(name);
            }
        }, miliseconds); // 5000 milliseconds = 5 seconds

    }

}


/* Notes
 * Sounds vs Music
 *
 * [Sounds]
 * Short audio clips
 * loaded into memory
 * cannot check if sound is playing
 * Can adjust pitch volume and panning
 *
 * [Music]
 * Longer sound files
 * can pan and check if playing
 * Played from disk (streamed)
 */
