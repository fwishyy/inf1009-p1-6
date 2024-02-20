package com.mygdx.engine.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerControlManager extends Manager {

    private final LinkedHashMap<Entity, ActionMap> entityActions;
    private final LinkedHashMap<Integer, Boolean> heldKeys;

    public PlayerControlManager() {
        heldKeys = new LinkedHashMap<>();
        entityActions = new LinkedHashMap<>();
        addKeyListener(new EventListener<KeyEvent>() {
            @Override
            public void onSignal(Signal<KeyEvent> signal, KeyEvent e) {
                handleKeyEvent(e);
            }
        });
    }

    /**
     * Keybindings are resolved here and translated into GameActions that are fired to the respective entities
     *
     * @param e KeyEvent to resolve and dispatch
     */
    private void handleKeyEvent(KeyEvent e) {
        heldKeys.put(e.getKeyCode(), e.isPressed());
    }

    public void setActionMap(Entity entity, ActionMap actionMap) {
        // Create a new specific action map for this entity and assign it to a dispatch list
        entityActions.put(entity, actionMap);

        for (Map.Entry<Integer, GameAction> entry : actionMap.getAllBindings().entrySet()) {
            heldKeys.put(entry.getKey(), false);
        }
    }

    // Currently this updates frame by frame
    // TODO: Maybe fix the very lazy dispatching
    public void update() {
        int keyCode;
        GameAction action;
        Entity entity;
        for (Map.Entry<Entity, ActionMap> entityEntry : entityActions.entrySet()) {
            entity = entityEntry.getKey();
            for (Map.Entry<Integer, GameAction> actionMapEntry : entityEntry.getValue().getAllBindings().entrySet()) {
                keyCode = actionMapEntry.getKey();
                action = actionMapEntry.getValue();

                if (heldKeys.get(keyCode)) {
                    action.act(entity);
                }
            }
        }
    }
}
