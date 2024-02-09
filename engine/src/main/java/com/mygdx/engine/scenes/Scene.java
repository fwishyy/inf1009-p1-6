package com.mygdx.engine.scenes;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.utils.Listener;

// Generic Scene class
public class Scene extends ScreenAdapter {

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected Stage stage;

    // TODO: implement show, hide, pause, resume methods (for now testing the rendering and handling of buttons)
    public Scene() {
        camera = new OrthographicCamera();
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        stage = new Stage();
    }

    @Override
    public void render(float deltaTime) {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void addPointerListener() {
        if (this instanceof Listener) {
            PointerEvent.addPointerListener((Listener<PointerEvent>) this);
        }
    }
}
