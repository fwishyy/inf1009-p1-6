package com.mygdx.entity.fsm.states;

import com.mygdx.entity.Character;
import com.mygdx.entity.fsm.StateMachine;

public class CharacterStateMachine extends StateMachine {
    protected Character character;
    protected IdleState idleState;
    protected AttackState attackState;
    protected RunState runState;
    protected HurtState hurtState;

    public CharacterStateMachine(Character character, IdleState idleState, RunState runState, AttackState attackState, HurtState hurtState) {
        this.character = character;
        this.idleState = idleState;
        this.runState = runState;
        this.attackState = attackState;
        this.hurtState = hurtState;
        idleState.setStateMachine(this);
        runState.setStateMachine(this);
        attackState.setStateMachine(this);
        hurtState.setStateMachine(this);
    }

    public void setIdleState() {
        changeState(idleState);
    }

    public void setRunState() {
        changeState(runState);
    }

    public void setAttackState() {
        changeState(attackState);
    }

    public void setHurtState() {
        changeState(hurtState);
    }
}
