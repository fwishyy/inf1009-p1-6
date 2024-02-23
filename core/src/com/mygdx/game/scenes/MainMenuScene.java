package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;


public class MainMenuScene extends Scene {
    private EntityManager em;
    private CollisionManager cm;
    private InputManager im;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    private Table table;
    private TextureAtlas textureAtlas;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private EventListener<PointerEvent> pointerEventListener;
    private SceneManager sceneManager;
    private boolean paused;

    public MainMenuScene(GameContainer container) {
        super(container);
        sceneManager = container.getSceneManager();
    }

    @Override
    public void show() {
        super.show();
        pointerEventListener = new EventListener<PointerEvent>() {
            @Override
            public void onSignal(Event e) {
                if (e instanceof PointerEvent) handlePointerEvents((PointerEvent) e);
            }
        };
        EventBus.addListener(PointerEvent.class, pointerEventListener);

        table = new Table();
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/sgx-ui.atlas"));

        table.setFillParent(true);
        bgTexture = new Texture("bg/PNG/graveyard.png");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);
        table.setBackground(bgTextureDrawable);
        stage.addActor(table);
        stage.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(5.0f)));
    }

    private void handlePointerEvents(PointerEvent pointerEvent) {
        PointerEvent.Type type = pointerEvent.getType();

        // Click anywhere to continue
        if (type == PointerEvent.Type.UP) {
            sceneManager.setScene(new GameScene(container));
        }
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {
        EventBus.removeListener(pointerEventListener);
        stage.dispose();
    }
}
