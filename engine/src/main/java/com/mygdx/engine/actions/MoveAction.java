package com.mygdx.engine.actions;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class MoveAction extends GameAction {

    private Vector2 targetPos;

    public MoveAction() {
        targetPos = new Vector2();
    }

    public Vector2 getTargetPos() {
        return targetPos;
    }

    public void setTargetPos(Vector2 targetPos) {
        this.targetPos = targetPos;
    }

    public void setTargetPos(float x, float y) {
        this.targetPos.x = x;
        this.targetPos.y = y;
    }

    @Override
    public boolean act(Entity entity) {
        entity.setPosition(targetPos);

        return true;
    }
}
