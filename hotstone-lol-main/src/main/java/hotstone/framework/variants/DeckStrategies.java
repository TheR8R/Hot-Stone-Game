package hotstone.framework.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardCard;

import java.util.Stack;

public interface DeckStrategies {
    /**
     * constructor Method to initialize a deck for each player
     *
     * @param who player whose deck to initialize
     * @return a stack being the players deck
     */
    Stack<StandardCard> initializeDeck(Player who);


}

