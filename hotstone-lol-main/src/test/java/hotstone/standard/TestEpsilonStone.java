package hotstone.standard;

import hotstone.Varients.epsilonStrat.EpsilonFactory;

import hotstone.framework.Card;
import hotstone.framework.Game;

import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.utility.FixedIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestEpsilonStone {
    /**
     * Test class for DeltaStone variants
     */
    private Game game;
    private Card unoP;
    /**
     * Fixture for DeltaStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new EpsilonFactory(new FixedIndex()));
        //game = new StandardHotStoneGame(new AlphaManaStrat(), new EpsilonWinnerStrat(), new EpsilonHeroStrat(), new AlphaDeckStrat(), new EpsilonEffectStrat(new FixedIndex()));
        unoP = game.getCardInHand(Player.PEDDERSEN, 2);
    }
    @Test
    public void findusHeroTypeIsFrenchChef(){
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.FRENCH_CHEF_HERO_TYPE));
    }

    @Test
    public void peddersenHeroTypeIsItalianChef(){
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.ITALIAN_CHEF_HERO_TYPE));
    }

    @Test
    public void findusWinsBecauseHeHas7OrMoreMinionToMinionAttackPower(){
        Card SevenAttackMinion = new StandardCard("EpsilonWinnerTestCard", Player.FINDUS,1,7,7, null);
        game.playCard(Player.FINDUS, SevenAttackMinion);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.endTurn();
        game.attackCard(Player.FINDUS, SevenAttackMinion, unoP);
        assertThat(game.getWinner(), is(Player.FINDUS));
    }

    @Test
    public void findusUseHeroPowerKillsMinionAndItIsRemoved(){
        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.endTurn();
        game.usePower(Player.FINDUS);
        assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }

    @Test
    public void findusUseHeroPowerAndDamagesMinion(){
        Card tresP = game.getCardInHand(Player.PEDDERSEN, 0);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, tresP);
        game.endTurn();
        game.usePower(Player.FINDUS);
        assertThat(tresP.getHealth(), is(1));
    }

    @Test
    public void findusUseHeroPowerAndNothingHappenes(){
        assertThat(game.usePower(Player.FINDUS), is(Status.OK));

    }

    @Test
    public void peddersenUseHeroPowerAndHisMinionIsBuffed(){
        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.usePower(Player.PEDDERSEN);
        assertThat(unoP.getAttack(), is(3));
    }

    @Test
    public void peddersenUsesHeroPowerOnEmptyBoardAndNothingHappens() {
        game.endTurn();
        assertThat(game.usePower(Player.PEDDERSEN), is(Status.OK));
    }

    @Test
    public void frenchHeroDescriptionIsCorrect(){
        assertThat(game.getHero(Player.FINDUS).getEffectDescription(), is("Opp M: (0,-2)"));
    }

    @Test
    public void italianHeroDescriptionIsCorrect(){
        assertThat(game.getHero(Player.PEDDERSEN).getEffectDescription(), is("M: (+2,0)"));
    }
}