package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.events.EnemyDefeatedEvent;
import com.mygdx.projectiles.Fireball;

public class Enemy extends Character {
	
	EventListener<EnemyDefeatedEvent> winEventListener;
	
    public Enemy(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.currentHp = 20;
        this.maxHp = 20;
    }

    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage);
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
//                this.takeDamage(5, new Vector2(getX(), getY())); // Apply damage
                this.takeDamage(5);
                this.showMessage("fuck you", 2.0f, Color.YELLOW);
                if (this.currentHp <= 0) {
                    potionDrop(); // Drop potion if enemy is defeated
                    this.dispose();
                    //TODO: add enemy defeated event to update enemy count
                    System.out.println("Enemy defeated.");
                }
            }
        }
    }
}
