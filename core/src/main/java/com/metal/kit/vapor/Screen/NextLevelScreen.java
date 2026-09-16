package com.metal.kit.vapor.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.metal.kit.vapor.MyGdxGame;

/**
 * The {@link NextLevelScreen} class represents the screen shown when the player progresses to the next level.
 * This screen provides options to continue the game or quit, and displays the necessary level information.
 */
public class NextLevelScreen implements Screen {
    /** The {@link SpriteBatch} used for rendering the screen's graphical elements. */
    private SpriteBatch batch;

    /** The {@link Skin} used for styling UI elements such as buttons. */
    private Skin skin;

    /** The {@link Stage} that holds and manages all actors (UI elements) in this screen. */
    private Stage stage;

    /** The {@link Table} used for organizing and positioning UI elements on the screen. */
    private Table table;

    /** The background image of the next level screen. */
    private Image backgroundImage;

    /** The button that allows the player to continue to the next level. */
    private TextButton playButton;

    /** The button that allows the player to quit the game. */
    private TextButton exitButton;

    /** The player's name. */
    private String playerName;

    /** The character selected by the player. */
    private String playerSelect;

    /** The player's score. */
    private int score;

    /** The player's current level. */
    private int playerLevel;

    /**
     * Constructs the NextLevelScreen.
     * Initializes the player details, such as name, selected character, level, and score.
     *
     * @param playerName   The name of the player.
     * @param playerSelect The selected character for the player.
     * @param playerLevel  The level of the player.
     * @param score        The score of the player.
     */
    public NextLevelScreen(String playerName, String playerSelect, int playerLevel, int score) {
        this.playerName = playerName;
        this.playerSelect = playerSelect;
        this.score = score;
        this.playerLevel = playerLevel;
    }

    /**
     * This method is called when the screen is displayed. It initializes the UI elements like buttons, fonts,
     * and background, and sets up the interactions for the buttons.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        backgroundImage = new Image(new Texture(GameConfigManager.NEXTLEVEL_BACKGROUND_TEXTURE));

        // Load bitmap fonts for buttons and titles
        BitmapFont buttonFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);

        // Button style setup using a background texture and font
        Texture buttonTexture = new Texture("menu/originalButton.png");
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonDrawable;
        textButtonStyle.font = buttonFont;
        textButtonStyle.down = buttonDrawable;

        Color textColor = new Color(0, 0, 0, 1);
        textButtonStyle.fontColor = textColor;

        // Create buttons for continuing or quitting
        playButton = new TextButton("C O N T I N U E", textButtonStyle);
        exitButton = new TextButton("Q U I T", textButtonStyle);

        // Set up the table layout
        table = new Table();
        table.setFillParent(true);

        table.top();

        // Add buttons to the table with padding and alignment
        table.add(playButton).pad(400, 0, 30, 700).center();
        table.row();

        table.add(exitButton).pad(30, 0, 0, 700).center();
        table.row();

        // Add background and table to the stage
        stage.addActor(backgroundImage);
        stage.addActor(table);

        // Set up listener for the play button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MyGdxGame myGame = (MyGdxGame) Gdx.app.getApplicationListener();
                myGame.setScreen(new GameScreen(playerName,playerSelect,playerLevel,score));
                dispose();
            }
        });

        // Set up listener for the exit button
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    /**
     * This method is called continuously to render the screen's graphics.
     * It updates the scene and handles any drawing tasks.
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    /**
     * Called when the window is resized.
     * This method updates the stage's viewport.
     *
     * @param width  The new width of the window
     * @param height The new height of the window
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called when the application is paused
     */
    @Override
    public void pause() {

    }

    /**
     * Called when the application resumes after being paused.
     */
    @Override
    public void resume() {

    }

    /**
     * Méthode appelée lorsque l'écran est caché (lorsque cet écran est remplacé par un autre).
     */
    @Override
    public void hide() {

    }

    /**
     * Disposes of the resources used by this screen.
     * This method cleans up the assets and UI elements to free memory.
     */
    @Override
    public void dispose() {
        // Print for debugging purposes
        System.out.println("NEXT LEVEL SCREEN DISPOSE ...");

        // Dispose of the SpriteBatch if it exists
        if (batch != null) {
            batch.dispose();
        }

        // Dispose of the Stage and its associated actors
        if (stage != null) {
            stage.clear();
            stage.dispose();
        }

        // Dispose of the Skin if it exists
        if (skin != null) {
            skin.dispose();
        }
    }
}
