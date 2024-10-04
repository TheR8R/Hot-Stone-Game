package hotstone.framework;

import hotstone.standard.StandardHero;

public interface MutableGame extends Game {

    public void initializeHeros(StandardHero hero, Player who);

    public void activateMinions(Player who);

    public void checkIfMinionDie(Card minion);

    public Status checkIfAttackIsLegal(Player playerAttacking, Card attackingCard, Card defendingCard);

    public void changeCardAttack(MutableCard card, int amount);

    public void drawFromDeck(MutableHero hero, int amount);

    public void reduceHeroHealth(MutableHero hero, int amount);

    public void changeCardHealth(MutableCard card, int amount);

}
