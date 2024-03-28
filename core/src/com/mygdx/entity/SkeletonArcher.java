package com.mygdx.entity;

import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyRangedAttackState;

public class SkeletonArcher extends Enemy {

    EnemyRangedAttackState attackState;

    public SkeletonArcher() {
        super("characters/Skeleton_Archer/Idle.png", 0, 0, "skeletonArcher", 1, 7, 0.1f);
        this.addAnimation("characters/Skeleton_Archer/Walk.png", "run", 8);
        this.addAnimation("characters/Skeleton_Archer/Shot_2.png", "attack", 15);
        this.addAnimation("characters/Skeleton_Archer/Hurt.png", "hurt", 2);
        this.addAnimation("characters/Skeleton_Archer/Dead.png", "die", 5);
        this.maxHp = 50;
        this.currentHp = 50;
        this.speed = 80;
        this.strikingDistance = 200;
        this.damage = 60;

        this.attackState = new EnemyRangedAttackState(this, stateMachine);
        stateMachine.addState(CharacterStateEnum.ATTACK, attackState);
    }
}
