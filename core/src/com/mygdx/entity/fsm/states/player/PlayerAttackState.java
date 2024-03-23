package com.mygdx.entity.fsm.states.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.Player;
import com.mygdx.entity.Player;
import com.mygdx.projectiles.Fireball;

public class PlayerAttackState extends PlayerState {

    private float animationDuration;
    private float attackTimer;

    public PlayerAttackState(Player player, PlayerStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        player.setAnimation("attack");
        animationDuration = player.getAnimationDuration();
    }

    @Override
    public void update() {
        ActionMap actionMap = player.getActionMap();
        InputAction attackAction = actionMap.getAction("attack");

        if (attackAction.isTriggered()) {
            attackTimer += Gdx.graphics.getDeltaTime();
            if (attackTimer >= animationDuration) {
                Vector2 origin = player.getVector2().cpy();
                origin.x += player.getWidth() / 2f;
                origin.y += player.getWidth() / 2f;

                // calculate the target direction using the Player's target vector
                // which represents where the cursor is aiming
                Vector2 target = player.getTarget();

                if (target != null) {
                    Vector2 direction = target.cpy().sub(origin);
                    Fireball fireball = new Fireball("projectiles/fireball.png", origin.x, origin.y, "fireball", 1, 40, 0.05f);
                    fireball.setX(fireball.getX() - fireball.getWidth() / 2f);
                    fireball.setY(fireball.getY() - fireball.getHeight() / 2f);
                    fireball.setDirection(direction.nor());
                    EntityAddedEvent.addEvent(new EntityAddedEvent(fireball));
                    attackTimer = 0;
                }
            }
        } else {
            attackTimer = 0;
            stateMachine.setIdleState();
        }
    }

    @Override
    public void onStateExit() {

    }
}