package com.mygdx.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class Player extends Entity {

	private boolean isDead = false;
	
	public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
		super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
	}
	
    public Player(String texture, float x, float y, String type) {
        super(texture, x, y, type);
    }

    @Override
    protected void update() {
    }

    @Override
    public void collide(Collider other) {
        if (other.getEntity().getType() == "player1") {
        	isDead = true;
            this.dispose();
        }
    }
    
    public boolean getIsDead() {
    	return isDead;
    }
	
	
}
