package com.mygdx.engine.actions;

// Temporal actions are like GameActions, but they take place over a course of time
// This of course means that they have to be left into the ActionQueue and updated over a fixed period until it's done
// and also means that the ActionQueue only allows one instance of it to be in the queue at any one time

import com.badlogic.gdx.utils.TimeUtils;

public abstract class TemporalAction extends GameAction {

    // interval is expressed in ms
    float interval = 1;

    public void setInterval(float interval) {
        this.interval = interval;
    }

    public float getInterval(float interval) {
        return interval;
    }

    public boolean update() {
        if (interval != 0) {
            float currentTimeMs = TimeUtils.nanoTime() / 1000000;
            float lastExecutedTimeMs = lastExecutedTime / 1000000;

            if (currentTimeMs - lastExecutedTimeMs >= interval) {
                act();
                lastExecutedTime = TimeUtils.nanoTime();
                return true;
            }
        }
        return false;
    }
}
