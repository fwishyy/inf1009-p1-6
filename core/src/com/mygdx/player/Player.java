package com.mygdx.player;

import com.mygdx.engine.entity.Character;

public class Player extends Character{
	
	public Player(String texture, float x, float y, float speed, String type) {
		super(texture, x, y, speed, type);
	}
	
	@Override
	public void move() {
		
	}
	
	@Override 
	public void onDeath() {
		
	}
	
}
