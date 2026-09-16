package com.metal.kit.vapor.Interfaces;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.metal.kit.vapor.characters.Enemy;

/**
 * Interface for the player and enemy entities.
 * This interface defines the basic actions that must be implemented for playable entities.
 */
public interface Playable {

    /**
     * Moves the entity along the X and Y axes based on the elapsed time (delta).
     * The movement logic should be implemented in the classes that implement this interface.
     *
     * @param delta The distance to move based on the elapsed time (usually called within the `update` method).
     * @param map   The game map that is used to manage collisions or interactions with the environment.
     */
    void move(float delta, TiledMap map);


    /**
     * Allows the entity to attack enemies. This method should handle the logic of the attack, including
     * applying damage and managing associated sound effects.
     *
     * @param enemies The list of enemies present in the game that the entity can attack.
     * @param shootSound The sound to play when an attack occurs (such as a shot or a strike).
     */
    void attack(Array<Enemy> enemies, Sound shootSound);

    /**
     * Applies damage to the entity. This method should be implemented to reduce the entity's health,
     * manage status effects, and trigger events like death if the health reaches zero.
     *
     * @param damage The amount of damage inflicted on the entity.
     */
    void takeDamage(int damage);

    /**
     * Handles the death of the entity. This method should be implemented to define the specific behavior
     * of the entity when it dies.
     */
    void die();
}
