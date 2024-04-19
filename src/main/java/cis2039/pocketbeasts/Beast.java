package cis2039.pocketbeasts;

public class Beast implements ICard {
    protected String id;
    protected String name;
    protected int manaCost;
    protected int attack;
    protected int health;

    public Beast(String id, String name, int manaCost, int attack, int health) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String getId() { return id; }
    @Override
    public String getName() { return name; }
    @Override
    public int getManaCost() { return manaCost; }
    @Override
    public int getAttack() { return attack; }
    @Override
    public int getHealth() { return health; }

    @Override
    public void damage(int amount) {
        health -= amount;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(ICard o) {
        return Integer.compare(this.getManaCost(), o.getManaCost());
    }
}
