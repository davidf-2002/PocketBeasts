package cis2039.pocketbeasts;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Player[] players;
    private int currentTurn;
    private boolean gameEnded;
    private List<String> actionHistory; // To log actions for monitoring

    public GameState(Player[] players) {
        this.players = players;
        this.currentTurn = 0;
        this.gameEnded = false;
        this.actionHistory = new ArrayList<>();
    }

    // Getters and Setters
    public Player[] getPlayers() {
        return players;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void incrementTurn() {
        this.currentTurn++;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public void logAction(String action) {
        actionHistory.add(action);
    }

    public void printActionHistory() {
        actionHistory.forEach(System.out::println);
    }

    // Other methods to manage game state
}
