package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class Cursor {

    private Pixmap cursorTexture;
    private com.badlogic.gdx.graphics.Cursor cursor;
    private int mouseX, mouseY;

    public Cursor(String cursorImagePath) {

        cursorTexture = new Pixmap(Gdx.files.internal(cursorImagePath));
        cursor = Gdx.graphics.newCursor(cursorTexture, cursorTexture.getWidth() / 2, cursorTexture.getHeight() / 2);

    }

    // Alternative constructor with self provided hotspot values
    public Cursor(String cursorImagePath, int hotspot_x, int hotspot_y) {

        cursorTexture = new Pixmap(Gdx.files.internal(cursorImagePath));
        cursor = Gdx.graphics.newCursor(cursorTexture, hotspot_x, hotspot_y);

    }

    public void update() {
        Gdx.graphics.setCursor(cursor);
    }

    public void dispose() {
        cursorTexture.dispose();
    }

}