package com.mygdx.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;

public class Enemy extends Character {

    public Enemy(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.currentHp = 20;
        this.maxHp = 20;
    }


    public void potionDrop() {
        // check for a 1/5 chance to drop a potion
        if (MathUtils.random(1, 5) == 1) {
            // decide which potion to drop (50% chance each)
            boolean dropHealthPotion = MathUtils.randomBoolean();

            if (dropHealthPotion) {
                // create a health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "healthPotion");
            } else {
                // create a max health potion at the enemy location
                potion = new Pickup(new Vector2(getX(), getY()), "maxHealthPotion");
            }

            // GameScene -> addEntity(potion);
        }
    }

    @Override
    public void collide(Collider other) {
        // TODO Auto-generated method stub
//        if (other.getEntity().getType().equals("player1")) {
//            LoseEvent.addEvent(new LoseEvent());
//            this.dispose();
//        }
//        if (other.getEntity().getType().equals("player2")) {
//            WinEvent.addEvent(new WinEvent());
//            this.dispose();
//        }
    }
}
