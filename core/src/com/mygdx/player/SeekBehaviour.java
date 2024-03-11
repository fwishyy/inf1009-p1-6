package com.mygdx.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.MoveAction;
import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.Entity;

public class SeekBehaviour implements Behaviour {

    private Entity target;
    private float speed;

    public SeekBehaviour(Entity target, float speed) {
        this.target = target;
        this.speed = speed;
    }

    @Override
    public void update(Entity entity, float deltaTime) {
        Vector2 dir = new Vector2();
        dir.x = (entity.getX() - target.getX() < 0) ? 1 : -1;
        dir.y = (entity.getY() - target.getY() < 0) ? 1 : -1;
        float newX = entity.getX() + dir.x * this.speed * Gdx.graphics.getDeltaTime();
        float newY = entity.getY() + dir.y * this.speed * Gdx.graphics.getDeltaTime();
        MoveAction moveAction = new MoveAction();
        moveAction.setTargetPos(newX, newY);
        entity.addAction(moveAction);
    }
}
