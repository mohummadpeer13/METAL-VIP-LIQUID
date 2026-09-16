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
import com.metal.kit.vapor.Manager.ScoreManager;
import com.metal.kit.vapor.MyGdxGame;

/**
 * {@link Screen} implementation that represents the Game Over screen.
 * This screen displays the player's score and the best score, with options to replay or exit the game.
 * The Game Over screen is shown when the player finishes the game or loses.
 */
public class GameOverScreen implements Screen {
    /**
     * SpriteBatch used to draw the screen elements.
     */
    private SpriteBatch batch;

    /**
     * Skin used for styling UI components like buttons, labels, etc.
     */
    private Skin skin;

    /**
     * Stage for managing and rendering UI components.
     */
    private Stage stage;

    /**
     * Table layout for arranging UI elements (labels, buttons) in the Game Over screen.
     */
    private Table table;

    /**
     * Background image displayed on the Game Over screen.
     */
    private Image backgroundImage;

    /**
     * Button that allows the player to replay the game.
     */
    private TextButton playButton;

    /**
     * Button that allows the player to quit the game.
     */
    private TextButton exitButton;

    /**
     * Name of the player who finished the game.
     */
    private String playerName;

    /**
     * The score of the player when the game ends.
     */
    private int score;

    /**
     * Manager responsible for saving and retrieving the player's score.
     */
    private ScoreManager scoreManager;

    /**
     * Constructs the GameOverScreen.
     * Initializes the player name and score, and saves the score using the {@link ScoreManager}.
     *
     * @param playerName The name of the player.
     * @param score The score of the player at the end of the game.
     */
    public GameOverScreen(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.scoreManager = new ScoreManager();
        scoreManager.saveScore(playerName, score);

    }

    /**
     * Called when the screen is shown.
     * This method sets up the user interface components such as the background, buttons, and labels.
     * It also defines the behavior for the "Play Again" and "Quit" buttons.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        // Set the background image for the game over screen
        backgroundImage = new Image(new Texture(GameConfigManager.GAMEOVER_BACKGROUND_TEXTURE));

        // Load fonts for the buttons and labels
        BitmapFont buttonFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);

        // Set up the button textures and styles
        Texture buttonTexture = new Texture("menu/originalButton.png");
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonDrawable;
        textButtonStyle.font = buttonFont;
        textButtonStyle.down = buttonDrawable;

        Color textColor = new Color(0, 0, 0, 1);
        textButtonStyle.fontColor = textColor;

        // Create the "Play Again" and "Quit" buttons
        playButton = new TextButton("R E P L A Y", textButtonStyle);
        exitButton = new TextButton("Q U I T", textButtonStyle);

        table = new Table();
        table.setFillParent(true);

        table.top();

        // Load the best score from the score manager
        String bestScore = scoreManager.getBestScore();

        // Display the player's score
        Label yourScoreLabel = new Label("YOUR SCORE : " + score, new Label.LabelStyle(titleFont, textColor));
        table.add(yourScoreLabel).expandX().pad(260,0,30,700).colspan(1);
        table.row();

        // Display the best score
        Label bestScoreLabel = new Label((bestScore), new Label.LabelStyle(titleFont, textColor));
        table.add(bestScoreLabel).expandX().pad(20,0,30,700).colspan(1);
        table.row();

        // Add the "Play Again" and "Quit" buttons to the table
        table.add(playButton).pad(30, 0, 30, 700).center();
        table.row();

        table.add(exitButton).pad(30, 0, 0, 700).center();
        table.row();

        // Add the table and background image to the stage
        stage.addActor(backgroundImage);
        stage.addActor(table);

        // Set the listener for the "Play Again" button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MyGdxGame myGame = (MyGdxGame) Gdx.app.getApplicationListener();
                myGame.setScreen(new GameScreen(playerName,"player1", 1,0));
                dispose();
            }
        });

        // Set the listener for the "Quit" button
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

    }

    /**
     * Called when the screen needs to be rendered.
     * This method clears the screen and draws the stage.
     *
     * @param delta The time elapsed since the last render.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    /**
     * Called when the screen is resized.
     * This method is empty and can be implemented if necessary to handle resizing behavior.
     *
     * @param width The new width of the screen.
     * @param height The new height of the screen.
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
        System.out.println("GAMEOVER SCREEN DISPOSE ...");

        // Dispose of the SpriteBatch if it exists
        if (batch != null) {
            batch.dispose();
        }

        // Dispose the stage and its actors
        if (stage != null) {
            stage.clear();
            stage.dispose();
        }

        // Dispose the skin, which holds assets like fonts and textures
        if (skin != null) {
            skin.dispose();
        }

    }

}
