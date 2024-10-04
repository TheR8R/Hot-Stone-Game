package hotstone.Varients.semiStrat;

import hotstone.Varients.epsilonStrat.EpsilonEffectStrat;
import hotstone.Varients.gammaStrat.GammaEffectStrat;
import hotstone.framework.*;
import hotstone.framework.variants.EffectStrategies;
import hotstone.framework.variants.IndexingStrategies;
import hotstone.standard.GameConstants;

public class SemiEffectStrat implements EffectStrategies {
    private EffectStrategies gammaStrat;
    private EffectStrategies epsilonStrat;

    public SemiEffectStrat(IndexingStrategies index) {
        this.epsilonStrat = new EpsilonEffectStrat(index);
        this.gammaStrat = new GammaEffectStrat();
    }
    @Override
    public void useCardEffect(Game game, Card card) {

    }

    @Override
    public Status useHeroPower(Game game) {
        Player who = game.getPlayerInTurn();
        Hero hero = game.getHero(who);
        String heroType = hero.getType();
        boolean isThaiChef = heroType.equals(GameConstants.THAI_CHEF_HERO_TYPE);
        boolean isDanishChef = heroType.equals(GameConstants.DANISH_CHEF_HERO_TYPE);
        boolean isFrenchChef = heroType.equals(GameConstants.FRENCH_CHEF_HERO_TYPE);
        boolean isItalianChef = heroType.equals(GameConstants.ITALIAN_CHEF_HERO_TYPE);

        if( isThaiChef|| isDanishChef) {
            return gammaStrat.useHeroPower(game);
        } else if (isFrenchChef|| isItalianChef) {
            return epsilonStrat.useHeroPower(game);
        }
        return Status.OK;
    }
}
