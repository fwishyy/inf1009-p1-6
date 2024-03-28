package com.mygdx.entity.projectiles;

import com.mygdx.entity.Character;

public class Arrow extends Projectile {
    public Arrow(Character character, String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(character, texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.speed = 700;
        addAffectedTag("player1");

        this.addAnimation("projectiles/arrow.png", "collided", 1);
    }
}
