package cis2039.pocketbeasts;

//public class HandTest {
//    private Hand hand;
//    private Card card1;
//    private Card card2;
//
//    @Before
//    public void setUp() {
//        hand = new Hand();
//
//        card1 = new Beast(Deck.STARTER_CARDS[1]);
//        card2 = new Beast(Deck.STARTER_CARDS[2]);
//
////        card1 = new GuardDog();  // Using the concrete subclass GuardDog
////        card2 = new HighlandTiger();  // Using the concrete subclass HighlandTiger
//        hand.add(card1);
//        hand.add(card2);
//    }
//
//    @Test
//    public void testAddCard() {
//        Card card3 = new Beast(Deck.STARTER_CARDS[3]);
//        hand.add(card3);
//        assertEquals(3, hand.count());  // Check if the count is correct after adding
//        assertTrue(hand.getCards().contains(card3));  // Ensure the card was added
//    }
//
//    @Test
//    public void testRemoveCard() {
//        hand.remove(card1);
//        assertEquals(1, hand.count());  // Check count after removing
//        assertFalse(hand.getCards().contains(card1));  // Check that the card is no longer present
//    }
//
//    @Test
//    public void testSortingCards() {
//        hand.sort();  // Sort cards, assuming sorting by mana cost or another attribute
//        assertEquals(card1, hand.getCard(0));  // Since GuardDog has lower mana cost
//    }
//
//    @Test
//    public void testGetCard() {
//        assertEquals(card1, hand.getCard(0));  // Verify correct retrieval by index
//        assertNull(hand.getCard(10));  // Verify handling of invalid index
//    }
//}
