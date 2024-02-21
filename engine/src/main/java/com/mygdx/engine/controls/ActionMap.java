package com.mygdx.engine.controls;

import com.badlogic.gdx.Input;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.actions.MoveByInputAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * ActionMap bind keys to GameActions
 * These can be assigned uniquely to entities to create really cool keybindings
 * actions is used to store labels for GameActions for easy reference and to also allow for multiple keys to trigger the same action
 * keybinds map actual keys to GameActions
 */
public class ActionMap {
    private static LinkedHashMap<String, GameAction> actions;
    private final LinkedHashMap<GameAction, List<Integer>> keybinds;

    public ActionMap() {
        actions = new LinkedHashMap<>();
        keybinds = new LinkedHashMap<>();
    }

    public synchronized void addAction(String label, GameAction action) {
        actions.put(label, action);
    }

    public synchronized void removeAction(String label) {
        actions.remove(label);
    }

    public synchronized GameAction getAction(String label) {
        return actions.get(label);
    }

    public void addNewBinding(Integer keyCode, String label) {
        GameAction action = getAction(label);
        if (action != null) {
            checkAndAddBinding(keyCode, action);
        }
    }

    public void addNewBinding(Integer keyCode, GameAction action) {
        if (action != null) {
            checkAndAddBinding(keyCode, action);
        }
    }

    private void checkAndAddBinding(Integer keyCode, GameAction action) {
        if (keybinds.containsKey(action)) {
            keybinds.get(action).add(keyCode);
        } else {
            keybinds.put(action, new ArrayList<Integer>(Arrays.asList(keyCode)));
        }
    }

    // TODO: maybe make this more customisable
    // Special helper function created to add generic 2d movement to an entity
    // Easy to use and applicable to many games
    public void add2DMovementBindings(Integer upKeyCode, Integer leftKeyCode, Integer downKeyCode, Integer rightKeyCode) {
        MoveByInputAction moveAction = new MoveByInputAction();
        addNewBinding(upKeyCode, moveAction);
        addNewBinding(leftKeyCode, moveAction);
        addNewBinding(downKeyCode, moveAction);
        addNewBinding(rightKeyCode, moveAction);
    }

    public LinkedHashMap<GameAction, List<Integer>> getAllBindings() {
        return keybinds;
    }
}
