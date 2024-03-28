package com.mygdx.mechanics.pickups;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Player;

public class IncreaseMaxHealthPotion extends Pickup {
    public IncreaseMaxHealthPotion(Vector2 position) {
        super(position);
        setTextureRegion("pickups/max_hp_potion.png");
    }

    public void activate(Player player) {
        player.increaseMaxHp(10);
    }
}
