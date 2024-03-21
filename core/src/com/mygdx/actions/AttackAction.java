package com.mygdx.actions;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.TemporalAction;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.Player;
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
            Vector2 origin = player.getVector2().cpy();
            origin.x += player.getWidth() / 2f;
            origin.y += player.getWidth() / 2f;
            Fireball fireball = new Fireball("projectiles/fireball.png", origin.x, origin.y, "fireball", 1, 40, 0.05f);

            // TODO: maybe this still needs to be reworked more, but the aiming feels kinda jank
            // calculate the target direction using the Player's target vector
            // which represents where the cursor is aiming
            Vector2 target = player.getTarget();

            if(target != null) {
                Vector2 direction = target.cpy().sub(origin).nor();
                fireball.setDirection(direction);
                EntityAddedEvent.addEvent(new EntityAddedEvent(fireball));
            }
        }
        return true;
    }
}
