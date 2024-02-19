package com.mygdx.engine.behaviour;

import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.math.Vector2;
import java.util.List;

/**
 * GroupSeekBehaviour is a behaviour that enables a group of entities to move together towards a common target.
 * It implements the Behaviour interface, meaning it contains the logic for updating the state of entities.
 */
public class GroupSeekBehaviour implements Behaviour {
    // the target location that the group of entities will seek towards
    private Vector2 target;
    // a list of entities that are considered members of the group
    private List<Entity> members;

    /**
     * constructor for the GroupSeekBehaviour class
     *
     * @param target -- the target position in the world that the group should seek towards
     * @param members -- the list of entities that make up the group
     */
    public GroupSeekBehaviour(Vector2 target, List<Entity> members) {
        this.target = target;
        this.members = members;
    }
    
    /**
     * adds a new member to the group
     * 
     * @param newMember -- the entity to be added to the group
     */
    public void addMember(Entity newMember) {
        // check if the new member is not already in the group
        if (!members.contains(newMember)) {
        	// add the new member to the group
            members.add(newMember); 
        }
    }

    /**
     * removes a member from the group
     * 
     * @param memberToRemove -- the entity to be removed from the group
     */
    public void removeMember(Entity memberToRemove) {
        // remove the member from the group if it exists
        members.remove(memberToRemove);
    }
    
    

    /**
     * updates the position of each entity in the group by moving it closer to the target location
     * this method calculates a desired velocity vector pointing towards the target
     * and updates each entity's position based on this velocity
     *
     * @param entity -- the entity that is part of the group exhibiting this behaviour
     *               note: the entity parameter is not directly used here because the update is applied to all members
     * @param deltaTime -- the time in seconds since the last update, used for frame-rate independent movement
     */
    @Override
    public void update(Entity entity, float deltaTime) {
        // loop through all entities in the group
        for (Entity member : members) {
            // calculate the desired direction towards the target for each member
            Vector2 desiredDirection = target.cpy().sub(member.getVector2()).nor();
            // scale the desired direction by the entity's speed to get the desired velocity
            Vector2 desiredVelocity = desiredDirection.scl(member.getSpeed());
            // update the member's position based on the desired velocity
            Vector2 newPosition = member.getVector2().add(desiredVelocity.scl(deltaTime));
            // set the new position for the entity
            member.setVector2(newPosition);
        }
    }
}
