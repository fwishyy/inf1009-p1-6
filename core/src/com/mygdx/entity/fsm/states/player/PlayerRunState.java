package com.mygdx.entity.fsm.states.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.entity.Player;

public class PlayerRunState extends PlayerState {
    public PlayerRunState(Player player, PlayerStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        player.setAnimation("run");
    }

    @Override
    public void update() {
        ActionMap actionMap = player.getActionMap();
        MoveAction moveAction = actionMap.getMoveAction();

        if (moveAction.isTriggered()) {
            Vector2 moveState = moveAction.readValue();
            if (moveState.x == -1) {
                player.setFlip(true);
            }
            if (moveState.x == 1) {
                player.setFlip(false);
            }
            float newX = player.getX() + moveState.x * 200 * Gdx.graphics.getDeltaTime();
            float newY = player.getY() + moveState.y * 200 * Gdx.graphics.getDeltaTime();

            player.setPosition(newX, newY);

            // Temporary workaround to move the target if the mouse isn't moved
            Vector2 target = player.getTarget();
            target.x = target.x + moveState.x * 200 * Gdx.graphics.getDeltaTime();
            target.y = target.y + moveState.y * 200 * Gdx.graphics.getDeltaTime();

        } else {
            stateMachine.setIdleState();
        }
    }

    @Override
    public void onStateExit() {

    }
}
