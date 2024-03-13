package com.mygdx.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;


public class Player extends AnimatedEntity {
    private int health;
    private Vector2 target;

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
    }

    @Override
    public void collide(Collider other) {
        if (other.getEntity().getType().equals("player1")) {
            LoseEvent.addEvent(new LoseEvent());
            this.dispose();
        }
        if (other.getEntity().getType().equals("player2")) {
            WinEvent.addEvent(new WinEvent());
            this.dispose();
        }
    }

    public Vector2 getTarget() {
        return target;
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }
}
