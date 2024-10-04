package hotstone.Varients.betaStrat;

import hotstone.framework.*;
import hotstone.framework.variants.WinningStrategies;

public class BetaWinnerStrat implements WinningStrategies {
    /**
     * Accessor method for BetaStone that calulates the winner of a game
     * @param game in which this stategy is used
     * @return Player winning the game, or null if none
     */
    @Override
    public Player calculateWinner(Game game) {
        Hero opponentHero = game.getHero(Utility.computeOpponent(game.getPlayerInTurn()));
        if(opponentHero.getHealth() <= 0){
            return game.getPlayerInTurn();
        } else {
            return null;
        }
    }

    @Override
    public void attackingMinionWinner(Player attackingPlayer, Card attackingCard) {
    }
}