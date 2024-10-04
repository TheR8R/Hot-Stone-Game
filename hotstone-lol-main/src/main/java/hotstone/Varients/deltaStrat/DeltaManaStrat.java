package hotstone.Varients.deltaStrat;

import hotstone.framework.variants.ManaStrategies;
import hotstone.framework.*;

public class DeltaManaStrat implements ManaStrategies{


    /**
     * Mutator method for calculating mana for DeltaStone. Sets mana to 7
     * @param game is not used
     * @return integer 7
     */
    @Override
    public int calculateMana(Game game){
        return 7;
    }
}
