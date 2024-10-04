package hotstone.Varients.deltaStrat;


import hotstone.framework.Player;
import hotstone.framework.variants.DeckStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class DeltaDeckStrat implements DeckStrategies {

    private List<StandardCard> deckList = new ArrayList<>();

    /**
     * Accessor method to get the dish deck which is used in DeltaStone
     * @param who is the player whose deck to initialize
     * @return a stack with StandardCards
     */
    @Override
    public Stack<StandardCard> initializeDeck(Player who) {

        for (int i = 0; i < 2; i++) {
            deckList.add(new StandardCard(GameConstants.FILET_MIGNON_CARD, who, 7, 9, 5, null));
            deckList.add(new StandardCard(GameConstants.BEEF_BURGER_CARD, who, 6, 5, 6, null));
            deckList.add(new StandardCard(GameConstants.BAKED_SALMON_CARD, who, 5, 8, 2, null));
            deckList.add(new StandardCard(GameConstants.FRENCH_FRIES_CARD, who, 1, 2, 1, null));
            deckList.add(new StandardCard(GameConstants.NOODLE_SOUP_CARD, who, 4, 5, 3, null));
            deckList.add(new StandardCard(GameConstants.PUMPKIN_SOUP_CARD, who, 4, 2, 7, null));
            deckList.add(new StandardCard(GameConstants.POKE_BOWL_CARD, who, 3, 2, 4, null));
            deckList.add(new StandardCard(GameConstants.CHICKEN_CURRY_CARD, who, 6, 8, 4, null));
            deckList.add(new StandardCard(GameConstants.SPRING_ROLLS_CARD, who, 5, 3, 7, null));
            deckList.add(new StandardCard(GameConstants.TOMATO_SALAD_CARD, who, 2, 3, 2, null));
            deckList.add(new StandardCard(GameConstants.GREEN_SALAD_CARD, who, 2, 2, 3, null));
            deckList.add(new StandardCard(GameConstants.BROWN_RICE_CARD, who, 1, 1, 2, null));
        }
        return shuffleDeck(deckList);
    }

        public Stack<StandardCard> shuffleDeck(List<StandardCard> deck) {
            Stack<StandardCard> tempDeck = new Stack<>();
            Collections.shuffle(deck);

            StandardCard card1 = deck.stream().filter(c -> c.getManaCost() == 1).findFirst().orElse(null);
            deck.remove(card1);

            StandardCard card2 = deck.stream().filter(c -> c.getManaCost() <= 2).findFirst().orElse(null);
            deck.remove(card2);

            StandardCard card3 = deck.stream().filter(c -> c.getManaCost() <= 4).findFirst().orElse(null);
            deck.remove(card3);

            for (StandardCard c : deck) {
                tempDeck.push(c);
            }
            deckList.clear();
            tempDeck.push(card3);
            tempDeck.push(card2);
            tempDeck.push(card1);
            return tempDeck;
        }
}


