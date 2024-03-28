package com.mygdx.entity.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.Character;
import com.mygdx.entity.fsm.states.projectiles.ProjectileCollidedState;
import com.mygdx.entity.fsm.states.projectiles.ProjectileMovingState;
import com.mygdx.entity.fsm.states.projectiles.ProjectileSpawnedState;

import java.util.ArrayList;

public class Projectile extends AnimatedEntity {

    protected Character character;
    protected float speed;
    protected Vector2 direction;
    protected ProjectileStateMachine stateMachine;
    protected ProjectileSpawnedState spawnedState;
    protected ProjectileMovingState movingState;
    protected ProjectileCollidedState collidedState;
    // affectedTags stores a list of entity tags that this projectile can damage to prevent enemies from hitting one another
    protected ArrayList<String> affectedTags;

    public Projectile(Character character, String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.character = character;
        this.direction = new Vector2();
        this.affectedTags = new ArrayList<>();
        this.stateMachine = new ProjectileStateMachine(this);
        this.spawnedState = new ProjectileSpawnedState(this, stateMachine);
        this.movingState = new ProjectileMovingState(this, stateMachine);
        this.collidedState = new ProjectileCollidedState(this, stateMachine);
        stateMachine.addState(ProjectileStateEnum.SPAWNED, spawnedState);
        stateMachine.addState(ProjectileStateEnum.MOVING, movingState);
        stateMachine.addState(ProjectileStateEnum.COLLIDED, collidedState);

        stateMachine.setState(ProjectileStateEnum.SPAWNED);
    }

    public Character getCharacter() {
        return character;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
        setRotation(direction.angleDeg());
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void addAffectedTag(String tag) {
        this.affectedTags.add(tag);
    }

    public ArrayList<String> getAffectedTags() {
        return affectedTags;
    }

    @Override
    public void update() {
        super.update();
        stateMachine.update();
    }

    @Override
    public void collide(Collider other) {
        stateMachine.getCurrentState().onCollideEnter(other);
    }
}
