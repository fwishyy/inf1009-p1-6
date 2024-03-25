package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;

public abstract class RunState extends CharacterState {
    public RunState(Character character) {
        super(character);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("run");
    }
}
