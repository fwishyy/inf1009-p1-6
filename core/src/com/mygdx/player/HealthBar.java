package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public HealthBar() {
        this.maxHp = 100; // base Max HP set to 100
        this.currentHp = 100; // starting current HP also set to 100
        this.barWidth = 200; // initialise bar width
        this.barHeight = 25; // initalise bar height
        this.shapeRenderer = new ShapeRenderer();
        
        this.font = new BitmapFont(); // libgdx default arial font
        this.font.setColor(Color.WHITE); // set font color to white
        this.font.getData().setScale(1); // set scale of font if needed
        
        // set initial position of bar to top left corner of screen
        setPosition(10, Gdx.graphics.getHeight() - barHeight - 10);

    }

    @Override
    protected void draw(SpriteBatch batch) {
        // end the SpriteBatch to use ShapeRenderer
        batch.end();
        
        // draw background of health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(getX(), getY(), barWidth, barHeight);

        // calculate width of current health
        float healthWidth = barWidth * (currentHp / maxHp);
        // draw current health
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getX(), getY(), healthWidth, barHeight);
        shapeRenderer.end();

        // start SpriteBatch again for other entities
        batch.begin();
    }

    // when player picks up increase max HP potion, it will increase by 5%
    // to be included in potion collisionevent
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

    // for when player picks up health potion
    // to be included in potion collisionevent
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
