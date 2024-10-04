package hotstone.Varients.epsilonStrat;

import hotstone.framework.*;
import hotstone.framework.variants.HeroStrategies;
import hotstone.standard.GameConstants;

public class EpsilonHeroStrat implements HeroStrategies {

    /**
     * Mutator method to set a players hero type in GammaStone
     * @param who player to assign a hero type
     * @return String of the hero types name
     */
    @Override
    public String setHeroType(Player who) {
        if (who == Player.FINDUS) {
            return GameConstants.FRENCH_CHEF_HERO_TYPE;
        } else
            return GameConstants.ITALIAN_CHEF_HERO_TYPE;
    }

}
