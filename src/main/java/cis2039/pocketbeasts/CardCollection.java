package cis2039.pocketbeasts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardCollection implements ICardCollection {
    private final ArrayList<ICard> cards;

    public CardCollection(ArrayList<ICard> initialCards) {
        this.cards = new ArrayList<>(initialCards);  // Initialize with the provided deck
    }
    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    @Override
    public int count() {
        return cards.size();
    }


    @Override
    public void add(ICard card) {
        cards.add(card);
    }

    @Override
    public ICard getCard(int index){
    if (index >= 0 && index < cards.size()) {
        return cards.get(index);
    }
    return null;
    }

    @Override
    public void remove(ICard card) {
        cards.remove(card);
    }

    @Override
    public ICard draw() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public ArrayList<ICard> getCards() {
        return cards;
    }

    @Override
    public void removeAll(ArrayList<ICard> cardsToBeRemoved) {
        cards.removeAll(cardsToBeRemoved);
    }

    @Override
    public void sort() {
        this.cards.sort(Comparator.comparingInt(ICard::getManaCost));    // This uses a lambda expression to sort cards by their mana cost.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ICard card : cards) {
            sb.append(card.getName() + "\n");
        }
        return sb.toString();
    }

}
