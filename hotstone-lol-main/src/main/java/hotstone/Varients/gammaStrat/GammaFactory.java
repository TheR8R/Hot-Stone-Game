package hotstone.Varients.gammaStrat;

import hotstone.Varients.alphaStrat.AlphaDeckStrat;
import hotstone.Varients.alphaStrat.AlphaManaStrat;
import hotstone.Varients.alphaStrat.AlphaWinnerStrat;
import hotstone.framework.variants.*;

public class GammaFactory implements AbstractFactory {
    @Override
    public DeckStrategies createDeckStrategies() {
        return new AlphaDeckStrat();
    }

    @Override
    public EffectStrategies createEffectStrategies() {
        return new GammaEffectStrat();
    }

    @Override
    public HeroStrategies createHeroStrategies() {
        return new GammaHeroStrat();
    }

    @Override
    public ManaStrategies createManaStrategies() {
        return new AlphaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new AlphaWinnerStrat();
    }
}
