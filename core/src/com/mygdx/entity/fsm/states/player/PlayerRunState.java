package com.mygdx.entity.fsm.states.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.AttackState;
import com.mygdx.entity.fsm.states.CharacterStateMachine;
import com.mygdx.entity.fsm.states.RunState;

public class PlayerRunState extends RunState {
    public PlayerRunState(Player player) {
        super(player);
    }

    @Override
    public void update() {
        Player player = (Player) character;
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

    @Override
    public void onCollisionEnter(Collider collider) {

    }
}
