package cis2039.pocketbeasts.Template;

import cis2039.pocketbeasts.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * An implementation of GameRunner.java using the template design pattern
 * Manages the flow and rules of the PocketBeasts game
 */

public class CardGameRunner extends GameRunner {

    private final Player[] players;
    private String winningMessage;

    public CardGameRunner() {
        this(new String[]{"Steve", "Chris"});
    }

    /**
     * Constructs a CardGameRunner with specific player names
     * @param playerNames Array of names used to initialise the players
     */
    public CardGameRunner(String[] playerNames) {
        players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i], new CardCollection(getStarterDeck()));
        }
        winningMessage = "";
    }

    @Override
    protected void gameInitialization() {
        printRules();
        System.out.println("Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        for (Player player : players) {
            player.newGame();
            System.out.println(player);
        }
    }

    @Override
    protected boolean isGameOver() {
        for (Player player : players) {
            if (player.getHealth() <= 0) {
                winningMessage = player.getName() + " has lost the game.";
                return true;
            }
        }
        return false;
    }

    @Override
    protected void playTurn() {
        for (Player player : players) {
            addManaAndDrawCard(player);
            printPlayerState(player);

            Player otherPlayer = findOpponent(player);
            if (otherPlayer == null){
                winningMessage = "Something has gone terribly wrong...";
                return;
            }

            handleCardAttacks(player, otherPlayer);
            if (isGameOver()) break;

            removeDeadCards(player);
            playCardsFromHand(player);
            printSeparator();
            printPlayerState(player);
        }
    }

    @Override
    protected void finalizeGame() {
        System.out.println(winningMessage);
    }

    private void printPlayerState(Player player) {
        System.out.println(player);
    }

    private void printSeparator() {
        System.out.println("\n".repeat(16));
    }

    private void addManaAndDrawCard(Player player) {
        player.addMana();
        player.drawCard();
        System.out.println(player);

    }

    private Player findOpponent(Player currentPlayer) {
        for (Player player : players) {
            if (player != currentPlayer) {
                return player;
            }
        }
        return null;
    }

    private void handleCardAttacks(Player player, Player otherPlayer) {
        for (Card card : player.getInPlay().getCards()) {
            System.out.println(card.toString());

            String attack = getPrompt(
                    player.getName() + " attack with " + card.getName() + "? (Yes/No): ",
                    new String[]{"Yes", "yes", "y", "No", "no", "n"}
            );
            if (attack.equalsIgnoreCase("Yes") || attack.equalsIgnoreCase("y")) {
                executeAttack(card, otherPlayer);
            }
            if (isGameOver()) {
                break;
            }
        }
    }

    private void executeAttack(Card card, Player otherPlayer) {
        int attackChoice = 2;
        System.out.println("Who would you like to attack? ");
        System.out.println("1. " + otherPlayer.getName());
        for (Card otherCard : otherPlayer.getInPlay().getCards()) {
            System.out.println(attackChoice + ". " + otherCard);
            attackChoice++;
        }

        ArrayList<String> prompts = new ArrayList<>();
        for (int i = 1; i < attackChoice; i++) {
            prompts.add(String.valueOf(i));
        }
        String target = getPrompt("Choose a number: ", prompts.toArray(new String[0]));

        if ("1".equals(target)) { // Player directly
            boolean result = otherPlayer.damage(card.getAttack());
            System.out.println(otherPlayer.getName() + " is now at " + otherPlayer.getHealth() + " health.");
            if (isGameOver()) {
                return;
            }
        } else { // A beast, index is `target-2`
            Card targetCard = otherPlayer.getInPlay().getCard(Integer.parseInt(target) - 2);
            targetCard.damage(card.getAttack());
            card.damage(targetCard.getAttack());
            System.out.println(otherPlayer.getName() + "'s " + targetCard.getName() + " now has " + targetCard.getHealth() + " health.");
        }
    }

    private void playCardsFromHand(Player player) {
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card card : player.getHand().getCards()) {
            if (card.getManaCost() <= player.getManaAvailable()) {
                System.out.println(card);
                String play = getPrompt(
                        player.getName() + ", play " + card.getName() + "? (Yes/No): ",
                        new String[]{"Yes", "yes", "y", "No", "no", "n"}
                );
                if (play.equalsIgnoreCase("yes")) {
                    player.getInPlay().add(card);
                    player.useMana(card.getManaCost());
                    toRemove.add(card);
                    System.out.println(card.getName() + " played.");
                }
            }
        }
        player.getHand().removeAll(toRemove);
    }

    private void removeDeadCards(Player player) {
        ArrayList<Card> toRemove = new ArrayList<>();
        // Collect dead cards from the player's in-play cards
        for (Card card : player.getInPlay().getCards()) {
            if (card.getHealth() <= 0) {
                toRemove.add(card);
            }
        }
        // Remove dead cards from in-play and add them to the graveyard
        player.getInPlay().removeAll(toRemove);
        for (Card card : toRemove) {
            player.getGraveyard().add(card);
        }
        // Get the opponent and repeat the process
        Player otherPlayer = findOpponent(player);
        toRemove.clear();
        for (Card card : otherPlayer.getInPlay().getCards()) {
            if (card.getHealth() <= 0) {
                toRemove.add(card);
            }
        }
        otherPlayer.getInPlay().removeAll(toRemove);
        for (Card card : toRemove) {
            otherPlayer.getGraveyard().add(card);
        }
    }

    public static void printRules() {
        System.out.println("");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Welcome to PocketBeasts!");
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("");
        System.out.println("This basic console application tests our underlying software design patterns.");
        System.out.println("");
        System.out.println("Here's a key for each card:");
        System.out.println("");
        System.out.println("                             +-------+ ");
        System.out.println("M  = Mana Cost               |      M| ");
        System.out.println("ID = Card identifier:        |  ID   | ");
        System.out.println("A  = Attack:                 |       | ");
        System.out.println("H  = Health:                 |A     H| ");
        System.out.println("                             +-------+ ");
        System.out.println("");
        System.out.println("New players each start with 15 Health and 1 Mana to spend on playing cards.");
        System.out.println("At the start of the game each player draws 4 cards from their deck to hand.");
        System.out.println("");
        System.out.println("Players each take turns. Each turn consists four phases:");
        System.out.println("1. Add mana (mana increases by one each turn and replenishes in full).");
        System.out.println("2. Draw a card.");
        System.out.println("3. Cycle through your cards in play (if any), choosing whether to attack.");
        System.out.println("   a. Attacking the other player directly with your card inflicts damage to their health.");
        System.out.println("      equal to the attack power of the card.");
        System.out.println("   b. Attacking another players beast will damage both cards (equal to their attack values).");
        System.out.println("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
        System.out.println("4. Play cards from hand.");
        System.out.println("");
    }

    public static final Card[] STARTER_CARDS = new Card[] {
            new Beast("BR", "Barn Rat", 1, 1, 1),
            new Beast("SP", "Scampering Pup", 2, 2, 1),
            new Beast("HB", "Hardshell Beetle", 2, 1, 2),
            new Beast("VHC", "Vicious House Cat", 3, 3, 2),
            new Beast("GD", "Guard Dog", 3, 2, 3),
            new Beast("ARH", "All Round Hound", 3, 3, 3),
            new Beast("MO","Moor Owl", 4, 4, 2),
            new Beast("HT", "Highland Tiger", 5, 4, 4)
    };

    /**
     * Provides a starter deck for each player
     * @return An array list containing initial set of cards
     */
    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            starterDeck.addAll(Arrays.asList(STARTER_CARDS));
        }

        return starterDeck;
    }

}
