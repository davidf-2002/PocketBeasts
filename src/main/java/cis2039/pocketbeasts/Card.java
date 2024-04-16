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

/**
 *
 * @author David Foomeni
 */

public interface Card {
    String getId();

    String getName();

    int getManaCost();

    int getAttack();

    int getHealth();

    void damage(int amount);


//    @Override
//    public String toString() {
//        return this.name + " (" + this.id + ") Mana Cost/" + this.manaCost +
//                " Attack/" + this.attack + " Health/" + this.health;
//    }
//
//    @Override
//    public int compareTo(Card o) {
//        return Integer.compare(this.getManaCost(), o.getManaCost());
//    }
}