package hotstone.Varients.alphaStrat;


import hotstone.framework.Player;
import hotstone.framework.variants.DeckStrategies;

import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.Stack;

public class AlphaDeckStrat implements DeckStrategies {

    Stack<StandardCard> deck1 = new Stack<>();
    Stack<StandardCard> deck2 = new Stack<>();

    /**
     * Mutator Method to initialize a deck for each player in AlphaStone
     * @param who player whose deck to initialize
     * @return a stack being the players deck
     */
    public Stack<StandardCard> initializeDeck(Player who) {
        if (who == Player.FINDUS) {
            this.deck1.push(new StandardCard(GameConstants.SIETE_CARD, who, 3, 2, 4, null));
            this.deck1.push(new StandardCard(GameConstants.SEIS_CARD, who, 2, 1, 3, null));
            this.deck1.push(new StandardCard(GameConstants.CINCO_CARD, who, 3, 5, 1, null));
            this.deck1.push(new StandardCard(GameConstants.CUATRO_CARD, who, 2, 3, 1, null));
            this.deck1.push(new StandardCard(GameConstants.TRES_CARD, who, 3, 3, 3, null));
            this.deck1.push(new StandardCard(GameConstants.DOS_CARD, who, 2, 2, 2, null));
            this.deck1.push(new StandardCard(GameConstants.UNO_CARD, who, 1, 1, 1, null));
            return deck1;
        } else {
            this.deck2.push(new StandardCard(GameConstants.SIETE_CARD, who, 3, 2, 4, null));
            this.deck2.push(new StandardCard(GameConstants.SEIS_CARD, who, 2, 1, 3, null));
            this.deck2.push(new StandardCard(GameConstants.CINCO_CARD, who, 3, 5, 1, null));
            this.deck2.push(new StandardCard(GameConstants.CUATRO_CARD, who, 2, 3, 1, null));
            this.deck2.push(new StandardCard(GameConstants.TRES_CARD, who, 3, 3, 3, null));
            this.deck2.push(new StandardCard(GameConstants.DOS_CARD, who, 2, 2, 2, null));
            this.deck2.push(new StandardCard(GameConstants.UNO_CARD, who, 1, 1, 1, null));
            return deck2;
        }
    }



}
