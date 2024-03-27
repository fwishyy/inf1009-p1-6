package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PowerUpWindow extends Window {
	private static final WindowStyle windowStyle;
	private static final ImageButtonStyle closeButtonStyle;
	private static final TextButtonStyle buttonStyle;
	private static final Skin skin;
	private static final BitmapFont font;
	
	static {
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/windows.pack"));
		TextureAtlas textureAtlas2 = new TextureAtlas(Gdx.files.internal("sgx/skin/menu-ui.atlas"));
		skin = new Skin(textureAtlas2);
		font = new BitmapFont(Gdx.files.internal("sgx/skin/font-export.fnt"));
		
		windowStyle = new WindowStyle(new BitmapFont(), Color.BLACK, new TextureRegionDrawable(textureAtlas.findRegion("window-1-background")));
		closeButtonStyle = new ImageButtonStyle();
		closeButtonStyle.imageUp = new TextureRegionDrawable(textureAtlas.findRegion("window-1-close-button"));
		
		
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.getDrawable("button-emphasis-disabled");
		buttonStyle.font = font;
		
	}
	
	
	public PowerUpWindow() {
		super("", windowStyle);
		
		final Button closeButton = new ImageButton(closeButtonStyle);
		closeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setVisible(false);
				CharacterSelectionScene characterSelectionScene = CharacterSelectionScene.getInstance();
				characterSelectionScene.setOverlayToFalse();
			}
		});
		getTitleTable().add(closeButton).size(38, 38).padRight(10).padTop(0);
		
		// Create a table for the text on the left side
        Table leftTextTable = new Table();
        Label leftTextLabel = new Label("Power up skill allows player to\nincrease their output damage to enemies by +10 dmg", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        leftTextLabel.setWrap(true);
        leftTextLabel.setWidth(200);
        leftTextTable.add(leftTextLabel).expand().left().pad(10).width(200).height(200).row();
        // Buttons for left side of power up selection
     	TextButton powerUpBtn = new TextButton("+ Atk Damage", buttonStyle);
        leftTextTable.add(powerUpBtn).center().pad(10).width(200);


        // Create a table for the text on the right side
        Table rightTextTable = new Table();
        Label rightTextLabel = new Label("Increase attack speed allows player to\nclear waves faster, adding +5 attack speed", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        rightTextLabel.setWrap(true);
        rightTextLabel.setWidth(200);
        rightTextTable.add(rightTextLabel).expand().right().pad(10).width(200).height(200).row();
        // Buttons for right side of increase attack speed window
     	TextButton increaseAtkSpeedBtn = new TextButton("+ Atk Speed", buttonStyle);
     	rightTextTable.add(increaseAtkSpeedBtn).center().pad(10).width(200);

        // Add the tables to the power up window
        add(leftTextTable).expand().fillY().pad(10);
        add(rightTextTable).expand().fillY().pad(10);
	
		setClip(false);
		setTransform(true);
	}
}
