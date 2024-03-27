package com.mygdx.entity.fsm.states.enemy;

import com.badlogic.gdx.Gdx;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.CharacterState;
import com.mygdx.entity.fsm.states.CharacterStateEnum;
import com.mygdx.entity.fsm.states.CharacterStateMachine;

public class EnemyHurtState extends CharacterState {
    float animationDuration;
    float animationTimer;

    public EnemyHurtState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("hurt");
        animationDuration = character.getAnimationDuration();
    }

    @Override
    public void update() {
        animationTimer += Gdx.graphics.getDeltaTime();
        if(animationTimer >= animationDuration) {
            stateMachine.setState(CharacterStateEnum.IDLE);
        }
    }
}
