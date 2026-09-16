package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.metal.kit.vapor.Manager.CollisionMapManager;
import com.metal.kit.vapor.Manager.GameConfigManager;

import java.util.List;


/**
 * Represents the player in the game. This class extends {@link Character} and adds specific features
 * such as inventory management, score tracking, and player actions (movement, attacking).
 * <p>
 * The player can attack enemies, move around the map, and interact with objects through their inventory.
 * The character's animation is managed based on their movement or idle state.
 * </p>
 */
public class Player extends Character {

    /** The player's inventory containing their weapons */
    private Inventory inventory;

    /** The player's score, which increases when they kill enemies */
    private int score;

    /** Indicates if the player is facing right (true) or left (false) */
    private boolean facingRight = false;

    /**
     * Constructs a player with basic parameters.
     *
     * @param name The name of the player
     * @param x The initial X position of the player
     * @param y The initial Y position of the player
     * @param hp The initial health points of the player
     * @param attackPower The attack power of the player
     * @param speed The movement speed of the player
     * @param score The initial score of the player.
     */
    public Player(String name, float x, float y, int hp, int attackPower, float speed, int score) {
        super(name, x, y, hp, attackPower, speed);
        this.score = score;
        this.inventory = new Inventory();
    }

    /**
     * Performs an attack on enemies within the player's attack range. If an enemy is within range,
     * it is eliminated, and the player's score increases.
     *
     * @param enemies The list of enemies that can be attacked
     * @param shootSound The sound played when the attack is executed
     */
    @Override
    public void attack(Array<Enemy> enemies, Sound shootSound) {
        try {
            for (int i = enemies.size - 1; i >= 0; i--) {
                Enemy enemy = enemies.get(i);
                if (isEnemyInRange(enemy)) {
                    this.score += 5;
                    shootSound.play();
                    enemies.removeIndex(i);
                    enemy.dispose();
                }
            }
        } catch (Exception e) {
            Gdx.app.error("Player", "Error during attack: " + e.getMessage());
        }
    }

    /**
     * Checks if an enemy is within the player's attack range.
     *
     * @param enemy The enemy to check
     * @return {@code true} if the enemy is within attack range, {@code false} otherwise
     */
    private boolean isEnemyInRange(Enemy enemy) {
        try {
            // Calculate the distance between the player and the enemy
            float distance = (float) Math.sqrt(Math.pow(this.x - enemy.getX(), 2) + Math.pow(this.y - enemy.getY(), 2));

            // Check if the enemy is within attack range
            if (distance <= GameConfigManager.PLAYER_ATTACK_RANGE) {

                // Check if the enemy is in the direction the player is facing (based on the facingRight variable)
                boolean isEnemyInFront = (!this.facingRight && enemy.getX() > this.x) || (this.facingRight && enemy.getX() < this.x);

                if (isEnemyInFront) {
                    return true;
                }
            }
        } catch (Exception e) {
            Gdx.app.error("Player", "Error calculating enemy range: " + e.getMessage());
        }
        return false;
    }


    /**
     * Updates the player's state, including animations and movement. This method is called on each
     * frame to handle player behavior (animation, movement).
     *
     * @param delta The time elapsed since the last update
     * @param map The game map used for collision handling
     */
    @Override
    public void update(float delta, TiledMap map) {
        super.update(delta, map);
        if (runAnimation != null) {
            stateTime += delta;

            if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT) ||
                Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP) || Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
                isRunning = true;
                TextureRegion currentFrame = runAnimation.getKeyFrame(stateTime, true);
                sprite.setRegion(currentFrame);
            } else {
                isRunning = false;
                TextureRegion currentFrame = idleAnimation.getKeyFrame(stateTime, true);
                sprite.setRegion(currentFrame);
            }
        }
        move(delta, map);
        this.sprite.setPosition(x, y);
    }

    /**
     * Moves the player based on the direction keys pressed. Movement is handled with collision checks
     * to avoid moving through obstacles.
     *
     * @param delta The time elapsed since the last update
     * @param map The game map used to check for collisions
     */
    @Override
    public void move(float delta, TiledMap map) {

        float moveX = 0;
        float moveY = 0;

        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.LEFT)) {
            moveX = -GameConfigManager.PLAYER_SPEED * delta;
            facingRight = true;
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.RIGHT)) {
            moveX = GameConfigManager.PLAYER_SPEED * delta;
            facingRight = false;
        }

        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.UP)) {
            moveY = GameConfigManager.PLAYER_SPEED * delta;
        }
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.DOWN)) {
            moveY = -GameConfigManager.PLAYER_SPEED * delta;
        }

        float newX = this.x + moveX;
        float newY = this.y + moveY;

        List<String> collisionLayers = List.of("map-border", "building-border", "vehicle-border", "barrier-border", "tree-border", "tower-border");

        if (!CollisionMapManager.isCollisionWithObjects(map, collisionLayers, newX, newY, this.sprite.getWidth(), this.sprite.getHeight())) {
            // If no collision, apply the movement
            this.x = newX;
            this.y = newY;
        }

        this.sprite.setFlip(facingRight, false);
    }

    /**
     * Adds a weapon to the player's inventory.
     *
     * @param weapon The weapon to add to the inventory
     */
    public void addWeapon(Weapon weapon) {
        inventory.addWeapon(weapon);
    }

    /**
     * Returns the player's inventory.
     *
     * @return The player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns the player's current score. The score increases each time an enemy is killed.
     *
     * @return The player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method called when the player dies. Displays a message in the console indicating the player is dead.
     */
    @Override
    public void die() {
        System.out.println(getName() + " is die !!!");
    }

    /**
     * Disposes of the resources used by the player, including textures and other graphical objects.
     */
    public void dispose() {
        try {
            super.dispose();
        } catch (Exception e) {
            throw new RuntimeException("ERROR DURING PLAYER DISPOSE : " + e.getMessage(), e);
        }
    }
}
