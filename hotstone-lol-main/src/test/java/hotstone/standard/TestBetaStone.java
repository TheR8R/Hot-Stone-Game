package hotstone.standard;

import hotstone.Varients.alphaStrat.AlphaEffectStrat;
import hotstone.Varients.alphaStrat.AlphaDeckStrat;
import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.betaStrat.BetaFactory;
import hotstone.Varients.betaStrat.BetaManaStrat;
import hotstone.Varients.betaStrat.BetaWinnerStrat;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class for BetaStone variants
 */
public class TestBetaStone {
    private Game game;
    private Card unoF;
    private Card unoP;

    /**
     * Fixture for BetaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new BetaFactory());
        //game = new StandardHotStoneGame(new BetaManaStrat(), new BetaWinnerStrat(), new AlphaHeroStrat(), new AlphaDeckStrat(), new AlphaEffectStrat());
        unoF = game.getCardInHand(Player.FINDUS, 2);
        unoP = game.getCardInHand(Player.PEDDERSEN, 2);
    }

    @Test
    public void peddersenCantUseFindusMinionWhileItsFindusTurn() {
        game.playCard(Player.FINDUS, unoF);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.endTurn();
        assertThat(game.attackHero(Player.PEDDERSEN, unoF), is(Status.NOT_OWNER));
    }

    @Test
    public void peddersenEndsHisTurnAndFindusNowHas2Mana(){
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getMana(), is(2));
    }

    @Test
    public void bothStartAtCorrectMana() {
        assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    }

    @Test
    public void afterPeddersenEndsHisTurn4thTimeFindusHas5Mana(){
        for(int i = 0; i < 8; i++) {
            game.endTurn();
        }
        assertThat(game.getHero(Player.FINDUS).getMana(),is(5));
    }


    @Test
    public void findusHasAMaximumOf7ManaAfterEndingTurns(){
        for(int i = 0; i < 14; i++) {
            game.endTurn();
        }
        assertThat(game.getHero(Player.FINDUS).getMana(),is(7));
    }

    @Test
    public void findusTakes2DamageWhenHeTriesToDrawFromEmptyDeck() {
        for (int i = 0; i < 10; i++) {
            game.endTurn();
        }
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(19));
        game.endTurn();
        game.endTurn();
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(17));
    }

    @Test
    public void whenPeddersenHealthGetsBelowZeroFindusWins() {
        ((StandardHero) game.getHero(Player.PEDDERSEN)).reduceHealth(20);
        game.playCard(Player.FINDUS, unoF);
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS, unoF);
        assertThat(game.getWinner(), is(Player.FINDUS));
    }
}

