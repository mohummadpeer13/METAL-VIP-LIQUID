package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.metal.kit.vapor.Manager.CollisionMapManager;
import com.metal.kit.vapor.Manager.DetectionManager;
import com.metal.kit.vapor.Manager.GameConfigManager;
import com.metal.kit.vapor.MyGdxGame;
import com.metal.kit.vapor.Screen.GameOverScreen;
import com.metal.kit.vapor.Screen.GameScreen;
import java.util.List;

/**
 * Abstract class representing an enemy in the game.
 * <p>
 * An enemy is capable of detecting the player within its detection range, pursuing the player if they enter its
 * detection area, and reacting based on the position and orientation of the player. Specific enemy types should extend
 * this class to implement their own behavior.
 * </p>
 */
public abstract class Enemy extends Character{

    /** Position and dimensions of the enemy's movement zone. */
    public float zoneX, zoneY, zoneWidth, zoneHeight;

    /** Detection range of the enemy. */
    public float detectionDistance;

    /** Angular detection radius of the enemy. */
    public float detectionRadius;

    /** Direction of detection, expressed in degrees. */
    public float detectionDirection;

    /** Indicator of the movement direction (moving left or right). */
    public boolean facingRight;

    /** Sound played when the alert is triggered. */
    public Sound alertSound;

    /** Indicator of whether the alert has been triggered. */
    public boolean alertTriggered = false;

    /**
     * Constructor to create an enemy with specific parameters for movement zone, detection range, and alert behavior.
     *
     * @param name                The name of the enemy.
     * @param x                   The initial X position of the enemy.
     * @param y                   The initial Y position of the enemy.
     * @param health              The health points of the enemy.
     * @param damage              The damage dealt by the enemy.
     * @param speed               The speed at which the enemy moves.
     * @param zoneX               The X position of the enemy's movement zone.
     * @param zoneY               The Y position of the enemy's movement zone.
     * @param zoneWidth           The width of the enemy's movement zone.
     * @param zoneHeight          The height of the enemy's movement zone.
     * @param detectionDistance   The detection range of the enemy.
     * @param detectionRadius     The angular detection radius of the enemy.
     * @param detectionDirection  The direction of detection, in degrees.
     */
    public Enemy(String name, float x, float y, int health, int damage, float speed, float zoneX, float zoneY, float zoneWidth, float zoneHeight, float detectionDistance, float detectionRadius, float detectionDirection) {
        super(name, x, y, health, damage, speed);
        this.zoneX = zoneX;
        this.zoneY = zoneY;
        this.zoneWidth = zoneWidth;
        this.zoneHeight = zoneHeight;
        this.detectionDistance = detectionDistance;
        this.detectionRadius = detectionRadius;
        this.detectionDirection = detectionDirection;
    }

    /**
     * Moves the enemy based on the map. This method is called during the game's update loop.
     *
     * @param delta The time elapsed since the last update (in seconds).
     * @param map   The map used to check for collisions.
     */
    @Override
    public void move(float delta, TiledMap map) {
        super.move(delta, map);
    }

    /**
     * Checks if the enemy detects the player based on distance and angle.
     * <p>
     * An enemy detects the player if the distance between the enemy and the player is less than the detection range,
     * and if the player is within the angular detection radius of the enemy.
     * </p>
     *
     * @param enemyPosition The position of the enemy.
     * @param playerPosition The position of the player.
     * @return true if the enemy detects the player, false otherwise.
     */
    public boolean detectPlayer(Vector2 enemyPosition, Vector2 playerPosition) {
        try {
            // Update the detection direction for specific enemies like CCTV
            if (!(this instanceof Cctv)) {
                if (facingRight) {
                    this.detectionDirection = GameConfigManager.CCTV_SENS_RIGHT;
                } else {
                    this.detectionDirection = GameConfigManager.CCTV_SENS_LEFT - 20;
                }
            }

            // Calculate the distance between the enemy and the player
            float distanceToPlayer = enemyPosition.dst(playerPosition);

            // Check if the player is within detection range
            if (distanceToPlayer <= this.detectionDistance) {

                Vector2 cameraDirection = new Vector2(MathUtils.cosDeg(this.detectionDirection), MathUtils.sinDeg(this.detectionDirection));
                Vector2 toPlayer = playerPosition.cpy().sub(enemyPosition);
                float angleToPlayer = cameraDirection.angleDeg(toPlayer);

                // Check if the player is within the detection angle
                return Math.abs(angleToPlayer) <= this.detectionRadius / 2;
            }
        } catch (Exception e) {
            Gdx.app.error("Enemy", "Error during player detection: " + e.getMessage());
        }

        return false;
    }

    /**
     * Makes the enemy pursue the player based on the player's position.
     * The enemy will move towards the player while considering the direction of its movement (left or right),
     * and the orientation of the sprite will be updated accordingly.
     * <p>
     * If the enemy changes direction based on the player's position, the sprite's orientation will also be updated.
     * </p>
     *
     * @param player    The player that the enemy is pursuing.
     * @param map       The map used to check for collisions.
     * @param deltaTime The time elapsed since the last update (in seconds).
     */
    public void pursuitPlayer(Player player, TiledMap map, float deltaTime) {
        try {
            // Calculate direction towards the player
            float deltaX = player.getX() - this.x;
            float deltaY = player.getY() - this.y;

            float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            if (length != 0) {
                deltaX /= length;
                deltaY /= length;
            }

            // Check if the enemy needs to change direction
            if (deltaX > 0 && !facingRight) {
                facingRight = true;
            } else if (deltaX < 0 && facingRight) {
                facingRight = false;
            }

            // Calculate the new position of the enemy
            float newX = this.x + deltaX * speed * deltaTime;
            float newY = this.y + deltaY * speed * deltaTime;

            // List of collision layers to check
            List<String> collisionLayers = List.of("map-border", "building-border", "vehicle-border", "barrier-border", "tree-border", "next-level-border", "tower-border");

            // Check for collisions before moving the enemy
            if (!CollisionMapManager.isCollisionWithObjects(map, collisionLayers, newX, newY, this.sprite.getWidth(), this.sprite.getHeight())) {
                this.x = newX;
                this.y = newY;
                if (facingRight){
                    this.sprite.setFlip(false, false);
                }else{
                    this.sprite.setFlip(true, false);
                }
                this.sprite.setPosition(x, y);
            }

            // Check if the enemy is close enough to the player to deal damage
            if (Math.abs(player.getX() - this.x) < this.sprite.getWidth() && Math.abs(player.getY() - this.y) < this.sprite.getHeight()) {
                player.takeDamage(this.getDamage());
                if (player.getHp() <= 0){
                    MyGdxGame myGame = (MyGdxGame) Gdx.app.getApplicationListener();
                    if (myGame.getScreen() instanceof GameScreen) {
                        DetectionManager.getInstance().updateDetection(false);
                        super.dispose();
                    }
                    myGame.setScreen(new GameOverScreen(player.getName(), player.getScore()));
                }
            }
        } catch (Exception e) {
            Gdx.app.error("Enemy", "Error during player pursuit: " + e.getMessage());
        }
    }

    /**
     * This method is called when the enemy triggers an alert.
     * It can be implemented by enemy subclasses to add specific behavior when the alert is triggered.
     */
    public void triggerAlert() {}

    /**
     * Updates the state of the enemy. This method can be extended by subclasses to add specific behaviors during
     * each game frame.
     *
     * @param player  The player for interactions.
     * @param map     The game map.
     * @param delta   The time elapsed since the last update (in seconds).
     */
    public void update(Player player, TiledMap map, float delta) {
    }

    /**
     * Renders the enemy on the screen. This method is used to draw the enemy with the `SpriteBatch`.
     *
     * @param batch The rendering batch to draw the enemy.
     */
    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    // Getters et setters

    /**
     * Returns the detection distance of the enemy.
     *
     * @return The detection distance of the enemy.
     */
    public float getDetectionDistance() {
        return detectionDistance;
    }

    /**
     * Returns the angular detection radius of the enemy.
     *
     * @return The angular detection radius of the enemy.
     */
    public float getDetectionRadius() {
        return detectionRadius;
    }

    /**
     * Disposes of the resources used by the enemy, including textures and other graphical objects.
     */
    public void dispose() {
        try {
            super.dispose();
        } catch (Exception e) {
            throw new RuntimeException("ERROR DURING ENEMY DISPOSE : " + e.getMessage(), e);
        }
    }
}
