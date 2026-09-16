package com.metal.kit.vapor.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.metal.kit.vapor.Manager.CameraManager;
import com.metal.kit.vapor.Manager.EnemyManager;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.metal.kit.vapor.Manager.WeaponManager;
import com.metal.kit.vapor.characters.Player;
import com.metal.kit.vapor.characters.Enemy;
import com.metal.kit.vapor.characters.Weapon;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.metal.kit.vapor.Manager.CollisionMapManager;
import com.metal.kit.vapor.MyGdxGame;
import java.util.ArrayList;
import com.metal.kit.vapor.Manager.DetectionManager;

/**
 * The {@link GameScreen} class represents the main game screen where the player can interact with the game world,
 * control the character, collect weapons, and fight enemies.
 * It handles the rendering of the map, player, enemies, and weapons, as well as user input for character actions.
 */
public class GameScreen implements Screen {

    /**
     * The SpriteBatch used for rendering the game graphics, such as the player, enemies, and weapons.
     */
    public SpriteBatch batch;

    /**
     * The TiledMap object that holds the game map.
     */
    public TiledMap map;

    /**
     * The TiledMapRenderer used for rendering the TiledMap to the screen.
     */
    public TiledMapRenderer mapRenderer;

    /**
     * The CameraManager responsible for managing the camera's position and movement within the game world.
     */
    private CameraManager cameraManager;

    /**
     * The width of the game map, calculated based on the map's properties.
     */
    private float mapWidth;

    /**
     * The height of the game map, calculated based on the map's properties.
     */
    private float mapHeight;

    /**
     * The player character in the game. This object contains information such as position, health, score, etc.
     */
    public Player player;

    /**
     * The name of the player character.
     */
    private String playerName;

    /**
     * The selected player character's name.
     */
    private String playerSelect;

    /**
     * The current level of the player.
     */
    private int playerLevel;

    /**
     * The current score of the player.
     */
    private int score;

    /**
     * The EnemyManager that handles the enemies in the game, including their behavior, creation, and updates.
     */
    public EnemyManager enemyManager;

    /**
     * The WeaponManager responsible for managing weapons in the game, including spawning and collection.
     */
    public WeaponManager weaponManager;

    /**
     * The sound effect that plays when the player picks up a weapon.
     */
    public Sound pickSoundWeapon;

    /**
     * The sound effect that plays when the player shoots their weapon.
     */
    public Sound shootSound;

    /**
     * A boolean flag that indicates whether to show the detection area of enemies on the screen.
     */
    private boolean showDetectionArea;

    /**
     * A ShapeRenderer used to draw shapes detection areas on the screen.
     */
    private ShapeRenderer shapeRenderer;

    /**
     * A BitmapFont used for rendering text on the screen, such as displaying the score and player information.
     */
    BitmapFont font = new BitmapFont();

    /**
     * Constructor for {@link GameScreen}.
     *
     * @param playerName   The name of the player.
     * @param playerSelect The selected player character.
     * @param playerLevel  The current level of the player.
     * @param score        The player's current score.
     */
    public GameScreen(String playerName, String playerSelect, int playerLevel, int score) {
        this.playerName = playerName;
        this.playerSelect = playerSelect;
        this.playerLevel = playerLevel;
        this.score = score;

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        // Exceptions
        try {
            this.pickSoundWeapon = Gdx.audio.newSound(Gdx.files.internal(GameConfigManager.PICK_SOUND));
            this.shootSound = Gdx.audio.newSound(Gdx.files.internal(GameConfigManager.GUN_SOUND));
        } catch (Exception e) {
            throw new RuntimeException("Error loading sound assets: " + e.getMessage(), e);
        }

    }

    /**
     * Loads fonts for rendering text on the screen.
     */
    private void loadFonts(){
        try {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/lato.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 20;
            font = generator.generateFont(parameter);
            generator.dispose();
        } catch (Exception e) {
            throw new RuntimeException("Error loading fonts: " + e.getMessage(), e);
        }
    }

    /**
     * Loads the map based on the current player level.
     */
    private void loadMaps(){
        try {
            map = new TmxMapLoader().load("map" + playerLevel + "/Map.tmx");
            mapRenderer = new OrthogonalTiledMapRenderer(map);
            mapWidth = map.getProperties().get("width", Integer.class) * 32;
            mapHeight = map.getProperties().get("height", Integer.class) * 32;
            cameraManager = new CameraManager(map);
        } catch (Exception e) {
            throw new RuntimeException("Error loading map for level " + playerLevel + ": " + e.getMessage(), e);
        }
    }

    /**
     * Called when the screen is shown. Initializes the game components like fonts, maps, player, enemies, and input listeners.
     */
    @Override
    public void show() {
        // fonts
        loadFonts();

        // maps
        loadMaps();

        // Initialize the player
        player = new Player(playerName,100, 300, 100, 10, GameConfigManager.PLAYER_SPEED, score);
        player.initTexture("player/"+playerSelect+"-idle.png", "player/"+playerSelect+"-running.png", 6);


        // Initialize enemies and weapons
        enemyManager = new EnemyManager(playerLevel);
        weaponManager = new WeaponManager();

        // Input listener for toggling detection area and player attack
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.S) {
                    showDetectionArea = !showDetectionArea;
                    return true;
                }
                if (keycode == Input.Keys.SPACE) {
                    player.attack(enemyManager.getEnemies(), shootSound);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Renders the game screen, including the map, player, enemies, weapons, and UI elements.
     *
     * @param delta Time elapsed since the last frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float deltaTime = Gdx.graphics.getDeltaTime();

        // Update the camera to follow the player
        cameraManager.update(player.getX(), player.getY());

        // Render the map
        mapRenderer.setView(cameraManager.getCamera());
        mapRenderer.render();

        // Prepare SpriteBatch for rendering
        batch.setProjectionMatrix(cameraManager.getCamera().combined);

        // Update and render the player
        player.update(delta, map);
        handleNextLevel(delta);

        // Update and render the enemies
        for (Enemy enemy : enemyManager.getEnemies()) {
            enemy.update(player, map, delta);
        }

        batch.begin();

        player.render(batch);

        // Render enemy
        for (Enemy enemy : enemyManager.getEnemies()) {
            enemy.render(batch);
        }

        // Render weapons
        for (Weapon weapon : weaponManager.getWeapons()) {
            weapon.render(batch);
            if (weapon.isCollected(player.getX(), player.getY(), player.sprite.getWidth(), player.sprite.getHeight())) {
                pickSoundWeapon.play();
                player.addWeapon(weapon);
                weaponManager.getWeapons().removeValue(weapon, true);
                break;
            }
        }

        // Draw detection area if enabled
        drawDetectionArea();

        // Draw on-screen info (score, health, weapons)
        drawScreenInfo();

        batch.end();
    }

    /**
     * Handles transitioning to the next level if the player reaches the "next level" area.
     *
     * @param delta Time elapsed since the last frame.
     */
    private void handleNextLevel(float delta) {
        float newX = player.getX();
        float newY = player.getY();

        // Check if the player collides with the next level trigger area
        if (CollisionMapManager.isCollisionWithNextLevel(map, "next-level-border", newX, newY, player.getSprite().getWidth(), player.getSprite().getHeight())) {
            MyGdxGame myGame = (MyGdxGame) Gdx.app.getApplicationListener();

            // Reset detection and player state, and transition to the next level
            DetectionManager.getInstance().updateDetection(false);
            player.dispose();
            playerLevel += 1;
            myGame.setScreen(new NextLevelScreen(player.getName(),playerSelect, playerLevel, player.getScore()));
        }
    }

    /**
     * Renders the on-screen information such as player name, score, health, and collected weapons.
     */
    private void drawScreenInfo() {
        float textX = cameraManager.getCamera().position.x - Gdx.graphics.getWidth() / 2 + 30;
        float textY = cameraManager.getCamera().position.y + Gdx.graphics.getHeight() / 2 - 20;
        font.draw(batch, "Player name : " + playerName, textX, textY );
        textY -= 20;
        font.draw(batch, "Score : " + player.getScore(), textX, textY);
        textY -= 20;
        font.draw(batch, "Health : " + player.getHp(), textX, textY);
        textY -= 20;
        font.draw(batch, "Weapons ", textX, textY );
        textY -= 60;

        // Render collected weapons
        ArrayList<Weapon> weapons = player.getInventory().getWeapons();
        for (Weapon weapon : weapons) {
            Texture weaponTexture = weapon.getTexture();
            batch.draw(weaponTexture, textX, textY);
            textY -= 50;
        }
    }

    /**
     * Renders the detection area of enemies if enabled.
     */
    private void drawDetectionArea() {
        if (showDetectionArea) {
            shapeRenderer.setProjectionMatrix(cameraManager.getCamera().combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 1, 0.5f);

            // Render detection areas for each enemy
            for (Enemy enemy : enemyManager.getEnemies()) {
                float cctvX = enemy.getX();
                float cctvY = enemy.getY();
                float detectionDistance = enemy.getDetectionDistance();
                float detectionRadius = enemy.getDetectionRadius();
                float detectionDirection = enemy.detectionDirection;

                shapeRenderer.setColor(0, 0, 1, 0.5f);
                shapeRenderer.arc(cctvX, cctvY, detectionDistance, detectionDirection-70, detectionRadius/2);
            }
            shapeRenderer.end();
        }
    }

    /**
     * Called when the window is resized.
     * This method updates the stage's viewport.
     *
     * @param width  The new width of the window
     * @param height The new height of the window
     */
    @Override
    public void resize(int width, int height) {}

    /**
     * Called when the application is paused
     */
    @Override
    public void pause() {}

    /**
     * Called when the application resumes after being paused.
     */
    @Override
    public void resume() {}

    /**
     * Méthode appelée lorsque l'écran est caché (lorsque cet écran est remplacé par un autre).
     */
    @Override
    public void hide() {}

    /**
     * Disposes of the resources used by this screen.
     * This method cleans up the assets and UI elements to free memory.
     */
    @Override
    public void dispose() {
        try {
            System.out.println("GAME SCREEN DISPOSE ...");

            // Dispose of the tiled map and its renderer
            if (map != null) {
                map.dispose();
            }

            // Dispose of the SpriteBatch if it exists
            if (batch != null) {
                batch.dispose();
            }

            // Dispose of the player object and its resources (textures, animations, etc.)
            if (player != null) {
                player.dispose();
            }

            // Dispose of the sound assets to free up memory
            if (pickSoundWeapon != null) {
                pickSoundWeapon.dispose();
            }
            if (shootSound != null) {
                shootSound.dispose();
            }

            // Dispose of the enemy manager and its resources (e.g., enemies, textures)
            if (enemyManager != null) {
                enemyManager.dispose();
            }

            // Dispose of the weapon manager and its resources (e.g., weapons, textures)
            if (weaponManager != null) {
                weaponManager.dispose();
            }

            // Dispose of the font resource used for text rendering
            if (font != null) {
                font.dispose();
            }

            // Dispose of the shape renderer
            if (shapeRenderer != null) {
                shapeRenderer.dispose();
            }

            // Dispose of the camera manager if necessary (usually handled by the map renderer, but for completeness)
            if (cameraManager != null) {
                cameraManager.dispose();
            }
        } catch (Exception e) {
            throw new RuntimeException("ERROR DURING GAME SCREEN DISPOSE : " + e.getMessage(), e);
        }
    }
}
