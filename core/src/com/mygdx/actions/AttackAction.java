package com.mygdx.actions;

import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.entity.Entity;
import com.mygdx.player.Player;

public class AttackAction extends GameAction {
    @Override
    public boolean act(Entity entity) {
        if(entity instanceof Player) {
            Player player = (Player)entity;
            // TODO: create pew pew here
        }

        return true;
    }
}
