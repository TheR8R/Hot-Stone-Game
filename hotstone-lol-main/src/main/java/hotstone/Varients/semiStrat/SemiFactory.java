package hotstone.Varients.semiStrat;

import hotstone.Varients.betaStrat.BetaManaStrat;
import hotstone.Varients.betaStrat.BetaWinnerStrat;
import hotstone.Varients.etaStrat.EtaDeckStrat;
import hotstone.framework.variants.*;

public class SemiFactory implements AbstractFactory {
    private IndexingStrategies indexStrat;

    public SemiFactory(IndexingStrategies index){
        this.indexStrat = index;
    }
    @Override
    public DeckStrategies createDeckStrategies() {
        return new EtaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new SemiEffectStrat(indexStrat);
    }

    @Override
    public HeroStrategies createHeroStrategies() {
        return new SemiHeroStrat(indexStrat);
    }

    @Override
    public ManaStrategies createManaStrategies() {
        return new BetaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new BetaWinnerStrat();
    }
}
