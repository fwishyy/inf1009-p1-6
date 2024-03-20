package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;
import com.mygdx.player.HealthBar;


public class Player extends Character {

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
        this.maxHp = 100;
        this.currentHp = 50;
        Vector2 healthbarOffset = new Vector2(38, -20);
        this.healthBar = new HealthBar(this, healthbarOffset, Color.GREEN, 80, 10);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch); 
        healthBar.update(maxHp, currentHp);
    }


    @Override
    public void collide(Collider other) {
//        if (other.getEntity().getType().equals("player1")) {
//            LoseEvent.addEvent(new LoseEvent());
//            this.dispose();
//        }
//        if (other.getEntity().getType().equals("player2")) {
//            WinEvent.addEvent(new WinEvent());
//            this.dispose();
//        }
        if (other.getEntity().getType().equals("maxHealthPotion")) {
            this.increaseMaxHp();
        }
        if (other.getEntity().getType().equals("healthPotion")) {
        	this.heal();
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
    
    // when player picks up increase max HP potion, it will increase by 5%
    // to be included in potion collisionevent
    public void increaseMaxHp() {
        maxHp += maxHp * (5.0f / 100.0f);
    }
    
    public void takeDamage() {
        currentHp -= 3;
        if (currentHp < 0) currentHp = 0;
    }

    // when player picks up healing potion, health will +10
    public void heal() {
        currentHp += 10;
        if (currentHp > maxHp) currentHp = maxHp;
    }
    
}
