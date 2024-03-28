package com.mygdx.entity.fsm.states.characters.player;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.actions.InputAction;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.entity.Character;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class PlayerHurtState extends CharacterState {
    float animationDuration;
    float stateEnterTime;

    public PlayerHurtState(Character character, CharacterStateMachine stateMachine) {
        super(character, stateMachine);
    }

    @Override
    public void onStateEnter() {
        stateEnterTime = 0;
        character.setAnimation("hurt");
        animationDuration = character.getAnimationDuration();
    }

    @Override
    public void update() {
        if (stateEnterTime >= animationDuration) {
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

            stateMachine.setState(CharacterStateEnum.IDLE);
        }
        stateEnterTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public void onStateExit() {

    }
}
