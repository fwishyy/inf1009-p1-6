package com.mygdx.engine.actions;

import com.mygdx.engine.entity.Entity;

// Generic class used to act on Entities
public abstract class GameAction {
    protected boolean fireOnce;
    protected Entity entity;

    public abstract boolean act();

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
    public boolean isFiredOnce() {
        return fireOnce;
    }
}
