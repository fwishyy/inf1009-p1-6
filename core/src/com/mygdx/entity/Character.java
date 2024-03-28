package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
import com.mygdx.entity.fsm.states.CharacterStateEnum;
import com.mygdx.entity.fsm.states.CharacterStateMachine;
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
        batch.begin();
        // Draw the message if there is one
        if (!message.isEmpty()) {
            layout.setText(font, message);
            float textWidth = layout.width;
            float textHeight = layout.height;

            font.setColor(messageColor);
            font.draw(batch, message, messagePosition.x - textWidth / 2, messagePosition.y + textHeight);
        }

        batch.end();

        super.draw(batch, shapeRenderer);

        healthBar.update(maxHp, currentHp);
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

    public void showMessage(String message, float time, Color color) {
        this.message = message;
        this.messageTime = time;
        this.messageColor = color;
        this.messagePosition.set(this.getX() + this.getWidth() / 2, this.getY() + this.getHeight() - 50);
    }

    @Override
    public void update() {
        super.update();

        if (messageTime > 0) {
            messageTime -= Gdx.graphics.getDeltaTime();
            messagePosition.y += messageSpeed * Gdx.graphics.getDeltaTime();
        } else {
            message = ""; // Clear the message when the time is up
        }

        if (isDead() && stateMachine.getCurrentState() != CharacterStateEnum.DIE) {
            stateMachine.setState(CharacterStateEnum.DIE);
        }
    }
}
