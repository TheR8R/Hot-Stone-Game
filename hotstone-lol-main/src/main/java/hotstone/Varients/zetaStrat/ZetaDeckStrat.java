package hotstone.Varients.zetaStrat;

import hotstone.framework.Player;
import hotstone.framework.variants.DeckStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.Stack;


public class ZetaDeckStrat implements DeckStrategies {
    private Stack<StandardCard> deck1 = new Stack<>();
    private Stack<StandardCard> deck2 = new Stack<>();

    @Override
    public Stack<StandardCard> initializeDeck(Player who) {
        if(who == Player.FINDUS) {
            for (int i = 0; i < 7; i++) {
                this.deck1.push(new StandardCard(GameConstants.CINCO_CARD, who,3,5,1, null));
            }
            return deck1;
        } else {
            for (int i = 0; i < 7; i++) {
                this.deck2.push(new StandardCard(GameConstants.CINCO_CARD, who,3,5,1, null));
            }
            return deck2;
        }
    }


}
