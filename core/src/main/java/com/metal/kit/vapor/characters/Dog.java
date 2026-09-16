package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.metal.kit.vapor.Manager.DetectionManager;
import com.metal.kit.vapor.Manager.GameConfigManager;

/**
 * The {@code Dog} class represents an enemy dog in the game.
 * <p>
 * The dog is an enemy that can detect a player within its detection zone. When a player is detected,
 * the dog triggers an alert sound and may pursue the player. The dog has animations for its movement
 * and idle state and can patrol within a designated zone.
 * </p>
 * <p>
 * The dog's movement is limited to a patrol area, and it will reverse direction upon reaching the edges
 * of its patrol zone. When the player enters the dog's detection field, the dog will trigger an alert.
 * </p>
 *
 * @see Enemy
 * @see Player
 * @see DetectionManager
 * @see GameConfigManager
 */
public class Dog extends Enemy {

    /**
     * Constructor to initialize a dog with basic parameters.
     *
     * @param name                The name of the dog.
     * @param x                   The initial X position of the dog.
     * @param y                   The initial Y position of the dog.
     * @param health              The initial health of the dog.
     * @param damage              The damage dealt by the dog.
     * @param speed               The movement speed of the dog.
     * @param zoneX               The X position of the dog's patrol zone.
     * @param zoneY               The Y position of the dog's patrol zone.
     * @param zoneWidth           The width of the dog's patrol zone.
     * @param zoneHeight          The height of the dog's patrol zone.
     * @param detectionDistance   The distance at which the dog can detect the player.
     * @param detectionRadius     The angular radius within which the dog can detect the player.
     * @param detectionDirection  The direction of detection for the dog, expressed in degrees.
     */
    public Dog(String name, float x, float y, int health, int damage, float speed, float zoneX, float zoneY, float zoneWidth, float zoneHeight,  float detectionDistance, float detectionRadius, float detectionDirection) {
        super(name, x, y, health, damage, speed, zoneX, zoneY, zoneWidth, zoneHeight, detectionDistance, detectionRadius, detectionDirection);
        this.alertSound = Gdx.audio.newSound(Gdx.files.internal(GameConfigManager.DOG_ALERT_SOUND));
        this.facingRight = true;
    }

    /**
     * Updates the dog's state every frame of the game.
     * This method checks if the player is detected. If the player is detected, the dog triggers the alert
     * and starts pursuing the player. If the player is not detected, the dog continues to patrol its zone.
     *
     * @param player The player to detect and pursue.
     * @param map    The game map used to check for collisions.
     * @param delta  The time elapsed since the last update (in seconds).
     */
    @Override
    public void update(Player player, TiledMap map, float delta) {
        stateTime += delta;
        TextureRegion currentFrame;

        // Update the dog's animation based on movement state
        if (facingRight || !facingRight) {
            currentFrame = runAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        sprite.setRegion(currentFrame);

        // Calculate positions of the player and the dog
        Vector2 playerPosition = new Vector2(player.getX() + player.getSprite().getHeight()/2, player.getY() + player.getSprite().getHeight()/2);
        Vector2 enemyPosition = new Vector2(this.x, this.y);

        // Check if the player is within the dog's detection zone
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
     * Moves the dog within its patrol zone.
     * The dog moves horizontally within the zone defined by {@code zoneX}, {@code zoneY},
     * {@code zoneWidth}, and {@code zoneHeight}. The dog changes direction when it reaches the edge of the zone.
     *
     * @param delta The time elapsed since the last update (in seconds).
     * @param map   The game map used to check for collisions during movement.
     */
    @Override
    public void move(float delta, TiledMap map) {

        if (facingRight) {
            x += GameConfigManager.DOG_SPEED * delta;
            sprite.setFlip(false, false);
        } else {
            x -= GameConfigManager.DOG_SPEED * delta;
            sprite.setFlip(true, false);
        }

        // Ensure the dog stays within its patrol zone and reverses direction if necessary
        if (x < zoneX) {
            x = zoneX;
            facingRight = true;
        } else if (x > zoneX + zoneWidth - sprite.getWidth()) {
            x = zoneX + zoneWidth - sprite.getWidth();
            facingRight = false;
        }

        // Keep the dog vertically centered within the patrol zone
        y = zoneY + zoneHeight / 2 - sprite.getHeight() / 2;

        sprite.setPosition(x, y);
    }

    /**
     * Triggers the alert sound when the player is detected by the dog.
     * The alert sound will only play once per detection event.
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
     * This method is called when the player exits the detection zone or when the alert needs to be reset.
     */
    public void resetAlert() {
        alertTriggered = false;
    }


    /**
     * Releases the resources used by the dog, including the alert sound.
     * This method is called when the dog is removed or when the application exits.
     */
    @Override
    public void dispose() {
        super.dispose();
        if (alertSound != null) {
            alertSound.dispose();
        }
    }
}
