package com.mygdx.backgroundsprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;
import com.mygdx.player.HealthBar;

public class BGSprite extends AnimatedEntity {
	
	private HealthBar healthBar;
	private float maxHp;
	private float currentHp;

    public BGSprite(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.maxHp = 20;
        this.currentHp = 20;
        healthBar = new HealthBar(new Vector2(38, 10));
    }
    
    public void draw(SpriteBatch batch) {
        super.draw(batch); 
        healthBar.update(maxHp, currentHp);
        healthBar.draw(batch, getX(), getY()); 
    }

    @Override
    public void collide(Collider other) {
        // TODO Auto-generated method stub
        if (other.getEntity().getType().equals("player1")) {
            
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
    
}


