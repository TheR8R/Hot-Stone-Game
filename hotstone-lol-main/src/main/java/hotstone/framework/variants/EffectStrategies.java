package hotstone.framework.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Status;

public interface EffectStrategies {
    void useCardEffect(Game game, Card card);


    /**
     * Mutator method to calculate the use of a hero power
     *
     * @param game is the game where this hero strategy is used
     * @return status saying if the action is legal or not
     */
    Status useHeroPower(Game game);
}
