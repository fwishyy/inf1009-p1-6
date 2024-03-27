package com.mygdx.entity.fsm.states.enemy;

import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.CharacterState;
import com.mygdx.entity.fsm.states.CharacterStateMachine;

public class EnemyDeathState extends CharacterState {

    public EnemyDeathState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("die", 1, true);
    }

    @Override
    public void update() {
        if (character.readyToDispose()) {
            character.dispose();
        }
    }
}
