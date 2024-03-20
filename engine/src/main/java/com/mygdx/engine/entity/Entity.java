package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.Actionable;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.actions.TemporalAction;

import java.util.ArrayList;
import java.util.Map;

public abstract class Entity implements Actionable {

    protected Sprite sprite;
    protected TextureRegion texture;
    protected Vector2 vector2;
    protected float width;
    protected float height;
    protected String type;
    protected ArrayList<GameAction> actions;
    protected Map<Class<? extends GameAction>, Float> actionInterval;

    protected Entity() {
        this.texture = null;
        this.vector2 = new Vector2();
        this.width = 0;
        this.height = 0;
        this.type = "";
        this.sprite = null;
        this.actions = new ArrayList<>();
    }

    protected Entity(String texture, float x, float y, String type) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = type;
        this.sprite = new Sprite(this.texture);
        this.actions = new ArrayList<>();
    }

    protected Entity(float x, float y, String type) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = 0;
        this.height = 0;
        this.type = type;
        this.sprite = null;
        this.actions = new ArrayList<>();
    }

    protected Entity(float x, float y) {
        this.texture = null;
        this.vector2 = new Vector2(x, y);
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = null;
        this.actions = new ArrayList<>();
    }

    protected Entity(String texture) {
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        this.vector2 = new Vector2();
        this.width = this.texture.getRegionWidth();
        this.height = this.texture.getRegionHeight();
        this.type = "";
        this.sprite = new Sprite(this.texture);
        this.actions = new ArrayList<>();
    }

    public void addAction(GameAction newAction) {
        if (newAction instanceof TemporalAction) {
            for (GameAction action : actions) {
                if (action.getClass().equals(newAction.getClass())) {
                    return;
                }
            }
        }
        actions.add(newAction);
    }

    public void update() {
        for (int i = 0; i < actions.size(); ++i) {
            GameAction currAction = actions.get(i);
            if (currAction instanceof TemporalAction) {
                TemporalAction temporalAction = (TemporalAction) currAction;
                boolean completed = temporalAction.update();
                if (completed) {
                    actions.remove(temporalAction);
                }
            } else {
                currAction.act();
                actions.remove(currAction);
            }
        }
    }

    public abstract void collide(Collider other);

    protected void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.vector2.x, this.vector2.y);
    }

    public TextureRegion getTextureRegion() {
        return texture;
    }

    public void setTextureRegion(String texture) {
        this.texture.getTexture().dispose();
        this.texture = new TextureRegion(new Texture(Gdx.files.internal(texture)));
        setWidth(this.texture.getRegionWidth());
        setHeight(this.texture.getRegionHeight());
        this.sprite = null;
        this.sprite = new Sprite(this.texture);
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

    public void dispose() {
        EntityDisposedEvent.addEvent(new EntityDisposedEvent(this));
        this.sprite.getTexture().dispose();
        this.texture.getTexture().dispose();
    }
}
