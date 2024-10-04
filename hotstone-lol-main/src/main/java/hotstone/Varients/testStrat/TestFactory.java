package hotstone.Varients.testStrat;

import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.alphaStrat.AlphaWinnerStrat;
import hotstone.Varients.etaStrat.EtaDeckStrat;
import hotstone.Varients.etaStrat.EtaEffectStrat;
import hotstone.framework.RandomIndexStrat;
import hotstone.framework.variants.*;
import minidraw.framework.Factory;

public class TestFactory implements AbstractFactory {

    @Override
    public DeckStrategies createDeckStrategies() {
        return new EtaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new EtaEffectStrat(new RandomIndexStrat());
    }

    @Override
    public HeroStrategies createHeroStrategies() {
        return new AlphaHeroStrat();
    }

    @Override
    public ManaStrategies createManaStrategies() {
        return new TestManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new AlphaWinnerStrat();
    }
}
