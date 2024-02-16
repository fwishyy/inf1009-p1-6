package com.mygdx.engine.controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.Gdx;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;

public class PlayerControlManager extends Manager {
    public PlayerControlManager() {
        addKeyListener(new EventListener<KeyEvent>() {
            @Override
            public void onSignal(Signal<KeyEvent> signal, KeyEvent e) {
                dispatchKeyEvent(e);
            }
        });

        addCollisionListener(new EventListener<CollisionEvent>() {
            @Override
            public void onSignal(Signal<CollisionEvent>, CollisionEvent e) {
                // TODO: handle collisions here
                resolveCollision(e);
            }
        });
    }


    // CollisionEvent.addCollisionEvent(new CollisionEvent(this, entityB))
    private void resolveCollision(CollisionEvent e) {
        entityA = e.getEntityA();
    }

    private void loadKeybindingsFromJson(String path) {
        FileHandle fileHandle = Gdx.files.internal(path);
        String jsonString = fileHandle.readString();
    }

    private void dispatchKeyEvent(KeyEvent e) {
        //TODO: handle button presses here
        System.out.println("Handle event here");

        // Another event
    }
}
