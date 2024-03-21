package com.mygdx.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;

public class Fireball extends AnimatedEntity {
    private int speed;
    private Vector2 direction;

    public Fireball(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.speed = 500;
        this.direction = new Vector2();
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
        setRotation(direction.angleDeg());
    }

    @Override
    public void update() {
        float newX = getX() + direction.x * speed * Gdx.graphics.getDeltaTime();
        float newY = getY() + direction.y * speed * Gdx.graphics.getDeltaTime();
        setPosition(newX, newY);
    }

    public void collide(Collider other) {

    }
}
