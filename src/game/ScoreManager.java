package game;

import game.player.Player;

import java.util.ArrayList;

public class ScoreManager {

    private static ScoreManager scoreManager;
    private ArrayList<Player> players;
    private Player winner;
    private int redLevel;
    private int maxScore;

    private ScoreManager(ArrayList<Player> players, int laserHeight) {
        this.players = players;
        redLevel = laserHeight;
    }

    public static ScoreManager getInstance(ArrayList<Player> players, int laserHeight) {
        if (scoreManager == null)
            scoreManager = new ScoreManager(players, laserHeight);
        return scoreManager;
    }

    public boolean isOver() {
        for (Player player : players)
            if (player.getMaxHeight() >= redLevel)
                return true;
        return false;
    }

    public Player getWinner() {
        maxScore = Integer.MIN_VALUE;
        for (Player player : players)
            if (player.getScore() > maxScore) {
                winner = player;
                maxScore = winner.getScore();
            }
        return winner;
    }

    public void updatePlayers(ArrayList<Player> players) {
        this.players = players;
    }
}