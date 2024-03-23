package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.Actionable;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.fsm.states.player.PlayerStateMachine;
import com.mygdx.ui.HealthBar;

// Characters are generic animated entities for all on-screen things, like characters and monsters that have a health bar, the ability to take damage, and so on
public class Character extends AnimatedEntity implements Actionable {

    public HealthBar healthBar;
    protected float maxHp;
    protected float currentHp;
    Pickup potion;
    ActionMap actionMap;

    public Character(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.maxHp = 100;
        this.currentHp = 100;
        Vector2 healthBarPositionOffset = new Vector2(38, -20);
        healthBar = new HealthBar(this, healthBarPositionOffset, Color.RED, 80, 10);
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.draw(batch, shapeRenderer);
        healthBar.update(maxHp, currentHp);
        healthBar.draw(batch, shapeRenderer);
    }

    public ActionMap getActionMap() {
        return actionMap;
    }

    public void setActionMap(ActionMap actionMap) {
        this.actionMap = actionMap;
    }

    @Override
    public void collide(Collider other) {

    }

    public HealthBar getHbar() {
        return healthBar;
    }

    public float getMaxHp() {
        return this.maxHp;
    }

    public float getCurrentHp() {
        return this.currentHp;
    }

    public void takeDamage() {
        currentHp -= 5;
        if (currentHp < 0) currentHp = 0;
    }

    public boolean isDead() {
        return currentHp <= 0;
    }

    @Override
    public void update() {
        super.update();
        // check if enemy isDead
        if (isDead()) {
            this.dispose();
        }
    }
}
