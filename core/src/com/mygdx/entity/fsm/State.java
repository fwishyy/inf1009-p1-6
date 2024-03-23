package com.mygdx.entity.fsm;

public abstract class State {
    protected StateMachine stateMachine;

    public State() {}
    public State(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public abstract void onStateEnter();

    public abstract void onStateExit();

    public abstract void update();
}
