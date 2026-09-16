package com.metal.kit.vapor.Manager;


/**
 * The detection manager that tracks the player's detection status.
 * This class uses the Singleton pattern to ensure that only one instance of the detection manager is used.
 * It is responsible for checking whether the player is currently detected by an enemy in the game.
 */
public class DetectionManager {

    /**
     * The unique instance of the detection manager.
     */
    private static DetectionManager instance;

    /**
     * A variable that stores the player's detection status.
     * This variable indicates whether the player is currently detected by an enemy.
     * It is updated via the {@link DetectionManager#updateDetection(boolean)} method.
     */
    public boolean detectPlayer = false;

    /**
     * Private constructor to prevent direct instantiation of the class.
     * Used as part of the Singleton pattern.
     */
    private DetectionManager() {}

    /**
     * Returns the unique instance of the DetectionManager.
     * If the instance does not exist, it will be created.
     *
     * @return The unique instance of the DetectionManager.
     */
    public static DetectionManager getInstance() {
        if (instance == null) {
            instance = new DetectionManager();
        }
        return instance;
    }

    /**
     * Updates the player's detection status.
     * This method is called to indicate whether the player is currently detected or not.
     *
     * @param isPlayerDetected True if the player is detected, false otherwise.
     */
    public void updateDetection(boolean isPlayerDetected) {
        this.detectPlayer = isPlayerDetected;
    }

    /**
     * Checks if the player is currently detected.
     *
     * @return True if the player is detected, otherwise false.
     */
    public boolean isPlayerDetected() {
        return detectPlayer;
    }
}
