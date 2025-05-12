package cis2039.pocketbeasts.DecoratorPattern;

import cis2039.pocketbeasts.Beast;
import cis2039.pocketbeasts.Card;
import cis2039.pocketbeasts.CardCollection;
import cis2039.pocketbeasts.Player;
import java.util.ArrayList;

/**
 * This test harness is used to demonstrate the enhancements created using the decorator pattern.
 * I have tested the attack boost and the health shield enhancements.
 */
public class DecoratorTestHarness {
    public static void main(String[] args) {
        Player player1 = new Player("Chris", new CardCollection(getStarterDeck()));
        Player player2 = new Player("Steve", new CardCollection(getStarterDeck()));

        player1.drawCard();
        player1.drawCard();
        player2.drawCard();
        player2.drawCard();

        // Apply enhancements
        Card player1Card1 = new AttackBoostEnhancement(player1.getHand().getCard(0)); // Player 1's first card gets attack boost
        Card player1Card2 = player1.getHand().getCard(1);
        Card player2Card1 = new HealthShieldEnhancement(player2.getHand().getCard(0)); // Player 2's first card gets health shield
        Card player2Card2 = player2.getHand().getCard(1);

        player1.getInPlay().add(player1Card1);
        player1.getInPlay().add(player1Card2);
        player2.getInPlay().add(player2Card1);
        player2.getInPlay().add(player2Card2);

        System.out.println("Initial State:");
        System.out.println(player1.getName() + " plays " + player1Card1.getName() + " with attack boost: " + player1Card1.getAttack());
        System.out.println(player1.getName() + " also plays " + player1Card2.getName());
        System.out.println(player2.getName() + " plays " + player2Card1.getName() + " with health shield");
        System.out.println(player2.getName() + " also plays " + player2Card2.getName());

        // Simulate attacks
        System.out.println("\n" + player1.getName() + "'s normal card attacks " + player2.getName() + "'s shielded card");
        player2Card1.damage(player1Card2.getAttack()); // Player 1's normal card attacks Player 2's shielded card

        // Check if shield absorbed the damage
        if (((HealthShieldEnhancement) player2Card1).shieldActive) {
            System.out.println(player2Card1.getName() + " absorbed the damage, shield now inactive.");
        } else {
            System.out.println(player2Card1.getName() + "'s remaining health: " + player2Card1.getHealth());
        }

        // Player 1's boosted attack card attacks Player 2's normal card
        System.out.println("\n" + player1.getName() + "'s boosted attack card attacks " + player2.getName() + "'s normal card");
        player2Card2.damage(player1Card1.getAttack()); // Apply boosted attack to player 2's normal card
        System.out.println(player2Card2.getName() + " took damage, remaining health: " + player2Card2.getHealth());

        System.out.println("\nEnd of Round:");
        System.out.println(player1.getName() + "'s boosted card: " + player1Card1.getName() + " - Attack: " + player1Card1.getAttack());
        System.out.println(player2.getName() + "'s shielded card: " + player2Card1.getName() + " - Health: " + player2Card1.getHealth());
        System.out.println(player2.getName() + "'s normal card: " + player2Card2.getName() + " - Health: " + player2Card2.getHealth());
    }

    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        deck.add(new Beast("BR", "Barn Rat", 1, 1, 1));
        deck.add(new Beast("SP", "Scampering Pup", 2, 2, 2));
        return deck;
    }
}
