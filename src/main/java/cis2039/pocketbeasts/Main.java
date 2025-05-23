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

import cis2039.pocketbeasts.ObserverPatttern.TestRunner;
import cis2039.pocketbeasts.Template.CardGameRunner;

/**
 * @author David Foomeni
 * When using a game mode uncomment out the other game
 */
public class Main {
    public static void main(String[] args) {

//        // Main Game
//        Game newGame = new Game();
//        newGame.startGame();

//        // Game Runner
//        CardGameRunner game = new CardGameRunner();
//        game.startGame();

        // Test Runner
        TestRunner testGame = new TestRunner();
        testGame.startGame();

    }
}

