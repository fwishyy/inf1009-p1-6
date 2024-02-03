package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.game.scenes.MainMenuScene;

public class MyGame extends Game {

    @Override
    public void create() {
        super.create();
        MainMenuScene mainMenuScene = new MainMenuScene();
        sceneManager.addScene(mainMenuScene);
        sceneManager.setScene(mainMenuScene);
    }

    @Override
    public void render() {
        sceneManager.getCurrentScene().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
    }
}
