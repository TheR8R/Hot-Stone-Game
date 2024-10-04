package hotstone.Varients.betaStrat;

import hotstone.Varients.alphaStrat.AlphaDeckStrat;
import hotstone.Varients.alphaStrat.AlphaEffectStrat;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.framework.variants.*;

public class BetaFactory implements AbstractFactory {
    @Override
    public DeckStrategies createDeckStrategies() {
        return new AlphaDeckStrat();
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
        return new BetaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new BetaWinnerStrat();
    }
}
