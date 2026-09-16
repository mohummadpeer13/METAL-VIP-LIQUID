package com.metal.kit.vapor.Manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.metal.kit.vapor.characters.Grenade;
import com.metal.kit.vapor.characters.Gun;
import com.metal.kit.vapor.characters.Weapon;


/**
 * The weapon manager for the game.
 * This class is responsible for creating, managing, and retrieving the weapons available in the game.
 * It allows for the creation of a list of weapons, retrieval of that list, and freeing the associated resources when they are no longer needed.
 */
public class WeaponManager {

    /**
     * List of weapons in the game.
     * This list is used to store all active weapons.
     */
    private Array<Weapon> weapons;

    /**
     * Constructor for the WeaponManager class.
     * Initializes the weapon list and generates the default weapons by calling the `createWeapon()` method.
     */
    public WeaponManager() {
        this.weapons = new Array<>();
        createWeapon();
    }

    /**
     * Creates the weapons to be placed in the game.
     * This method creates different weapons (such as guns and grenades) and adds them to the list of available weapons.
     */
    private void createWeapon() {
        weapons.add(new Gun("Gun1", new Texture(GameConfigManager.GUN_TEXTURE), 350, 500, 10, GameConfigManager.CCTV_ALERT_SOUND));
        weapons.add(new Gun("Gun2", new Texture(GameConfigManager.GUN_TEXTURE), 1000, 1500, 10, GameConfigManager.CCTV_ALERT_SOUND));
        weapons.add(new Gun("Gun3", new Texture(GameConfigManager.GUN_TEXTURE), 1500, 300, 10, GameConfigManager.CCTV_ALERT_SOUND));

        weapons.add(new Grenade("Grenade1", new Texture(GameConfigManager.GRENADE_TEXTURE), 1100, 850, 10, GameConfigManager.CCTV_ALERT_SOUND));
        weapons.add(new Grenade("Grenade2", new Texture(GameConfigManager.GRENADE_TEXTURE), 1400, 1400, 10, GameConfigManager.CCTV_ALERT_SOUND));
    }

    /**
     * Retrieves the list of available weapons in the game.
     * This method returns all the weapons created and placed on the map.
     *
     * @return The list of available weapons as an `Array` of `Weapon` objects.
     */
    public Array<Weapon> getWeapons() {
        return weapons;
    }


    /**
     * Frees the resources associated with the weapons when the manager is no longer needed.
     * This method calls the `dispose()` method for each weapon and clears the weapon list.
     */
    public void dispose() {
        for (Weapon weapon : weapons) {
            weapon.dispose();
        }
        weapons.clear();
    }
}
