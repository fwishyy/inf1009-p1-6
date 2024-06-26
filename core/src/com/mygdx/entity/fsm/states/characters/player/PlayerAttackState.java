package com.mygdx.entity.fsm.states.characters.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.entity.projectiles.Fireball;
import com.mygdx.events.PlaySFXEvent;

public class PlayerAttackState extends CharacterState {

    private float animationSpeedScale;
    private float animationDuration;
    private float attackTimer;

    public PlayerAttackState(Player player, CharacterStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
        animationDuration = character.getAnimationDuration();
        animationDuration *= (1 / character.getAttackSpeed());
    }

    @Override
    public void update() {
        Player player = (Player) character;
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
                Vector2 target = player.getCrosshairPosition();

                if (target != null) {
                    Vector2 direction = target.cpy().sub(origin);
                    Fireball fireball = new Fireball(player, "projectiles/fireball.png", origin.x, origin.y, "fireball", 1, 40, 0.05f);
                    fireball.setX(fireball.getX() - fireball.getWidth() / 2f);
                    fireball.setY(fireball.getY() - fireball.getHeight() / 2f);
                    fireball.setDirection(direction.nor());
                    EntityAddedEvent.addEvent(new EntityAddedEvent(fireball));
                    PlaySFXEvent.addEvent(new PlaySFXEvent("FireBallFX"));
                    attackTimer = 0;
                }
            }
        } else {
            attackTimer = 0;
            stateMachine.setState(CharacterStateEnum.IDLE);
        }
    }

    @Override
    public void onStateExit() {
        animationDuration /= (1 / character.getAttackSpeed());
    }
}
