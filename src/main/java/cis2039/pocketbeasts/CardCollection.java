package cis2039.pocketbeasts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardCollection implements ICardCollection {
    private final ArrayList<Card> cards;

    public CardCollection(ArrayList<Card> initialCards) {
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
    public void add(Card card) {
        cards.add(card);
    }

    @Override
    public Card getCard(int index){
    if (index >= 0 && index < cards.size()) {
        return cards.get(index);
    }
    return null;
    }

    @Override
    public void remove(Card card) {
        cards.remove(card);
    }

    @Override
    public Card draw() {
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
    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public void removeAll(ArrayList<Card> cardsToBeRemoved) {
        cards.removeAll(cardsToBeRemoved);
    }

    @Override
    public void sort() {
        this.cards.sort(Comparator.comparingInt(Card::getManaCost));    // This uses a lambda expression to sort cards by their mana cost.
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.getName() + "\n");
        }
        return sb.toString();
    }

}
