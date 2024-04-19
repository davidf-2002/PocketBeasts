package cis2039.pocketbeasts;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        ArrayList<ICard> starterDeck = StarterDeck.getStarterDeck();
        ICard testCard = starterDeck.get(0);      // Testing "Barn Rat", 1, 1, 1
        player = new Player("TestPlayer", new CardCollection(StarterDeck.getStarterDeck()));
    }

    @Test
    public void testAddMana() {
        player.addMana();
        assertEquals(1, player.getManaAvailable());
    }

    @Test
    public void testDrawCard() {
        int initialCount = player.getHand().count();
        player.drawCard();
        assertEquals(initialCount + 1, player.getHand().count());
    }

    @Test
    public void testTakeDamage() {
        int initialHealth = player.getHealth();
        player.damage(5);
        assertEquals(initialHealth - 5, player.getHealth());
    }

    @Test
    public void testManaUsage() {
        player.addMana();
        player.useMana(1);
        assertEquals(0, player.getManaAvailable());
    }
}