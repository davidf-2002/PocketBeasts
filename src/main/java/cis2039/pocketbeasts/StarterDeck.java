/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package cis2039.pocketbeasts;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author David Foomeni
 */
public class StarterDeck {
    public static final Card[] STARTER_CARDS = new Card[] {
            new Beast("BR", "Barn Rat", 1, 1, 1),
            new Beast("SP", "Scampering Pup", 2, 2, 1),
            new Beast("HB", "Hardshell Beetle", 2, 1, 2),
            new Beast("VHC", "Vicious House Cat", 3, 3, 2),
            new Beast("GD", "Guard Dog", 3, 2, 3),
            new Beast("ARH", "All Round Hound", 3, 3, 3),
            new Beast("MO","Moor Owl", 4, 4, 2),
            new Beast("HT", "Highland Tiger", 5, 4, 4)
    };

    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            starterDeck.addAll(Arrays.asList(STARTER_CARDS));
        }

        return starterDeck;
    }
}
