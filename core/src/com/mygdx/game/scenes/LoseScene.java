package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;

public class LoseScene extends Scene {

    private Table table;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private EventListener<PointerEvent> pointerEventListener;

    @Override
    public void show() {
        pointerEventListener = new EventListener<PointerEvent>() {
            @Override
            public void onSignal(Event e) {
                if (e instanceof PointerEvent) {
                    handlePointerEvent((PointerEvent) e);
                }
            }
        };

        EventBus.addListener(PointerEvent.class, pointerEventListener);

        table = new Table();
        table.setFillParent(true);
        bgTexture = new Texture("bg/PNG/lose_screen.png");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);
        table.setBackground(bgTextureDrawable);
        stage.addActor(table);
    }

    private void handlePointerEvent(PointerEvent e) {
        PointerEvent.Type type = e.getType();

        if (type == PointerEvent.Type.UP) {
            // TODO: add restart here
        }
    }

    @Override
    public void render(float deltaTime) {
        stage.draw();
    }

    @Override
    public void dispose() {
        EventBus.removeListener(pointerEventListener);
    }
}
