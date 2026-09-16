package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.metal.kit.vapor.Manager.DetectionManager;
import com.metal.kit.vapor.Manager.GameConfigManager;

/**
 * The {@code Cctv} class represents a stationary surveillance camera (CCTV) in the game.
 * <p>
 * The CCTV camera is an enemy that detects players within its detection zone. Once a player is detected,
 * the camera triggers an alert sound. The camera remains fixed at a given position and continuously monitors
 * its detection zone for the presence of the player. If the player enters the camera's field of view,
 * the camera will trigger the alert.
 * </p>
 *
 * @see Enemy
 * @see Player
 * @see DetectionManager
 * @see GameConfigManager
 */
public class Cctv extends Enemy{

    /**
     * Constructor to initialize a surveillance camera with basic parameters.
     *
     * @param name                The name of the surveillance camera.
     * @param x                   The initial position of the camera on the X axis.
     * @param y                   The initial position of the camera on the Y axis.
     * @param health              The health of the camera (used by the parent class {@link Enemy}).
     * @param damage              The damage dealt by the camera (used by the parent class {@link Enemy}).
     * @param speed               The speed of movement for the camera (useful if the camera moved, but unused here).
     * @param zoneX               The X position of the camera's detection zone.
     * @param zoneY               The Y position of the camera's detection zone.
     * @param zoneWidth           The width of the camera's detection zone.
     * @param zoneHeight          The height of the camera's detection zone.
     * @param detectionDistance   The distance at which the camera can detect a player.
     * @param detectionRadius     The angular radius within which the camera can detect a player.
     * @param detectionDirection  The detection direction of the camera, expressed in degrees.
     */
    public Cctv(String name, float x, float y, int health, int damage, float speed, float zoneX, float zoneY, float zoneWidth, float zoneHeight, float detectionDistance, float detectionRadius, float detectionDirection) {
        super(name, x, y, health, damage, speed, zoneX, zoneY, zoneWidth, zoneHeight, detectionDistance, detectionRadius, detectionDirection);
        this.alertSound = Gdx.audio.newSound(Gdx.files.internal(GameConfigManager.CCTV_ALERT_SOUND));
    }

    /**
     * Updates the state of the surveillance camera each frame.
     * This method checks if a player is detected within the camera's detection zone.
     * If a player is detected, the camera triggers an alert and notifies the {@link DetectionManager}.
     * If no player is detected, the alert is reset.
     *
     * @param player The player to be detected.
     * @param map    The game map used to check collisions and detection.
     * @param delta  The time elapsed since the last update (in seconds).
     */
    @Override
    public void update(Player player,  TiledMap map,  float delta) {
        super.update(player, map, delta);

        // Calculate the positions of the player and the CCTV camera
        Vector2 playerPosition = new Vector2(player.getX() + player.getSprite().getHeight()/2, player.getY() + player.getSprite().getHeight()/2);
        Vector2 enemyPosition = new Vector2(this.x, this.y);

        // Check if the player is within the camera's detection zone
        boolean playerDetected = detectPlayer(enemyPosition, playerPosition);
        if (playerDetected) {
            System.out.println(getName() + " detect an enemy !!!");
            DetectionManager.getInstance().updateDetection(true);
            triggerAlert();
        } else {
            resetAlert();
        }
    }

    /**
     * Triggers the alert sound when the player is detected within the camera's detection zone.
     * The alert sound will only play once for each detection event.
     */
    @Override
    public void triggerAlert() {
        super.triggerAlert();
        if (!alertTriggered) {
            alertSound.play();
            alertTriggered = true;
        }
    }

    /**
     * Resets the alert state, indicating that the alert has been lifted.
     * This method is called when the player exits the detection zone or when the alert should be reset.
     */
    public void resetAlert() {
        alertTriggered = false;
    }

    /**
     * Releases the resources used by the camera, including the alert sound.
     * This method is called when the camera is destroyed or when the game ends.
     */
    @Override
    public void dispose() {
        super.dispose();
        if (alertSound != null) {
            alertSound.dispose();
        }
    }
}
