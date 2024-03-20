package com.mygdx.engine.actions;

import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;

public class FlipAction extends GameAction {
	
	private final EntityManager em;
	private boolean direction;
	
    public FlipAction(EntityManager entityManager, boolean b) {
    	this.direction = b;
        this.em = entityManager;
    }
    
    public boolean faceLeft() {
    	((AnimatedEntity) em.getEntity("player1")).setFlip(true);
    	return true;
    }
    
    public boolean faceRight() {
    	((AnimatedEntity) em.getEntity("player1")).setFlip(false);
    	return true;
    }

	@Override
	public boolean act() {
        
        if (this.direction) {
            return faceLeft();
        } else{
            return faceRight();
        }
	}
}