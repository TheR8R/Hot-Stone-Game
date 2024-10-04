package hotstone.Varients.deltaStrat;

import hotstone.Varients.alphaStrat.AlphaEffectStrat;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.alphaStrat.AlphaWinnerStrat;
import hotstone.framework.variants.*;

public class DeltaFactory implements AbstractFactory {
    @Override
    public DeckStrategies createDeckStrategies() {
        return new DeltaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new AlphaEffectStrat();
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
