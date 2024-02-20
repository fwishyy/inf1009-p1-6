package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    private Texture texture;
    private Vector2 vector2;
    private float width;
    private float height;
    private String type;
    protected Sprite sprite;

    protected Entity() {
        this.texture = null;
        this.vector2 = new Vector2();
        this.width = 0;
        this.height = 0;
        this.type = "";
        this.sprite = null;
    }

    protected Entity(String texture, float x, float y, String type) {
        this.texture = new Texture(Gdx.files.internal(texture));
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getWidth();
        this.height = this.texture.getHeight();
        this.type = type;
        this.sprite = new Sprite(this.texture, (int) this.width, (int) this.height);
    }

    protected Entity(float x, float y, String type) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = 0;
        this.height = 0;
        this.type = type;
        this.sprite = null;
    }

    protected Entity(float x, float y) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getWidth();
        this.height = this.texture.getHeight();
        this.type = "";
        this.sprite = null;
    }

    protected Entity(String texture) {
        this.texture = new Texture(Gdx.files.internal(texture));
        this.vector2 = new Vector2();
        this.width = this.texture.getWidth();
        this.height = this.texture.getHeight();
        this.type = "";
        this.sprite = new Sprite(this.texture, (int) this.width, (int) this.height);
    }

    protected void update() {
    }

    public abstract void collide(Collider other);

    protected void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.vector2.x, this.vector2.y);
    }

    public Texture getTexture() {
        return texture;
    }

    protected float getX() {
        return this.vector2.x;
    }

    protected float getY() {
        return this.vector2.y;
    }

    protected Vector2 getVector2() {
        return this.vector2;
    }

    protected float getWidth() {
        return this.width;
    }

    protected float getHeight() {
        return this.height;
    }

    public String getType() {
        return this.type;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    protected void setTexture(String texture) {
        this.texture = null;
        this.texture = new Texture(Gdx.files.internal(texture));
        setWidth(this.texture.getWidth());
        setHeight(this.texture.getHeight());
        this.sprite = null;
        this.sprite = new Sprite(this.texture, (int) this.width, (int) this.height);
    }

    protected void setX(float x) {
        this.vector2.x = x;
        this.sprite.setX(x);
    }

    protected void setY(float y) {
        this.vector2.y = y;
        this.sprite.setY(y);
    }

    public void setPosition(Vector2 vector2) {
        this.vector2 = vector2;
        this.sprite.setPosition(vector2.x, vector2.y);
    }

    protected void setPosition(float x, float y) {
        this.vector2 = null; // Release previous object
        this.vector2 = new Vector2(x, y);
    }

    protected void setWidth(float width) {
        this.width = width;
        this.sprite.setSize(width, this.height);
    }

    protected void setHeight(float height) {
        this.height = height;
        this.sprite.setSize(this.width, height);
    }

    protected void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        this.sprite.setSize(width, height);
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected void dispose() {
        EntityDisposedEvent.addDisposedEvent(new EntityDisposedEvent(this));
        sprite.getTexture().dispose();
        this.texture.dispose();
    }
}
