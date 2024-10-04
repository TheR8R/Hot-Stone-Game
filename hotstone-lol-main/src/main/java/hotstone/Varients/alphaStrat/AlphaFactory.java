package hotstone.Varients.alphaStrat;

import hotstone.framework.variants.*;

public class AlphaFactory implements AbstractFactory {


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
        return new AlphaManaStrat();
    }

    @Override
    public WinningStrategies createWinningStrategies() {
        return new AlphaWinnerStrat();
    }
}
