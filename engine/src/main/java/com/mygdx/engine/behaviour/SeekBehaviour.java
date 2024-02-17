package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.math.Vector2;

/**
 * SeekBehaviour is a class that implements the Behaviour interface to define a seeking behaviour.
 * It represents the action of an entity moving directly towards a target point in the game world.
 */

public class SeekBehaviour implements Behaviour {
    // The target location that the entity will seek towards.
    private Vector2 target;
    
    /**
     * Constructor for the SeekBehaviour class.
     * 
     * @param target The target position in the world that the entity should seek towards.
     */

    public SeekBehaviour(Vector2 target) {
        this.target = target;
    }
    
    /**
     * Updates the entity's position by moving it closer to the target location.
     * This method calculates a desired velocity vector pointing towards the target
     * and updates the entity's position based on this velocity.
     * 
     * @param entity The entity that is exhibiting this behaviour.
     * @param deltaTime The time in seconds since the last update, used for frame-rate independent movement.
     */

    @Override
    public void update(Entity entity, float deltaTime) {
        // get the current position of the entity
        Vector2 position = entity.getVector2();
        // Calculate the desired velocity towards the target. This is done by subtracting
        // the entity's position from the target position to get the direction vector,
        // normalizing it to get a unit vector, and then scaling it by the entity's speed.
        Vector2 desiredVelocity = target.cpy().sub(position).nor().scl(entity.getSpeed());
        // set the new position based on the desired velocity
        Vector2 newPosition = position.add(desiredVelocity.scl(deltaTime));
        
        // update the entity's position to the newly calculated position
        entity.setVector2(newPosition);
    }
}
