package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;

public abstract class AttackState extends CharacterState {
    public AttackState(Character character, CharacterStateMachine stateMachine) {
        super(character, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
    }
}
