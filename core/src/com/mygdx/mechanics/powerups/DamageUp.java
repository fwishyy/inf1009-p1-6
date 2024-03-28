package com.mygdx.mechanics.powerups;

import com.mygdx.entity.Player;

public class DamageUp extends PowerUp {

    public DamageUp() {
        this.name = "+ Damage";
        this.description = "Power up skill allows player to increase their output damage to enemies by +10 damage";
    }

    @Override
    public void activate(Player player) {
        player.setDamage(player.getDamage() + 10);
    }
}
