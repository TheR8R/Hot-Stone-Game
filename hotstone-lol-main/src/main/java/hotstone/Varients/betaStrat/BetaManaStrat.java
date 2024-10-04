package hotstone.Varients.betaStrat;


import hotstone.framework.Game;
import hotstone.framework.variants.ManaStrategies;


public class BetaManaStrat implements ManaStrategies {

    /**
     * Mutator method for BetaStone that sets mana at game start and in endTurn
     * @param game is the game using this strategy
     * @return integer being the turnNumber
     */
    @Override
   public int calculateMana(Game game) {
        if(game.getTurnNumber() >=7) {
            return 7;
        } else {
            return game.getTurnNumber();
        }
    }
}
