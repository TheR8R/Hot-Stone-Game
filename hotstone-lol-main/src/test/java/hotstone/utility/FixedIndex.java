package hotstone.utility;

import hotstone.framework.variants.IndexingStrategies;

public class FixedIndex implements IndexingStrategies {


    @Override
    public int calculateIndex(int upperLimit) {
        return upperLimit-1;
    }
}
