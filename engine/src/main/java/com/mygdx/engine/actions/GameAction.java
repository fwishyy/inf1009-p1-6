package com.mygdx.engine.actions;

// Generic class used to act on Entities
public abstract class GameAction {
    protected boolean fireOnce;

    abstract boolean act();

    public boolean isFiredOnce() {
        return fireOnce;
    }
}
