package com.mygdx.engine.actions;

import com.mygdx.engine.entity.Entity;

// Generic class used to act on Entities
public abstract class GameAction {
    public abstract boolean act(Entity entity);
}
