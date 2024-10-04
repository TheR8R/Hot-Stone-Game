package hotstone.Varients.epsilonStrat;

import hotstone.Varients.alphaStrat.AlphaDeckStrat;
import hotstone.Varients.alphaStrat.AlphaManaStrat;
import hotstone.framework.variants.*;

public class EpsilonFactory implements AbstractFactory {
    private IndexingStrategies indexStrat;

    public EpsilonFactory(IndexingStrategies index){
        this.indexStrat = index;
    }

    @Override
    public DeckStrategies createDeckStrategies() {
        return new AlphaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new EpsilonEffectStrat(indexStrat);
    }

    @Override
    public HeroStrategies createHeroStrategies() {
        return new EpsilonHeroStrat();
    }

    @Override
    public ManaStrategies createManaStrategies() {
        return new AlphaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new EpsilonWinnerStrat();
    }
}
