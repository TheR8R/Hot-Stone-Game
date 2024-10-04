package hotstone.Varients.alphaStrat;

import hotstone.framework.*;
import hotstone.framework.variants.WinningStrategies;

public class AlphaWinnerStrat implements WinningStrategies {

    /**
     * Accessor method for AlphaStone to get the winning player
     * @param game the game where this strategy is implemented
     * @return The winning player, or null if no one has won yet.
     */
    @Override
    public Player calculateWinner(Game game) {
        Player who = game.getPlayerInTurn();
        Player opponent = Utility.computeOpponent(who);
        Hero opponentHero = game.getHero(opponent);
        if (game.getTurnNumber() >= 4) {
            return Player.FINDUS;
        } else if(opponentHero.getHealth() <= 0){
            return game.getPlayerInTurn();
        } else {
            return null;
        }
    }

    @Override
    public void attackingMinionWinner(Player attackingPlayer, Card attackingCard) {

    }
}
