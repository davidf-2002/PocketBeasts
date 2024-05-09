package cis2039.pocketbeasts.ObserverPatttern;

import cis2039.pocketbeasts.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as the subject to implement the Observer pattern for a spectator mode.
 * This is done by updating the GameState through the CardGameRunner
 */
public class GameState {
    private Player[] players;
    private int currentPlayerIndex;
    private boolean gameRunning;
    private String winningMessage;
    private List<ISpectator> spectators;

    public GameState(Player[] players) {
        this.players = players;
        this.currentPlayerIndex = 0;
        this.gameRunning = true;
        this.spectators = new ArrayList<>();
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Player getOpponent() {
        return players[(currentPlayerIndex + 1) % players.length];
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void endGame(String message) {
        winningMessage = message;
        gameRunning = false;
    }

    public String getWinningMessage() {
        return winningMessage;
    }

    public Player[] getPlayers() {
        return players;
    }


    // Methods for the spectator using the observer pattern
    public void addSpectator(ISpectator spectator) {
        spectators.add(spectator);
    }

    public void notifySpectators(String message) {
        for (ISpectator spectator : spectators) {
            spectator.update(message);
        }
    }

}
