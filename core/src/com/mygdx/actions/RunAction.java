package com.mygdx.actions;

import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.entity.Entity;

public class RunAction extends GameAction {

    private boolean isRun;

    @Override
    public boolean act(Entity entity) {
        // TODO Auto-generated method stub
        entity.setIsAnimation(this.isRun);
        return false;
    }

    public void setIsRun(boolean isRun) {
        this.isRun = isRun;
    }


}
