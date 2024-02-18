package com.mygdx.engine.controls;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.engine.actions.DirectionalMoveAction;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.input.KeyEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerControlManager extends Manager {

    private final LinkedHashMap<Integer, GameAction> keybindings;

    private final Actions actionMap;

    public PlayerControlManager() {
        keybindings = new LinkedHashMap<>();
        actionMap = new Actions();
        addKeyListener(new EventListener<KeyEvent>() {
            @Override
            public void onSignal(Signal<KeyEvent> signal, KeyEvent e) {
                handleKeyEvent(e);
            }
        });
    }

    public void loadKeybindingsFromJson(String path) {
        FileHandle fileHandle = Gdx.files.internal(path);
        String jsonString = fileHandle.readString();

        JsonReader jsonReader = new JsonReader();
        JsonValue jsonRoot = jsonReader.parse(jsonString);
        JsonValue controlsArray = jsonRoot.get("controls");

        for (JsonValue control : controlsArray) {
            String actionLabel = control.getString("action");
            String key = control.getString("key");
            int keyCode;
            if (key.contains("MOUSE")) {
                int mouseIndex = key.indexOf("MOUSE");
                int button = Integer.parseInt(key.substring(mouseIndex + "MOUSE".length()));
                keyCode = 1000 + button;
            } else {
                keyCode = Input.Keys.valueOf(key);
            }

            GameAction action = actionMap.getAction(actionLabel);

            keybindings.put(keyCode, action);
        }
    }

    public void addAction(String label, GameAction action) {
        actionMap.addAction(label, action);
    }

    public void removeAction(String label) {
        actionMap.removeAction(label);
    }

    /**
     * Keybindings are resolved here and translated into GameActions that are fired to the respective entities
     *
     * @param e KeyEvent to resolve and dispatch
     */
    private void handleKeyEvent(KeyEvent e) {
        GameAction action = keybindings.get(e.getKeyCode());
        // TODO: integrate getting player entity here

        if (action instanceof DirectionalMoveAction) {
            DirectionalMoveAction directionalMoveAction = (DirectionalMoveAction) action;
            // TODO: assign entity to directionalMoveAction and execute
        }
    }
}
