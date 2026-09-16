package com.metal.kit.vapor.characters;

/**
 * Represents a score associated with a player.
 * <p>
 * This class stores the name of the player and the score obtained in the game. It is typically used to track
 * player performance and display high scores in the game.
 * </p>
 */
public class Score {
    /** The name of the player associated with this score */
    public String playerName;

    /** The score of the player */
    public int score;

    /**
     * Constructor to create a {@link Score} object with a given player name and score.
     * <p>
     * This constructor initializes the player name and score for the {@link Score} object.
     * </p>
     *
     * @param playerName The name of the player.
     * @param score      The score of the player.
     */
    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Gets the name of the player associated with this score.
     *
     * @return The player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the score of the player.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }
}
