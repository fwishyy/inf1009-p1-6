package com.mygdx.events;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.utils.Event;

public class EnemyHitEvent extends Event {

    private String text;
    private Vector2 position;
    private float duration;
    private Color color;

    public EnemyHitEvent(String text, Vector2 position) {
        this.text = text;
        this.position = position;
        this.duration = 0f;
        this.color = Color.WHITE;
    }

    public EnemyHitEvent(String text, Vector2 position, float duration) {
        this.text = text;
        this.position = position;
        this.duration = duration;
        this.color = Color.WHITE;
    }

    public EnemyHitEvent(String text, Vector2 position, float duration, Color color) {
        this.text = text;
        this.position = position;
        this.duration = duration;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDuration() {
        return duration;
    }

    public Color getColor() {
        return color;
    }
}
