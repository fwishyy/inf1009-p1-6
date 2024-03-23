package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.projectiles.Fireball;

public class Enemy extends Character {

    public Enemy(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.currentHp = 20;
        this.maxHp = 20;
        this.addAnimation("characters/Skeleton_Warrior/Hurt.png", "hurt", 2);
    }
    
    @Override
    public void takeDamage(int damage, Vector2 position) {
    	super.takeDamage(damage, position);
        this.setAnimation("hurt", 1);
    }
    
    public int getCurrentFrame() {
        return this.currentFrame;
    }

    public void potionDrop() {
        // check for a 1/5 chance to drop a potion
        if (MathUtils.random(1, 5) == 1) {
            // decide which potion to drop (50% chance each)
            boolean dropHealthPotion = MathUtils.randomBoolean();

            if (dropHealthPotion) {
                // create a health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "healthPotion");
                EntityAddedEvent.addEvent(new EntityAddedEvent(potion));
            } else {
                // create a max health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "maxHealthPotion");
                EntityAddedEvent.addEvent(new EntityAddedEvent(potion));
            }

            // GameScene -> addEntity(potion);
        }
    }
   

    @Override
    public void collide(Collider other) {
        // TODO Auto-generated method stub
        if (other.getEntity().getType().equals("fireball")) {
        	Fireball fireball = (Fireball) other.getEntity();
        	if (!fireball.hasHit()) { // Check if the fireball has not already hit
                fireball.completeHit(); // Prevent further damage by marking the fireball
                this.takeDamage(2, new Vector2(getX(), getY())); // Apply damage
                this.showMessage("-2", 2.0f, Color.MAGENTA);
                if (this.currentHp <= 0) {
                	potionDrop(); // Drop potion if enemy is defeated
                	this.dispose();      
                    System.out.println("Enemy defeated.");
                }
            }
        }
    }
}
