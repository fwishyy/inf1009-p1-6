package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Character;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class Player extends Character{
	
	public Player(String texture, float x, float y, float speed, String type) {
		super(texture, x, y, speed, type);
	}
	
	@Override 
	public void onDeath() {
		
	}
	
	public void move() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			super.setX(this.getX() - this.getSpeed() * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			super.setX(this.getX() + this.getSpeed() * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			super.setY(this.getY() + this.getSpeed() * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			super.setY(this.getY() - this.getSpeed() * Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void collide(Collider other) {
		if(other.getEntity().getType() == "player1") {
			this.setVector2(new Vector2(400,400));
		}
	}
}
