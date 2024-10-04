package hotstone.Varients.etaStrat;

import hotstone.framework.*;
import hotstone.framework.variants.EffectStrategies;
import hotstone.framework.variants.IndexingStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHero;

import java.util.ArrayList;

public class EtaEffectStrat implements EffectStrategies {
    private IndexingStrategies indexStrat;

    public EtaEffectStrat(IndexingStrategies indexType){
        this.indexStrat = indexType;
    }

    @Override
    public void useCardEffect(Game game, Card card) {
        Player currentPlayer = game.getPlayerInTurn();
        Player opponentPlayer = Utility.computeOpponent((currentPlayer));
        StandardHero friendlyHero = (StandardHero) game.getHero(currentPlayer);
        StandardHero oppenentHero = (StandardHero) game.getHero(Utility.computeOpponent(currentPlayer));
        ArrayList<StandardCard> friendlyField = (ArrayList<StandardCard>) game.getField(currentPlayer);
        switch (card.getName()) {
            case GameConstants.BROWN_RICE_CARD -> {
                ((MutableGame)game).reduceHeroHealth(oppenentHero, 1);
            }
            case GameConstants.TOMATO_SALAD_CARD -> {
                int fieldSize = game.getFieldSize(currentPlayer);
                if (fieldSize <= 1) {
                    break;
                }
                int indexOfMinion = indexStrat.calculateIndex(fieldSize);

                if (indexOfMinion == friendlyField.indexOf(card)) {
                    useCardEffect(game, card);
                }

                MutableCard friendlyMinion = (MutableCard) game.getCardInField(currentPlayer, indexOfMinion);
                ((MutableGame)game).changeCardAttack(friendlyMinion, 1);
            }
            case GameConstants.POKE_BOWL_CARD -> {
                ((MutableGame) game).reduceHeroHealth(friendlyHero, -2);
            }
            case GameConstants.NOODLE_SOUP_CARD -> {
                ((MutableGame) game).drawFromDeck(friendlyHero, 1);
            }
            case GameConstants.CHICKEN_CURRY_CARD -> {
                int fieldSize = game.getFieldSize(currentPlayer);
                if (fieldSize == 0) {
                    break;
                }
                int indexOfMinion = indexStrat.calculateIndex(fieldSize);
                MutableCard enemyMinion = (MutableCard) game.getCardInField(opponentPlayer, indexOfMinion);
                enemyMinion.changeHealth(enemyMinion.getHealth());
                ((MutableGame)game).checkIfMinionDie(enemyMinion);
            }
            case GameConstants.BEEF_BURGER_CARD -> {
                int fieldSize = game.getFieldSize(currentPlayer);
                if (fieldSize == 0) {
                    break;
                }
                int indexOfMinion = indexStrat.calculateIndex(fieldSize);
                StandardCard enemyMinion = (StandardCard) game.getCardInField(opponentPlayer, indexOfMinion);
                enemyMinion.changeAttack(2);
            }
        }
    }

    @Override
    public Status useHeroPower(Game game) {
        return Status.OK;
    }
}
