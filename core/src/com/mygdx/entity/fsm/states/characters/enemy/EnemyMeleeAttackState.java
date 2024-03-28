package com.mygdx.entity.fsm.states.characters.enemy;

import com.badlogic.gdx.Gdx;
import com.mygdx.entity.Character;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;

public class EnemyMeleeAttackState extends CharacterState {

    float animationDuration;
    float attackTimer;

    public EnemyMeleeAttackState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("attack");
        animationDuration = character.getAnimationDuration();
    }

    @Override
    public void update() {
        Enemy enemy = (Enemy) character;
        Character target = enemy.getTarget();
        attackTimer += Gdx.graphics.getDeltaTime();
        if (attackTimer >= animationDuration) {
            target.takeDamage(enemy.getDamage());
            attackTimer = 0;
        }
    }
}
