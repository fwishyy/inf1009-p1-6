package com.mygdx.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.entity.Player;

public class TrajectoryLine {
    private ShapeRenderer shapeRenderer;
    private Player player;

    public TrajectoryLine(Player player) {
        this.player = player;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    public void draw() {
        shapeRenderer.begin();
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.line(new Vector2(320, 240), player.getTarget());
        shapeRenderer.end();
    }
}
