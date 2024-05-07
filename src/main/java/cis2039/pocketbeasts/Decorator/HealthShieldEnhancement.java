package cis2039.pocketbeasts.Decorator;

import cis2039.pocketbeasts.Card;

/**
 * A decorator for Card objects that adds a one-time shield to absorb any incoming damage.
 * This enhancement is designed to activate once when the card first takes damage, after which the
 * shield is deactivated
 */
public class HealthShieldEnhancement extends CardDecorator {
    boolean shieldActive;

    public HealthShieldEnhancement(Card decoratedCard) {
        super(decoratedCard);
        this.shieldActive = true;
    }

    /**
     * Applies damage to the card. If the shield is active, the damage is absorbed and the shield
     * is deactivated. If the shield is not active, the damage is passed to the decorated card.
     *
     * @param amount The amount of damage that would be inflicted on the card.
     */
    @Override
    public void damage(int amount) {
        if (shieldActive) {
            // When shield is active, absorb damage and deactivate shield
            shieldActive = false;
            System.out.println("Shield absorbed the damage. Shield is now inactive.");
        } else {
            // Once shield is inactive, pass damage to the decorated card
            super.damage(amount);
        }
    }

    @Override
    public String toString() {
        return super.decoratedCard.toString() + (shieldActive ? " - Health Shield active" : " - Health Shield used");
    }
}
