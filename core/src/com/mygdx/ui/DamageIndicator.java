package com.mygdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class DamageIndicator {
    private Vector2 position;
    private float lifetime;
    private String damageText;
    private BitmapFont font;
    private Color color;

    public DamageIndicator(Vector2 position, int damageAmount, float lifetime, Color color) {
        this.position = new Vector2(position);
        this.lifetime = lifetime;
        this.damageText = "-" + damageAmount;
        this.font = new BitmapFont(); // Customize this font as needed
        this.color = color;
    }

    public void update(float deltaTime) {
        // Move the damage indicator upwards
        position.y += deltaTime * 50; // Adjust the speed
        lifetime -= deltaTime;
    }

    public void draw(SpriteBatch batch) {
        font.setColor(color);
        font.draw(batch, damageText, position.x, position.y);
    }

    public boolean isFinished() {
        return lifetime <= 0;
    }

    public void dispose() {
        font.dispose();
    }
}
