package cis2039.pocketbeasts.Factory;

import cis2039.pocketbeasts.Beast;
import cis2039.pocketbeasts.Card;

public class BeastFactory implements CardFactory {
    public Card createCard(String id) {
        switch (id) {
            case "BR":
                return new Beast("BR", "Barn Rat", 1, 1, 1);
            // Additional beast types
            default:
                throw new IllegalArgumentException("Unknown beast ID: " + id);
        }
    }
}