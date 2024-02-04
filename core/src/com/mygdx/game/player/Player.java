package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Character;

public class Player extends Character {
	
	public Player() {
		this.hp = 100;
		super.setVector2(new Vector2(0,0));
		this.speed = 200;
		this.type = "player";
	}
	
	public Player(String texture) {
		this.sprite.setTexture(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		batch.draw(this.sprite.getTexture(), this.vector2.x, this.vector2.y);
		
	}
	
	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		this.vector2.x -= this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		this.vector2.x += this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		this.vector2.y += this.speed * Gdx.graphics.getDeltaTime();
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		this.vector2.y -= this.speed * Gdx.graphics.getDeltaTime();
	}
}
