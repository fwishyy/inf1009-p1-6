package com.mygdx.entity.fsm.states.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.physics.Collider;
import com.mygdx.entity.Character;
import com.mygdx.entity.projectiles.Projectile;
import com.mygdx.entity.projectiles.ProjectileStateEnum;
import com.mygdx.entity.projectiles.ProjectileStateMachine;

import java.util.ArrayList;

public class ProjectileMovingState extends ProjectileState {
    public ProjectileMovingState(Projectile projectile, ProjectileStateMachine stateMachine) {
        super(projectile, stateMachine);
    }

    @Override
    public void onStateEnter() {
        projectile.setAnimation("moving");
    }

    @Override
    public void update() {
        Vector2 direction = projectile.getDirection();
        float newX = projectile.getX() + direction.x * projectile.getSpeed() * Gdx.graphics.getDeltaTime();
        float newY = projectile.getY() + direction.y * projectile.getSpeed() * Gdx.graphics.getDeltaTime();
        projectile.setPosition(newX, newY);
    }

    @Override
    public void onCollideEnter(Collider other) {
        ArrayList<String> affectedTags = projectile.getAffectedTags();
        if (affectedTags.contains(other.getEntity().getType())) {
            Character target = (Character) other.getEntity();
            target.takeDamage(projectile.getCharacter().getDamage());
            stateMachine.setState(ProjectileStateEnum.COLLIDED);
        }
    }
}
