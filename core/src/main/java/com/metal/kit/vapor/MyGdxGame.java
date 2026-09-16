package com.metal.kit.vapor;

import com.badlogic.gdx.Game;
import com.metal.kit.vapor.Screen.GameScreen;
import com.metal.kit.vapor.Screen.MenuScreen;


/**
 * The main game class for the game application.
 * This class is the entry point for the game and extends {@link com.badlogic.gdx.Game},
 * providing the logic for initializing and switching between game screens.
 * It manages the game lifecycle and ensures the correct screen is displayed at the start.
 */
public class MyGdxGame extends Game{
    /** Singleton instance of the game */
    private static MyGdxGame instance;

    /**
     * Gets the singleton instance of the game.
     * This method provides access to the unique instance of the game throughout the application.
     *
     * @return The instance of {@link MyGdxGame}.
     */
    public static MyGdxGame getInstance() {
        return instance;
    }

    /**
     * Called when the game is first created.
     * Initializes the game and sets the initial screen to the {@link MenuScreen}.
     * This is the entry point where the game begins.
     */
    @Override
    public void create() {
        instance = this;
        this.setScreen(new MenuScreen());
    }

    /**
     * Renders the current screen of the game.
     * This method is called continuously in the game loop to update and draw the current screen.
     */
    @Override
    public void render() {
        super.render();
    }

    /**
     * Disposes of the resources used by the game.
     * This method is called when the game is closed or the application is destroyed,
     * and it ensures that any allocated resources are cleaned up properly.
     */
    @Override
    public void dispose() {
    }
}
