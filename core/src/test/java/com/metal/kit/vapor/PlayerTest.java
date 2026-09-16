package com.metal.kit.vapor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.metal.kit.vapor.characters.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Unit test class for testing the behavior of the {@link Player} class.
 * This class uses JUnit 5 and Mockito for mocking dependencies such as
 * the map, sprite, and input to test the player's movement, rendering, and death functionality.
 */
public class PlayerTest {
    /**
     * The {@link Player} object being tested.
     */
    private Player player;

    /**
     * Mocked {@link TiledMap} used to simulate the game map for testing purposes.
     */
    private TiledMap mockMap;

    /**
     * Mocked {@link MapLayers} object to simulate the layers of the map.
     */
    private MapLayers mockLayers;

    /**
     * Mocked {@link Sprite} object representing the player's sprite.
     */
    private Sprite mockSprite;

    /**
     * Mocked {@link SpriteBatch} used for rendering the player in the tests.
     */
    private SpriteBatch mockBatch;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes the player, mocked map, layers, sprite, and batch for the tests.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the player object with test parameters
        player = new Player("Player1", 0f, 0f, 50, 20, 200f, 0);

        // Mock map and layers
        mockMap = Mockito.mock(TiledMap.class);
        mockLayers = mock(MapLayers.class);
        when(mockMap.getLayers()).thenReturn(mockLayers);

        // Mock sprite and sprite batch
        mockSprite = mock(Sprite.class);
        mockBatch = mock(SpriteBatch.class);
        Texture mockTexture = mock(Texture.class);
        when(mockSprite.getTexture()).thenReturn(mockTexture);

        // Assign mocked sprite to the player
        player.sprite = mockSprite;
    }

    /**
     * Tests the rendering behavior of the player.
     * This test verifies that the player's sprite is drawn exactly once when the render method is called.
     */
    @Test
    public void testPlayerRender() {
        player.render(mockBatch);
        verify(player.getSprite(), times(1)).draw(mockBatch);
    }

    /**
     * Tests the player's movement to the right.
     * This test ensures that when the right arrow key is pressed, the player's X position is updated
     * correctly based on the player's speed.
     */
    @Test
    public void testMoveRight() {
        float initialX = player.getX();
        float initialY = player.getY();

        // Mock input for the right arrow key being pressed
        Gdx.input = mock(Input.class);
        when(Gdx.input.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);

        // Simulate no collision
        when(mockLayers.get("map-border")).thenReturn(null);

        // Move the player
        player.move(5f, mockMap);

        // Assert that the X position of the player has increased
        assertTrue(player.getX() > initialX);
        assertEquals(initialY, player.getY());

        // Calculate the expected X position based on speed and delta time
        float expectedX = initialX + player.getSpeed() * 5f; // Speed = 200 units per second
        assertEquals(expectedX, player.getX(), 0.1f, "La position X du joueur doit correspondre à la vitesse et au delta.");
    }

    /**
     * Tests the player's movement to the left.
     * This test ensures that when the left arrow key is pressed, the player's X position is updated
     * correctly, specifically moving the player by a fixed amount to the left.
     */
    @Test
    public void testMoveLeft() {
        float initialX = player.getX();
        float initialY = player.getY();

        // Mock input for the left arrow key being pressed
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);

        // Assert that the player has moved the expected distance to the left
        player.move(3f, mockMap);
        assertEquals(-600f, player.getX(), 0.1f, "Le joueur doit se déplacer de 600 unités vers la gauche.");
    }

    /**
     * Tests the player's death behavior.
     * This test checks if the player's death prints the correct message to the console.
     */
    @Test
    public void testDie() {
        // Capture the console output to verify the death message
        PrintStream originalSystemOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));

        // Call the die method
        player.die();

        // Assert that the death message is printed to the console
        assertTrue(byteArrayOutputStream.toString().contains("is die !!!"));

        // Restore the original System.out
        System.setOut(originalSystemOut);
    }


}
