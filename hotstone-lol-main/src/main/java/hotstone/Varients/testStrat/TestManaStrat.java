package hotstone.Varients.testStrat;

import hotstone.framework.Game;
import hotstone.framework.variants.ManaStrategies;

public class TestManaStrat implements ManaStrategies {

    @Override
    public int calculateMana(Game game) {
        return 25;
    }
}
