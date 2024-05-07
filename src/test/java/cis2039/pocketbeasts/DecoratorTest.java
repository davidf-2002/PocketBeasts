package cis2039.pocketbeasts;

import cis2039.pocketbeasts.Decorator.AttackBoostEnhancement;
import cis2039.pocketbeasts.Decorator.HealthShieldEnhancement;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DecoratorTest {

    @Test
    public void testAttackPotionIncreasesAttack() {
        Card baseCard = new Beast("SP", "Scampering Pup", 2, 2, 3);
        Card decoratedCard = new AttackBoostEnhancement(baseCard);

        assertEquals("Attack should be increased by 80%", 4, decoratedCard.getAttack());
        assertEquals("Health should remain unchanged", 3, decoratedCard.getHealth());
        assertEquals("Mana cost should remain unchanged", 2, decoratedCard.getManaCost());
    }

    @Test
    public void testHealthShieldPotionPreventsFirstDamage() {
        Card baseCard = new Beast("BR", "Barn Rat", 1, 1, 2);
        Card decoratedCard = new HealthShieldEnhancement(baseCard);

        decoratedCard.damage(1);  // First attack should be absorbed
        assertEquals("Health should remain unchanged after first damage", 2, decoratedCard.getHealth());

        decoratedCard.damage(1);  // Second attack should apply
        assertEquals("Health should decrease by 1 after second attack", 1, decoratedCard.getHealth());
    }

    @Test
    public void testStackedDecorators() {
    }

}
