package com.metal.kit.vapor;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.metal.kit.vapor.characters.Enemy;
import com.metal.kit.vapor.characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for testing the behavior of the {@link Enemy} class.
 * This class uses the JUnit 5 framework and Mockito to mock dependencies
 * such as the {@link TiledMap} and {@link Player} objects to test the enemy's
 * behavior in isolation.
 */
public class EnemyTest {
    /**
     * Mocked {@link TiledMap} object used for testing purposes.
     * It simulates the game map but does not provide actual functionality in this test.
     */
    @Mock
    private TiledMap mockMap;

    /**
     * Mocked {@link Player} object used for testing purposes.
     * It represents the player character but is not fully implemented in this test.
     */
    @Mock
    private Player mockPlayer;

    /**
     * The {@link Enemy} object that is being tested.
     * It is initialized with basic parameters and mocked dependencies.
     */
    private Enemy enemy;

    /**
     * Sets up the test environment before each test method is executed.
     * This method initializes the Mockito mocks and creates an {@link Enemy} instance.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize a basic enemy with slow speed and specific attributes
        enemy = new Enemy("TestEnemy", 250, 270, 100, 10, 5, 0, 0, 100, 100, 100, 150, -110) {};

    }

    /**
     * Tests the enemy's ability to detect the player within a specific range and angle.
     * This test checks if the enemy correctly detects the player when the player is within range.
     */
    @Test
    void testDetectPlayerWithinRangeAndAngle() {
        // Set up enemy and player positions
        Vector2 enemyPosition = new Vector2(enemy.getX(), enemy.getY());
        Vector2 playerPosition = new Vector2(200, 200);

        // Check if the enemy detects the player within the specified range and angle
        boolean detected = enemy.detectPlayer(enemyPosition, playerPosition);

        // Assert that the player is detected
        assertTrue(detected);
    }
}
