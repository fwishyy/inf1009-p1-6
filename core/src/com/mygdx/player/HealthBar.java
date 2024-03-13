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
    private Vector2 positionOffset; // position relative to owner entity
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public HealthBar(Vector2 positionOffset){
        this.barWidth = 50; // initialise bar width
        this.barHeight = (float) 6.25; // initalise bar height
        this.shapeRenderer = new ShapeRenderer();
        this.positionOffset = positionOffset;
        
        this.font = new BitmapFont(); // libgdx default arial font
        this.font.setColor(Color.WHITE); // set font color to white
        
    }
    
    // update health
    public void update(float maxHp, float currentHp) {
        this.maxHp = maxHp;
        this.currentHp = currentHp;
    }

    // draw method
    public void draw(SpriteBatch batch, float ownerX, float ownerY) {
        float x = ownerX + positionOffset.x;
        float y = ownerY - barHeight - positionOffset.y;

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, barWidth, barHeight);

        float healthWidth = barWidth * (currentHp / maxHp);
        shapeRenderer.setColor(Color.RED);
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
	
