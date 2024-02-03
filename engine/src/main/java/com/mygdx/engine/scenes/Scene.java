package com.mygdx.engine.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

// Generic Scene class
public class Scene extends ScreenAdapter {

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;

    public Scene() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 600, camera);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float deltaTime) {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
