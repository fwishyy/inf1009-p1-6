package com.mygdx.player;

import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;

public class AnimatedGirl extends AnimatedEntity {

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
