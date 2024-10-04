package hotstone.Varients;

import hotstone.Varients.deltaStrat.DeltaDeckStrat;
import hotstone.framework.Player;
import hotstone.framework.variants.DeckStrategies;
import hotstone.standard.StandardCard;
import thirdparty.CardPODO;
import thirdparty.PersonalDeckReader;

import java.util.*;

public class PersonalizedDeckStrat implements DeckStrategies {

    private PersonalDeckReader pDeck;
    private List<StandardCard> deckList = new ArrayList<>();

    private DeltaDeckStrat shuffler;

    public PersonalizedDeckStrat() {
        pDeck = new PersonalDeckReader("animaldeck.json");
        shuffler = new DeltaDeckStrat();

    }

    @Override
    public Stack<StandardCard> initializeDeck(Player who) {
        Iterator<CardPODO> it = pDeck.iterator();
        while (it.hasNext()) {
            CardPODO card = it.next();
            StandardCard x = new StandardCard(card.name(), who, card.mana(), card.attack(), card.health(), null);
            deckList.add(x);
            deckList.add(x);
        }
        return shuffler.shuffleDeck(deckList);
    }
}
