package hotstone.standard;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public class StandardHero implements Hero, MutableHero {
    private int currentMana;
    private Player playerOwningHero;
    private int currentHealth;
    private ArrayList<StandardCard> hand;
    private Stack<StandardCard> heroDeck;
    private boolean active;
    private String heroType;
    private String id;

    /**
     * Constructor
    **/
    StandardHero(){
        this.currentHealth = GameConstants.HERO_MAX_HEALTH;
        //create hand and deck
        this.hand = new ArrayList<>();
        //adding cards to hand
        //hero can use hero power
        this.active = true;
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Mutator method to set mana that the hero has.
     *
     * @param amount of mana the hero should have.
     */
    public void setMana(int amount) {
        currentMana = amount;
    }

    /**
     * Mutator method to reduce the amount of mana a hero has.
     *
     * @param amount of mana to be reduced
     */
    public void reduceMana(int amount) {
        currentMana -= amount;
    }


    /**
     * Mutator method to add cards to a heros hand.
     *
     * @param amount of cards to be drawn
     */
    public void drawFromDeck(int amount) {
        if(heroDeck.isEmpty()) {
            reduceHealth(GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK);
        } else {
                for(int i = 0; i < amount; i++) {
                    StandardCard cardDrawn = this.heroDeck.pop();
                    hand.add(0, cardDrawn);
            }
        }
    }

    @Override
    public void setType(String type) {
        this.heroType = type;
    }


    @Override
    public void setDeck(Stack<StandardCard> deck) {
        this.heroDeck = deck;
    }

    @Override
    public void setOwner(Player who) {
        this.playerOwningHero = who;
    }


    /**
     * Checks if a card is in the hero's deck
     *
     * @param name of the card you want to find
     * @return true if the card is in the deck, and false if it isn't
     */
    public boolean checkCardInDeck(String name) {
        for (StandardCard c: heroDeck) {
            if (c.getName() == name) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCardInHand(String name) {
        for (StandardCard c: hand) {
            if (c.getName() == name) {
                return true;
            }
        }
        return false;
    }

    /**
     * Accessor method to the deck of a hero.
     *
     * @return the deck stack
     */
    public Stack<StandardCard> getDeck() {
        return heroDeck;
    }

    /**
     * Accessor method to get a heros mana
     *
     * @return integer being the amount of mana of the hero
     */
    @Override
    public int getMana() {
        return this.currentMana;
    }

    /**
     * Accessor method to get the health of a hero
     *
     * @return integer being the amount of health of the hero
     */
    @Override
    public int getHealth() {
        return currentHealth;
    }

    /**
     * Mutator method for reducing health of a hero.
     *
     * @param amount of health to be reduced
     */
    public void reduceHealth(int amount) {
        currentHealth -= amount;
    }

    /**
     * Accessor method to know if a hero can use his hero power
     *
     * @return true if hero can use hero power and false if hero can't
     */
    @Override
    public boolean isActive() {
        return active;
    }

    /**
     * Mutator method to change the state of a hero, (so he can't use his hero power twice)
     *
     * @param state is a boolean that determines if a hero is active or not
     */
    public void setActive(boolean state) {
        active = state;
    }

    /**
     * Accessor method to get type of hero (AlphaStone only has baby)
     *
     * @return the name of the hero type. currently only baby
     */
    @Override
    public String getType() {
        return heroType;
    }

    /**
     * Accessor method to get the Player owning the hero.
     *
     * @return player being the owner of the hero
     */
    @Override
    public Player getOwner() {
        return playerOwningHero;
    }

    /**
     * get the effect description of a hero's hero-power
     *
     * @return The description of the hero-power of this hero
     */
    @Override
    public String getEffectDescription() {
        switch (heroType) {
            case GameConstants.BABY_HERO_TYPE -> {
                return "Is Cute";
            }
            case GameConstants.THAI_CHEF_HERO_TYPE -> {
                return "Opp H: (0,-2)";
            }
            case GameConstants.DANISH_CHEF_HERO_TYPE -> {
                return "Field Sovs";
            }
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> {
                return "Opp M: (0,-2)";
            }
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> {
                return "M: (+2,0)";
            }
        }
        return "This hero doesn't have a description";
    }

    /**
     * Accessor method to get a hero's hand
     *
     * @return ArrayList being a hero's hand.
     */
    public ArrayList<StandardCard> getHeroHand() {
        return hand;
    }

    @Override
    public void intializeHand(Player who) {
        drawFromDeck(3);
    }


    @Override
    public String getId() {
        return id;
    }
}
