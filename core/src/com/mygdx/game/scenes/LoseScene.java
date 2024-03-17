package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.input.PointerEvent;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;

public class LoseScene extends Scene {

    private Table table;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private EventListener<PointerEvent> pointerEventListener;
    private SceneManager sceneManager;
    private InputManager im;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private BitmapFont font;
    
    private TextButton restartBtn;
    private TextButton menuBtn;

    public LoseScene(GameContainer container) {
        super(container);
        sceneManager = container.getSceneManager();
        im = container.getInputManager();
    }

    @Override
    public void show() {
        pointerEventListener = new EventListener<PointerEvent>() {
            @Override
            public void onSignal(Event e) {
                if (e instanceof PointerEvent) {
                    handlePointerEvent((PointerEvent) e);
                }
            }
        };

        EventBus.addListener(PointerEvent.class, pointerEventListener);
        
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/menu-ui.atlas"));
        font = new BitmapFont(Gdx.files.internal("sgx/skin/font-export.fnt"));
        skin = new Skin(textureAtlas);
        
        // Button style
        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = skin.getDrawable("Button-BG-shadow");
        buttonStyle.font = font;  
        
        // End scene buttons
        restartBtn = new TextButton("Restart", buttonStyle);
        menuBtn = new TextButton("Main Menu", buttonStyle);

        table = new Table();
        table.setFillParent(true);
        bgTexture = new Texture("bg/PNG/lose_screen.png");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);
        table.setBackground(bgTextureDrawable);
        
        // Table for the 2 buttons
        table.add(restartBtn).row();
        table.add(menuBtn);
        stage.addActor(table);
        im.addInputProcessor(stage);
        
        // Handler for the 2 buttons
        restartBtn.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				sceneManager.setScene(new GameScene(container));
				return super.touchDown(event, x, y, pointer, button);
			}
        	
        });
        
        menuBtn.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				// TODO Auto-generated method stub
				sceneManager.setScene(new MainMenuScene(container));
				return super.touchDown(event, x, y, pointer, button);
			}
        	
        });
    }

    private void handlePointerEvent(PointerEvent e) {
        PointerEvent.Type type = e.getType();
        
        /*
        if (type == PointerEvent.Type.UP) {
            sceneManager.setScene(new MainMenuScene(container));
        } */
    }

    @Override
    public void render(float deltaTime) {
        stage.draw();
    }

    @Override
    public void dispose() {
        EventBus.removeListener(pointerEventListener);
    }
}
