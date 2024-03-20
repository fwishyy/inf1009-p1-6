package com.mygdx.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class Fireball extends Entity {
    private int speed;
    private Vector2 direction;

    public Fireball() {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal("projectiles/fireball.png")));
        this.texture.setRegionWidth(25);
        this.texture.setRegionHeight(25);
        this.sprite = new Sprite(this.texture);
        this.speed = 1000;
        this.direction = new Vector2();
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
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
