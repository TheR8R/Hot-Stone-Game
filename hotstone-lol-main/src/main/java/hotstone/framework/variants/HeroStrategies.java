package hotstone.framework.variants;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;

public interface HeroStrategies {
    /**
     * Mutator method to set the type of hero.
     *
     * @return String being the heroTypes name
     */
    String setHeroType(Player who);
}
