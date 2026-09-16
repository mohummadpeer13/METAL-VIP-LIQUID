package com.metal.kit.vapor.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * The camera manager for the game. This class allows the camera to be moved based on the player's position,
 * with adjustments to keep the camera within the bounds of the map and to perform smooth movement.
 */
public class CameraManager {

    /**
     * The orthographic camera used to display the game scene and follow the player.
     * It is responsible for managing the 2D view of the game map.
     */
    private OrthographicCamera camera;

    /**
     * The game map to which the camera is restricted. It is used to determine the map's dimensions
     * and ensure that the camera does not move beyond its edges.
     */
    private TiledMap map;

    /**
     * Constructor to initialize the camera manager.
     * The camera is created with the screen's dimensions and is centered on the player's position.
     *
     * @param map The game map to which the camera should be restricted.
     */
    public CameraManager(TiledMap map) {
        this.map = map;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    /**
     * Updates the camera's position based on the player's position.
     * The camera follows the player with smooth movement using interpolation.
     * The camera is restricted to the map's dimensions to prevent it from going out of bounds.
     *
     * @param playerX The player's X position.
     * @param playerY The player's Y position.
     */
    public void update(float playerX, float playerY) {
        float targetX = playerX + 32 / 2;
        float targetY = playerY + 32 / 2;

        // Interpolation for smooth movement
        camera.position.x += (targetX - camera.position.x) * GameConfigManager.CAMERA_SMOOTHING;
        camera.position.y += (targetY - camera.position.y) * GameConfigManager.CAMERA_SMOOTHING;

        // Restrict the camera within the map's dimensions
        camera.position.x = Math.max(camera.viewportWidth / 2, Math.min(camera.position.x, map.getProperties().get("width", Integer.class) * 32 - camera.viewportWidth / 2));
        camera.position.y = Math.max(camera.viewportHeight / 2, Math.min(camera.position.y, map.getProperties().get("height", Integer.class) * 32 - camera.viewportHeight / 2));

        camera.update();
    }

    /**
     * Returns the camera instance used by the manager.
     *
     * @return The {@link OrthographicCamera} object used to display the scene.
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Disposes of the resources used by the CameraManager.
     * This includes the TiledMap if it's no longer needed, and any other resources
     * that the CameraManager might be holding onto.
     */
    public void dispose() {
        if (map != null) {
            map.dispose();
        }
    }
}
