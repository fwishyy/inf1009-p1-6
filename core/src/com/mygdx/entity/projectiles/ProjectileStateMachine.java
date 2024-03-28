package com.mygdx.entity.projectiles;

import com.mygdx.entity.fsm.StateMachine;

public class ProjectileStateMachine extends StateMachine {
    Projectile projectile;

    public ProjectileStateMachine(Projectile projectile) {
        this.projectile = projectile;
    }
}
