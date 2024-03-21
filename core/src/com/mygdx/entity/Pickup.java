package com.mygdx.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pickup extends Entity {

	private String potionType;

    public Pickup(Vector2 position, String potionType) {
        super(potionType.equals("healthPotion") ? "sprite/health_potion.png" : "sprite/max_hp_potion.png", position.x, position.y, potionType);
        this.potionType = potionType;
    }
    
    public Pickup(String texture, float x, float y, String potionType) {
    	super(texture, x, y, potionType);
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        // draw the pickup item
        super.draw(batch, shapeRenderer);
    }

	@Override
	public void collide(Collider other) {
		System.out.println("Collision detected with: " + other.getEntity().getType());
	    if (other.getEntity().getType().equals("player1")) {
	        System.out.println("Potion collected by the player.");
	        this.dispose();
        }
	}

}
