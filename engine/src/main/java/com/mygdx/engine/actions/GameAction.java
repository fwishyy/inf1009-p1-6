package com.mygdx.engine.actions;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.engine.entity.Entity;

// Generic class used to act on Entities
public abstract class GameAction {
    // interval in ms, all gameactions are by default hardcapped to 1ms
    protected float interval = 1;
    protected Entity actor;
    public boolean isRunning;
    public float lastExecutedTime;

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public Entity getActor() {
        return actor;
    }

    public void setActor(Entity actor) {
        this.actor = actor;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public abstract boolean act();
}
