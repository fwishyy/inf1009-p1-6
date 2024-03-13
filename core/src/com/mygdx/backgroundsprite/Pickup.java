package com.mygdx.backgroundsprite;

import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pickup extends Entity {

	private String potionType;

    public Pickup(Vector2 position, String potionType) {
        super(potionType.equals("healthPotion") ? "sprite/health-potion.gif" : "sprite/max-hp-potion.png", position.x, position.y, potionType);
        this.potionType = potionType;
    }
    
    public Pickup(String texture, float x, float y, String potionType) {
    	super(texture, x, y, potionType);
    }

    @Override
    public void draw(SpriteBatch batch) {
        // draw the pickup item
        super.draw(batch);
    }

	@Override
	public void collide(Collider other) {
		if (other.getEntity().getType().equals("player1")) {
            this.dispose();
        }
	}

}
