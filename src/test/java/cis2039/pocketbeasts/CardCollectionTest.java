package cis2039.pocketbeasts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardCollectionTest {

    private CardCollection collection;
    private Beast beast1;
    private Beast beast2;

    @Before
    public void setUp() {
        collection = new CardCollection();
        beast1 = new Beast("BR", "Barn Rat", 1, 1, 1);
        beast2 = new Beast("SP", "Scampering Pup", 2, 2, 1);
    }

    @Test
    public void testEmptyInitialization() {
        assertEquals(0, collection.count());
    }

    @Test
    public void testAddCard() {
        collection.add(beast1);
        assertEquals(1, collection.count());
    }

    @Test
    public void testAddMultipleCards() {
        collection.add(beast1);
        collection.add(beast2);
        assertEquals(2, collection.count());
    }

    @Test
    public void testRemoveExistingCard() {
        collection.add(beast1);
        collection.remove(beast1);
        assertEquals(0, collection.count());
    }

    @Test
    public void testRemoveNonExistentCard() {
        collection.add(beast1);
        collection.remove(beast2); // beast2 was never added
        assertEquals(1, collection.count());
    }

    @Test
    public void testDrawCard() {
        collection.add(beast1);
        collection.add(beast2);
        Card drawnCard = collection.draw();
        assertEquals("Barn Rat", drawnCard.getName());
        assertEquals(1, collection.count());
    }

    @Test
    public void testRemoveMultipleSpecificCards() {
        collection.add(beast1);
        collection.add(beast2);
        collection.add(new Beast("GD", "Guard Dog", 3, 2, 3));
        ArrayList<Card> toRemove = new ArrayList<>();
        toRemove.add(beast1);
        toRemove.add(beast2);
        collection.removeAll(toRemove);
        assertEquals(1, collection.count());
        assertEquals("Guard Dog", collection.getCards().get(0).getName());
    }

}
