package cis2039.pocketbeasts.Collections;

import cis2039.pocketbeasts.Card;

import java.util.ArrayList;

public abstract class CardCollection {

    protected ArrayList<Card> cards = new ArrayList<>();

    public void add(Card card){
        cards.add(card);
    }

    public void remove(Card card){
        cards.remove(card);
    }

    public Card getCard(int index){
        if (index >= 0 && index < cards.size()) {
            return cards.get(index);
        }
        return null;
    }

    public int count() {
        return cards.size();
    }
}
