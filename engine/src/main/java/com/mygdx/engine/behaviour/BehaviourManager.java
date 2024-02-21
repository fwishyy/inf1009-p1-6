package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;

import java.util.HashMap;
import java.util.Map;

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
        // initialise the mapping of entities to behaviours
        behaviours = new HashMap<>();
    }

    /**
     * assigns a behaviour to a specific entity
     *
     * @param entity    -- the entity to which the behaviour should be assigned
     * @param behaviour -- the behaviour to assign to the entity
     */
    public void addBehaviour(Entity entity, Behaviour behaviour) {
        // put the entity and its corresponding behaviour into the map
        behaviours.put(entity, behaviour);
        System.out.println("Behaviour added");
        //print statement action
    }

    /**
     * removes the behaviour associated with the specified entity
     *
     * @param entity -- the entity whose behaviour is to be removed
     */
    public void removeBehaviour(Entity entity) {
        // remove the behaviour from the map using the entity as the key
        behaviours.remove(entity);
//        System.out.println("Behaviour removed");
    }

    /**
     * sets or replaces the behaviour of the specified entity
     * if the entity already has a behaviour, it will be replaced with the new one
     *
     * @param entity       -- the entity whose behaviour is to be set
     * @param newBehaviour -- the new behaviour to assign to the entity
     */
    public void setBehaviour(Entity entity, Behaviour newBehaviour) {
        // put the new behaviour into the map, replacing any existing behaviour
        behaviours.put(entity, newBehaviour);
//        System.out.println("Behaviour changed");
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
            //if (behaviours.get(entity) != null) {
            // if so, update the entity's behaviour, passing in the entity and the delta time
            // behaviours.get(entity).update(entity, deltaTime);
            //}
            Behaviour behaviour = behaviours.get(entity);
            if (behaviour != null) {
                behaviour.update(entity, deltaTime);
//                System.out.println("Behaviour updated");
            }
        }
    }
}
