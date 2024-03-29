package com.mygdx.entity.fsm;

import com.mygdx.engine.physics.Collider;

public abstract class State {
    protected StateMachine stateMachine;

    public State() {
    }

    public State(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void onStateEnter() {
    }

    ;

    public void onStateExit() {
    }

    ;

    public void onCollideEnter(Collider other) {
    }

    ;

    public void onCollideExit(Collider other) {
    }

    ;

    public abstract void update();
}
