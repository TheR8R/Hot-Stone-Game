package hotstone.standard;


import hotstone.Varients.epsilonStrat.EpsilonWinnerStrat;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.betaStrat.BetaWinnerStrat;
import hotstone.Varients.zetaStrat.ZetaDeckStrat;
import hotstone.Varients.zetaStrat.ZetaWinnerStrat;
import hotstone.framework.*;
import hotstone.framework.variants.DeckStrategies;
import hotstone.framework.variants.HeroStrategies;
import hotstone.framework.variants.WinningStrategies;
import hotstone.observer.GameObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestZetaStone {
    private TestDouble testGame;

    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        this.testGame = new TestDouble(new ZetaWinnerStrat(new BetaWinnerStrat(),new EpsilonWinnerStrat()), new ZetaDeckStrat(), new AlphaHeroStrat());
    }

    private class TestDouble implements Game {

        public int turnNumber;
        public WinningStrategies winnerStrat;
        public DeckStrategies deckStrat;

        public StandardHero findusHero, peddersenHero;

        public Player currentPlayer;
        public HeroStrategies heroStrat;

        public TestDouble(WinningStrategies winnerStrat, DeckStrategies deckStrat, HeroStrategies heroStrats) {
            this.currentPlayer = Player.FINDUS;
            this.deckStrat = deckStrat;
            this.winnerStrat = winnerStrat;
            this.heroStrat = heroStrats;
            this.findusHero = new StandardHero();
            initializeHeros(findusHero, Player.FINDUS);
            this.peddersenHero = new StandardHero();
            initializeHeros(peddersenHero, Player.PEDDERSEN);

        }


        @Override
        public Player getPlayerInTurn() {
            return currentPlayer;
        }
        public void initializeHeros(StandardHero hero, Player who){
            hero.setType(heroStrat.setHeroType(who));
            hero.setDeck(deckStrat.initializeDeck(who));
        }

        @Override
        public Hero getHero(Player who) {
            if (who == Player.FINDUS) {
                return findusHero;
            } else
                return peddersenHero;
        }

        @Override
        public Player getWinner() {
            return winnerStrat.calculateWinner(this);
        }

        @Override
        public int getTurnNumber() {
            return turnNumber;
        }

        @Override
        public int getDeckSize(Player who) {
            return 0;
        }

        @Override
        public Card getCardInHand(Player who, int indexInHand) {
            return null;
        }

        @Override
        public Iterable<? extends Card> getHand(Player who) {
            return null;
        }

        @Override
        public int getHandSize(Player who) {
            return 0;
        }

        @Override
        public Card getCardInField(Player who, int indexInField) {
            return null;
        }

        @Override
        public Iterable<? extends Card> getField(Player who) {
            return null;
        }

        @Override
        public int getFieldSize(Player who) {
            return 0;
        }

        @Override
        public void endTurn() {

        }

        @Override
        public Status playCard(Player who, Card card) {
            return null;
        }

        @Override
        public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
            return null;
        }

        @Override
        public Status attackHero(Player playerAttacking, Card attackingCard) {
            return null;
        }

        @Override
        public Status usePower(Player who) {
            return null;
        }

        @Override
        public void addObserver(GameObserver observer) {

        }
    }

    @Test
    public void thePlayerWhoMakesHisOpponentHave0HealthWins(){
        testGame.peddersenHero.reduceHealth(testGame.peddersenHero.getHealth());
        assertThat(testGame.getWinner(), is(Player.FINDUS));

    }

    @Test
    public void strategyChangeAfterTurn7() {
        testGame.turnNumber = 8;
        testGame.peddersenHero.reduceHealth(testGame.peddersenHero.getHealth());
        assertNull(testGame.getWinner());
    }

    @Test
    public void everyCardInFindusDeckIsCinco(){
        Stack<StandardCard> theDeck = testGame.findusHero.getDeck();
        for(StandardCard c: theDeck){
            assertThat(c.getName(), is(GameConstants.CINCO_CARD));
        }
    }

    @Test
    public void findusHas7CardsInHisDeck(){
        assertThat(testGame.findusHero.getDeck().size(), is(7));
    }

    @Test
    public void everyCardInPeddersenDeckIsCinco(){
        Stack<StandardCard> theDeck = testGame.peddersenHero.getDeck();
        for(StandardCard c: theDeck){
            assertThat(c.getName(), is(GameConstants.CINCO_CARD));
        }
    }

    @Test
    public void peddersenHas7CardsInHisDeck(){
        assertThat(testGame.peddersenHero.getDeck().size(), is(7));
    }



}
