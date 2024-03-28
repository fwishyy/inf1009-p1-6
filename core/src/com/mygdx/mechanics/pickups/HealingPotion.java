package com.mygdx.mechanics.pickups;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Player;

public class HealingPotion extends Pickup {
    public HealingPotion(Vector2 position) {
        super(position);
        setTextureRegion("pickups/health_potion.png");
    }

    public void activate(Player player) {
        player.heal(20);
    }
}
