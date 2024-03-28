package com.mygdx.entity.fsm.states.characters.enemy;

import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class EnemyRangedAttackState extends CharacterState {
    float animationDuration;

    public EnemyRangedAttackState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
    }

    @Override
    public void update() {
        System.out.println("archer shooting");
    }
}
