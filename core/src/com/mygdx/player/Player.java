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
		// TODO Auto-generated method stub
		move();
	}
	
	@Override
	public void collide(Collider other) {
		if(other.getEntity().getType() == "player2") {
			this.dispose();
		}
	}
	
	private void move() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			super.setX(this.getX() - 200 * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			super.setX(this.getX() + 200 * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			super.setY(this.getY() + 200 * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			super.setY(this.getY() - 200 * Gdx.graphics.getDeltaTime());
	}
}
