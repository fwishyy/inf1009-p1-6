package com.mygdx.engine.actions;

import com.mygdx.engine.entity.Entity;

// Generic class used to act on Entities
public abstract class GameAction {
    // interval in ms, all gameactions are by default hardcapped to 1ms
    protected float interval = 1;
    protected Entity actor;

    public Entity getActor() {
        return actor;
    }

    public void setActor(Entity actor) {
        this.actor = actor;
    }

    public abstract boolean act();
}
