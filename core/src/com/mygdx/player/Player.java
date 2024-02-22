package com.mygdx.player;

import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;

public class Player extends Entity {

    private boolean isDead = false;

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
    }

    public Player(String texture, float x, float y, String type) {
        super(texture, x, y, type);
    }

    @Override
    protected void update() {
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

    public boolean getIsDead() {
        return isDead;
    }
}
