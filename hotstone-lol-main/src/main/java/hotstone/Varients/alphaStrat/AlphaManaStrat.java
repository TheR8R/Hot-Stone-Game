package hotstone.Varients.alphaStrat;

import hotstone.framework.variants.ManaStrategies;
import hotstone.framework.*;

public class AlphaManaStrat implements ManaStrategies{


    /**
     * Accessor method to calculate mana in AlphaStone
     * @param game is not used
     * @return integer 3
     */
    @Override
    public int calculateMana(Game game){
        return 3;
    }
}
