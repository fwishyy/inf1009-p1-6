package com.mygdx.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


public class HealthBar {
	
	/* owner: the entity that the health bar belongs to
	 * xOffset: horizontal offset from entity position
	 * yOffset: vertical offset from entity position
	 * maxHp: maximum health points of entity
	 * currentHp: current health points of entity
	 * barWidth: width of health bar (fixed)
	 * barHeight: height of health bar (fixed)
	 */
	
    private float maxHp;
    private float currentHp;
    private float barWidth;
    private float barHeight;
    private Color healthColour;
    private Vector2 positionOffset; // position relative to owner entity
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public HealthBar(Vector2 positionOffset, Color healthColour, float barWidth, float barHeight){
        this.barWidth = barWidth; // initialise bar width
        this.barHeight = barHeight; // initalise bar height
        this.shapeRenderer = new ShapeRenderer();
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
    public void draw(SpriteBatch batch, float ownerX, float ownerY, float ownerWidth) {
        float x = ownerX + ownerWidth / 2 - barWidth / 2;
        float y = ownerY - barHeight - positionOffset.y;

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x - 2, y - 2, barWidth + 4, barHeight + 4);

        float healthWidth = barWidth * (currentHp / maxHp);
        shapeRenderer.setColor(healthColour);
        shapeRenderer.rect(x, y, healthWidth, barHeight);
        shapeRenderer.end();

        batch.begin(); 
        // GlyphLayout to measure the dimensions of HP value text
        GlyphLayout layout = new GlyphLayout(); 
        String text = String.format("%d/%d", (int) currentHp, (int) maxHp);
        layout.setText(font, text); // sets text of layout and calculates the bounds

        // calculate the x and y position to draw the text at the center of the health bar
        float textX = x + (barWidth - layout.width) / 2;
        float textY = y + (barHeight + layout.height) / 2; // add layout.height because the y-coordinate is the bottom of the text

        // draw HP text
        font.draw(batch, layout, textX, textY);
    }

    public void dispose() {
        font.dispose();
        shapeRenderer.dispose();
    }
 
}
	
