package com.mygdx.entity.fsm;

public class StateMachine {
    private State currentState;

    public void changeState(State newState) {
        if (currentState != null) {
            currentState.onStateExit();
        }
        currentState = newState;
        newState.onStateEnter();
    }

    public void update() {
        currentState.update();
    }
}