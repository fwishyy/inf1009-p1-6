package com.mygdx.entity.fsm.states.player;

import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.entity.Player;

public class PlayerIdleState extends PlayerState {
    public PlayerIdleState(Player player, PlayerStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        player.setAnimation("default");
    }

    @Override
    public void update() {
        ActionMap actionMap = player.getActionMap();
        MoveAction moveAction = actionMap.getMoveAction();
        InputAction attackAction = actionMap.getAction("attack");
        if (moveAction.isTriggered()) {
            stateMachine.setRunState();
        }

        if (attackAction.isTriggered()) {
            stateMachine.setAttackState();
        }
    }

    @Override
    public void onStateExit() {

    }
}
