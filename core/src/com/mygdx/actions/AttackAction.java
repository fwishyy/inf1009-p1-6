package com.mygdx.actions;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.actions.TemporalAction;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.player.Player;
import com.mygdx.projectiles.Fireball;

public class AttackAction extends TemporalAction {

    // TODO: fix a bug where too many attack commands are generated per click
    public AttackAction() {
        setInterval(150);
    }
    @Override
    public boolean act() {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            Fireball fireball = new Fireball();
            Vector2 origin = player.getVector2().cpy();
            // set the origin to the center of the player
            origin.x += player.getWidth() / 2;
            origin.y += player.getHeight() / 2;
            fireball.setPosition(origin);

            // calculate the target direction using the Player's target vector
            // which represents where the cursor is aiming
            Vector2 target = player.getTarget();

            if(target != null) {
                Vector2 direction = new Vector2(target.cpy().sub(origin).nor());
                fireball.setDirection(direction);

                EntityAddedEvent.addEvent(new EntityAddedEvent(fireball));
            }
        }
        return true;
    }
}
