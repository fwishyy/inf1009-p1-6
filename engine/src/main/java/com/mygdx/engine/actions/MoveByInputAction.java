package com.mygdx.engine.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class MoveByInputAction extends MoveAction {

    private Vector2 keyState;

    @Override
    public boolean act(Entity entity) {

        float newX = entity.getX() + keyState.x * 200 * Gdx.graphics.getDeltaTime();
        float newY = entity.getY() + keyState.y * 200 * Gdx.graphics.getDeltaTime();

        super.setTargetPos(newX, newY);
        super.act(entity);

        return true;
    }

    public void setKeyState(Vector2 keyState) {
        this.keyState = keyState;
    }
}
