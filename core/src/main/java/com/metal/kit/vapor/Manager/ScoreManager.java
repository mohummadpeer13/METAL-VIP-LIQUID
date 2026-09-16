package com.metal.kit.vapor.Manager;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.metal.kit.vapor.characters.Score;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The game score manager.
 * This class handles saving, loading, and managing player scores in a local JSON file.
 * It provides functionalities to save a score, load the score list, and get the highest score.
 */
public class ScoreManager {
    // The JSON file where the scores are saved
    private File scoreFile;

    // Gson instance for JSON serialization and deserialization
    private Gson gson;

    // List of scores
    private List<Score> scores;

    /**
     * Constructor for the ScoreManager class.
     * Initializes the score file, Gson instance, and loads existing scores from the file.
     */
    public ScoreManager() {
        scoreFile = new File(Gdx.files.local("scores.json").path());
        gson = new Gson();
        scores = loadScores();
    }

    /**
     * Saves a new score to the JSON file.
     * The score will be added to the list of existing scores, and the complete list will then be saved back to the file.
     *
     * @param playerName The name of the player whose score is to be saved.
     * @param score      The score of the player to be saved.
     */
    public void saveScore(String playerName, int score) {
        scores.add(new Score(playerName, score));

        try (FileWriter writer = new FileWriter(scoreFile)) {
            gson.toJson(scores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the scores from the JSON file.
     * If the file does not exist or cannot be read, an empty list is returned.
     *
     * @return The list of scores loaded from the file, or an empty list if the file is missing or corrupted.
     */
    public List<Score> loadScores() {
        if (scoreFile.exists()) {
            try (FileReader reader = new FileReader(scoreFile)) {
                Type listType = new TypeToken<ArrayList<Score>>() {}.getType();
                return gson.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Gets the best score from the saved scores.
     * If multiple players have the same highest score, the first encountered one will be returned.
     *
     * @return The best score recorded, or null if the list of scores is empty.
     */
    public String getBestScore() {
        if (scores.isEmpty()) {
            return "No scores available.";
        }

        // Highest score
        int highestScore = Integer.MIN_VALUE;
        List<String> topPlayers = new ArrayList<>();

        // Iterate through all scores and find the highest
        for (Score score : scores) {
            if (score.score > highestScore) {
                highestScore = score.score;
                topPlayers.clear();
                topPlayers.add(score.playerName);
            } else if (score.score == highestScore) {
                // If the score is equal to the highest, add the player to the list
                topPlayers.add(score.playerName);
            }
        }

        if (topPlayers.size() > 1) {
            return "Same score between " + String.join(", ", topPlayers) + " with a score of " + highestScore;
        } else {
            return topPlayers.get(0) + " has the highest score: " + highestScore;
        }
    }

    /**
     * Gets the list of top scores (10).
     * This method can be used to display the top N scores in the UI.
     *
     * @return The list of top scores.
     */
    public List<Score> getTopScores() {
        List<Score> scores = loadScores();
        scores.sort(Comparator.comparingInt(Score::getScore).reversed());

        if (scores.size() > 10) {
            scores = scores.subList(0, 10);
        }

        return scores;
    }
}
