package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.engine.audio.AudioManager;
import com.mygdx.engine.controls.PlayerControlManager;
import com.mygdx.engine.core.GameContainer;
import com.mygdx.engine.entity.AnimatedEntity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.engine.input.InputManager;
import com.mygdx.engine.scenes.Scene;
import com.mygdx.engine.scenes.SceneManager;
import com.mygdx.ui.Cursor;

public class CharacterSelectionScene extends Scene {

    private AudioManager am;
    private InputManager im;
    private EntityManager em;
    private PlayerControlManager pm;
    private Table table;
    private TextureAtlas textureAtlas;
    private Texture bgTexture;
    private TextureRegionDrawable bgTextureDrawable;
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private SceneManager sceneManager;
    private Skin skin;
    private BitmapFont font;
    private TextButton mageSelectBtn;
    private TextButton skeletonSelectBtn;
    private TextButton playBtn;
    private boolean mageSelected;
    private boolean skeletonSelected;

    // Cursor
    private Cursor cursor;
    private Cursor hand;

    // Singleton instance
    private static CharacterSelectionScene instance;

    public CharacterSelectionScene(GameContainer container) {
        super(container);
        sceneManager = container.getSceneManager();
        im = container.getInputManager();
        am = container.getAudioManager();
        em = container.getEntityManager();
        instance = this;
    }

    @Override
    public void show() {
        super.show();

        Preferences character_prefs = Gdx.app.getPreferences("CharacterSelection");

        // Add 2 different cursors
        cursor = new Cursor("mouse/pointer.png");
        hand = new Cursor("mouse/hand.png");
        table = new Table();
        textureAtlas = new TextureAtlas(Gdx.files.internal("sgx/skin/menu-ui.atlas"));
        font = new BitmapFont(Gdx.files.internal("sgx/skin/font-export.fnt"));
        skin = new Skin(textureAtlas);

        // Character 1 & 2
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        em.createEntity(1, AnimatedEntity.class, "characters/Mage_Fire/Idle.png", Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight()/2, "playable1", 1, 7, 0.1f);
        em.createEntity(1, AnimatedEntity.class, "characters/Skeleton_Warrior/Idle.png", Gdx.graphics.getWidth() / 2 + 10, Gdx.graphics.getHeight()/2, "playable2", 1, 7, 0.1f);

        // Create background texture
        bgTexture = new Texture("bg/PNG/main-menu-bg.jpg");
        bgTextureDrawable = new TextureRegionDrawable(bgTexture);

        // Button style for the 2 character selection
        TextButtonStyle mageButtonStyle = new TextButtonStyle();
        TextButtonStyle skeletonButtonStyle = new TextButtonStyle();
        TextButtonStyle playButtonStyle = new TextButtonStyle();

        // Mage Button Style
        mageButtonStyle.up = skin.getDrawable("button-emphasis-disabled");
        mageButtonStyle.font = font;

        // Archer Button Style
        skeletonButtonStyle.up = skin.getDrawable("button-emphasis-disabled");
        skeletonButtonStyle.font = font;

        // Play Button Style
        playButtonStyle.up = skin.getDrawable("Button-BG-shadow");
        playButtonStyle.font = font;

        // Buttons
        mageSelectBtn = new TextButton("Mage", mageButtonStyle);
        skeletonSelectBtn = new TextButton("Skeleton", skeletonButtonStyle);
        playBtn = new TextButton("Play", playButtonStyle);

        // Create a table and fill the screen
        //table.setDebug(true);
        table.setBackground(bgTextureDrawable);
        table.setFillParent(true);
        stage.addActor(table);

        table.add(mageSelectBtn).width(100).padTop(200).padRight(50);
        table.add(skeletonSelectBtn).width(100).padTop(200);
        table.row().padTop(20);
        table.add(playBtn).height(50).colspan(2).center();


        im.addInputProcessor(stage);

        mageSelectBtn.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.update();

                // Scale the TextButton
                mageSelectBtn.getLabel().setFontScale(1.2f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                cursor.update();

                // Reset the TextButton
                mageSelectBtn.getLabel().setFontScale(1f);

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {

                // Select mage
                mageSelected = true;
                mageSelectBtn.getLabel().setColor(Color.RED);
                // Unselect Archer
                skeletonSelected = false;
                skeletonSelectBtn.getLabel().setColor(Color.WHITE);

                super.clicked(event, x, y);
            }

        });

        skeletonSelectBtn.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.update();

                // Scale the TextButton
                skeletonSelectBtn.getLabel().setFontScale(1.2f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                cursor.update();

                // Reset the TextButton
                skeletonSelectBtn.getLabel().setFontScale(1f);

            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Select Archer
                skeletonSelected = true;
                skeletonSelectBtn.getLabel().setColor(Color.RED);
                // Unselect Mage
                mageSelected = false;
                mageSelectBtn.getLabel().setColor(Color.WHITE);
                super.clicked(event, x, y);
            }

        });

        playBtn.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hand.update();

                // Scale the TextButton
                playBtn.getLabel().setFontScale(1.2f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                cursor.update();

                // Reset the TextButton
                playBtn.getLabel().setFontScale(1f);

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Store the character preference based on what the user has selected.
                character_prefs.putBoolean("mage", mageSelected);
                character_prefs.putBoolean("skeleton", skeletonSelected);
                sceneManager.setScene(new GameScene(container));
                am.stop("MenuMusic");
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
        stage.draw();
        stage.act();
        em.update();
        em.draw(batch, shape);
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        em.dispose();
    }

    public boolean isMageSelected() {
        return mageSelected;
    }

    public boolean isSkeletonSelected() {
        return skeletonSelected;
    }

    // Singleton pattern method to get the instance
    public static CharacterSelectionScene getInstance() {
        return instance;
    }

}
