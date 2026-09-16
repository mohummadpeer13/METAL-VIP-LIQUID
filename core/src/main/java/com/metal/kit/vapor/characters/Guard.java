package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.metal.kit.vapor.Manager.DetectionManager;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.badlogic.gdx.math.Vector2;

/**
 * The {@code Guard} class represents an enemy guard in the game.
 * <p>
 * A guard is a mobile enemy that can detect a player within a specific area. When the player is detected,
 * the guard triggers an alert sound and begins pursuing the player within its detection range. The guard also
 * has animations for its movement and idle states.
 * </p>
 * <p>
 * The guard patrols a defined area and is capable of detecting a player when they enter its detection radius.
 * If the player is detected, the guard will trigger an alert and chase after the player. If the player is not
 * detected, the guard continues patrolling in its designated zone.
 * </p>
 *
 * @see Enemy
 * @see Player
 * @see DetectionManager
 * @see GameConfigManager
 */
public class Guard extends Enemy{

    /**
     * Constructor to initialize a guard with basic parameters.
     *
     * @param name                The name of the guard.
     * @param x                   The initial X position of the guard.
     * @param y                   The initial Y position of the guard.
     * @param health              The initial health of the guard.
     * @param damage              The damage dealt by the guard.
     * @param speed               The movement speed of the guard.
     * @param zoneX               The X position of the guard's patrol zone.
     * @param zoneY               The Y position of the guard's patrol zone.
     * @param zoneWidth           The width of the guard's patrol zone.
     * @param zoneHeight          The height of the guard's patrol zone.
     * @param detectionDistance   The distance at which the guard can detect the player.
     * @param detectionRadius     The angular radius within which the guard can detect the player.
     * @param detectionDirection  The direction of detection for the guard, expressed in degrees.
     */
    public Guard(String name, float x, float y, int health, int damage, float speed, float zoneX, float zoneY, float zoneWidth, float zoneHeight,  float detectionDistance, float detectionRadius, float detectionDirection) {
        super(name, x, y, health, damage, speed, zoneX, zoneY, zoneWidth, zoneHeight, detectionDistance, detectionRadius, detectionDirection);
        this.alertSound = Gdx.audio.newSound(Gdx.files.internal(GameConfigManager.CCTV_ALERT_SOUND));
        this.facingRight = true;
    }

    /**
     * Updates the guard's state on every frame.
     * This method checks if the player is detected. If the player is detected, the guard triggers the alert
     * and pursues the player. If the player is not detected, the guard continues patrolling within its defined zone.
     *
     * @param player The player to detect and pursue.
     * @param map    The game map used to check for collisions.
     * @param delta  The time elapsed since the last update (in seconds).
     */
    @Override
    public void update( Player player,  TiledMap map, float delta) {
        stateTime += delta;
        TextureRegion currentFrame;

        // Update the guard's animation based on the movement state
        if (facingRight || !facingRight) {
            currentFrame = runAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        sprite.setRegion(currentFrame);

        // Calculate the positions for player and guard
        Vector2 playerPosition = new Vector2(player.getX() + player.getSprite().getHeight()/2, player.getY() + player.getSprite().getHeight()/2);
        Vector2 enemyPosition = new Vector2(this.x, this.y);

        // Detect if the player is within the guard's detection range
        boolean playerDetected = detectPlayer(enemyPosition, playerPosition);
        if (playerDetected) {
            System.out.println(getName() + " detect " + player.getName());
            DetectionManager.getInstance().updateDetection(true);
            triggerAlert();
        } else {
            if (!DetectionManager.getInstance().isPlayerDetected()) {
                resetAlert();
                move(delta, map);
            }
        }

        // Pursue the player if detected
        if (DetectionManager.getInstance().isPlayerDetected()) {
            pursuitPlayer(player, map, delta);
        }
    }

    /**
     * Moves the guard within its patrol zone.
     * The guard moves horizontally within the specified zone defined by {@code zoneX}, {@code zoneY},
     * {@code zoneWidth}, and {@code zoneHeight}. The guard changes direction when it reaches the zone boundaries.
     *
     * @param delta The time elapsed since the last update (in seconds).
     * @param map   The game map used to check for collisions during movement.
     */
    @Override
    public void move(float delta, TiledMap map) {

        if (facingRight) {
            x += GameConfigManager.GUARD_SPEED * delta;
            sprite.setFlip(false, false);
        } else {
            x -= GameConfigManager.GUARD_SPEED * delta;
            sprite.setFlip(true, false);
        }

        // Ensure the guard stays within the patrol zone and reverse direction if necessary
        if (x < zoneX) {
            x = zoneX;
            facingRight = true;
        } else if (x > zoneX + zoneWidth - sprite.getWidth()) {
            x = zoneX + zoneWidth - sprite.getWidth();
            facingRight = false;
        }

        // Keep the guard vertically centered in the patrol zone
        y = zoneY + zoneHeight / 2 - sprite.getHeight() / 2;

        sprite.setPosition(x, y);
    }

    /**
     * Triggers the alert sound when the player is detected by the guard.
     * The alert sound will only be played once for each detection event.
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
     * This method is called when the player exits the detection range or when the alert needs to be reset.
     */
    public void resetAlert() {
        alertTriggered = false;
    }


    /**
     * Releases the resources used by the guard, including the alert sound.
     * This method is called when the guard is destroyed or when the application exits.
     */
    @Override
    public void dispose() {
        super.dispose();
        if (alertSound != null) {
            alertSound.dispose();
        }
    }

}
