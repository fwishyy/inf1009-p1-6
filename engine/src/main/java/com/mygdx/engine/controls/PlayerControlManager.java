package com.mygdx.engine.controls;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.actions.MoveByInputAction;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventListener;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerControlManager extends Manager {

    private final LinkedHashMap<Entity, ActionMap> entityActions;
    private final LinkedHashMap<Integer, Boolean> heldKeys;
    private static final float dispatchDelay = 0.1f;
    private float timeSinceLastDispatch = 0;

    public PlayerControlManager() {
        heldKeys = new LinkedHashMap<>();
        entityActions = new LinkedHashMap<>();
        addKeyListener(new EventListener<KeyEvent>() {
            @Override
            public void onSignal(Event e) {
                if (e instanceof KeyEvent)
                    handleKeyEvent((KeyEvent) e);
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

        for (Map.Entry<GameAction, List<Integer>> entry : actionMap.getAllBindings().entrySet()) {
            List<Integer> keyCodes = entry.getValue();
            for (int keyCode : keyCodes) {
                heldKeys.put(keyCode, false);
            }
        }
    }

    public ActionMap getActionMap(Entity entity) {
        return entityActions.get(entity);
    }

    public boolean isDispatchReady() {
        return timeSinceLastDispatch >= dispatchDelay;
    }

    // Currently this updates frame by frame
    // TODO: Maybe fix the very lazy dispatching
    public void update() {
        GameAction action;
        Entity entity;
        for (Map.Entry<Entity, ActionMap> entityEntry : entityActions.entrySet()) {
            entity = entityEntry.getKey();
            for (Map.Entry<GameAction, List<Integer>> actionMapEntry : entityEntry.getValue().getAllBindings().entrySet()) {
                action = actionMapEntry.getKey();
                List<Integer> keyCodes = actionMapEntry.getValue();
                for (int keyCode : keyCodes) {
                    if (heldKeys.get(keyCode)) {
                        action.setActor(entity);
                        if (action instanceof MoveByInputAction) {
                            MoveByInputAction inputAction = (MoveByInputAction) action;
                            int upKeyState = heldKeys.get(keyCodes.get(0)) ? 1 : 0;
                            int leftKeyState = heldKeys.get(keyCodes.get(1)) ? 1 : 0;
                            int downKeyState = heldKeys.get(keyCodes.get(2)) ? 1 : 0;
                            int rightKeyState = heldKeys.get(keyCodes.get(3)) ? 1 : 0;

                            Vector2 keyState = new Vector2(rightKeyState - leftKeyState, upKeyState - downKeyState);
                            inputAction.setKeyState(keyState);
                            entity.addAction(inputAction);
                            break;
                        } else {
                            entity.addAction(action);
                        }
                    }
                }
            }
        }
    }

    public void dispose() {
        heldKeys.clear();
        entityActions.clear();
    }
}
