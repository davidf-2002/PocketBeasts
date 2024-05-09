package cis2039.pocketbeasts;

import org.junit.Before;
import org.junit.Test;

import static cis2039.pocketbeasts.Game.getStarterDeck;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private Game game;
    private Player player1;

    @Before
    public void setUp() {
        game = new Game();
        player1 = new Player("Player 1", new CardCollection(getStarterDeck()));
        player1.newGame();
    }

    @Test
    public void testCardGoesToGraveyard() {
        Card testCard = new Beast("Test", "Fragile Beast", 1, 1, 1);
        player1.getInPlay().add(testCard);

        testCard.damage(2);

        if (testCard.getHealth() <= 0) {
            player1.getInPlay().remove(testCard);
            player1.getGraveyard().add(testCard);
        }

        assertTrue(player1.getGraveyard().getCards().contains(testCard));
        assertFalse(player1.getInPlay().getCards().contains(testCard));
    }

}