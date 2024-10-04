package hotstone.Varients.etaStrat;

import hotstone.Varients.deltaStrat.DeltaDeckStrat;
import hotstone.framework.Player;
import hotstone.framework.variants.DeckStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class EtaDeckStrat implements DeckStrategies {


    private List<StandardCard> deckList = new ArrayList<>();

    private DeltaDeckStrat shuffler = new DeltaDeckStrat();

    @Override
    public Stack<StandardCard> initializeDeck(Player who) {
        deckList.clear();
        for (int i = 0; i < 2; i++) {
            deckList.add(new StandardCard(GameConstants.FILET_MIGNON_CARD, who,7,9,5, null));
            deckList.add(new StandardCard(GameConstants.BEEF_BURGER_CARD, who,6,8,6, "Opp M: (+2, 0)"));
            deckList.add(new StandardCard(GameConstants.BAKED_SALMON_CARD, who,5,8,2, null));
            deckList.add(new StandardCard(GameConstants.FRENCH_FRIES_CARD, who,1,2,1, null));
            deckList.add(new StandardCard(GameConstants.NOODLE_SOUP_CARD, who,4,5,3, "Draw Card"));
            deckList.add(new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, who,4,2,7, null));
            deckList.add(new StandardCard(GameConstants.POKE_BOWL_CARD, who,3,2,3, "H: (0, +2)"));
            deckList.add(new StandardCard(GameConstants.CHICKEN_CURRY_CARD, who,6,4,4, "Kill opp M"));
            deckList.add(new StandardCard(GameConstants.SPRING_ROLLS_CARD, who,5,3,7, null));
            deckList.add(new StandardCard(GameConstants.TOMATO_SALAD_CARD, who,2,2,2, "M: (+1,0)"));
            deckList.add(new StandardCard(GameConstants.GREEN_SALAD_CARD, who,2,2,3, null));
            deckList.add(new StandardCard(GameConstants.BROWN_RICE_CARD, who,1,1,1, "Opp H: (0, -1)"));
        }
        for(StandardCard c: deckList){
        }
        return shuffler.shuffleDeck(deckList);
    }
}
