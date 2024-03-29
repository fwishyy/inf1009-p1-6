package com.mygdx.entity.fsm.states.characters;

import com.mygdx.engine.physics.Collider;
import com.mygdx.entity.Character;
import com.mygdx.entity.fsm.State;

public abstract class CharacterState extends State {
    protected Character character;
    protected CharacterStateMachine stateMachine;

    public CharacterState(Character character, CharacterStateMachine stateMachine) {
        this.character = character;
        this.stateMachine = stateMachine;
    }

    public void onCollisionEnter(Collider collider) {

    }
}
