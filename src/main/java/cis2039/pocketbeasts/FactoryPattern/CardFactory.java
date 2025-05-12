package cis2039.pocketbeasts.FactoryPattern;

import cis2039.pocketbeasts.Card;

public interface CardFactory {
    Card createCard(String id);
}
