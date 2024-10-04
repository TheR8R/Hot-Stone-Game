package hotstone.Varients.gammaStrat;

import hotstone.framework.Player;
import hotstone.framework.variants.HeroStrategies;

import hotstone.standard.GameConstants;

public class GammaHeroStrat implements HeroStrategies {
    /**
     * Mutator method to set a players hero type in GammaStone
     * @param who player to assign a hero type
     * @return String of the hero types name
     */
    @Override
    public String setHeroType(Player who) {
        if (who == Player.FINDUS) {
            return GameConstants.THAI_CHEF_HERO_TYPE;
        } else
            return GameConstants.DANISH_CHEF_HERO_TYPE;
    }

}

