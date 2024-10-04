package hotstone.framework.variants;

import hotstone.framework.Game;

public interface ManaStrategies {

    /**
     * Accessor method to calculate mana
     *
     * @param game is not used
     * @return the calculated mana for a player
     */
    int calculateMana(Game game);
}
