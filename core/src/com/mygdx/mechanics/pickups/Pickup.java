package com.mygdx.mechanics.pickups;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.entity.Player;
import com.mygdx.events.PlaySFXEvent;

public abstract class Pickup extends Entity {

    private String potionType;

    public Pickup(Vector2 position) {
        super(position.x, position.y, "pickup");
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        // draw the pickup item
        super.draw(batch, shapeRenderer);
    }

    public abstract void activate(Player player);

    @Override
    public void collide(Collider other) {
        System.out.println("collider");
        if (other.getEntity().getType().equals("player1")) {
            Player player = (Player) other.getEntity();
            PlaySFXEvent.addEvent(new PlaySFXEvent("PotionPickUpFX"));
            activate(player);
            this.dispose();
        }
    }

}
