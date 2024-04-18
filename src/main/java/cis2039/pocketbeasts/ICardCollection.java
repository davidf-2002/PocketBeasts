package cis2039.pocketbeasts;

import java.util.ArrayList;

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
