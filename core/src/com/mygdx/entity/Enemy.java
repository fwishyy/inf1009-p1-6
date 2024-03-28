package com.mygdx.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyDieState;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyHurtState;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyIdleState;
import com.mygdx.entity.fsm.states.characters.enemy.EnemyRunState;


public class Enemy extends Character {

    protected Character target;
    protected EnemyIdleState idleState;
    protected EnemyRunState runState;
    protected EnemyHurtState hurtState;
    protected EnemyDieState dieState;

    // Enemy specific stats
    protected float strikingDistance;

    public Enemy(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);

        idleState = new EnemyIdleState(this, stateMachine);
        runState = new EnemyRunState(this, stateMachine);
        hurtState = new EnemyHurtState(this, stateMachine);
        dieState = new EnemyDieState(this, stateMachine);

        stateMachine.addState(CharacterStateEnum.IDLE, idleState);
        stateMachine.addState(CharacterStateEnum.RUN, runState);
        stateMachine.addState(CharacterStateEnum.HURT, hurtState);
        stateMachine.addState(CharacterStateEnum.DIE, dieState);

        stateMachine.setState(CharacterStateEnum.IDLE);

        collider.setWidth(64);
        collider.setHeight(64);
        collider.setOffset(new Vector2(collider.getWidth() / 2, collider.getHeight() / 2));
    }

    public void setTarget(Character target) {
        this.target = target;
    }

    public Character getTarget() {
        return target;
    }

    public float getStrikingDistance() {
        return strikingDistance;
    }

    public CharacterStateEnum getState() {
        return (CharacterStateEnum) stateMachine.getCurrentStateEnum();
    }

    public void move() {
        stateMachine.setState(CharacterStateEnum.RUN);
    }

    public void attack() {
        stateMachine.setState(CharacterStateEnum.ATTACK);
    }

    @Override
    public void collide(Collider other) {
    }

    @Override
    public void update() {
        super.update();
        stateMachine.update();
    }
}