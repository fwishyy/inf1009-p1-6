package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class HealthBar extends Entity {
	
	/* maxHp: maximum health points of player
	 * currentHp: current health points of player
	 * barWidth: width of health bar (fixed)
	 * barHeight: height of health bar (fixed)
	 */
	
    private float maxHp;
    private float currentHp;
    private float barWidth;
    private float barHeight;
    private ShapeRenderer shapeRenderer;

    public HealthBar(float barWidth, float barHeight) {
        this.maxHp = 100; // base Max HP
        this.currentHp = 100; // starting current HP
        this.barWidth = barWidth;
        this.barHeight = barHeight;
        this.shapeRenderer = new ShapeRenderer();
        
        // set initial position of bar to top right corner of screen
        setPosition(Gdx.graphics.getWidth() - barWidth, Gdx.graphics.getHeight() - barHeight);
    }

    @Override
    protected void draw(SpriteBatch batch) {
        // end the SpriteBatch to use ShapeRenderer
        batch.end();
        
        // Draw the background of the health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(getX(), getY(), barWidth, barHeight);

        // Calculate the width of the current health
        float healthWidth = barWidth * (currentHp / maxHp);

        // Draw the current health
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getX(), getY(), healthWidth, barHeight);
        shapeRenderer.end();

        // Start the SpriteBatch again for other entities
        batch.begin();
    }

    // when player picks up increase max HP potion, it will increase by 5%
    // to implement collisionevent for this
    public void increaseMaxHp() {
        maxHp += maxHp * (5.0f / 100.0f);
    }

    // when lv 1 mob attacks player, default decrease value is 3
    // need to implement collisionevent where mob attacks player
    public void decreaseHp1() {
        currentHp -= 3;
        if (currentHp < 0) {
            currentHp = 0;
        }
    }
    
    // when lv 2 mob attacks player, default decrease value is 5
    // need to implement collisionevent where mob attacks player
    public void decreaseHp2() {
        currentHp -= 5;
        if (currentHp < 0) {
            currentHp = 0;
        }
    }
    
    // when boss basic attack hits player, default decrease value is 8
    /* to implement control flow where if collisionevent is boss basic/special skill, then
       basic attack -8hp, special attack -15hp
     */
    public void decreaseHpBoss() {
        currentHp -= 8;
        if (currentHp < 0) {
            currentHp = 0;
        }
    }

    // for when player picks up health potion, need to implement collisionevent
    // function to return int heal to be input to increaseHp
    public void increaseHp(int heal) {
        currentHp += heal;
        if (currentHp > maxHp) {
            currentHp = maxHp;
        }
    }

	@Override
	public void collide(Collider other) {
		// todo - not sure how to use this for healthbar
		
	}

	// getter
	public float getHp() {
		return this.currentHp;
	}
	
}
