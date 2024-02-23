package com.mygdx.backgroundsprite;

import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;

public class bgField extends Entity {

    public bgField(String texture, float x, float y, String type) {
        super(texture, x, y, type);
    }

    @Override
    public void collide(Collider other) {
        // TODO Auto-generated method stub
        if (other.getEntity().getType() == "player1") {
            this.dispose();
        }
    }

}
