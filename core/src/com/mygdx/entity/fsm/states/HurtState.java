package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;

public abstract class HurtState extends CharacterState {

    public HurtState(Character character) {
        super(character);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("hurt");
    }
}
