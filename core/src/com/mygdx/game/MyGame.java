package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.Game;
import com.mygdx.game.scenes.GameScene;
import com.mygdx.game.scenes.MainMenuScene;

public class MyGame extends Game {
	MainMenuScene mainMenuScene;
    GameScene gameScene;
    @Override
    public void create() {
        super.create();
        mainMenuScene = new MainMenuScene();
        gameScene = new GameScene(container);
        
//      sceneManager.addScene(mainMenuScene);
        sceneManager.addScene(gameScene);
        sceneManager.setScene(gameScene);
        
        
        
//        audioManager.addSound("water_drop", "audio/fx/water_drop.wav");
//        audioManager.addMusic("bgm", "audio/music/Hell-Night.mp3");
//        audioManager.play("bgm");
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
        inputManager.processAllEvents();
        playerControlManager.update();
        collisionManager.update();
        collisionManager.drawCollider(new ShapeRenderer(), Color.RED);
    }

    @Override
    public void dispose() {
    }
}
