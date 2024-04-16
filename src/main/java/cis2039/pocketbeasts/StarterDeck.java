package cis2039.pocketbeasts;

/**
 *
 * @author David Foomeni
 */

import cis2039.pocketbeasts.Cards.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StarterDeck {
    public static final Card[] STARTER_CARDS = new Card[] {
            new BarnRat(),
            new ScamperingPup(),
            new HardshellBeetle(),
            new ViciousHouseCat(),
            new GuardDog(),
            new AllRoundHound(),
            new MoorOwl(),
            new HighlandTiger()
    };

    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            starterDeck.addAll(Arrays.asList(STARTER_CARDS));
        }

        return starterDeck;
    }
}

