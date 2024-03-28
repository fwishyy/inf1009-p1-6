package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    protected Sprite sprite;
    protected TextureRegion texture;
    protected Vector2 vector2;
    protected Vector2 previous_vector2;
    protected float width;
    protected float height;
    protected String type;
    protected boolean direction; //true for left, false for right
    protected Collider collider;

    protected Entity() {
        this.texture = null;
        this.vector2 = new Vector2();
        this.previous_vector2 = null;
        this.width = 0;
        this.height = 0;
        this.type = "";
        this.sprite = null;
        this.direction = false;
        this.collider = new Collider(this, width, height);
    }

    protected Entity(String texture, float x, float y, String type) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2(x, y);
        this.previous_vector2 = null;
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = type;
        this.sprite = new Sprite(this.texture);
        this.direction = false;
        this.collider = new Collider(this, width, height);
    }

    protected Entity(float x, float y, String type) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.previous_vector2 = null;
        this.width = 0;
        this.height = 0;
        this.type = type;
        this.sprite = null;
        this.direction = false;
        this.collider = new Collider(this, width, height);
    }

    protected Entity(float x, float y) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.previous_vector2 = null;
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = null;
        this.direction = false;
        this.collider = new Collider(this, width, height);
    }

    protected Entity(String texture) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2();
        this.previous_vector2 = null;
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = new Sprite(this.texture);
        this.direction = false;
        this.collider = new Collider(this, width, height);
    }

    public void update() {
        // TODO: transition this to FSM
    }


    public abstract void collide(Collider other);

    // Helper function to check for direction given previous position
    protected void updateDirection() {

        if (this.previous_vector2 == null) {
            this.direction = false;

        } else {
            float deltaX = vector2.x - previous_vector2.x;
            if (deltaX >= 0) {
                this.direction = false;
            } else {
                this.direction = true;
            }
        }

        this.previous_vector2 = this.vector2;
    }

    protected void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        batch.draw(texture, getX(), getY(), width, height);
        batch.end();
    }

    public TextureRegion getTextureRegion() {
        return texture;
    }

    public void setTextureRegion(String texture) {
        if (this.texture != null) {
            this.texture.getTexture().dispose();
        }
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.sprite = new Sprite(this.texture);
        setWidth(this.texture.getRegionWidth());
        setHeight(this.texture.getRegionHeight());
        collider.setWidth(width);
        collider.setHeight(height);
    }

    public float getX() {
        return this.vector2.x;
    }

    public void setX(float x) {
        this.vector2.x = x;
        this.sprite.setX(x);
    }

    public float getY() {
        return this.vector2.y;
    }

    public void setY(float y) {
        this.vector2.y = y;
        this.sprite.setY(y);
    }

    public Vector2 getVector2() {
        return this.vector2;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
        this.sprite.setSize(width, this.height);
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
        this.sprite.setSize(this.width, height);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public void setPosition(Vector2 vector2) {
        this.vector2 = vector2;
        this.sprite.setPosition(vector2.x, vector2.y);
    }

    public void setPosition(float x, float y) {
        this.vector2 = null; // Release previous object
        this.vector2 = new Vector2(x, y);
    }


    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        this.sprite.setSize(width, height);
    }

    public Collider getCollider() {
        return this.collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }

    public void dispose() {
        this.sprite.getTexture().dispose();
        this.texture.getTexture().dispose();
        System.out.println("dispose");
        EntityDisposedEvent.addEvent(new EntityDisposedEvent(this));
    }
}