package com.mygdx.engine.controls;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.Actionable;
import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.input.InputManager;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerControlManager extends Manager {

    private final LinkedHashMap<Actionable, ActionMap> entityActionMaps;
    private InputManager inputManager;

    public PlayerControlManager(GameContainer container) {
        super(container);
        entityActionMaps = new LinkedHashMap<>();
        inputManager = container.getInputManager();
    }

    public void setActionMap(Actionable entity, ActionMap actionMap) {
        // Create a new specific action map for this entity and assign it to a dispatch list
        entityActionMaps.put(entity, actionMap);
        entity.setActionMap(actionMap);
    }

    public void removeActionMap(Actionable entity) {
        entityActionMaps.remove(entity);
    }
    public void update() {
        for (Map.Entry<Actionable, ActionMap> entry : entityActionMaps.entrySet()) {
            ActionMap actionMap = entry.getValue();
            for (Map.Entry<String, InputAction> actionEntry : actionMap.getActions().entrySet()) {
                InputAction inputAction = actionEntry.getValue();
                if (inputAction instanceof MoveAction) {
                    MoveAction moveAction = (MoveAction) inputAction;
                    HashMap<String, Integer> bindings = moveAction.getBindings();
                    int upKeyState = (inputManager.getKeyState(bindings.get("up")) ? 1 : 0);
                    int leftKeyState = (inputManager.getKeyState(bindings.get("left")) ? 1 : 0);
                    int downKeyState = (inputManager.getKeyState(bindings.get("down")) ? 1 : 0);
                    int rightKeyState = (inputManager.getKeyState(bindings.get("right")) ? 1 : 0);

                    Vector2 moveState = new Vector2();
                    moveState.x = rightKeyState - leftKeyState;
                    moveState.y = upKeyState - downKeyState;
                    moveAction.setValue(moveState);

                    moveAction.setTriggered(moveState.x != 0 || moveState.y != 0);
                } else {
                    inputAction.setTriggered(inputManager.getKeyState(inputAction.getBinding()));
                }
            }
        }
    }

    public void dispose() {
        entityActionMaps.clear();
    }
}
