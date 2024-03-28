package com.mygdx.entity;

import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyMeleeAttackState;

public class SkeletonWarrior extends Enemy {
    private EnemyMeleeAttackState attackState;

    public SkeletonWarrior() {
        super("characters/Skeleton_Warrior/Idle.png", 0, 0, "skeletonWarrior", 1, 7, 0.1f);
        this.addAnimation("characters/Skeleton_Warrior/Run.png", "run", 8);
        this.addAnimation("characters/Skeleton_Warrior/Attack_2.png", "attack", 6);
        this.addAnimation("characters/Skeleton_Warrior/Hurt.png", "hurt", 2);
        this.addAnimation("characters/Skeleton_Warrior/Dead.png", "die", 4);
        this.maxHp = 100;
        this.currentHp = 100;
        this.speed = 100;
        this.strikingDistance = 30;
        this.damage = 20;

        this.attackState = new EnemyMeleeAttackState(this, stateMachine);
        stateMachine.addState(CharacterStateEnum.ATTACK, attackState);
    }
}
