package com.mygdx.mechanics;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class Boundary {

    private Entity entity = null;
    private Vector2 max = null;
    private Vector2 min = null;

    public Boundary(Entity entity, Vector2 min, Vector2 max) {
        this.entity = entity;
        this.min = min;
        this.max = max;
    }

    public void update() {
        float targetMinX = entity.getX();
        float targetMinY = entity.getY();
        float targetMaxX = targetMinX + entity.getWidth();
        float targetMaxY = targetMinY + entity.getHeight();

        if (targetMinX <= min.x)
            entity.setX(min.x);
        if (targetMaxX >= max.x)
            entity.setX(max.x - entity.getWidth());
        if (targetMinY <= min.y + 50)
            entity.setY(min.y + 50);
        if (targetMaxY >= max.y)
            entity.setY(max.y - entity.getHeight());

    }
}
