package com.metal.kit.vapor.characters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.metal.kit.vapor.Interfaces.Playable;

/**
 * Represents a character in the game. This abstract class provides basic functionalities
 * such as managing health points, attack power, and texture handling. It is intended to be extended
 * by specific character classes (e.g., the player or enemy).
 * <p>
 * This class implements the {@link Playable} interface, which defines movement and attack methods.
 * Movement and attack behaviors are left to the subclasses because each character type can have
 * different behavior.
 * </p>
 */
public abstract class Character implements Playable {
    /** The character's name */
    public String name;

    /** The character's position on the X-axis */
    public float x, y;

    /** The character's health points */
    public int hp;

    /** The character's attack power */
    public int attackPower;

    /** The character's movement speed */
    public float speed;

    /** The character's idle texture */
    public Texture idleTexture;

    /** The character's running texture */
    public Texture runningTexture;

    /** The sprite used to render the character */
    public Sprite sprite;

    /** The running animation for the character */
    public Animation<TextureRegion> runAnimation;

    /** The idle animation for the character */
    public Animation<TextureRegion> idleAnimation;

    /** The time elapsed since the last frame update (used for animations) */
    public float stateTime = 0f;

    /** Indicates if the character is running */
    public boolean isRunning = false;

    /**
     * Constructs a character with basic parameters.
     *
     * @param name The character's name.
     * @param x The initial X position of the character.
     * @param y The initial Y position of the character.
     * @param hp The initial health points of the character.
     * @param attackPower The attack power of the character.
     * @param speed The movement speed of the character.
     */
    public Character(String name, float x, float y, int hp, int attackPower, float speed) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.attackPower = attackPower;
        this.speed = speed;
    }

    /**
     * Initializes the textures and animations for the character, using paths to the texture files.
     * This method sets up both the idle and running animations.
     *
     * @param idleTexturePath The path to the idle texture.
     * @param runningTexturePath The path to the running texture.
     * @param nbOfFrames The number of frames for each animation (idle and running).
     */
    public void initTexture(String idleTexturePath, String runningTexturePath, int nbOfFrames) {
        stateTime = 0f;
        idleTexture = new Texture(idleTexturePath);
        runningTexture = new Texture(runningTexturePath);

        // Load the idle animation frames
        TextureRegion[] idleFrames = new TextureRegion[nbOfFrames];
        for (int i = 0; i < nbOfFrames; i++) {
            idleFrames[i] = new TextureRegion(idleTexture, i * 32, 0, 32, 37);
        }
        idleAnimation = new Animation<TextureRegion>(0.1f, idleFrames);

        // Load the running animation frames
        TextureRegion[] runningFrames = new TextureRegion[nbOfFrames];
        for (int i = 0; i < nbOfFrames; i++) {
            runningFrames[i] = new TextureRegion(runningTexture, i * 32, 0, 32, 37);
        }
        runAnimation = new Animation<TextureRegion>(0.1f, runningFrames);

        sprite = new Sprite(idleAnimation.getKeyFrame(stateTime, true));
        sprite.setPosition(x, y);
    }

    /**
     * Moves the character based on delta coordinates. The movement logic is left to subclasses.
     *
     * @param delta The distance to move (time-based).
     * @param map The game map (used for collision detection and terrain).
     */
    @Override
    public void move(float delta, TiledMap map) {
        // Movement logic should be implemented by subclasses.
    }

    /**
     * Performs an attack on enemies. The method is left to subclasses to define specific attack behavior.
     *
     * @param enemies A list of enemies that can be attacked.
     * @param shootSound The sound to play when the character attacks.
     */
    @Override
    public void attack(Array<Enemy> enemies,  Sound shootSound) {
        // Attack logic should be implemented by subclasses.
    }

    /**
     * Takes damage from an attack. If the health points drop to zero or below, the {@link #die()} method is called.
     *
     * @param damage The amount of damage dealt to the character.
     */
    public void takeDamage(int damage) {
        hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }

        // If health reaches zero, call die() method
        if (this.hp == 0) {
            die();
        }
    }

    /**
     * This method is called when the character's health reaches zero. It can be used to perform actions
     * when the character dies, such as displaying a message or triggering an animation.
     */
    public void die() {
        System.out.println("Someone has died.");
    }

    /**
     * Updates the state of the character. This method can be used to handle animations or other gameplay logic.
     * The update logic is left to subclasses.
     *
     * @param delta The time elapsed since the last update.
     * @param map The game map.
     */
    public void update(float delta, TiledMap map) {}

    /**
     * Draws the character on the screen using a {@link SpriteBatch}.
     *
     * @param batch The {@link SpriteBatch} used to render the character.
     */
    public void render(SpriteBatch batch) {sprite.draw(batch);}

    /**
     * Releases resources used by the character, such as textures and other graphical objects.
     */
    public void dispose() {
        if (idleTexture != null || runningTexture != null) {
            idleTexture.dispose();
            runningTexture.dispose();
        }
        if (sprite != null) {
            sprite.getTexture().dispose();
        }
    }

    // Getters et setters

    /**
     * Returns the name of the character.
     *
     * @return The name of the character.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the health points of the character.
     *
     * @return The health points of the character.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Returns the attack power of the character.
     *
     * @return The attack power of the character.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Returns the damage dealt by the character.
     *
     * @return The damage dealt by the character.
     */
    public int getDamage() {
        return attackPower;
    }

    /**
     * Returns the character's position on the X-axis.
     *
     * @return The character's position on the X-axis.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the character's position on the Y-axis.
     *
     * @return The character's position on the Y-axis.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the character's position as a {@link Vector2}.
     *
     * @return The position of the character.
     */
    public Vector2 getPosition() {
        return new Vector2(x, y);
    }

    /**
     * Sets the position of the character.
     *
     * @param x The new X position of the character.
     * @param y The new Y position of the character.
     */
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        this.sprite.setPosition(x, y);
    }

    /**
     * Returns the current texture used to render the character.
     * If the character is running, the running texture is returned, otherwise the idle texture.
     *
     * @return The texture used to render the character.
     */
    public Texture getTexture() {
        if (isRunning) {
            return runningTexture;
        } else {
            return idleTexture;
        }
    }

    /**
     * Returns the movement speed of the character.
     *
     * @return The speed of the character.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Returns the sprite used to render the character.
     *
     * @return The sprite used to render the character.
     */
    public Sprite getSprite() {
        return sprite;
    }

}
