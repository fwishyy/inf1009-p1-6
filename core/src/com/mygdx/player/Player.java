package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class Player extends Entity {
	
	public Player(String texture, float x, float y, String type) {
		super(texture, x, y, type);
	}
	
	@Override
	protected void update() {
	}
	
	@Override
	public void collide(Collider other) {
		if(other.getEntity().getType() == "player2") {
			this.dispose();
		}
	}
}
