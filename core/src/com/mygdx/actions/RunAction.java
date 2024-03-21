package com.mygdx.actions;

import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.entity.AnimatedEntity;

public class RunAction extends MoveAction {
    @Override
    public boolean act() {
        if (actor instanceof AnimatedEntity) {
            AnimatedEntity animatedEntity = (AnimatedEntity) actor;
            animatedEntity.setAnimation("run");
        }

        return true;
    }
}
