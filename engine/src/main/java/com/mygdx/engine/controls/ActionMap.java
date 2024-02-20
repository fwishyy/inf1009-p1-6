package com.mygdx.engine.controls;

import com.badlogic.gdx.Input;
import com.mygdx.engine.actions.GameAction;

import java.util.LinkedHashMap;

// Action maps bind keys to GameActions
// These can be assigned uniquely to entities to create really cool keybindings
public class ActionMap {
    private LinkedHashMap<Integer, GameAction> mapBindings;

    public ActionMap() {
        mapBindings = new LinkedHashMap<>();
    }

    public void setBindings(LinkedHashMap<Integer, GameAction> bindings) {
        this.mapBindings = bindings;
    }

    public void addNewBinding(int keyCode, GameAction action) {
        mapBindings.put(keyCode, action);
    }

    public GameAction getBindings(int keyCode) {
        return mapBindings.get(keyCode);
    }

    public LinkedHashMap<Integer, GameAction> getAllBindings() {
        return mapBindings;
    }
}
