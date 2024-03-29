package com.mygdx.entity;

import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyMeleeAttackState;

public class Yokai extends Enemy {

    private EnemyMeleeAttackState attackState;

    public Yokai() {
        super("characters/Yokai_Yamabushi_Tengu/Idle.png", 0, 0, "yokai", 1, 6, 0.1f);
        this.addAnimation("characters/Yokai_Yamabushi_Tengu/Run.png", "run", 8);
        this.addAnimation("characters/Yokai_Yamabushi_Tengu/Attack_1.png", "attack", 3);
        this.addAnimation("characters/Yokai_Yamabushi_Tengu/Hurt.png", "hurt", 3);
        this.addAnimation("characters/Yokai_Yamabushi_Tengu/Dead.png", "die", 6);

        this.attackState = new EnemyMeleeAttackState(this, stateMachine);
        stateMachine.addState(CharacterStateEnum.ATTACK, attackState);

        this.currentHp = 300;
        this.maxHp = 300;
        this.damage = 50;
        this.speed = 100;
        this.strikingDistance = 30;
    }
}
