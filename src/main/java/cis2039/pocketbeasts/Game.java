package cis2039.pocketbeasts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public void startGame() {
        printRules();

        System.out.println("Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        Player[] players = new Player[] {
                new Player("Steve", new CardCollection(getStarterDeck())),
                new Player("Chris", new CardCollection(getStarterDeck()))
        };

        for (Player player : players) {
            player.newGame();
            System.out.println(player);
        }

        String winningMessage = "";
        boolean run = true;
        while(run) {
            for (Player player : players) {
                // Add mana and draw card
                player.addMana();
                player.drawCard();

                // Print initial play state
                System.out.println(player);

                // HACK assumes only one other player
                Player otherPlayer = null;
                for (Player iPlayer : players) {
                    if (iPlayer != player) {
                        otherPlayer = iPlayer;
                    }
                }
                if (otherPlayer == null){
                    winningMessage = "Something has gone terribly wrong...";
                    run = false;
                    break;
                }

                // Cycle through cards in play to attack
                for (Card card : player.getInPlay().getCards()) {
                    System.out.println(card.toString());

                    String attack = getPrompt(
                            player.getName() + " attack with " + card.getName() + "? (Yes/No): ",
                            new String[]{"Yes", "yes", "y", "No", "no", "n"});
                    if (attack.equals("Yes") || attack.equals("yes") || attack.equals("y")) {
                        // Choose who to attack, player directly or a player's beast
                        int attackChoice = 2;
                        System.out.println("Who would you like to attack? ");
                        System.out.println("1. " + otherPlayer.getName());
                        for (Card otherCard: otherPlayer.getInPlay().getCards()) {
                            System.out.println(attackChoice + ". " + otherCard);
                            attackChoice++;
                        }
                        ArrayList<String> prompts = new ArrayList<>();
                        for (int i=1; i<attackChoice; i++) {
                            prompts.add(String.valueOf(i));
                        }
                        String target = getPrompt("Choose a number: ", prompts.toArray(new String[0]));
                        if (target.equals("1")) { // Player
                            if (otherPlayer.damage(card.getAttack())) {
                                // if true returned players health <= 0
                                winningMessage = player.getName() + " wins!";
                                run = false;
                                break;
                            }
                            System.out.println(otherPlayer.getName() + " is now at " + otherPlayer.getHealth());
                        }
                        else { // Beast, index is `target-2`
                            Card targetCard = otherPlayer.getInPlay().getCard(Integer.parseInt(target)-2);
                            targetCard.damage(card.getAttack());
                            card.damage(targetCard.getAttack());
                        }
                    }
                }

                if (!run) {
                    break;
                }

                // Cycle through cards in play remove "dead" cards (health <= 0)
                ArrayList<Card> toRemove = new ArrayList<>();
                for (Card card : player.getInPlay().getCards()) {
                    if (card.getHealth() <= 0) {
                        toRemove.add(card);
                        player.getGraveyard().add(card);
                    }
                }
                player.getInPlay().removeAll(toRemove);

                toRemove = new ArrayList<>();
                for (Card card : otherPlayer.getInPlay().getCards()) {
                    if (card.getHealth() <= 0) {
                        toRemove.add(card);
                        otherPlayer.getGraveyard().add(card);
                    }
                }
                otherPlayer.getInPlay().removeAll(toRemove);

                // Play cards from hand
                toRemove = new ArrayList<>();
                //ArrayList<Card> toRemove = new ArrayList<>();
                for (int i = 0; i < player.getHand().getCards().size(); i++) {
                    Card card = player.getHand().getCards().get(i);
                    if (card.getManaCost() <= player.getManaAvailable()) {
                        System.out.println(card);

                        String play = getPrompt(
                                player.getName() + ", play " + card.getName() + "? (Yes/No): ",
                                new String[]{"Yes", "yes", "Y", "No", "no", "N"});

                        if (play.equalsIgnoreCase("yes")) {
                            player.getInPlay().add(card);
                            player.useMana(card.getManaCost());
                            toRemove.add(card);
                            System.out.println(card.getName() + " played.");
                        }
                    }
                }
                player.getHand().removeAll(toRemove);


                // Print final play state
                System.out.println("\n".repeat(16));
                System.out.println(player);
            }
        }

        System.out.println(winningMessage);
    }

    public static String getPrompt(String prompt, String[] validResponse){
        System.out.print(prompt);

        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();

        if (Arrays.stream(validResponse).anyMatch(response::equals)) {
            return response;
        }

        return getPrompt(prompt, validResponse);
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

    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            starterDeck.addAll(Arrays.asList(STARTER_CARDS));
        }

        return starterDeck;
    }

}
