package hotstone.broker.service;

import hotstone.framework.Card;
import hotstone.framework.Hero;

import java.util.HashMap;
import java.util.Map;

public class InMemoryNameService implements NameService{
    private Map<String, Card> cardMap;
    private Map<String, Hero> heroMap;

    public InMemoryNameService() {
        this.cardMap = new HashMap<>();
        this.heroMap = new HashMap<>();
    }

    @Override
    public void putCard(String objectId, Card card) {
        cardMap.put(objectId, card);
    }

    @Override
    public Card getCard(String objectId) {
        return cardMap.get(objectId);
    }

    @Override
    public void putHero(String objectId, Hero hero) {
        heroMap.put(objectId, hero);
    }

    @Override
    public Hero getHero(String objectId) {
        return heroMap.get(objectId);
    }
}
