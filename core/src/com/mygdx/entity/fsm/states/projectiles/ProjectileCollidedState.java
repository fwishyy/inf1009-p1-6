package com.mygdx.entity.fsm.states.projectiles;

import com.mygdx.entity.projectiles.Projectile;
import com.mygdx.entity.projectiles.ProjectileStateMachine;

public class ProjectileCollidedState extends ProjectileState {
    public ProjectileCollidedState(Projectile projectile, ProjectileStateMachine stateMachine) {
        super(projectile, stateMachine);
    }

    @Override
    public void onStateEnter() {
        projectile.setAnimation("collided", 1, true);
    }

    @Override
    public void update() {
        if (projectile.readyToDispose()) {
            projectile.dispose();
        }
    }
}
