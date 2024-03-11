package com.mygdx.engine.scenes;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.engine.core.GameContainer;

// Generic Scene class
public abstract class Scene extends ScreenAdapter {

    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected Stage stage;
    protected GameContainer container;
    private State state;
    private SceneManager sceneManager;

    // TODO: implement show, hide, pause, resume methods (for now testing the rendering and handling of buttons)
    public Scene() {
        viewport = new ScreenViewport();
        batch = new SpriteBatch();
        stage = new Stage();
    }

    public Scene(GameContainer container) {
        this();
        this.container = container;
        sceneManager = container.getSceneManager();
    }

    @Override
    public void render(float deltaTime) {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        LOADING, RUNNING, PAUSED, UNLOADING
    }
}
