package hotstone.framework.variants;

public interface AbstractFactory {
    DeckStrategies createDeckStrategies();
    EffectStrategies createEffectStrategies();
    HeroStrategies createHeroStrategies();
    ManaStrategies createManaStrategies();
    WinningStrategies createWinningStrategies();
}
