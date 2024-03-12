package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.math.Vector2;

/* The SkeletonBehaviour class simulates hordes of skeletons moving
towards the player in a staggered fashion. */

public class SkeletonBehaviour implements Behaviour {

	/*
	 stepInterval: no. of seconds to wait between each step
	 timeSinceLastStep: tracks time elapsed since last step was taken
	 playerPosition: central position of player on game screen
	 stepSize: the distance each skeleton will move in a single step
	 */
	
    private Vector2 playerPosition;
    private float stepSize;
    private float pauseDuration;
    private float timeSinceLastStep;

    public SkeletonBehaviour(Vector2 playerPosition, float stepSize, float pauseDuration) {
        this.playerPosition = playerPosition;
        this.stepSize = stepSize;
        this.pauseDuration = pauseDuration;
        this.timeSinceLastStep = 0;
    }
    
    @Override
    public void update(Entity entity, float deltaTime) {
        // increment timer using deltaTime (time elapsed since the last frame)
        timeSinceLastStep += deltaTime;

        // if pause duration has passed, move skeleton towards player
        if (timeSinceLastStep >= pauseDuration) {
            // reset timer
            timeSinceLastStep = 0;

            // calculate direction vector from skeleton to player
            Vector2 direction = new Vector2(
                playerPosition.x - entity.getX(),
                playerPosition.y - entity.getY()
            ).nor(); // normalise to obtain the direction

            // calculate next position with given step size
            Vector2 nextPosition = new Vector2(
                entity.getX() + direction.x * stepSize,
                entity.getY() + direction.y * stepSize
            );

            // update entity position
            entity.setPosition(nextPosition);
        }
    }
}