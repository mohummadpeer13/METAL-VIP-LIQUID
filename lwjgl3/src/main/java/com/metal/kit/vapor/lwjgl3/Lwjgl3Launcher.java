package com.metal.kit.vapor.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.metal.kit.vapor.MyGdxGame;

/**
 * Launches the desktop (LWJGL3) application for the game.
 * This class sets up the necessary configuration and starts the application using the LWJGL3 backend.
 */
public class Lwjgl3Launcher {

    /**
     * The main entry point for launching the desktop application.
     * It checks if a new JVM is needed (e.g., for macOS compatibility) and then starts the application.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        createApplication();
    }

    /**
     * Creates and launches the LWJGL3 application with the given configuration.
     * It initializes the game with the default configuration and starts the application.
     *
     * @return The {@link Lwjgl3Application} instance used to run the game.
     */
    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MyGdxGame(), getDefaultConfiguration());
    }

    /**
     * Returns the default configuration for the LWJGL3 application.
     * This configuration sets up the window size, title, Vsync, and FPS settings.
     *
     * @return The {@link Lwjgl3ApplicationConfiguration} object with the default settings.
     */
    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Metal Kit Vapor");
        //// Vsync limits the frames per second to what your hardware can display, and helps eliminate
        //// screen tearing. This setting doesn't always work on Linux, so the line after is a safeguard.
        configuration.useVsync(true);
        //// Limits FPS to the refresh rate of the currently active monitor, plus 1 to try to match fractional
        //// refresh rates. The Vsync setting above should limit the actual FPS to match the monitor.
        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);
        //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        //// useful for testing performance, but can also be very stressful to some hardware.
        //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
        configuration.setWindowedMode(GameConfigManager.WINDOW_WIDTH, GameConfigManager.WINDOW_HEIGHT);
        //// You can change these files; they are in lwjgl3/src/main/resources/ .
        return configuration;
    }
}
