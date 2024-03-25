package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;

public abstract class IdleState extends CharacterState {
    public IdleState(Character character) {
        super(character);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("default");
    }
}