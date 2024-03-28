package com.mygdx.entity.fsm.states.characters.enemy;

import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class EnemyIdleState extends CharacterState {
    public EnemyIdleState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("default");
    }
    @Override
    public void update() {

    }
}
