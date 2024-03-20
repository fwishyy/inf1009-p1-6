package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.player.HealthBar;

// Characters are generic animated entities for all on-screen things, like characters and monsters that have a health bar, the ability to take damage, and so on
public class Character extends AnimatedEntity {

    public HealthBar healthBar;
    protected float maxHp;
    protected float currentHp;
    Pickup potion;

    public Character (String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.maxHp = 100;
        this.currentHp = 100;
        Vector2 positionOffset = new Vector2(38, -20);
        healthBar = new HealthBar(this, positionOffset, Color.RED, 80, 10);
    }

    public void draw(SpriteBatch batch) {
        super.draw(batch);
        healthBar.update(maxHp, currentHp);
        healthBar.draw(batch);
    }

    @Override
    public void collide(Collider other) {
        // TODO Auto-generated method stub
        if (other.getEntity().getType().equals("player1")) {
            if (this.currentHp >= 5) {
                this.currentHp -= 5;
            }
        }
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

    // TODO: remove this and move it to an enemy
    // potion drop logic
    public void potionDrop() {
        // check for a 1/5 chance to drop a potion
        if (MathUtils.random(1, 5) == 1) {
            // decide which potion to drop (50% chance each)
            boolean dropHealthPotion = MathUtils.randomBoolean();

            if (dropHealthPotion) {
                // create a health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "healthPotion");
            } else {
                // create a max health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "maxHealthPotion");
            }

            // GameScene -> addEntity(potion);
        }
    }

    @Override
    public void update() {
        super.update();
        // check if enemy isDead
        if (isDead()) {
            this.potionDrop();
            this.dispose();
        }
    }
}
