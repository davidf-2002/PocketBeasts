package cis2039.pocketbeasts.DecoratorPattern;

import cis2039.pocketbeasts.Card;

/**
 *  A decorator for Card objects that enhances the attack attribute of the card.
 *  This class applies a specific boost to the attack value of the card it decorates
 */
public class AttackBoostEnhancement extends CardDecorator {
    public AttackBoostEnhancement(Card decoratedCard) {
        super(decoratedCard);
    }

    /**
     * Returns the enhanced attack value of the decorated card. The enhancement increases
     * the original attack value by 80%.
     *
     * @return The enhanced attack value, rounded up to the nearest whole number.
     */
    @Override
    public int getAttack() {
        return (int) Math.ceil(super.getAttack() * 1.8);
    }

    @Override
    public int compareTo(Card o) {
        return 0;
    }

    @Override
    public String toString() {
        return super.decoratedCard.toString() + " - Attack boosted";
    }
}
