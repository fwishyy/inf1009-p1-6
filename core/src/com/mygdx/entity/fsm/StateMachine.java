package com.mygdx.entity.fsm;

import java.util.LinkedHashMap;

public class StateMachine<E extends Enum<E>, S extends State> {

    private final LinkedHashMap<E, S> stateMap;
    private S currentState;
    private E currentStateEnum;

    public StateMachine() {
        stateMap = new LinkedHashMap<>();
    }

    public E getCurrentStateEnum() {
        return currentStateEnum;
    }

    public S getCurrentState() {
        return currentState;
    }

    public void addState(E enumConst, S state) {
        stateMap.put(enumConst, state);
    }

    public void setState(E enumConst) {
        if (currentState != null) {
            currentState.onStateExit();
        }
        S newState = stateMap.get(enumConst);
        currentStateEnum = enumConst;
        currentState = newState;
        currentState.onStateEnter();
    }

    public void update() {
        currentState.update();
    }
}