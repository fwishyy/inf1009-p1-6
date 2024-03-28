package com.mygdx.entity.fsm.states.characters.player;

import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class PlayerIdleState extends CharacterState {
    public PlayerIdleState(Player player, CharacterStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("default");
    }

    @Override
    public void update() {
        Player player = (Player) character;
        ActionMap actionMap = player.getActionMap();
        MoveAction moveAction = actionMap.getMoveAction();
        InputAction attackAction = actionMap.getAction("attack");
        if (moveAction.isTriggered()) {
            stateMachine.setState(CharacterStateEnum.RUN);
        }

        if (attackAction.isTriggered()) {
            stateMachine.setState(CharacterStateEnum.ATTACK);
        }
    }

    @Override
    public void onStateExit() {

    }

    @Override
    public void onCollisionEnter(Collider other) {

    }
}
