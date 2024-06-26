package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.behaviour.BehaviourManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.physics.CollisionManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.ui.Cursor;


public class MainMenuScene extends Scene {
    private AudioManager am;
    private EntityManager em;
    private CollisionManager cm;
    private InputManager im;
    private PlayerControlManager pm;
    private BehaviourManager bm;
    private Table table;
    private TextureAtlas textureAtlas;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private SceneManager sm;
    private Skin skin;
    private TextButton startButton;
    private TextButton settingsButton;
    private BitmapFont font;
    private boolean paused;
    private Cursor cursor;
    private Cursor hand;

    public MainMenuScene(GameContainer container) {
        super(container);
        sm = container.getSceneManager();
        im = container.getInputManager();
        am = container.getAudioManager();
    }

    @Override
    public void show() {
        super.show();

        // Add 2 different cursors
        cursor = new Cursor("mouse/pointer.png", 18, 13);
        hand = new Cursor("mouse/hand.png", 27, 11);
        am.addMusic("GameMusic", "audio/music/medieval-battle-music.mp3");

        // Menu Screen Audio
        if (!am.isPlaying("MenuMusic")) {
            am.addMusic("MenuMusic", "audio/music/Hell-Night.mp3");
            am.play("MenuMusic");
        }

        table = new Table();
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/menu-ui.atlas"));
        font = new BitmapFont(Gdx.files.internal("sgx/skin/font-export.fnt"));
        skin = new Skin(textureAtlas);

        TextButtonStyle buttonStyle = new TextButtonStyle();
        buttonStyle.up = skin.getDrawable("Button-BG-shadow");
        //buttonStyle.down = skin.getDrawable("button-emphasis-color-over");
        buttonStyle.font = font;

        // Create background texture
        bgTexture = new Texture("bg/PNG/main-menu-bg.jpg");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);

        // Create buttons
        startButton = new TextButton("Start", buttonStyle);
        settingsButton = new TextButton("Settings", buttonStyle);

        // Create a table and fill the screen
        table.setFillParent(true);
        table.setBackground(bgTextureDrawable);
        table.add(startButton).padTop(100f).row();
        table.add(settingsButton).row();


        stage.addActor(table);
        stage.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.fadeIn(1.0f)));
        im.addInputProcessor(stage);


        startButton.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.update();

                // Scale the TextButton
                startButton.getLabel().setFontScale(1.2f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                cursor.update();

                // Reset the TextButton
                startButton.getLabel().setFontScale(1f);

            }


            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                System.out.println("START");
                //sm.setScene(new GameScene(container));
                sm.setScene(new CharacterSelectionScene(container));
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                super.touchUp(event, x, y, pointer, button);
            }

        });

        settingsButton.addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.update();

                // Scale the TextButton
                settingsButton.getLabel().setFontScale(1.2f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                cursor.update();

                // Scale the TextButton
                settingsButton.getLabel().setFontScale(1f);
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // TODO Auto-generated method stub
                System.out.println("SETTINGS");
                sm.setScene(new SettingsScene(container));
                return super.touchDown(event, x, y, pointer, button);
            }

        });


        // Set cursor as the displayed cursor
        cursor.update();

    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(); // Need this so that the mouse hover works
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
