package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.actions.Actionable;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.player.PlayerAttackState;
import com.mygdx.entity.fsm.states.characters.player.PlayerHurtState;
import com.mygdx.entity.fsm.states.characters.player.PlayerIdleState;
import com.mygdx.entity.fsm.states.characters.player.PlayerRunState;
import com.mygdx.events.EnemyHitEvent;
import com.mygdx.ui.HealthBar;
import com.mygdx.ui.TrajectoryLine;


public class Player extends Character implements Actionable {

    private Vector2 crosshairPosition;
    private TrajectoryLine trajectoryLine;
    private int fireRate;
    private PlayerIdleState idleState;
    private PlayerRunState runState;
    private PlayerAttackState attackState;
    private PlayerHurtState hurtState;

    private ActionMap actionMap;

    public Player(String texture, float x, float y, String type, int frameCountRow, int frameCountCol, float frameDuration) {
        // set idle animation for player
        super(texture, x, y, type, frameCountRow, frameCountCol, frameDuration);

        // add other animation
        addAnimation("characters/Mage_Fire/Hurt.png", "hurt", 3);
        addAnimation("characters/Mage_Fire/Run.png", "run", 8);
        addAnimation("characters/Mage_Fire/Fireball.png", "attack", 8);

        // Player Base Stats
        this.maxHp = 100;
        this.currentHp = 50;
        this.invincibilityDurationMS = 1000;
        this.damage = 50;
        // Attack Speed represents attacks per minutes
        // This has to be incorporated along with the animations
        this.attackSpeed = 1;

        Vector2 healthBarOffset = new Vector2(38, -20);
        this.healthBar = new HealthBar(this, healthBarOffset, Color.GREEN, 80, 10);
        this.trajectoryLine = new TrajectoryLine(this);
        this.crosshairPosition = new Vector2();

        this.idleState = new PlayerIdleState(this, stateMachine);
        this.attackState = new PlayerAttackState(this, stateMachine);
        this.runState = new PlayerRunState(this, stateMachine);
        this.hurtState = new PlayerHurtState(this, stateMachine);

        stateMachine.addState(CharacterStateEnum.IDLE, idleState);
        stateMachine.addState(CharacterStateEnum.RUN, runState);
        stateMachine.addState(CharacterStateEnum.ATTACK, attackState);
        stateMachine.addState(CharacterStateEnum.HURT, hurtState);

        stateMachine.setState(CharacterStateEnum.IDLE);
    }


    public ActionMap getActionMap() {
        return actionMap;
    }

    public void setActionMap(ActionMap actionMap) {
        this.actionMap = actionMap;
    }

    public Vector2 getCrosshairPosition() {
        return crosshairPosition;
    }

    public void setCrosshairPosition(Vector2 crosshairPosition) {
        this.crosshairPosition = crosshairPosition;
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

    // TODO: refactor or remove this
    public void move() {
        move(actionMap);
    }

    public void move(ActionMap actionMap) {
        stateMachine.setState(CharacterStateEnum.RUN);

    }

    @Override
    public void collide(Collider other) {
        // TODO: refactor this such that they lie on the other party, not the player
        if (other.getEntity().getType().equals("maxHealthPotion")) {
            this.increaseMaxHp();
//            this.showMessage("Max HP +5%", 2.0f, Color.GREEN);
            EnemyHitEvent.addEvent(new EnemyHitEvent(Float.toString(damage), new Vector2(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() - 50), 0.5f, Color.GREEN));
            System.out.println("Max health increased by 5%");
        }
        if (other.getEntity().getType().equals("healthPotion")) {
            this.heal();
//            this.showMessage("+10 HP", 2.0f, Color.GREEN);
            EnemyHitEvent.addEvent(new EnemyHitEvent(Float.toString(damage), new Vector2(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() - 50), 0.5f, Color.RED));
            System.out.println("Healed 10 HP.");
        }
    }
}