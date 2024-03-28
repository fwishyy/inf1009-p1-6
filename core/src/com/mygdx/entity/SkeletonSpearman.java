package com.mygdx.entity;

import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyMeleeAttackState;

public class SkeletonSpearman extends Enemy {
    private EnemyMeleeAttackState attackState;
    public SkeletonSpearman() {
        super("characters/Skeleton_Spearman/Idle.png", 0, 0, "skeletonWarrior", 1, 7, 0.1f);
        this.addAnimation("characters/Skeleton_Spearman/Run.png", "run", 6);
        this.addAnimation("characters/Skeleton_Spearman/Attack_1.png", "attack", 4);
        this.addAnimation("characters/Skeleton_Spearman/Hurt.png", "hurt", 3);
        this.addAnimation("characters/Skeleton_Spearman/Dead.png", "die", 5);

        this.maxHp = 120;
        this.currentHp = 120;
        this.strikingDistance = 60;
        this.damage = 20;
        this.speed = 100;

        this.attackState = new EnemyMeleeAttackState(this, stateMachine);
        stateMachine.addState(CharacterStateEnum.ATTACK, attackState);
    }
}
