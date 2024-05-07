package cis2039.pocketbeasts.Decorator;

import cis2039.pocketbeasts.Card;

/**
 * Provides a flexible alternative to subclassing for extending functionality by wrapping Card instances
 * to add new behaviours or modify existing ones. This class serves as the base class for all specific card decorators
 */
public abstract class CardDecorator implements Card {
    protected Card decoratedCard;

    /**
     * Constructs a CardDecorator wrapping a given Card
     * @param decoratedCard the Card instance to be decorated
     */
    public CardDecorator(Card decoratedCard){
        this.decoratedCard = decoratedCard;
    }

    public String getId() {return decoratedCard.getId();}
    public String getName() {return decoratedCard.getName();}
    public int getManaCost() {return decoratedCard.getManaCost();}
    public int getAttack() {return decoratedCard.getAttack();}
    public int getHealth() {return decoratedCard.getHealth();}
    public void damage(int amount) {decoratedCard.damage(amount);}

}

