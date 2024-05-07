package cis2039.pocketbeasts;

import java.util.ArrayList;

/**
 * Defines the contract for a collection of cards. This interface specifies methods
 * for managing a card collection within a card game
 */
public interface ICardCollection {
    int count();
    void add(Card card);
    void remove(Card card);
    Card draw();
    Card getCard(int index);
    void shuffle();
    ArrayList<Card> getCards();
    void removeAll(ArrayList<Card> cardsToBeRemoved);
    void sort();

}
