package hotstone.Varients.gammaStrat;

import hotstone.framework.*;
import hotstone.framework.variants.EffectStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHero;

import java.util.ArrayList;

public class GammaEffectStrat implements EffectStrategies {
    @Override
    public void useCardEffect(Game game, Card card) {

    }

    /**
     * Mutator method for Gamma hero strat.
     * @param game is the game where this hero strategy is used
     * @return Status ok because the use of the hero power is allowed
     */
    @Override
    public Status useHeroPower(Game game) {
        Player who = game.getPlayerInTurn();
        StandardHero hero = (StandardHero) game.getHero(who);
        StandardHero opponentHero = (StandardHero) game.getHero(Utility.computeOpponent(who));
        switch (hero.getType()){
            case GameConstants.THAI_CHEF_HERO_TYPE -> {
                ((MutableGame) game).reduceHeroHealth(opponentHero, 2);
            }
            case GameConstants.DANISH_CHEF_HERO_TYPE -> {
                StandardCard sovs = new StandardCard(GameConstants.SOVS_CARD, who, 0,1, 1, null);
                game.playCard(who, sovs);
            }
        }
        return Status.OK;
    }
}
