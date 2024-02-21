package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;

/**
 * The Behaviour interface defines a contract for implementing various behaviours
 * for entities in a game. Each behaviour is responsible for modifying the state
 * of an entity over time based on specific rules or AI logic.
 */

public interface Behaviour {

    /**
     * Update the state of the given entity based on the behaviour's logic.
     *
     * @param entity    The entity whose state is to be updated.
     * @param deltaTime The time in seconds since the last update. This is used to ensure
     *                  that the behaviour's logic is frame-rate independent.
     */

    void update(Entity entity, float deltaTime);
}
