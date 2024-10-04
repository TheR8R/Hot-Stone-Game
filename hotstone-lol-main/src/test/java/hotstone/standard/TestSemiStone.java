package hotstone.standard;

import hotstone.Varients.semiStrat.SemiFactory;
import hotstone.framework.Game;
import hotstone.utility.FixedIndex;
import org.junit.jupiter.api.BeforeEach;
import hotstone.framework.*;

import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestSemiStone {


/**
 * Test class for SemiStone variant
 *
 **/
private Game game;

    /**
     * Fixture for SemiStone testing.
     */
    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new SemiFactory(new FixedIndex()));
        //game = new StandardHotStoneGame(new AlphaManaStrat(), new AlphaWinnerStrat(), new GammaHeroStrat(), new AlphaDeckStrat(), new GammeEffectStrat());
    }

    @Test
    public void findusHasCorrectHeroType(){
        assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void peddersenHasCorrectHeroType(){
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
    }

    @Test
    public void findusHeroPowerWorks(){
        game.endTurn();
        game.endTurn();
        game.usePower(Player.FINDUS);
        assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(19));
    }
}
