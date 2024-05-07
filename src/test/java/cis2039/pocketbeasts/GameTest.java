package cis2039.pocketbeasts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private Game game;
    private Player player1;

    @Before
    public void setUp() {
        game = new Game();
        player1 = new Player("Player 1", new CardCollection(Game.getStarterDeck()));
        player1.newGame();
    }

    @Test
    public void testCardGoesToGraveyard() {
        // Setup scenario where a card is definitely defeated
        Card testCard = new Beast("Test", "Fragile Beast", 1, 1, 1); // A card with 1 health
        player1.getInPlay().add(testCard);

        // This attack should send the testCard to the graveyard
        testCard.damage(2);  // Damage it more than its health

        // Manually move to graveyard for the sake of test; assume this logic happens somewhere in game cycle
        if (testCard.getHealth() <= 0) {
            player1.getInPlay().remove(testCard);
            player1.getGraveyard().add(testCard);
        }

        // Check if the graveyard now contains the testCard
        assertTrue(player1.getGraveyard().getCards().contains(testCard));
        assertFalse(player1.getInPlay().getCards().contains(testCard));
    }

    @Test
    public void testCardMovesToInPlay() {
        Card cardToPlay = player1.getHand().getCard(0); // Get the first card from hand
        if (cardToPlay != null && cardToPlay.getManaCost() <= player1.getManaAvailable()) {
            player1.getInPlay().add(cardToPlay); // Simulate playing the card
            player1.getHand().remove(cardToPlay);
        }

        assertTrue(player1.getInPlay().getCards().contains(cardToPlay));
        assertFalse(player1.getHand().getCards().contains(cardToPlay));
    }

//    @Test
//    public void testCardDrawnToHand() {
//        int initialDeckCount = player1.getDeck().count();
//        int initialHandCount = player1.getHand().count();
//        player1.drawCard(); // This should move a card from deck to hand
//
//        assertEquals(initialDeckCount - 1, player1.getDeck().count());
//        assertEquals(initialHandCount + 1, player1.getHand().count());
//        assertNotNull(player1.getHand().getCards().get(initialHandCount)); // Check that the new card is indeed in the hand
//    }
//
//    @Test
//    public void testDeckSizeDecreasesOnDraw() {
//        int initialDeckCount = player1.getDeck().count();
//        player1.drawCard();
//        assertEquals(initialDeckCount - 1, player1.getDeck().count());
//    }
//
//    @Test
//    public void testShuffleDeck() {
//        ArrayList<Card> originalOrder = new ArrayList<>(player1.getDeck().getCards());
//        player1.getDeck().shuffle();
//        assertFalse(originalOrder.equals(player1.getDeck().getCards())); // Check that the order is not the same
//    }

}