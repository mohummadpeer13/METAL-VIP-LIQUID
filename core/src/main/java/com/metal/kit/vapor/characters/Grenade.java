package com.metal.kit.vapor.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Represents a grenade in the game.
 * The {@link Grenade} class extends {@link Weapon} and adds a sound effect for the grenade's explosion.
 * When the grenade is used, it plays an explosion sound.
 */
public class Grenade extends Weapon{

    /** The sound played when the grenade explodes */
    private Sound grenadeSound;

    /**
     * Constructs a new {@link Grenade} object.
     *
     * @param name The name of the grenade (e.g., "Frag Grenade").
     * @param texture The texture that represents the appearance of the grenade.
     * @param x The X position of the grenade in the game world.
     * @param y The Y position of the grenade in the game world.
     * @param damage The damage dealt by the grenade's explosion.
     * @param grenadeSound The path to the sound file that plays when the grenade explodes.
     */
    public Grenade(String name, Texture texture, float x, float y, int damage, String grenadeSound) {
        super(name, texture, x, y, damage);
        this.grenadeSound = Gdx.audio.newSound(Gdx.files.internal(grenadeSound));;
    }


    /**
     * Disposes of the resources used by the grenade, including its texture and sound.
     * <p>
     * This method is called when the grenade is no longer in use, in order to free memory.
     * </p>
     */
    @Override
    public void dispose() {
        super.dispose();

        if (grenadeSound != null) {
            grenadeSound.dispose();
            grenadeSound = null;
        }
    }

    /**
     * Uses the grenade, triggering its explosion and playing the associated sound.
     * <p>
     * In this implementation, using the grenade consists solely of playing the explosion sound.
     * This method could be extended to include additional effects, such as animation or damage to enemies.
     * </p>
     */
    @Override
    public void use() {
        if (grenadeSound != null) {
            grenadeSound.play();
        }
    }
}
