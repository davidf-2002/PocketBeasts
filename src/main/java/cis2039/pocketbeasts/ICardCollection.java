package cis2039.pocketbeasts;

import java.util.ArrayList;

public interface ICardCollection {
    int count();
    void add(ICard card);
    void remove(ICard card);
    ICard draw();
    ICard getCard(int index);
    void shuffle();
    ArrayList<ICard> getCards();
    void removeAll(ArrayList<ICard> cardsToBeRemoved);
    void sort();
}
