package com.mygdx.entity.fsm.states.player;

import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.CharacterStateMachine;
import com.mygdx.entity.fsm.states.IdleState;

public class PlayerIdleState extends IdleState {
    public PlayerIdleState(Player player) {
        super(player);
    }

    @Override
    public void update() {
        Player player = (Player) character;
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
