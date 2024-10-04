package hotstone.Varients.zetaStrat;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.variants.WinningStrategies;

public class ZetaWinnerStrat implements WinningStrategies {
    private WinningStrategies betaStrat, epsilonStrat;


    public ZetaWinnerStrat(WinningStrategies beta, WinningStrategies epsilon) {
        this.betaStrat = beta;
        this.epsilonStrat = epsilon;
    }
    @Override
    public Player calculateWinner(Game game) {
        if(game.getTurnNumber() < 7){
            return betaStrat.calculateWinner(game);
        } else {
            return epsilonStrat.calculateWinner(game);
        }
    }

    @Override
    public void attackingMinionWinner(Player attackingPlayer, Card attackingCard) {

    }
}
