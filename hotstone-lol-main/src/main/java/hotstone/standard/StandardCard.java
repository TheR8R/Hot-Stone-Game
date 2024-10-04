package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.MutableCard;
import hotstone.framework.Player;

import java.util.UUID;


public class StandardCard implements Card, MutableCard {
    private String cardName;
    private int attack;
    private int health;
    private int manaCost;
    private boolean active;
    private Player owner;
    private String effectDescription;
    private String id;


    /**
     * Constructor
     *
     * @param name of the card
     * @param who player owning this card
     */
    public StandardCard(String name, Player who, int mana, int att, int hp, String effect){
        this.cardName = name;
        this.owner = who;
        this.active = false;
        this.manaCost = mana;
        this.attack = att;
        this.health = hp;
        this.effectDescription = effect;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Accessor method to get name of a card.
     *
     * @return name of card
     */
    @Override
    public  String getName() {
        return cardName;
    }

    /**
     * Accessor method to get the mana cost of a card.
     *
     * @return mana cost of a card
     */
    @Override
    public int getManaCost() {
        return manaCost;
    }

    /**
     * Accessor method to get the attack of a card.
     *
     * @return attack of a card
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Accessor method to get the health of a card.
     *
     * @return health of a card
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Mutator method to change health of a card.
     *
     * @param damage done to a card
     */
    public void changeHealth(int damage) {
        health -= damage;
    }



    /**
     * Accessor method to see if a card is active.
     *
     * @return boolean being true for active and false for inactive.
     */
    public boolean isActive() {
        return active;
    }

    @Override
    public String getEffectDescription() {
        return effectDescription;
    }

    /**
     * Mutator method to activate or deactivate minions.
     *
     * @param state if a minion should be active or inactive
     */
    public void setActive(boolean state) {
        active = state;
    }

    public void changeAttack(int amount) {
        attack += amount;
    }

    /**
     * Accessor method to see the owner of this card.
     *
     * @return player owning this card
     */
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public String getId() {
        return id;
    }
}
