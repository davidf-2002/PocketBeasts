package cis2039.pocketbeasts.Template;

import java.util.Scanner;

/**
 * This abstract class represents the general framework for the game.
 * It uses the template method which manages a sequence of the game.
 * Subclasses must implement the abstract methods to provide specific game logic.
 */

public abstract class GameRunner {

    /**
     * Starts the game and controls the game flow using template
     */
    public final void startGame() {
        gameInitialization();
        while (!isGameOver()) {
            playTurn();
        }
        finalizeGame();
    }

    /**
     * The following methods should be overwritten in the subclasses
     */
    protected abstract void gameInitialization();

    protected abstract boolean isGameOver();

    protected abstract void playTurn();

    protected abstract void finalizeGame();

    /**
     * Prompts user with specific question and validates input against valid response
     * @param prompt The prompt displayed to the user
     * @param validResponses Array of valid responses
     * @return A valid user response
     */
    public static String getPrompt(String prompt, String[] validResponses) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String response = sc.nextLine();
            for (String validResponse : validResponses) {
                if (response.equalsIgnoreCase(validResponse)) {
                    return response;
                }
            }
            System.out.println("Invalid input, please try again.");
        }
    }
}
