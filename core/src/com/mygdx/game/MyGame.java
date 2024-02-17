package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.Game;
import com.mygdx.engine.actions.DirectionalMoveAction;
import com.mygdx.engine.controls.Actions;
import com.mygdx.engine.entity.IMoveable;
import com.mygdx.engine.entity.IMoveable.Direction;
import com.mygdx.game.scenes.MainMenuScene;

public class MyGame extends Game {

    @Override
    public void create() {
        super.create();
        configure();
        MainMenuScene mainMenuScene = new MainMenuScene();
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

    public void configure() {
        // Add necessary GameActions
        playerControlManager.addAction("MOVE_UP", new DirectionalMoveAction(Direction.UP));
        playerControlManager.addAction("MOVE_LEFT", new DirectionalMoveAction(Direction.LEFT));
        playerControlManager.addAction("MOVE_RIGHT", new DirectionalMoveAction(Direction.RIGHT));
        playerControlManager.addAction("MOVE_DOWN", new DirectionalMoveAction(Direction.DOWN));

        playerControlManager.loadKeybindingsFromJson("keybinds.json");
    }
}
