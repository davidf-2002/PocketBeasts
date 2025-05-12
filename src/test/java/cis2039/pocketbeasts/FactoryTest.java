package cis2039.pocketbeasts;

import cis2039.pocketbeasts.FactoryPattern.BeastFactory;
import cis2039.pocketbeasts.FactoryPattern.CardFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

    @Test
    public void testCreateCardValidId() {
        CardFactory factory = new BeastFactory();

        // Test creation of each known beast card
        String[] validIds = {"BR", "SP", "HB", "VHC", "GD", "ARH", "MO", "HT"};
        for (String id : validIds) {
            Card card = factory.createCard(id);
            assertNotNull("Card creation should not be null for ID: " + id, card);
            assertEquals("Created card should have correct ID: " + id, id, card.getId());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCardInvalidId() {
        CardFactory factory = new BeastFactory();

        // Test creation with an invalid ID
        String invalidId = "InvalidID";
        factory.createCard(invalidId);
        fail("Expected IllegalArgumentException for invalid ID: " + invalidId);
    }
}