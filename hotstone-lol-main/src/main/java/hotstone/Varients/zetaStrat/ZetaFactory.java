package hotstone.Varients.zetaStrat;

import hotstone.Varients.epsilonStrat.EpsilonWinnerStrat;
import hotstone.Varients.alphaStrat.AlphaEffectStrat;
import hotstone.Varients.alphaStrat.AlphaHeroStrat;
import hotstone.Varients.alphaStrat.AlphaManaStrat;
import hotstone.Varients.betaStrat.BetaWinnerStrat;
import hotstone.framework.variants.*;

public class ZetaFactory implements AbstractFactory {
    @Override
    public DeckStrategies createDeckStrategies() {
        return new ZetaDeckStrat();
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
        return new ZetaWinnerStrat(new BetaWinnerStrat(), new EpsilonWinnerStrat());
    }
}
