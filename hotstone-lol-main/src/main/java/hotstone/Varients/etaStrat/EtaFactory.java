package hotstone.Varients.etaStrat;

import hotstone.Varients.deltaStrat.DeltaManaStrat;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.alphaStrat.AlphaWinnerStrat;
import hotstone.framework.variants.*;

public class EtaFactory implements AbstractFactory {
    private IndexingStrategies indexStrat;

    public EtaFactory(IndexingStrategies index){
        this.indexStrat = index;
    }
    @Override
    public DeckStrategies createDeckStrategies() {
        return new EtaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new EtaEffectStrat(indexStrat);
    }

    @Override
    public HeroStrategies createHeroStrategies() {
        return new AlphaHeroStrat();
    }

    @Override
    public ManaStrategies createManaStrategies() {
        return new DeltaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new AlphaWinnerStrat();
    }
}
