package cis2039.pocketbeasts.FactoryPattern;

import cis2039.pocketbeasts.Card;
import cis2039.pocketbeasts.CardCollection;
import cis2039.pocketbeasts.Player;

/**
 * This test harness is used to demonstrate the creation of the Card objects using the Factory pattern.
 */

import java.util.ArrayList;

public class FactoryTestHarness {
    private static final BeastFactory beastFactory = new BeastFactory();

    public static void main(String[] args) {
        Player player1 = new Player("Alice", new CardCollection(getStarterDeck()));
        Player player2 = new Player("Bob", new CardCollection(getStarterDeck()));

        player1.newGame();
        player2.newGame();

        simulateRound(player1);
        simulateRound(player2);
    }

    private static void simulateRound(Player player) {
        System.out.println("Starting round for " + player.getName());

        player.addMana();
        System.out.println(player.getName() + " has " + player.getManaAvailable() + " mana available.");

        player.drawCard();
        System.out.println(player.getName() + "'s hand:");
        System.out.println(player.getHand());

        ArrayList<Card> handCards = player.getHand().getCards();
        for (Card card : handCards) {
            if (card.getManaCost() <= player.getManaAvailable()) {
                player.getInPlay().add(card);
                player.useMana(card.getManaCost());
                System.out.println(player.getName() + " plays " + card.getName() + "!");
            }
        }

        System.out.println(player.getName() + "'s field:");
        System.out.println(player.getInPlay());
    }

    private static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();
        String[] cardIDs = {"BR", "SP", "HB", "VHC", "GD", "ARH", "MO", "HT"};

        for (String cardID : cardIDs) {
            starterDeck.add(beastFactory.createCard(cardID));
        }
        return starterDeck;
    }
}

