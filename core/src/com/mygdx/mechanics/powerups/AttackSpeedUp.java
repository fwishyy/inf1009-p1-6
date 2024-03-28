package com.mygdx.mechanics.powerups;

import com.mygdx.entity.Player;

public class AttackSpeedUp extends PowerUp {
    public AttackSpeedUp() {
        this.name = "+ Attack Speed";
        this.description = "Increase attack speed, allowing player to clear waves faster";
    }

    @Override
    public void activate(Player player) {
        player.setAttackSpeed(player.getAttackSpeed() + 0.2f);
    }
}
