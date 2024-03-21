package com.mygdx.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.entity.AnimatedEntity;

public class MoveByInputAction extends MoveAction {

    private Vector2 keyState;

    @Override
    public boolean act() {

        // default multiplier is 200 for any game entities
        float newX = actor.getX() + keyState.x * 200 * Gdx.graphics.getDeltaTime();
        float newY = actor.getY() + keyState.y * 200 * Gdx.graphics.getDeltaTime();

        super.setTargetPos(newX, newY);
        super.act();

        return true;
    }

    public void setKeyState(Vector2 keyState) {
        this.keyState = keyState;
    }
}