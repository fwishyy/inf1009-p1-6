package com.mygdx.game;

import com.badlogic.gdx.Gdx;
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

        sceneManager.setScene(mainMenuScene);
    }

    @Override
    public void render() {
        sceneManager.render(Gdx.graphics.getDeltaTime());
        inputManager.processAllEvents();
    }

    @Override
    public void dispose() {
    }
}
