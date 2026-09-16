package com.metal.kit.vapor.characters;

import java.util.ArrayList;

/**
 * Represents an inventory containing a list of weapons.
 * The inventory allows adding, removing, and accessing weapons.
 * It also keeps track of the total number of weapons in the inventory.
 * <p>
 * The inventory is managed through an {@link ArrayList} of {@link Weapon} objects.
 * </p>
 */
public class Inventory {

    /**
     * List of weapons contained in the inventory.
     * This list stores all weapons currently in the inventory.
     * It is an {@link ArrayList}, where each element is a {@link Weapon} object.
     */
    private ArrayList<Weapon> weapons;

    /**
     * Constructs a new empty inventory.
     * <p>
     * Initializes an empty list of weapons, ready to store {@link Weapon} objects.
     * </p>
     */
    public Inventory() {
        weapons = new ArrayList<>();
    }

    /**
     * Adds a weapon to the inventory.
     * <p>
     * This method allows you to add a weapon to the inventory list.
     * </p>
     *
     * @param weapon The weapon to be added to the inventory.
     */
    public void addWeapon(Weapon weapon) {
        weapons.add(weapon);
        System.out.println(weapon.getName() + " has been added to the inventory.");
    }

    /**
     * Retrieves a weapon from the inventory by its index.
     * <p>
     * This method allows you to access a specific weapon in the inventory using its index.
     * </p>
     *
     * @param index The index of the weapon to retrieve.
     * @return The weapon at the specified index, or {@code null} if the index is invalid
     *         (out of bounds of the list).
     */
    public Weapon getWeapon(int index) {
        if (index >= 0 && index < weapons.size()) {
            return weapons.get(index);
        }
        return null;
    }


    /**
     * Retrieves the full list of weapons currently in the inventory.
     * <p>
     * This method returns the complete list of weapons in the inventory.
     * </p>
     *
     * @return An {@link ArrayList} of {@link Weapon} objects representing all weapons in the inventory.
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Removes a weapon from the inventory by its index.
     * <p>
     * This method allows you to remove a weapon from the inventory and prints a message
     * confirming the removal of the weapon.
     * </p>
     *
     * @param index The index of the weapon to be removed.
     */
    public void removeWeapon(int index) {
        if (index >= 0 && index < weapons.size()) {
            System.out.println(weapons.get(index).getName() + " has been removed from the inventory.");
            weapons.remove(index);
        }
    }

    /**
     * Retrieves the number of weapons in the inventory.
     * <p>
     * This method returns the total number of weapons stored in the inventory.
     * </p>
     *
     * @return The total number of weapons in the inventory.
     */
    public int getWeaponCount() {
        return weapons.size();
    }

    /**
     * Checks whether the inventory contains a specific weapon.
     * <p>
     * This method checks if the inventory contains a given weapon.
     * </p>
     * <p>
     * Currently, this method always returns {@code true}, as the actual logic for checking
     * the presence of a weapon is not implemented. This method could be extended to
     * return a result based on the weapon provided.
     * </p>
     *
     * @param weapon The weapon to check for in the inventory.
     * @return {@code true} if the inventory contains the specified weapon.
     *         (Currently, the logic always returns {@code true}, but it can be extended in future implementations.)
     */
    public boolean hasWeapon(Weapon weapon) {
        return true;
    }
}
