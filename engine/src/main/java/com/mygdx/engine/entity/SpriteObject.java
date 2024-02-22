/**
 * CURRENTLY NOT BEING USED. 16 February 2024 0311H
 */

package com.mygdx.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class SpriteObject {
    private Texture texture = null;
    private Color color = Color.BLACK;

    public SpriteObject(String texture, Color color) {
        this.texture = new Texture(Gdx.files.internal(texture));
        this.color = color;
    }

    public SpriteObject(String texture) {
        this.texture = new Texture(Gdx.files.internal(texture));
    }

    /**
     * @ return texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * @ set texture
     */
    public void setTexture(String texture) {
        this.texture = new Texture(Gdx.files.internal(texture));
    }

    /**
     * @ return color
     */
    public Color getColor(Color color) {
        return this.color;
    }

    /**
     * @ set color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}