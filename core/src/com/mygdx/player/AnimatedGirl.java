package com.mygdx.player;

import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.events.WinEvent;

public class AnimatedGirl extends Entity{
	public AnimatedGirl(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
		super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
	}

	@Override
	public void collide(Collider other) {
		if (other.getEntity().getType().equals("player1")) {
			this.dispose();
		}
	}
}
