package hotstone.standard;

import hotstone.Varients.etaStrat.EtaFactory;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.utility.FixedIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestEtaStone {
    private Game game;

    /**
     * Fixture for GammaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new EtaFactory(new FixedIndex()));
    }
    @Test
    public void firstCardInFindusDeckCosts1Mana() {
        assertThat(game.getCardInHand(Player.FINDUS, 2).getManaCost(), is(1));
    }

    @Test
    public void secondCardInFindusDeckCosts2OrLess() {
        assertThat(game.getCardInHand(Player.FINDUS, 1).getManaCost() <= 2, is(true));
    }

    @Test
    public void thirdCardInFindusDeckCosts4Orless() {
        assertThat(game.getCardInHand(Player.FINDUS, 0).getManaCost() <= 4, is(true));
    }

    @Test
    public void whenPeddersenDrawsACardFindusDoesNotLoseACardFromDeck() {
        game.endTurn();
        assertThat(game.getDeckSize(Player.FINDUS), is(21));
    }

    /**
     * This test is to make sure that the decks aren't initialised as the same deck
     */
    @Test
    public void playersDeckDifferWithAtLeastOneCard() {
        StandardHero f = (StandardHero) game.getHero(Player.FINDUS);
        StandardHero p = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(f.getDeck().equals(p.getDeck()), is(false));
    }

    @Test
    public void BrownRiceCardEffectWorksAsIntended(){
        StandardCard brownRiceF = new StandardCard(GameConstants.BROWN_RICE_CARD, Player.FINDUS,1,1,1, null);
        game.playCard(Player.FINDUS, brownRiceF);
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(20));
    }

    @Test
    public void tomatoSaladCardEffectWorksAsIntended(){
        StandardCard tomatoSaladF = new StandardCard(GameConstants.TOMATO_SALAD_CARD, Player.FINDUS,2,2,2, null);
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,2));
        int iniValue = game.getCardInField(Player.FINDUS, 0).getAttack();
        game.playCard(Player.FINDUS, tomatoSaladF);
        assertThat(game.getCardInField(Player.FINDUS,1).getAttack(), is(iniValue+1));
    }

    @Test
    public void tomatoSaladCardEffectDoesNotBuffItSelf(){
        StandardCard tomatoSaladF = new StandardCard(GameConstants.TOMATO_SALAD_CARD, Player.FINDUS,2,2,2, null);
        game.playCard(Player.FINDUS, tomatoSaladF);
        assertThat(game.getCardInField(Player.FINDUS,0).getAttack(), is(2));
    }

    @Test
    public void pokeBowlCardEffectWorksAsIntended() {
        StandardCard pokeBowlF = new StandardCard(GameConstants.POKE_BOWL_CARD, Player.FINDUS, 2, 2, 2, null);
        game.playCard(Player.FINDUS, pokeBowlF);
        assertThat(game.getHero(Player.FINDUS).getHealth(), is(23));
    }

    @Test
    public void noodleSoupCardEffectWorkAsIntended() {
        StandardCard noodleSoupF = new StandardCard(GameConstants.NOODLE_SOUP_CARD, Player.FINDUS, 4, 5, 3, null);
        game.playCard(Player.FINDUS, noodleSoupF);
        assertThat(game.getHandSize(Player.FINDUS), is(4));
    }

    @Test
    public void chickenCurryCardEffectWorksAsIntended() {
        StandardCard chickenCurryP = new StandardCard(GameConstants.CHICKEN_CURRY_CARD, Player.PEDDERSEN, 4, 5, 3, null);
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
        game.endTurn();
        game.playCard(Player.PEDDERSEN, chickenCurryP);
        assertThat(game.getFieldSize(Player.FINDUS), is(0));
    }

    @Test
    public void beefBurgerCardEffectworksAsIntended() {
        StandardCard beffBurgerP = new StandardCard(GameConstants.BEEF_BURGER_CARD, Player.PEDDERSEN, 4, 5, 3, null);
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
        int initialValue = game.getCardInField(Player.FINDUS,0).getAttack();
        game.endTurn();
        game.playCard(Player.PEDDERSEN, beffBurgerP);
        initialValue +=2;
        assertThat(game.getCardInField(Player.FINDUS, 0).getAttack(), is(initialValue));
    }

    @Test
    public void getEffectDescription(){
        StandardHero findusHero = (StandardHero) game.getHero(Player.FINDUS);
        Stack<StandardCard> deckF = findusHero.getDeck();
        StandardCard beef = null;
        for(StandardCard c: deckF){
            if(c.getName().equals(GameConstants.BEEF_BURGER_CARD)){
                beef = c;
            }
        }
        assertThat(beef.getEffectDescription(), is("Opp M: (+2, 0)"));
    }

    @Test
    public void findusOwnsAllHisCards(){
        MutableHero findusHero = ((MutableHero) game.getHero(Player.FINDUS));
        for(StandardCard c: findusHero.getDeck()){
            assertThat(c.getOwner(), is(Player.FINDUS));
        }
    }

    @Test
    public void peddersenOwnsAllHisCards(){
        MutableHero peddersenHero = ((MutableHero) game.getHero(Player.PEDDERSEN));
        for(StandardCard c: peddersenHero.getDeck()){
            assertThat(c.getOwner(), is(Player.PEDDERSEN));
        }
    }


}
