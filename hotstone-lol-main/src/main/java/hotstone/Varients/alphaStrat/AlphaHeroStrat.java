package hotstone.Varients.alphaStrat;

import hotstone.framework.Player;
import hotstone.framework.variants.HeroStrategies;


public class AlphaHeroStrat implements HeroStrategies{
    /**
     * Accessor method to get hero type in AlphaStone
     * @param who is the player to assign a hero type
     * @return string "baby"
     */
    @Override
    public String setHeroType(Player who) {
        return "Baby";
    }


}
