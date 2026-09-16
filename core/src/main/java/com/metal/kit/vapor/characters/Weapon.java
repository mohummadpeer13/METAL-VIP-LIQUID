package com.metal.kit.vapor.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Abstract class representing a weapon in the game.
 * <p>
 * Weapons have a name, texture, position, collision rectangle, and damage.
 * Each weapon can be used in a specific way depending on its subclass.
 * </p>
 */
public abstract class Weapon {
    /** The name of the weapon. */
    public String name;

    /** The texture representing the weapon. */
    public Texture texture;

    /** The position of the weapon in the game. */
    public float x, y;

    /** The collision rectangle of the weapon. */
    public Rectangle bounds;

    /** The damage dealt by the weapon when used. */
    public int damage;

    /**
     * Constructor for the {@link Weapon} class.
     * <p>
     * This constructor initializes the weapon's properties, including its name, texture, position, collision rectangle,
     * and damage.
     * </p>
     *
     * @param name   The name of the weapon.
     * @param texture The texture representing the weapon.
     * @param x The X position of the weapon.
     * @param y The Y position of the weapon.
     * @param damage The damage dealt by the weapon.
     */
    public Weapon(String name, Texture texture, float x, float y, int damage) {
        this.name = name;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.damage = damage;
    }

    /**
     * Gets the name of the weapon.
     *
     * @return The name of the weapon.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the texture of the weapon.
     *
     * @return The texture of the weapon.
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Gets the X position of the weapon.
     *
     * @return The X position of the weapon.
     */
    public float getX() {
        return x;
    }

    /**
     * Gets the Y position of the weapon.
     *
     * @return The Y position of the weapon.
     */
    public float getY() {
        return y;
    }

    /**
     * Gets the width of the weapon based on the width of its texture.
     *
     * @return The width of the weapon.
     */
    public float getWidth() {
        return texture.getWidth();
    }

    /**
     * Gets the height of the weapon based on the height of its texture.
     *
     * @return The height of the weapon.
     */
    public float getHeight() {
        return texture.getHeight();
    }

    /**
     * Updates the dimensions of the collision rectangle based on the current position of the weapon.
     * <p>
     * This method should be called if the position of the weapon changes to update its collision area.
     * </p>
     */
    public void updateBounds() {
        bounds.setPosition(x, y);
    }

    /**
     * Gets the collision rectangle of the weapon.
     * <p>
     * This rectangle is used to handle collisions with other objects in the game.
     * </p>
     *
     * @return The collision rectangle of the weapon.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gets the damage dealt by the weapon.
     * <p>
     * This method returns the amount of damage the weapon deals when used.
     * </p>
     *
     * @return The damage dealt by the weapon.
     */
    public int getDamage() {
        return damage;
    }


    /**
     * Gets the position of the weapon as a 2D vector.
     * <p>
     * This method returns a {@link Vector2} object that represents the position of the weapon in 2D space.
     * </p>
     *
     * @return A {@link Vector2} representing the position of the weapon.
     */
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    /**
     * Checks if the weapon has been collected by the player.
     * <p>
     * This method checks if the weapon is in collision with the player, based on the player's position and dimensions.
     * </p>
     *
     * @param playerX The X position of the player.
     * @param playerY The Y position of the player.
     * @param playerWidth The width of the player.
     * @param playerHeight The height of the player.
     * @return True if the player is in collision with the weapon, otherwise false.
     */
    public boolean isCollected(float playerX, float playerY, float playerWidth, float playerHeight) {
        return playerX < x + texture.getWidth() && playerX + playerWidth > x && playerY < y + texture.getHeight() && playerY + playerHeight > y;
    }

    /**
     * Abstract method to be implemented in subclasses to define the specific use of the weapon.
     * <p>
     * Each weapon subclass (e.g., Pistol, Grenade) must provide an implementation of this method to define its behavior
     * when used, such as firing a bullet or throwing an explosive.
     * </p>
     */
    public abstract void use();

    /**
     * Renders the weapon on the screen at position (x, y) using the {@link SpriteBatch}.
     * <p>
     * This method is responsible for drawing the weapon in the game. It uses a {@link SpriteBatch} to draw the weapon's texture
     * at the specified position on the screen.
     * </p>
     *
     * @param batch The {@link SpriteBatch} used to draw the weapon on the screen.
     */
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth() * 0.5f, texture.getHeight() * 0.5f);
    }

    /**
     * Releases resources used by the weapon, including its texture.
     * <p>
     * This method should be called when the weapon is no longer needed to free up memory used by the texture.
     * </p>
     */
    public void dispose() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }
}
