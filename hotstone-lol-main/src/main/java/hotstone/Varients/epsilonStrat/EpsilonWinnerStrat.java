package hotstone.Varients.epsilonStrat;

import hotstone.framework.*;
import hotstone.framework.Player;
import hotstone.framework.variants.WinningStrategies;

public class EpsilonWinnerStrat implements WinningStrategies {
    private int findusAttackSum;
    private int peddersenAttackSum;


    @Override
    public Player calculateWinner(Game game) {
        if(findusAttackSum >= 7){
            return Player.FINDUS;
        }
        if(peddersenAttackSum >= 7) {
            return Player.PEDDERSEN;
        }
        return null;
    }
    @Override
    public void attackingMinionWinner(Player attackingPlayer, Card attackingCard) {
        if (attackingPlayer == Player.FINDUS){
            findusAttackSum += attackingCard.getAttack();
        } else if(attackingPlayer == Player.PEDDERSEN){
            peddersenAttackSum += attackingCard.getAttack();
        }
    }
}
