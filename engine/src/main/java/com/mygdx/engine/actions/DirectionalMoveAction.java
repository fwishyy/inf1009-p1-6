package com.mygdx.engine.actions;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.IMoveable;
import com.mygdx.engine.entity.IMoveable.Direction;

// Move in one direction, distance is dictated by the Entity that is moving
// TODO: this might too much into the game layer logic, can consider shifting out
public class DirectionalMoveAction extends GameAction {

    private final Entity entity;
    private final Direction direction;

    public DirectionalMoveAction(Direction direction) {
        this.entity = null;
        this.direction = direction;
    }

    // Generic move command
    public DirectionalMoveAction(Entity entity, Direction direction) {
        this.entity = entity;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    protected boolean act() {
        return true;
    }
}
