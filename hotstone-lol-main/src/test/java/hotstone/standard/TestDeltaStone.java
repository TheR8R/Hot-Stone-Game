package hotstone.standard;

import hotstone.Varients.deltaStrat.DeltaFactory;

import hotstone.framework.Game;
import hotstone.framework.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDeltaStone {
    /**
     * Test class for DeltaStone variants
     */
    private Game game;

    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new DeltaFactory());
        //game = new StandardHotStoneGame(new DeltaManaStrat(), new AlphaWinnerStrat(), new AlphaHeroStrat(), new DeltaDeckStrat(), new AlphaEffectStrat());
    }

    @Test
    public void peddersenAndFindusStartWith7Mana() {
        assertThat(game.getHero(Player.FINDUS).getMana(), is(7));
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(7));
    }

    @Test
    public void whenFindusEndsHisTurnPeddersenHas7Mana() {
        game.endTurn();
        assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(7));
    }

    @Test
    public void eachPlayerStartsWith21CardsEachInTheirDecks() {
        assertThat(game.getDeckSize(Player.FINDUS), is(21));
        assertThat(game.getDeckSize(Player.PEDDERSEN), is(21));
    }

    @Test
    public void cardBrownRiceIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.BROWN_RICE_CARD)
                || fHero.checkCardInHand(GameConstants.BROWN_RICE_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.BROWN_RICE_CARD)
                || pHero.checkCardInHand(GameConstants.BROWN_RICE_CARD), is(true));
    }
    @Test
    public void cardFrenchFriesIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.FRENCH_FRIES_CARD)
                || fHero.checkCardInHand(GameConstants.FRENCH_FRIES_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.FRENCH_FRIES_CARD)
                || pHero.checkCardInHand(GameConstants.FRENCH_FRIES_CARD), is(true));
    }
    @Test
    public void cardGreenSaladIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.GREEN_SALAD_CARD)
        || fHero.checkCardInHand(GameConstants.GREEN_SALAD_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.GREEN_SALAD_CARD)
                || pHero.checkCardInHand(GameConstants.GREEN_SALAD_CARD), is(true));
    }
    @Test
    public void cardTomatoSaladIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.TOMATO_SALAD_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.TOMATO_SALAD_CARD), is(true));
    }
    @Test
    public void cardPokeBowlIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.POKE_BOWL_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.POKE_BOWL_CARD), is(true));
    }
    @Test
    public void cardPumpkinSoupIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.PUMPKIN_SOUP_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.PUMPKIN_SOUP_CARD), is(true));
    }
    @Test
    public void cardNoodleSoupIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.NOODLE_SOUP_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.NOODLE_SOUP_CARD), is(true));
    }
    @Test
    public void cardSpringRollsIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.SPRING_ROLLS_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.SPRING_ROLLS_CARD), is(true));
    }
    @Test
    public void cardBakedSalmonIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.BAKED_SALMON_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.BAKED_SALMON_CARD), is(true));
    }
    @Test
    public void cardChickenCurryIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.CHICKEN_CURRY_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.CHICKEN_CURRY_CARD), is(true));
    }
    @Test
    public void cardBeefBurgerIsInBothPlayerDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.BEEF_BURGER_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.BEEF_BURGER_CARD), is(true));
    }
    @Test
    public void cardFilletMignonIsInBothPlayersDeck() {
        StandardHero fHero = (StandardHero) game.getHero(Player.FINDUS);
        assertThat(fHero.checkCardInDeck(GameConstants.FILET_MIGNON_CARD), is(true));
        StandardHero pHero = (StandardHero) game.getHero(Player.PEDDERSEN);
        assertThat(pHero.checkCardInDeck(GameConstants.FILET_MIGNON_CARD), is(true));
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




}
