package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.fsm.states.player.PlayerStateMachine;
import com.mygdx.ui.HealthBar;
import com.mygdx.ui.TrajectoryLine;


public class Player extends Character {

    private Vector2 target;
    private TrajectoryLine trajectoryLine;
    private int fireRate;
    private PlayerStateMachine stateMachine;

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        // set idle animation for player
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);

        // add other animation
        addAnimation("characters/Mage_Fire/Hurt.png", "hurt", 3);
        addAnimation("characters/Mage_Fire/Run.png", "run", 8);
        addAnimation("characters/Mage_Fire/Fireball.png", "attack", 8);

        this.maxHp = 100;
        this.currentHp = 50;
        Vector2 healthBarOffset = new Vector2(38, -20);
        this.healthBar = new HealthBar(this, healthBarOffset, Color.GREEN, 80, 10);
        this.trajectoryLine = new TrajectoryLine(this);
        this.target = new Vector2();
        this.fireRate = 30;
        this.stateMachine = new PlayerStateMachine(this);
        stateMachine.setIdleState();
    }


    public Vector2 getTarget() {
        return target;
    }

    public void setTarget(Vector2 target) {
        this.target = target;
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shape) {
        super.draw(batch, shape);
        healthBar.update(maxHp, currentHp);
        trajectoryLine.draw(shape);
    }


    @Override
    public void update() {
        super.update();
        stateMachine.update();
        // when player picks up increase max HP potion, it will increase by 5%
        // to be included in potion collisionevent
    }

    public void increaseMaxHp() {
        maxHp += maxHp * (5.0f / 100.0f);
        System.out.println("New Max HP: " + this.maxHp);
    }

    // when player picks up healing potion, health will +10
    public void heal() {
        currentHp += 10;
        if (currentHp > maxHp) currentHp = maxHp;
        System.out.println("New Current HP: " + this.currentHp);

    }

    @Override
    public void takeDamage(int damage, Vector2 position) {
        super.takeDamage(damage, position);
        this.setAnimation("hurt", 1);
    }

    @Override
    public void collide(Collider other) {
//        if (other.getEntity().getType().equals("player1")) {
//            LoseEvent.addEvent(new LoseEvent());
//            this.dispose();
//        }
//        if (other.getEntity().getType().equals("player2")) {
//            WinEvent.addEvent(new WinEvent());
//            this.dispose();
//        }
        if (other.getEntity().getType().equals("maxHealthPotion")) {
            this.increaseMaxHp();
            this.showMessage("Max HP +5%", 2.0f, Color.GREEN);
            System.out.println("Max health increased by 5%");
        }
        if (other.getEntity().getType().equals("healthPotion")) {
            this.heal();
            this.showMessage("+10 HP", 2.0f, Color.GREEN);
            System.out.println("Healed 10 HP.");
        }
        if (other.getEntity().getType().equals("skeleton")) {
            Enemy skeleton = (Enemy) other.getEntity();
            if (skeleton.getCurrentFrame() == 3) { // Frames are 0-indexed, so the fourth frame is index 3
                if (this.getCurrentHp() >= 0) {
                    this.takeDamage(1, target);
                    this.showMessage("-1", 2.0f, Color.RED);
                    System.out.println("Player took damage from skeleton attack.");
                } else {
                    // implement lose event here
                    this.isDead();
                    System.out.println("GAME OVER");
                }
            }
        }
    }
}