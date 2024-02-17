package com.mygdx.engine.controls;

import com.mygdx.engine.actions.GameAction;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Actions {
    // Used to bind actions in the settings.json to an actual GameAction instance
    private static LinkedHashMap<String, GameAction> actions = new LinkedHashMap<>();

    /**
     * @param label  The key to identify the action
     * @param action The GameAction instance to store
     */
    public void addAction(String label, GameAction action) {
        actions.put(label, action);
    }

    /**
     * Removes an action from the binding map
     *
     * @param label The action to remove
     */
    public void removeAction(String label) {
        actions.remove(label);
    }

    public GameAction getAction(String label) {
        return actions.get(label);
    }

    public LinkedHashMap<String, GameAction> getActionMap() {
        return actions;
    }
}
