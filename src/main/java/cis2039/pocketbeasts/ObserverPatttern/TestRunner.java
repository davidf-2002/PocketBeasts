package cis2039.pocketbeasts.ObserverPatttern;

import cis2039.pocketbeasts.*;
import cis2039.pocketbeasts.GameLogger;
import cis2039.pocketbeasts.Template.GameRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TestRunner extends GameRunner {
    public GameState gameState;

    public TestRunner() {
        this(new String[]{"Steve", "Chris"});
    }

    public TestRunner(String[] playerNames) {
        Player[] players = new Player[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i], new CardCollection(getStarterDeck()));
        }
        this.gameState = new GameState(players);
        setupSpectators();
    }

    @Override
    protected void gameInitialization() {
        printRules();
        System.out.println("Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        for (Player player : gameState.getPlayers()) {
            player.newGame();
            System.out.println(player);
        }
    }

    @Override
    protected boolean isGameOver() {
        for (Player player : gameState.getPlayers()) {
            if (player.getHealth() <= 0) {
                gameState.endGame(player.getName() + " has lost the game.");
                return true;
            }
        }
        return false;
    }

    @Override
    protected void playTurn() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Player otherPlayer = gameState.getOpponent();

        GameLogger.logEvent(currentPlayer.getName() + "'s turn starts.");
        gameState.notifySpectators(currentPlayer.getName() + "'s turn starts");

        GameLogger.logEvent("Mana and Card Draw Phase begins.");
        addManaAndDrawCard(currentPlayer);

        GameLogger.logEvent("Combat Phase begins.");
        handleCardAttacks(currentPlayer, otherPlayer);

        if (isGameOver()) {
            finalizeGame();
            return;
        }

        GameLogger.logEvent("Card Removal Phase begins.");
        removeDeadCards(currentPlayer, otherPlayer);

        GameLogger.logEvent("Card Play Phase begins.");
        gameState.notifySpectators(currentPlayer.getName() + " is considering playing his cards.");
        playCardsFromHand(currentPlayer);

        printSeparator();
        printPlayerState(currentPlayer);

        gameState.getOpponent();
        if (gameState.isGameRunning()) {
            GameLogger.logEvent(currentPlayer.getName() + "'s turn ends.");
        } else {
            finalizeGame();
        }

    }

    @Override
    protected void finalizeGame() {
        System.out.println(gameState.getWinningMessage());
    }

    private void printPlayerState(Player player) {
        System.out.println("Player: " + player.getName());
        System.out.println("Health: " + player.getHealth() + ", Mana: " + player.getManaAvailable());
        System.out.println("Deck size: " + player.getDeck().count() + ", Hand size: " + player.getHand().count());
        System.out.println("In play cards:");
        for (Card card : player.getInPlay().getCards()) {
            System.out.println("Card: " + card.getName() + ", Attack: " + card.getAttack() + ", Health: " + card.getHealth());
        }
        System.out.println("Graveyard cards:");
        for (Card card : player.getGraveyard().getCards()) {
            System.out.println("Card: " + card.getName());
        }
        GameLogger.logEvent("State printed for player: " + player.getName());
    }

    private void printSeparator() {
        System.out.println("\n".repeat(16));
    }

    private void addManaAndDrawCard(Player player) {
        player.addMana();
        gameState.notifySpectators(player.getName() + " is about to draw a card has " + player.getManaAvailable() + " mana.");
        player.drawCard();
        System.out.println(player);
    }

    private void handleCardAttacks(Player player, Player otherPlayer) {
        for (Card card : player.getInPlay().getCards()) {
            System.out.println(card.toString());

            gameState.notifySpectators(player.getName() + " is considering an attack with " + card.getName());

            String attack = getPrompt(
                    player.getName() + " attack with " + card.getName() + "? (Yes/No): ",
                    new String[]{"Yes", "yes", "y", "No", "no", "n"}
            );
            if (attack.equalsIgnoreCase("Yes") || attack.equalsIgnoreCase("y")) {
                executeAttack(player, card, otherPlayer);
                if (isGameOver()) {
                    break;
                }
            }
        }
    }

    private void executeAttack(Player player, Card card, Player otherPlayer) {
        GameLogger.logEvent(player.getName() + " decides to attack with " + card.getName());
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
            gameState.notifySpectators(card.getName() + " attacks " + otherPlayer.getName() + " directly for " + card.getAttack() + " damage.");
            System.out.println(otherPlayer.getName() + " is now at " + otherPlayer.getHealth() + " health.");
            if (isGameOver()) {
                return;
            }
        } else { // A beast, index is `target-2`
            Card targetCard = otherPlayer.getInPlay().getCard(Integer.parseInt(target) - 2);
            targetCard.damage(card.getAttack());
            card.damage(targetCard.getAttack());
            gameState.notifySpectators(card.getName() + " attacks " + targetCard.getName() + " dealing " + card.getAttack() + " damage and receiving " + targetCard.getAttack() + " damage in return.");
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
                    gameState.notifySpectators(player.getName() + " plays " + card.getName() + ", costing " + card.getManaCost() + " mana.");
                    gameState.notifySpectators("Turn ended. Next player is " + gameState.getOpponent().getName());
                }
                else{
                    gameState.notifySpectators(player.getName() + " did not play any cards this turn.");
                }
            }
        }
        player.getHand().removeAll(toRemove);    }

    private void removeDeadCards(Player currentPlayer, Player otherPlayer) {
        removeDeadCardsFromPlayer(currentPlayer);
        removeDeadCardsFromPlayer(otherPlayer);
    }

    private void removeDeadCardsFromPlayer(Player player) {
        ArrayList<Card> toRemove = new ArrayList<>();
        for (Card card : player.getInPlay().getCards()) {
            if (card.getHealth() <= 0) {
                toRemove.add(card);
            }
        }
        player.getInPlay().removeAll(toRemove);
        for (Card card : toRemove) {
            player.getGraveyard().add(card);
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

    private void setupSpectators() {
        ISpectator consoleSpectator = new Spectator();  // Create a new spectator instance
        gameState.addSpectator(consoleSpectator);  // Add the spectator to the game state
    }

}