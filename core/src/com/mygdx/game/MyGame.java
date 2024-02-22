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
        mainMenuScene = new MainMenuScene(container);
        gameScene = new GameScene(container);

        sceneManager.addScene(mainMenuScene);
        sceneManager.setScene(mainMenuScene);
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
        inputManager.processAllEvents();
    }

    @Override
    public void dispose() {
    }
}
