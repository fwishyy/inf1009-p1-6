package com.mygdx.engine.physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class Collider {

    protected Rectangle rect;
    protected float x;
    protected float y;
    private boolean isCollidable;
    private Entity entity;
    private Vector2 offset;

    public Collider(Entity entity) {
        this.entity = entity;
        rect = new Rectangle(this.entity.getX(), this.entity.getY(), this.entity.getWidth(), this.entity.getHeight());
        this.isCollidable = true;
        offset = new Vector2();
    }

    public Collider(Entity entity, boolean isCollidable) {
        this.entity = entity;
        rect = new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        this.isCollidable = isCollidable;
        offset = new Vector2();
    }

    public Collider(Entity entity, float width, float height) {
        this.entity = entity;
        rect = new Rectangle(entity.getX(), entity.getY(), width, height);
        this.isCollidable = true;
        offset = new Vector2();
    }

    public void onCollide(Collider other) {
        CollisionEvent.addEvent(new CollisionEvent(this.entity, other.getEntity()));
    }

    public boolean isCollide(Collider other) {
        Rectangle _curr = this.rect;
        Rectangle _other = other.getRect();

        if (Intersector.overlaps(_curr, _other)) {
//            System.out.println("Collision detected");
            return true;
        }
        return false;
    }

    public Rectangle getRect() {
        return this.rect;
    }

    public boolean getIsCollidable() {
        return this.isCollidable;
    }

    public void setIsCollidable(boolean tf) {
        this.isCollidable = tf;
    }

    public float getX() {
        return this.rect.getX();
    }

    public void setX(float x) {
        this.rect.setX(x);
    }

    public float getY() {
        return this.rect.getY();
    }

    public void setY(float y) {
        this.rect.setY(y);
    }

    public float getWidth() {
        return this.rect.getWidth();
    }

    public void setWidth(float width) {
        this.rect.width = width;
    }

    public float getHeight() {
        return this.rect.getHeight();
    }

    public void setHeight(float height) {
        this.rect.height = height;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setPosition(Vector2 v2) {
        this.rect.setPosition(v2);
    }

    public Vector2 getOffset() {
        return this.offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public void drawCollider(ShapeRenderer shapeRenderer, Color color) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(this.rect.getX(), this.rect.getY(), this.rect.getWidth(), this.rect.getHeight()); // x, y, width, height
        shapeRenderer.end();
    }

    public void update() {
        this.rect.x = this.entity.getX() + this.offset.x;
        this.rect.y = this.entity.getY() + this.offset.y;
        //System.out.println("X: " + this.rect.x + " Y: " + this.rect.y);
    }

    public void dispose() {
        this.entity = null;
        this.rect = null;
    }
}
