package com.mygdx.player;

<<<<<<< Updated upstream
=======
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.AnimatedEntity;
>>>>>>> Stashed changes
import com.mygdx.engine.entity.Collider;
import com.mygdx.engine.entity.Entity;
import com.mygdx.events.LoseEvent;
import com.mygdx.events.WinEvent;

<<<<<<< Updated upstream
public class Player extends Entity {

    private boolean isDead = false;

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
    }

    public Player(String texture, float x, float y, String type) {
        super(texture, x, y, type);
    }
=======
public class Player extends AnimatedEntity {
	
	public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
		super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);
	}
>>>>>>> Stashed changes

    @Override
    protected void update() {
    }

    @Override
    public void collide(Collider other) {
<<<<<<< Updated upstream
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
=======
        if (other.getEntity().getType() == "player1") {
            this.dispose();
        }
    }
	
>>>>>>> Stashed changes
}
