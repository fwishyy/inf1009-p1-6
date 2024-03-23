package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;

public class Cursor {
	
    private Pixmap cursorTexture;
    private com.badlogic.gdx.graphics.Cursor cursor;
    private int mouseX, mouseY;

    public Cursor(String cursorImagePath) {
    	
        cursorTexture = new Pixmap(Gdx.files.internal(cursorImagePath));
        cursor = Gdx.graphics.newCursor(cursorTexture, mouseX, mouseY);
        
    }

    public void updateCursorPosition() {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y coordinate
        Gdx.graphics.setCursor(cursor);
    }

    public void dispose() {
        cursorTexture.dispose();
    }

}