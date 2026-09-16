package com.metal.kit.vapor.Manager;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

/**
 * Collision manager for objects defined in map layers.
 * This class provides methods to check if a player or an object collides with
 * objects defined in the map layers in a LibGDX-based game.
 */
public class CollisionMapManager {

    /**
     * Checks if a rectangle (representing a player or object) collides with any objects
     * defined in several map layers. The objects are compared to a rectangle specified
     * by the coordinates (x, y), width, and height.
     *
     * @param map           The game map containing the layers with collision objects.
     * @param objectLayers  List of names of the layers containing collision objects.
     * @param x             The X position of the player or object to check.
     * @param y             The Y position of the player or object to check.
     * @param width         The width of the player or object to check.
     * @param height        The height of the player or object to check.
     * @return              Returns true if a collision is detected with an object on the map, otherwise false.
     *
     * @throws IllegalArgumentException If the map or specified layers are invalid.
     */
    public static boolean isCollisionWithObjects(TiledMap map, List<String> objectLayers, float x, float y, float width, float height) {
        // Check if the map and its layers are initialized correctly
        if (map == null || map.getLayers() == null) {
            System.out.println("Error: The map or its layers are null!");
            return false;
        }
        // Check if the specified layers exist
        for (String layerName : objectLayers) {
            if (map.getLayers().get(layerName) == null) {
                System.out.println("Warning: The collision layer '" + layerName + "' is missing from the map.");
            }
        }

        Rectangle playerRect = new Rectangle(x, y, width, height);

        // Iterate through each collision layer in the list
        for (String objectLayer : objectLayers) {
            MapLayer collisionLayer = map.getLayers().get(objectLayer);

            if (collisionLayer != null) {
                MapObjects objects = collisionLayer.getObjects();

                // Check each object in the layer
                for (MapObject object : objects) {
                    if (object instanceof RectangleMapObject) {
                        Rectangle objectRect = ((RectangleMapObject) object).getRectangle();

                        // If the player collides with this object, return true
                        if (playerRect.overlaps(objectRect)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Checks if a rectangle (representing a player) collides with any object
     * defined in a single map layer, specifically for detecting collisions related to
     * the transition to the next level.
     *
     * @param map           The game map containing the layer with collision objects for the next level.
     * @param objectLayer   The name of the layer containing objects for next-level collision detection.
     * @param x             The X position of the player or object to check.
     * @param y             The Y position of the player or object to check.
     * @param width         The width of the player or object to check.
     * @param height        The height of the player or object to check.
     * @return              Returns true if a collision is detected with an object on the map, otherwise false.
     */
    public static boolean isCollisionWithNextLevel(TiledMap map, String objectLayer, float x, float y, float width, float height) {
        MapLayer collisionLayer = map.getLayers().get(objectLayer);

        if (collisionLayer != null) {
            MapObjects objects = collisionLayer.getObjects();
            Rectangle playerRect = new Rectangle(x, y, width, height);

            for (MapObject object : objects) {
                if (object instanceof RectangleMapObject) {
                    RectangleMapObject rectObject = (RectangleMapObject) object;
                    Rectangle objectRect = rectObject.getRectangle();

                    if (playerRect.overlaps(objectRect)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
