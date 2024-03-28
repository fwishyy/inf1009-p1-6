package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.events.CharacterDeathEvent;
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
        EventBus.processEvents(CharacterDeathEvent.class);
    }

    @Override
    public void dispose() {
    }
}
