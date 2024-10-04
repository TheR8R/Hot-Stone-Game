package hotstone.framework;

import hotstone.framework.variants.IndexingStrategies;
import java.util.Random;

public class RandomIndexStrat implements IndexingStrategies {
    private Random random = new Random();

    @Override
    public int calculateIndex(int upperLimit) {
        return random.nextInt(upperLimit);
    }
}
