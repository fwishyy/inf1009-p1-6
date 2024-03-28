package com.mygdx.entity.projectiles;

import com.mygdx.entity.Character;

public class Fireball extends Projectile {

    public Fireball(Character character, String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(character, texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.speed = 500;
        this.addAnimation("projectiles/fireball.png", "moving", 40);
        this.addAnimation("projectiles/fireball_explosion.png", "collided", 16);

        addAffectedTag("skeletonWarrior");
        addAffectedTag("skeletonArcher");
        addAffectedTag("skeletonSpearman");
    }
}