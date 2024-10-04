package hotstone.Varients.epsilonStrat;

import hotstone.framework.*;
import hotstone.framework.variants.EffectStrategies;
import hotstone.framework.variants.IndexingStrategies;
import hotstone.standard.GameConstants;


public class EpsilonEffectStrat implements EffectStrategies {

    private IndexingStrategies indexStrat;

    public EpsilonEffectStrat(IndexingStrategies indexType){
        this.indexStrat = indexType;
    }
    @Override
    public void useCardEffect(Game game, Card card) {

    }
    @Override
    public Status useHeroPower(Game game) {
        Player who = game.getPlayerInTurn();
        MutableHero hero = (MutableHero) game.getHero(who);
        Player opponent = Utility.computeOpponent(who);
        switch (hero.getType()){
            case GameConstants.FRENCH_CHEF_HERO_TYPE -> {
                int fieldSize = game.getFieldSize(opponent);
                if(fieldSize == 0) {
                    return Status.OK;
                }
                int indexOfMinion = indexStrat.calculateIndex(fieldSize);
                MutableCard enemyMinion = (MutableCard) game.getCardInField(opponent, indexOfMinion);
                ((MutableGame) game).changeCardHealth(enemyMinion, 2);

            }
            case GameConstants.ITALIAN_CHEF_HERO_TYPE -> {
                int fieldSize = game.getFieldSize(who);
                if (fieldSize == 0) {
                    return Status.OK;
                }
                int indexOfMinion = indexStrat.calculateIndex(fieldSize);
                MutableCard friendlyMinion = (MutableCard) game.getCardInField(who, indexOfMinion);
                ((MutableGame) game).changeCardAttack(friendlyMinion, 2);
            }
        }
        return Status.OK;
    }


}

