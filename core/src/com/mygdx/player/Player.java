package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.entity.Character;

public class Player extends Character {
	
	public Player() {
		this.hp = 100;
		this.x = 100;
		this.y = 100;
		this.speed = 200;
		this.type = "player";
	}
	
	public Player(String texture) {
		this.sprite.setTexture(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(this.sprite.getTexture(), this.x, this.y);
		
	}
	
	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		this.x -= this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		this.x += this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		this.y += this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		this.y -= this.speed * Gdx.graphics.getDeltaTime();
	}
}
