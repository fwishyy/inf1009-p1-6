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
	
	AudioManager audioManager;

    public MyGame(GameContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void create() {
        super.create();
        MainMenuScene mainMenuScene = new MainMenuScene();
        audioManager = new AudioManager(true);
        sceneManager.addScene(mainMenuScene);
        sceneManager.setScene(mainMenuScene);
        
        audioManager.addSound("water_drop", "audio/fx/water_drop.wav");
        audioManager.addMusic("bgm", "audio/music/rainy_day.mp3");
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
        
    }

    @Override
    public void dispose() {
    }
}
