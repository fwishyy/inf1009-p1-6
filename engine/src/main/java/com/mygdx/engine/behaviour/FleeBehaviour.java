package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.math.Vector2;

/**
 * FleeBehaviour is a class that implements the Behaviour interface to define a fleeing behaviour.
 * It represents the action of an entity moving directly away from a target point in the game world.
 */
public class FleeBehaviour implements Behaviour {
    // the target location that the entity will flee from
    private Vector2 target;

    /**
     * constructor for the FleeBehaviour class
     * 
     * @param target -- the target position in the world that the entity should flee from
     */
    public FleeBehaviour(Vector2 target) {
        this.target = target;
    }

    /**
     * updates the entity's position by moving it further away from the target location
     * this method calculates a desired velocity vector pointing away from the target
     * and updates the entity's position based on this velocity
     * 
     * @param entity -- the entity that is exhibiting this behaviour
     * @param deltaTime -- the time in seconds since the last update, used for frame-rate independent movement
     */
    @Override
    public void update(Entity entity, float deltaTime) {
        // get the current position of the entity
        Vector2 position = entity.getVector2();
        // calculate the desired velocity away from the target
        // this is done by subtracting the target position from the entity's position to get the direction vector,
        // normalizing it to get a unit vector, and then scaling it by the entity's speed
        Vector2 desiredVelocity = position.cpy().sub(target).nor().scl(entity.getSpeed());
        // set the new position based on the desired velocity
        Vector2 newPosition = position.add(desiredVelocity.scl(deltaTime));
        
        // update the entity's position to the newly calculated position
        entity.setVector2(newPosition);
    }
}
