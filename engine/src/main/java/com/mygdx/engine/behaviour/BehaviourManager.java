package com.mygdx.engine.behaviour;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;

public class BehaviourManager {
    private EntityManager entityManager;
    // a mapping from entities to their assigned behaviours
    // this allows each entity to have a specific behaviour
    private Map<Entity, Behaviour> behaviours;

    /**
     * constructs a BehaviourManager with a reference to an EntityManager
     * 
     * @param entityManager -- the EntityManager that this BehaviourManager should use to access entities
     */
    public BehaviourManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        // initialize the mapping of entities to behaviours
        behaviours = new HashMap<>();
    }

    /**
     * assigns a behaviour to a specific entity
     * 
     * @param entity -- the entity to which the behaviour should be assigned
     * @param behaviour -- the behaviour to assign to the entity
     */
    public void addBehaviour(Entity entity, Behaviour behaviour) {
        // put the entity and its corresponding behaviour into the map
        behaviours.put(entity, behaviour);
    }

    /**
     * updates the behaviour of all entities that have been assigned a behaviour
     * this should be called every frame to ensure that entity behaviours are updated based on game logic
     * 
     * @param deltaTime -- the time in seconds since the last update, used for time-based calculations
     */
    public void update(float deltaTime) {
        // iterate over all entities managed by the EntityManager
        for (Entity entity : entityManager.getEntities()) {
            // check if the current entity has an assigned behaviour
            if (behaviours.containsKey(entity)) {
                // if so, update the entity's behaviour, passing in the entity and the delta time
                behaviours.get(entity).update(entity, deltaTime);
            }
        }
    }
}
