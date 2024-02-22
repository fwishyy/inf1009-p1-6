package com.mygdx.engine.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class MoveByInputAction extends MoveAction {
    @Override
    public boolean act(Entity entity, Vector2 keyState) {

        float newX = entity.getX() + keyState.x * 200 * Gdx.graphics.getDeltaTime();
        float newY = entity.getY() + keyState.y * 200 * Gdx.graphics.getDeltaTime();

        super.act(entity, newX, newY);

        return true;
    }
}