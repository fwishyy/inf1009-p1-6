package com.mygdx.engine.actions;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.IMoveable.Direction;

// Move in one direction, distance is dictated by the Entity that is moving
public class DirectionalMoveAction extends GameAction {

    private final Entity entity;
    private final Direction direction;

    // Generic move command
    public DirectionalMoveAction(Direction direction) {
        this.entity = null;
        this.direction = direction;
    }

    public DirectionalMoveAction(Entity entity, Direction direction, String tag) {
        super(tag);
        this.entity = entity;
        this.direction = direction;
    }

    protected boolean act() {
        //TODO: check if movable and also calculate distance for movement
        return true;
    }
}
