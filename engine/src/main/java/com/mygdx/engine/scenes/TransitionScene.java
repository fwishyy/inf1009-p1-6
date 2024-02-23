package com.mygdx.engine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class TransitionScene extends Scene {
    private Table table;

    @Override
    public void show() {
        // Create a table to hold the label
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);


        // Start the transition
        startTransition();
    }

    private void startTransition() {
        // Fade in from black
        stage.addAction(Actions.sequence(
                Actions.alpha(0), // Set initial alpha to fully transparent
                Actions.fadeIn(10f), // Fade in over 1 second
                Actions.delay(2f), // Wait for 2 seconds
                Actions.fadeOut(1f), // Fade out over 1 second
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        // Transition complete, do something here if needed
                        System.out.println("TRANSITION");
                    }
                })
        ));
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
