package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a gun-type weapon (such as a pistol, rifle, etc.) in the game.
 * The {@link Gun} class extends {@link Weapon} and represents a weapon with a method to be used.
 * The {@link Gun#use()} method is currently defined but needs to be implemented to perform
 * specific actions when the weapon is used (e.g., firing a projectile).
 */
public class Gun  extends Weapon{

    /** The sound played when the gun is fired */
    private Sound shootSound;

    /**
     * Constructs a new {@link Gun} object.
     *
     * @param name The name of the weapon (e.g., "Pistol", "Rifle").
     * @param texture The texture that represents the appearance of the weapon.
     * @param x The X position of the weapon in the game world.
     * @param y The Y position of the weapon in the game world.
     * @param damage The damage dealt by the weapon when it is used.
     * @param shootSound The path to the sound file that plays when the gun is fired.
     */
    public Gun(String name, Texture texture, float x, float y, int damage, String shootSound) {
        super(name, texture, x, y, damage);
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal(shootSound));;
    }

    /**
     * Disposes of the resources used by the gun, including its texture and sound.
     * <p>
     * This method is called when the weapon is no longer in use to release the allocated memory.
     * </p>
     */
    @Override
    public void dispose() {
        super.dispose();

        if (shootSound != null) {
            shootSound.dispose();
            shootSound = null;
        }
    }

    /**
     * Uses the gun, triggering the firing action and playing the associated sound.
     * <p>
     * In this implementation, using the gun consists solely of playing the shoot sound.
     * This method can be extended to include additional effects such as firing a projectile,
     * animating the gun, or inflicting damage on enemies.
     * </p>
     */
    @Override
    public void use() {
        if (shootSound != null) {
            shootSound.play();
        }
    }
}

