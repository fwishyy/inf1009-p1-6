package com.mygdx.engine.controls;

import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;

import java.util.LinkedHashMap;

/**
 * New Action
 */
public class ActionMap {
    private String name;
    private LinkedHashMap<String, InputAction> actions;

    public ActionMap(String name) {
        this.name = name;
        this.actions = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, InputAction> getActions() {
        return actions;
    }

    public void addInputAction(String actionName, int keyCode) {
        actions.put(actionName, new InputAction(keyCode));
    }

    public void addMoveAction(int upKey, int leftKey, int downKey, int rightKey) {
        actions.put("Move", new MoveAction(upKey, leftKey, downKey, rightKey));
    }

    public InputAction getAction(String name) {
        return actions.get(name);
    }

    public MoveAction getMoveAction() {
        MoveAction moveAction = (MoveAction) actions.get("Move");
        return moveAction;
    }
}
