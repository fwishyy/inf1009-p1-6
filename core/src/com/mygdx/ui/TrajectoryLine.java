package com.mygdx.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Player;

public class TrajectoryLine {
    private Player player;

    public TrajectoryLine(Player player) {
        this.player = player;
    }

    public void draw(ShapeRenderer shape) {
        Vector2 position = player.getVector2().cpy();
        position.x += player.getWidth() / 2f;
        position.y += player.getHeight() / 2f;
        shape.setAutoShapeType(true);
        shape.begin();
        shape.setColor(1, 1, 1, 1);
        shape.line(position, player.getCrosshairPosition());
        shape.end();
    }
}
