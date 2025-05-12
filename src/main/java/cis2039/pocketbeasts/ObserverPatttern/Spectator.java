package cis2039.pocketbeasts.ObserverPatttern;

/**
 * Concrete implementation of an observer used to be notified of stages of the game.
 * This is used through the TestRunner class
 */
public class Spectator implements ISpectator {
    @Override
    public void update(String message) {
        String text = message;
        System.out.println("///////// Spectator View: " + text);
    }
}
