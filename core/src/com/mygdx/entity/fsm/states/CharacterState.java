package com.mygdx.entity.fsm.states;

import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.Character;
import com.mygdx.entity.fsm.State;

public abstract class CharacterState extends State {
    protected Character character;
    protected CharacterStateMachine stateMachine;

    public CharacterState(Character character) {
        this.character = character;
    }

    public void setStateMachine(CharacterStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void onCollisionEnter(Collider collider) {

    }
}
