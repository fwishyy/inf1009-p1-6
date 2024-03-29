package com.mygdx.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.events.EnemyHitEvent;
import com.mygdx.events.PlaySFXEvent;
import com.mygdx.mechanics.pickups.Pickup;
import com.mygdx.ui.HealthBar;


// Characters are generic animated entities for all on-screen things, like characters and monsters that have a health bar, the ability to take damage, and so on
public class Character extends AnimatedEntity {

    private String message = "";
    private float messageTime = 0f;
    private BitmapFont font = new BitmapFont();
    private Color messageColor = new Color(Color.WHITE);
    private GlyphLayout layout = new GlyphLayout();
    private Vector2 messagePosition = new Vector2();
    private final float messageSpeed = 20f;

    // Character UI elements
    public HealthBar healthBar;

    // Character stats
    protected float maxHp;
    protected float currentHp;
    protected float speed;
    protected float damage;
    protected float attackSpeed;

    protected float invincibilityDurationMS;
    protected long lastTookDamageMS;
    Pickup potion;

    // FSM
    CharacterStateMachine stateMachine;

    public Character(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.maxHp = 100;
        this.currentHp = 100;
        Vector2 healthBarPositionOffset = new Vector2(38, -20);
        healthBar = new HealthBar(this, healthBarPositionOffset, Color.RED, 80, 10);
        stateMachine = new CharacterStateMachine(this);
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.draw(batch, shapeRenderer);

        healthBar.draw(batch, shapeRenderer);
    }

    @Override
    public void collide(Collider other) {
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(float attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    // take damage function
    public void takeDamage(float damage) {
        // check whether character can take damage again
        if (canTakeDamage()) {
            currentHp -= damage;
            EnemyHitEvent.addEvent(new EnemyHitEvent(Float.toString(damage), new Vector2(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() - 50), 0.5f, Color.RED));
            if(this instanceof SkeletonArcher  || this instanceof SkeletonWarrior || this instanceof SkeletonSpearman)
            	PlaySFXEvent.addEvent(new PlaySFXEvent("SkeletonHurtFX"));
            if(this instanceof Player)
            	PlaySFXEvent.addEvent(new PlaySFXEvent("PlayerHurtFX"));
            if (currentHp < 0) {
                currentHp = 0;
            }
            lastTookDamageMS = TimeUtils.millis();
            stateMachine.setState(CharacterStateEnum.HURT);
        }
    }

    protected boolean canTakeDamage() {
        return (TimeUtils.millis() - lastTookDamageMS) >= invincibilityDurationMS;
    }

    // check if character is dead
    public boolean isDead() {
        return currentHp <= 0;
    }

    @Override
    public void update() {
        super.update();
        healthBar.update(maxHp, currentHp);

        if (isDead() && stateMachine.getCurrentStateEnum() != CharacterStateEnum.DIE) {
            stateMachine.setState(CharacterStateEnum.DIE);
            PlaySFXEvent.addEvent(new PlaySFXEvent("DeathFX"));
        }
    }
}
