package hotstone.Varients.alphaStrat;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Status;
import hotstone.framework.variants.EffectStrategies;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;

public class AlphaEffectStrat implements EffectStrategies {
    @Override
    public void useCardEffect(Game game, Card card) {
    }

    /**
     * Mutator method to calculate the effects of using hero power in AlphaStone
     * @param game is the game where this hero strategy is used
     * @return Status saying if using hero power is legal
     */
    @Override
    public Status useHeroPower(Game game) {
        StandardHero hero = (StandardHero) game.getHero(game.getPlayerInTurn());
        return Status.OK;
    }
}
