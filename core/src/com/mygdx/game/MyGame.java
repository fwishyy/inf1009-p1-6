package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.game.scenes.GameScene;
import com.mygdx.game.scenes.MainMenuScene;

public class MyGame extends Game {

    @Override
    public void create() {
        super.create();
        MainMenuScene mainMenuScene = new MainMenuScene();
        GameScene gameScene = new GameScene(container);
        sceneManager.addScene(gameScene);
        sceneManager.setScene(gameScene);
        audioManager.addSound("water_drop", "audio/fx/water_drop.wav");
        audioManager.addMusic("bgm", "audio/music/Hell-Night.mp3");
        audioManager.play("bgm");
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
        inputManager.processAllEvents();
        playerControlManager.update();
    }

    @Override
    public void dispose() {
    }
}
