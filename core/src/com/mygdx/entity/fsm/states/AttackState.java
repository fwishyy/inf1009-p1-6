package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;

public abstract class AttackState extends CharacterState {
    public AttackState(Character character) {
        super(character);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
    }
}
