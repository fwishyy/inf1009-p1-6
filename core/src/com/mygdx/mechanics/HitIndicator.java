package com.mygdx.mechanics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.events.EnemyHitEvent;

public class HitIndicator {
	
	private BitmapFont font = new BitmapFont();
    private String text = "";
    private Vector2 position = null;
    private Color color = Color.WHITE;
    private float duration = 0;
    private float speed = 20f;
    
    private EventListener<EnemyHitEvent> enemyHitEventListener = new EventListener<EnemyHitEvent>() {
        @Override
        public void onSignal(Event e) {
        	System.out.println("=========== SHOW HIT INDICATOR ==============");
            showText((EnemyHitEvent) e);
        }
    };
    
    public HitIndicator() {
    	EnemyHitEvent.addListener(EnemyHitEvent.class, enemyHitEventListener);
    }
    
    public void update(SpriteBatch batch) {
    	EventBus.processEvents(EnemyHitEvent.class);
    	
    	if(text.length() > 0) {
    		batch.begin();
            font.setColor(color);
            font.draw(batch, text, position.x, position.y);
            batch.end();
    	}
    	if (duration > 0) {
            duration -= Gdx.graphics.getDeltaTime();
            position.y += speed * Gdx.graphics.getDeltaTime();
        } else {
            text = ""; // Clear the message when the time is up
        }
    }
    
    public void showText(EnemyHitEvent e) {
    	this.text = e.getText();
    	this.position = e.getPosition();
    	this.duration = e.getDuration();
    	this.color = e.getColor();
    }
    
    public void dispose() {
    	EventBus.removeListener(enemyHitEventListener);
    }
}
