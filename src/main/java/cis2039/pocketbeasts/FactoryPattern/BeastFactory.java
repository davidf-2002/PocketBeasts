package cis2039.pocketbeasts.FactoryPattern;

import cis2039.pocketbeasts.Beast;
import cis2039.pocketbeasts.Card;

public class BeastFactory implements CardFactory {
    @Override
    public Card createCard(String id) {
        switch (id) {
            case "BR":
                return new Beast("BR", "Barn Rat", 1, 1, 1);
            case "SP":
                return new Beast("SP", "Scampering Pup", 2, 2, 1);
            case "HB":
                return new Beast("HB", "Hardshell Beetle", 2, 1, 2);
            case "VHC":
                return new Beast("VHC", "Vicious House Cat", 3, 3, 2);
            case "GD":
                return new Beast("GD", "Guard Dog", 3, 2, 3);
            case "ARH":
                return new Beast("ARH", "All Round Hound", 3, 3, 3);
            case "MO":
                return new Beast("MO", "Moor Owl", 4, 4, 2);
            case "HT":
                return new Beast("HT", "Highland Tiger", 5, 4, 4);
            default:
                throw new IllegalArgumentException("Unknown beast ID: " + id);
        }
    }
}
