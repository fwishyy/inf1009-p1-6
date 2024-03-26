package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.controls.ActionMap;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.Collider;
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
    public HealthBar healthBar;
    protected float maxHp;
    protected float currentHp;
    Pickup potion;
    ActionMap actionMap;

    public Character(String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        super(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
        this.maxHp = 100;
        this.currentHp = 100;
        Vector2 healthBarPositionOffset = new Vector2(38, -20);
        healthBar = new HealthBar(this, healthBarPositionOffset, Color.RED, 80, 10);
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
        // implement common behaviours here
        if (other.getEntity().getType().equals("player1")) {
            if (this.currentHp >= 5) {
                this.currentHp -= 5;
            }
        }
    }

    // health bar getter
    public HealthBar getHbar() {
        return healthBar;
    }

    // max HP getter
    public float getMaxHp() {
        return this.maxHp;
    }

    // current HP getter
    public float getCurrentHp() {
        return this.currentHp;
    }

    // take damage function
    public void takeDamage(int damage, Vector2 position) {
        currentHp -= damage;
        if (currentHp < 0) {
            currentHp = 0;
        }

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

        if (isDead()) {
            this.dispose();
        }

    }
}
