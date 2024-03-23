package com.mygdx.engine.actions;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class MoveAction extends InputAction {
    Vector2 moveState;
    // movement keys
    private int upKey;
    private int leftKey;
    private int downKey;
    private int rightKey;
    private HashMap<String, Integer> binding;

    public MoveAction(int upKey, int leftKey, int downKey, int rightKey) {
        this.moveState = new Vector2();
        this.upKey = upKey;
        this.leftKey = leftKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.binding = new HashMap<>();
        binding.put("up", upKey);
        binding.put("left", leftKey);
        binding.put("down", downKey);
        binding.put("right", rightKey);
    }

    public HashMap<String, Integer> getBindings() {
        return binding;
    }

    public Vector2 readValue() {
        return moveState;
    }

    public void setValue(Vector2 moveState) {
        this.moveState = moveState;
    }
}
