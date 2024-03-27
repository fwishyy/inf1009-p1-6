package com.mygdx.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.entity.fsm.states.CharacterStateEnum;
import com.mygdx.entity.fsm.states.enemy.EnemyDeathState;
import com.mygdx.entity.fsm.states.enemy.EnemyHurtState;
import com.mygdx.entity.fsm.states.enemy.EnemyIdleState;
import com.mygdx.entity.fsm.states.enemy.EnemyRunState;

public class Enemy extends Character {

    protected Character target;
    protected EnemyIdleState idleState;
    protected EnemyRunState runState;
    protected EnemyHurtState hurtState;
    protected EnemyDeathState deathState;

    // Enemy specific stats
    protected float strikingDistance;

    public Enemy(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);

        idleState = new EnemyIdleState(this, stateMachine);
        runState = new EnemyRunState(this, stateMachine);
        hurtState = new EnemyHurtState(this, stateMachine);
        deathState = new EnemyDeathState(this, stateMachine);

        stateMachine.addState(CharacterStateEnum.IDLE, idleState);
        stateMachine.addState(CharacterStateEnum.RUN, runState);
        stateMachine.addState(CharacterStateEnum.HURT, hurtState);
        stateMachine.addState(CharacterStateEnum.DIE, deathState);

        stateMachine.setState(CharacterStateEnum.IDLE);
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
        return (CharacterStateEnum) stateMachine.getCurrentState();
    }

    public void move() {
        stateMachine.setState(CharacterStateEnum.RUN);
    }

    public void attack() {
        stateMachine.setState(CharacterStateEnum.ATTACK);
    }

    public void potionDrop() {
        // check for a 1/5 chance to drop a potion
        if (MathUtils.random(1, 5) == 1) {
            // decide which potion to drop (50% chance each)
            boolean dropHealthPotion = MathUtils.randomBoolean();

            if (dropHealthPotion) {
                // create a health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "healthPotion");
                EntityAddedEvent.addEvent(new EntityAddedEvent(potion));
            } else {
                // create a max health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "maxHealthPotion");
                EntityAddedEvent.addEvent(new EntityAddedEvent(potion));
            }

            // GameScene -> addEntity(potion);
        }
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
