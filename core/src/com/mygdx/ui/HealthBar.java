package com.mygdx.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entity.Character;


public class HealthBar {

    /* owner: the entity that the health bar belongs to
     * xOffset: horizontal offset from entity position
     * yOffset: vertical offset from entity position
     * maxHp: maximum health points of entity
     * currentHp: current health points of entity
     * barWidth: width of health bar (fixed)
     * barHeight: height of health bar (fixed)
     */

    private Character character;
    private float maxHp;
    private float currentHp;
    private float barWidth;
    private float barHeight;
    private Color healthColour;
    private Vector2 positionOffset; // position relative to owner character
    private BitmapFont font;

    public HealthBar(Character character, Vector2 positionOffset, Color healthColour, float barWidth, float barHeight) {
        this.character = character;
        this.barWidth = barWidth; // initialise bar width
        this.barHeight = barHeight; // initalise bar height
        this.positionOffset = positionOffset;

        this.healthColour = healthColour;
        this.font = new BitmapFont(); // libgdx default arial font
        this.font.setColor(Color.WHITE); // set font color to white
    }

    // update health
    public void update(float maxHp, float currentHp) {
        this.maxHp = maxHp;
        this.currentHp = currentHp;
    }

    // draw method
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.begin();
        // TODO: fix health bar positioning
        float x = character.getX() + character.getWidth() / 2 - barWidth / 2;
        float y = character.getY() - barHeight - positionOffset.y;

        // GlyphLayout to measure the dimensions of HP value text
        GlyphLayout layout = new GlyphLayout();
        String text = String.format("%d/%d", (int) currentHp, (int) maxHp);
        layout.setText(font, text); // sets text of layout and calculates the bounds

        // calculate the x and y position to draw the text at the center of the health bar
        float textX = x + (barWidth - layout.width) / 2;
        float textY = y + (barHeight + layout.height) / 2; // add layout.height because the y-coordinate is the bottom of the text

        // draw HP text
        font.draw(batch, layout, textX, textY);
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x - 2, y - 2, barWidth + 4, barHeight + 4);

        float healthWidth = barWidth * (currentHp / maxHp);
        shapeRenderer.setColor(healthColour);
        shapeRenderer.rect(x, y, healthWidth, barHeight);
        shapeRenderer.end();
    }

    public void dispose() {
        font.dispose();
    }
}
	
