 package hotstone.standard;

/**
 * Test class for gammaStone variant
 */

 import hotstone.Varients.gammaStrat.GammaFactory;

 import hotstone.framework.*;

         import org.junit.jupiter.api.*;
         import static org.hamcrest.CoreMatchers.*;
         import static org.hamcrest.MatcherAssert.assertThat;

 public class TestGammaStone {

     private Game game;

     /**
      * Fixture for GammaStone testing.
      */
     @BeforeEach
     public void setUp() {
         game = new StandardHotStoneGame(new GammaFactory());
         //game = new StandardHotStoneGame(new AlphaManaStrat(), new AlphaWinnerStrat(), new GammaHeroStrat(), new AlphaDeckStrat(), new GammeEffectStrat());
     }

     @Test
     public void findusHeroTypeIsThaiChef(){
         assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.THAI_CHEF_HERO_TYPE));
     }

     @Test
     public void PeddersenHeroTypeIsDanishChef(){
         assertThat(game.getHero(Player.PEDDERSEN).getType(), is(GameConstants.DANISH_CHEF_HERO_TYPE));
     }

     @Test
     public void findusUsesHeroPowerChilli(){
         game.usePower(Player.FINDUS);
         assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(19));
     }

     @Test
     public void peddersenUsesHeroPowerSovs() {
         game.endTurn();
         game.usePower(Player.PEDDERSEN);
         assertThat(game.getCardInField(Player.PEDDERSEN, 0).getName(), is(GameConstants.SOVS_CARD));
     }

     @Test
     public void findusHeroDescriptionWorks(){
         Hero findusHero = game.getHero(Player.FINDUS);
         assertThat(findusHero.getEffectDescription(), is("Opp H: (0,-2)"));
     }

     @Test
     public void peddersenHeroDescriptionWorks(){
         Hero peddersenHero = game.getHero(Player.PEDDERSEN);
         assertThat(peddersenHero.getEffectDescription(), is("Field Sovs"));
     }

 }