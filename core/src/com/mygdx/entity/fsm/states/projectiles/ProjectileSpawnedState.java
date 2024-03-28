package com.mygdx.entity.fsm.states.projectiles;

import com.mygdx.entity.projectiles.Projectile;
import com.mygdx.entity.projectiles.ProjectileStateEnum;
import com.mygdx.entity.projectiles.ProjectileStateMachine;

public class ProjectileSpawnedState extends ProjectileState {

    public ProjectileSpawnedState(Projectile projectile, ProjectileStateMachine stateMachine) {
        super(projectile, stateMachine);
    }

    @Override
    public void onStateEnter() {
        projectile.setAnimation("default");
    }

    @Override
    public void update() {
        stateMachine.setState(ProjectileStateEnum.MOVING);
    }
}
