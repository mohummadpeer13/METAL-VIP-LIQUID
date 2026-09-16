package com.metal.kit.vapor.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The manager responsible for handling character textures and animations.
 * This class loads sprite sheets for the character's running and idle animations,
 * splits them into individual frames, and creates corresponding {@link Animation} objects.
 */
public class CharacterTextureManager {

    /**
     * The animation for the running state of the character.
     * It is created from a sprite sheet containing running frames.
     */
    private Animation<TextureRegion> runAnimation;

    /**
     * The animation for the idle state of the character.
     * It is created from a sprite sheet containing idle frames.
     */
    private Animation<TextureRegion> idleAnimation;

    /**
     * The texture for the running animation (sprite sheet).
     */
    private Texture runSheet;

    /**
     * The texture for the idle animation (sprite sheet).
     */
    private Texture idleSheet;


    /**
     * Constructor to initialize the {@link CharacterTextureManager} by loading sprite sheets
     * for the character's running and idle animations. The sprite sheets are split into frames,
     * and animations are created from those frames.
     *
     * @param texturePathRun The path to the sprite sheet for the running animation.
     * @param texturePathIdle The path to the sprite sheet for the idle animation.
     * @param nbOfFrames The number of frames in each animation (both running and idle).
     */
    public CharacterTextureManager(String texturePathRun, String texturePathIdle, int nbOfFrames) {

        // Load the running texture (sprite sheet)
        runSheet = new Texture(Gdx.files.internal(texturePathRun));
        idleSheet = new Texture(Gdx.files.internal(texturePathIdle));

        // Split the sprite sheets into frames
        TextureRegion[][] tmpFrames = TextureRegion.split(runSheet, runSheet.getWidth() / nbOfFrames, runSheet.getHeight());
        TextureRegion[][] tmpIdleFrames = TextureRegion.split(idleSheet, idleSheet.getWidth() / nbOfFrames, idleSheet.getHeight());

        // Create arrays to hold individual frames
        TextureRegion[] runFrames = new TextureRegion[nbOfFrames];
        TextureRegion[] idleFrames = new TextureRegion[nbOfFrames];

        // Populate the frames arrays with frames from the sprite sheets
        int index = 0;
        for (int i = 0; i < nbOfFrames; i++) {
            runFrames[i] = tmpFrames[0][i];
            idleFrames[i] = tmpIdleFrames[0][i];
        }

        // Create the running and idle animations with a frame duration of 0.10 seconds
        runAnimation = new Animation<TextureRegion>(0.10f, runFrames);
        idleAnimation = new Animation<TextureRegion>(0.10f, idleFrames);

    }

    /**
     * Returns the running animation for the character.
     *
     * @return The {@link Animation} representing the character's running animation.
     */
    public Animation<TextureRegion> getRunAnimation() {
        return runAnimation;
    }

    /**
     * Returns the idle animation for the character.
     *
     * @return The {@link Animation} representing the character's idle animation.
     */
    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    /**
     * Disposes of the resources used by the texture sheets.
     * This method should be called when the textures are no longer needed to free up memory.
     */
    public void dispose() {
        runSheet.dispose();
    }

}
