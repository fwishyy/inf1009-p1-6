package com.mygdx.backgroundsprite;

import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class BGSprite extends AnimatedEntity{
	
	public BGSprite(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
		super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
	}

	@Override
	public void collide(Collider other) {
		// TODO Auto-generated method stub
	}
}
