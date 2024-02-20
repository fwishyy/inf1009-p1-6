package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.IMoveable;
import com.mygdx.engine.entity.IMoveable.Direction;

// Move in one direction, distance is dictated by the Entity that is moving
// TODO: this might too much into the game layer logic, can consider shifting out
public class DirectionalMoveAction extends GameAction {
    private final Direction direction;

    public DirectionalMoveAction(Direction direction, boolean fireOnce) {
        this.entity = null;
        this.direction = direction;
        this.fireOnce = fireOnce;
    }

    // Generic move command
    public DirectionalMoveAction(Entity entity, Direction direction, boolean fireOnce) {
        this.entity = entity;
        this.direction = direction;
        this.fireOnce = fireOnce;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean act() {
        switch (direction) {
            case LEFT:
                entity.setX(entity.getX() - 200 * Gdx.graphics.getDeltaTime());
                break;
            case RIGHT:
                entity.setX(entity.getX() + 200 * Gdx.graphics.getDeltaTime());
                break;
            case UP:
                entity.setY(entity.getY() + 200 * Gdx.graphics.getDeltaTime());
                break;
            case DOWN:
                entity.setY(entity.getY() - 200 * Gdx.graphics.getDeltaTime());
                break;
        }
        return true;
    }
}
