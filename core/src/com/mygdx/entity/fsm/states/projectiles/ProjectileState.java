package com.mygdx.entity.fsm.states.projectiles;

import com.mygdx.entity.fsm.State;
import com.mygdx.entity.projectiles.Projectile;
import com.mygdx.entity.projectiles.ProjectileStateMachine;

public abstract class ProjectileState extends State {
    Projectile projectile;
    ProjectileStateMachine stateMachine;

    public ProjectileState(Projectile projectile, ProjectileStateMachine stateMachine) {
        this.projectile = projectile;
        this.stateMachine = stateMachine;
    }
}
