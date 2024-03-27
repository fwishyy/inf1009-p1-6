package com.mygdx.entity.fsm.states.enemy;

import com.mygdx.entity.Character;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.CharacterState;
import com.mygdx.entity.fsm.states.CharacterStateMachine;

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
