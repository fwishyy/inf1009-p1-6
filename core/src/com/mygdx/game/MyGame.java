package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.utils.GameContainer;
import com.mygdx.game.scenes.MainMenuScene;

public class MyGame extends Game {

    @Override
    public void create() {
        super.create();
        MainMenuScene mainMenuScene = new MainMenuScene();
        AudioManager audioManager = new AudioManager();
        sceneManager.addScene(mainMenuScene);
        sceneManager.setScene(mainMenuScene);
        
        //audioManager.loadAudioFile("hell-night", "audio/music/Hell-Night.mp3", 1, true, false);
        //audioManager.startAudio("hell-night");
        //audioManager.loadAudioFile("swing-fx", "audio/fx/swing-fx.mp3", 1, false, true);
        //audioManager.startAudio("swing-fx");
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }
}
