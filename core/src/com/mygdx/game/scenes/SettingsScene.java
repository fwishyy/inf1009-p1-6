package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.EventListener;

public class SettingsScene extends Scene{

	private InputManager im;
	private AudioManager am;
	private Table table;
    private TextureAtlas textureAtlas;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private EventListener<PointerEvent> pointerEventListener;
    private SceneManager sceneManager;
    private Skin skin;
    private BitmapFont font;
    private TextButton back;
    
    // Audio Buttons
    private TextButton volume;
    private TextButton volumeUp;
    private TextButton volumeDown;
    private float MenuMusicVolume;
    
    public SettingsScene(GameContainer container) {
    	super(container);
    	sceneManager = container.getSceneManager();
        im = container.getInputManager();
        am = container.getAudioManager();
    }
    
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		MenuMusicVolume = am.getVolume("MenuMusic");
		
		table = new Table();
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/menu-ui.atlas"));
        font = new BitmapFont(Gdx.files.internal("sgx/skin/font-export.fnt"));
        skin = new Skin(textureAtlas);
        
        // Style for Back button
        TextButtonStyle backButtonStyle = new TextButtonStyle();
        backButtonStyle.up = skin.getDrawable("Button-BG-shadow");
        backButtonStyle.font = font;
        
        // Style for Center volume
        TextButtonStyle centerButtonStyle = new TextButtonStyle();
        centerButtonStyle.up = skin.getDrawable("button-emphasis-disabled");
        centerButtonStyle.font = font;
        
        // Style for right increase volume
        TextButtonStyle increaseVolumeStyle = new TextButtonStyle();
        increaseVolumeStyle.up = skin.getDrawable("button-plus-disabled");
        increaseVolumeStyle.font = font;
        
        // Style for left decrease volume
        TextButtonStyle decreaseVolumeStyle = new TextButtonStyle();
        decreaseVolumeStyle.up = skin.getDrawable("button-minus-disabled");
        decreaseVolumeStyle.font = font;
        
        // Create background texture
        bgTexture = new Texture("bg/PNG/settings-bg.jpg");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);
        
        // Volume
        volume = new TextButton("BGM Volume: " + MenuMusicVolume, centerButtonStyle);
        volumeUp = new TextButton("", increaseVolumeStyle);
        volumeDown = new TextButton("", decreaseVolumeStyle);
        
        // Back button
        back = new TextButton("Back", backButtonStyle);
        
        
        table.setFillParent(true);
        table.setBackground(bgTextureDrawable); 
        
        // Volume buttons row
        table.add(volumeDown).padTop(100f);
        table.add(volume).padTop(100f);
        table.add(volumeUp).padTop(100f);
        table.row();
        
        // Back button row
        table.add(back).padTop(20f).colspan(3);
        table.setDebug(false);
        
        stage.addActor(table);
        im.addInputProcessor(stage);
        
        // When the volume down button is pressed
        volumeDown.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				if (MenuMusicVolume > 0.0f) {
					MenuMusicVolume -= 0.1f;
					MenuMusicVolume = Math.round(MenuMusicVolume * 10.0f) / 10.0f;
					am.setVolume("MenuMusic", MenuMusicVolume);
					//am.stop("MenuMusic");
					//am.play("MenuMusic");
					volume.setText("BGM Volume: " + MenuMusicVolume);
				}
				return super.touchDown(event, x, y, pointer, button);
			}
        	
        });
        
        // When the volume up button is pressed
        volumeUp.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				
				if (MenuMusicVolume < 1.0f) {
					MenuMusicVolume += 0.1f;
					MenuMusicVolume = Math.round(MenuMusicVolume * 10.0f) / 10.0f;
					am.setVolume("MenuMusic", MenuMusicVolume);
					//am.stop("MenuMusic");
					//am.play("MenuMusic");
					volume.setText("BGM Volume: " + MenuMusicVolume);
				}
				return super.touchDown(event, x, y, pointer, button);
			}
        	
        });
        
        back.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				sceneManager.setScene(new MainMenuScene(container));
				return super.touchDown(event, x, y, pointer, button);
			}
        	
        });
        
	}
    
    @Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
        skin.dispose();
	}
}