package hotstone.framework;

import hotstone.standard.StandardCard;

import java.util.ArrayList;
import java.util.Stack;

public interface MutableHero extends Hero{

    void setMana(int mana);

    void reduceMana(int amount);

    void reduceHealth(int amount);

    void setActive(boolean state);

    void drawFromDeck(int amount);

    void setType(String type);
    void setDeck(Stack<StandardCard> deck);

    void setOwner(Player who);

    Stack<StandardCard> getDeck();

    ArrayList<StandardCard> getHeroHand();
    void intializeHand(Player who);

}
