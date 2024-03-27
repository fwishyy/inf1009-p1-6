package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;
import com.mygdx.entity.fsm.StateMachine;

public class CharacterStateMachine extends StateMachine {
    protected Character character;

    public CharacterStateMachine(Character character) {
        this.character = character;
    }
}
