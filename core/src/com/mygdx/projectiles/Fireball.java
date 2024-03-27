package com.mygdx.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.Character;
import com.mygdx.entity.Enemy;

public class Fireball extends AnimatedEntity {
    private int speed;
    private Vector2 direction;
    private boolean hasHit = false;
    private Character character;

    public Fireball(Character character, String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.character = character;
        this.speed = 500;
        this.direction = new Vector2();
        this.addAnimation("projectiles/fireball_explosion.png", "explode", 12);
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
        setRotation(direction.angleDeg());
    }

    public boolean hasHit() {
        return hasHit;
    }

    public void completeHit() {
        this.hasHit = true;
        this.setAnimation("explode", 1, true);
    }

    @Override
    public void update() {
        if (!hasHit) {
            float newX = getX() + direction.x * speed * Gdx.graphics.getDeltaTime();
            float newY = getY() + direction.y * speed * Gdx.graphics.getDeltaTime();
            setPosition(newX, newY);
        } else {
            // Check if the explosion animation is complete
            if (readyToDispose()) {
                this.dispose();
            }
        }
        super.update();
    }

    public void collide(Collider other) {
        if (other.getEntity() instanceof Enemy && !hasHit) {
            completeHit();
            Character target = (Character) other.getEntity();
            target.takeDamage(character.getDamage());
        }
    }
}