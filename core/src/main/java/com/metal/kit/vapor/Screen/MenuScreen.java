package com.metal.kit.vapor.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.metal.kit.vapor.MyGdxGame;
import com.metal.kit.vapor.Manager.ScoreManager;
import com.metal.kit.vapor.characters.Score;
import java.util.List;

/**
 * {@link MenuScreen} represents the main menu screen of the game.
 * This screen contains interactive elements such as buttons for starting the game,
 * accessing options, quitting the game, and an input field for entering the player's name.
 */
public class MenuScreen implements Screen {
    /** The sprite batch used for rendering the screen. */
    private SpriteBatch batch;

    /** The skin used for UI elements (buttons, text fields, etc.). */
    private Skin skin;

    /** The stage that holds all the UI elements. */
    private Stage stage;

    /** The table layout used to arrange the UI elements. */
    private Table table;

    /** The input field where the player can enter their name. */
    private TextField nameInputField;

    /** The button that starts the game. */
    private TextButton playButton;

    /** The button to open the options menu. */
    private TextButton optionButton;

    /** The button to view the high scores. */
    private TextButton scoreButton;

    /** The button to quit the game. */
    private TextButton exitButton;

    /** The button to go back. */
    private TextButton backButton;

    /** The background image for the menu screen. */
    private Image backgroundImage;

    /** The music that plays in the background during the menu. */
    private Music menuMusic;

    /** The button to select character 1. */
    private TextButton character1Button;

    /** The button to select character 2. */
    private TextButton character2Button;

    /** The button to select character 3. */
    private TextButton character3Button;

    /** The image representing character 1. */
    private Image character1Image;

    /** The image representing character 2. */
    private Image character2Image;

    /** The image representing character 3. */
    private Image character3Image;

    /** The name of the player entered in the input field. */
    private String playerName = "Player";

    /** The selected player character. */
    private String playerSelect = "player1";

    /** The level for the player. */
    private int playerLevel = 1;

    /** The current state of the menu screen ("Menu", "Options", "Score"). */
    String state = "Menu";

    /** The manager displaying scores. */
    private ScoreManager scoreManager;

    /**
     * Constructs the {@link MenuScreen} and initializes the background music for the menu.
     */
    public MenuScreen() {
        String musicFilePath = GameConfigManager.MENU_SOUND;

        if (isFileExists(musicFilePath)) {
            try {
                menuMusic = Gdx.audio.newMusic(Gdx.files.internal(musicFilePath));
                menuMusic.setLooping(true);
                menuMusic.play();
            } catch (Exception e) {
                Gdx.app.error("MenuScreen", "Error loading music. The game will continue without music.", e);
                menuMusic = null;
            }
        } else {
            Gdx.app.log("MenuScreen", "Music file not found: " + musicFilePath);
            menuMusic = null;
        }
    }

    /**
     * Vérifie si un fichier existe dans le chemin donné.
     *
     * @param filePath Le chemin du fichier à vérifier.
     * @return true si le fichier existe, sinon false.
     */
    private boolean isFileExists(String filePath) {
        FileHandle fileHandle = Gdx.files.internal(filePath);
        return fileHandle.exists();
    }

    /**
     * Called when the screen is shown. Initializes UI elements such as buttons, labels, music, and other graphical resources.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        // Load bitmap fonts for buttons and titles
        BitmapFont buttonFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);

        // Create buttons with a background texture and font
        Texture buttonTexture = new Texture("menu/originalButton.png");
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = buttonDrawable;
        textButtonStyle.font = buttonFont;
        textButtonStyle.down = buttonDrawable;

        Color textColor = new Color(0, 0, 0, 1); // Couleur du texte en noir
        textButtonStyle.fontColor = textColor;

        // Create game buttons
        playButton = new TextButton("P L A Y", textButtonStyle);
        optionButton = new TextButton("O P T I O N S", textButtonStyle);
        scoreButton = new TextButton("S C O R E", textButtonStyle);
        exitButton = new TextButton("Q U I T", textButtonStyle);
        backButton = new TextButton("BACK", textButtonStyle);

        // Style for the name input field
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = buttonFont;
        textFieldStyle.fontColor = textColor;
        textFieldStyle.messageFontColor = textColor;
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture("menu/originalButton.png")));

        // Input field for entering player name
        nameInputField = new TextField("", textFieldStyle);
        nameInputField.setMessageText("Enter your name ...");
        nameInputField.setAlignment(Align.center);

        // Add buttons for character selection
        character1Button = new TextButton("Player 1", textButtonStyle);
        character2Button = new TextButton("Player 2", textButtonStyle);
        character3Button = new TextButton("Player 3", textButtonStyle);

        // Load character images
        character1Image = new Image(new Texture("menu/character1.png"));
        character2Image = new Image(new Texture("menu/character2.png"));
        character3Image = new Image(new Texture("menu/character3.png"));

        // Create the table to organize UI elements
        table = new Table();
        table.setFillParent(true);

        if (state.equals("Menu")) {
            loadMenu();
        } else if (state.equals("Options")){
            loadOptions();
        } else if (state.equals("Score")){
            if (scoreManager == null) {
                scoreManager = new ScoreManager();
            }
            loadScore();
        }

        // Add all elements to the stage
        stage.addActor(backgroundImage);
        stage.addActor(table);

        // Play button listener
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerName = nameInputField.getText();

                if (playerName.isEmpty()) {
                    playerName = "Player";
                }

                MyGdxGame myGame = (MyGdxGame) Gdx.app.getApplicationListener();
                myGame.setScreen(new GameScreen(playerName, playerSelect, playerLevel,0));
                dispose();
            }
        });

        // Options button listener
        optionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                state = "Options";
                show();
            }
        });

        // Score button listener
        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                state = "Score";
                show();
            }
        });

        // Quit button listener
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Select player1 button listener
        character1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerSelect = "player1";
                state = "Menu";
                show();
            }
        });

        // Select player2 button listener
        character2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerSelect = "player2";
                state = "Menu";
                show();
            }
        });

        // Select player3 button listener
        character3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerSelect = "player3";
                state = "Menu";
                show();
            }
        });

        // Back button listener
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                state = "Menu";
                show();
            }
        });

    }

    /**
     * Called to render the screen. It is called each frame of the application.
     * This method handles the rendering of the stage and background music control.
     *
     * @param delta Time elapsed since the last frame
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the stage
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
        stage.getViewport().update(width, height, true);
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
        // Aucun traitement spécifique lors de la disparition de l'écran
    }

    /**
     * Disposes of the resources used by this screen.
     * This method cleans up the assets and UI elements to free memory.
     */
    public void dispose() {
        System.out.println("MENU SCREEN DISPOSE ...");

        // Stop and dispose of the background music
        menuMusic.stop();
        menuMusic.dispose();

        // Dispose of the SpriteBatch used for rendering
        batch.dispose();

        // Dispose of the Stage to release actors and resources
        stage.clear();
        stage.dispose();

        // Dispose of the Skin used for UI elements
        skin.dispose();

    }

    /**
     * Loads and displays the main menu UI elements.
     */
    private void loadMenu() {
        backgroundImage = new Image(new Texture(GameConfigManager.MENU_BACKGROUND_TEXTURE));

        table.add(nameInputField).expandX().minWidth(350).pad(0, 0, 40, 950);
        table.row();

        table.add(playButton).pad(0, 0, 20, 950).center();
        table.row();

        table.add(optionButton).pad(0, 0, 20, 950).center();
        table.row();

        table.add(scoreButton).pad(0, 0, 20, 950).center();
        table.row();

        table.add(exitButton).pad(20, 0, 60, 950).center();
        table.row();
    }

    /**
     * Loads and displays the options menu UI elements, such as character selection.
     */
    private void loadOptions() {
        backgroundImage = new Image(new Texture(GameConfigManager.MENUOPTIONS_BACKGROUND_TEXTURE));
        table.top().left();

        table.add(character1Button).expandX().pad(170,10,10,10);
        table.add(character2Button).expandX().pad(170,10,10,10);
        table.add(character3Button).expandX().pad(170,10,10,10);
        table.row();

        table.add(character1Image).pad(10,50,10,10);
        table.add(character2Image).pad(10,50,10,10);
        table.add(character3Image).pad(10,50,10,10);
        table.row();
    }

    /**
     * Loads and displays the options menu UI elements, such as character selection.
     */
    private void loadScore() {
        // Load bitmap fonts for buttons and titles
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/bitmap.fnt"), Gdx.files.internal("fonts/bitmap.png"), false);
        backgroundImage = new Image(new Texture(GameConfigManager.TOPSCORES_BACKGROUND_TEXTURE));

        List<Score> topScores = scoreManager.getTopScores();

        // Create a list to display the scores
        StringBuilder scoreListText = new StringBuilder();
        for (Score score : topScores) {
            scoreListText.append(score.getPlayerName())
                .append(" : ")
                .append(score.getScore())
                .append("\n");
        }

        // Create a label to display the scores in the ScroolPane
        Label scoreListLabel = new Label(scoreListText.toString(), new Label.LabelStyle(titleFont, Color.BLACK));
        ScrollPane scrollPane = new ScrollPane(scoreListLabel);

        table.top().left();
        table.add(scrollPane).expand().pad(200,0,0,800);
        table.row();
        table.add(backButton).expand().pad(0,0,200,800);
    }

}
