package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.engine.scenes.Scene;


public class MainMenuScene extends Scene {

    private Table table;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private BitmapFont bitmapFont;

    public MainMenuScene() {
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/sgx-ui.atlas"));
        skin = new Skin();
        skin.addRegions(textureAtlas);

        TextButtonStyle buttonStyle = new TextButtonStyle();
        bitmapFont = new BitmapFont();
        buttonStyle.font = bitmapFont;
        buttonStyle.up = skin.getDrawable("button");
        buttonStyle.down = skin.getDrawable("button-emphasis");
        buttonStyle.disabled = skin.getDrawable("button-emphasis");
        TextButton testButton = new TextButton("test", buttonStyle);
        testButton.setPosition(100, 150);
        stage.addActor(testButton);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.draw();
    }
}
