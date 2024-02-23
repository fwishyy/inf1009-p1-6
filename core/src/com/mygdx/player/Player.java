package com.mygdx.player;

import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;


public class Player extends AnimatedEntity {

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
}
