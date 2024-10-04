package hotstone.Varients.semiStrat;

import hotstone.framework.Player;
import hotstone.framework.variants.HeroStrategies;
import hotstone.framework.variants.IndexingStrategies;
import hotstone.standard.GameConstants;

public class SemiHeroStrat implements HeroStrategies {
    private IndexingStrategies indexStrat;
    SemiHeroStrat(IndexingStrategies index) {
        this.indexStrat = index;
    }
    @Override
    public String setHeroType(Player who) {
        int i = indexStrat.calculateIndex(4);
        switch (i) {
            case 0 -> {return GameConstants.ITALIAN_CHEF_HERO_TYPE;}
            case 1 -> {return GameConstants.FRENCH_CHEF_HERO_TYPE;}
            case 2 -> {return GameConstants.DANISH_CHEF_HERO_TYPE;}
            case 3 -> {return GameConstants.THAI_CHEF_HERO_TYPE;}
            }
            return null;
        }

}
