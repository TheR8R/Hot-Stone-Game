package hotstone.doubles;

import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.observer.GameObserver;

import java.util.*;

public class GameObserverSpy implements GameObserver {


    private String lastMethodCall;

    List<String> methodCalls = new ArrayList<>();

    @Override
    public void onCardPlay(Player who, Card card) {
        methodCalls.add("onCardPlay");
        lastMethodCall = "onCardPlay";
    }

    @Override
    public void onTurnChangeTo(Player playerBecomingActive) {
        methodCalls.add("onTurnChangeTo");
        lastMethodCall = "onTurnChangeTo";
    }

    @Override
    public void onAttackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        methodCalls.add("onAttackCard");
        lastMethodCall = "onAttackCard";
    }

    @Override
    public void onAttackHero(Player playerAttacking, Card attackingCard) {
        methodCalls.add("onAttackHero");
        lastMethodCall = "onAttackHero";
    }

    @Override
    public void onUsePower(Player who) {
        methodCalls.add("onUsePower");
        lastMethodCall = "onUsePower";
    }

    @Override
    public void onCardDraw(Player who, Card drawnCard) {
        methodCalls.add("onCardDraw");
        lastMethodCall = "onCardDraw";
    }

    @Override
    public void onCardUpdate(Card card) {
        methodCalls.add("onCardUpdate");
        lastMethodCall = "onCardUpdate";
    }

    @Override
    public void onCardRemove(Player who, Card card) {
        methodCalls.add("onCardRemove");
        lastMethodCall = "onCardRemove";
    }

    @Override
    public void onHeroUpdate(Player who) {
        methodCalls.add("onHeroUpdate");
        lastMethodCall = "onHeroUpdate";
    }

    @Override
    public void onGameWon(Player playerWinning) {
        methodCalls.add("onGameWon");
        lastMethodCall = "onGameWon";
    }

    public String getlastMethodCall() {
        return lastMethodCall;
    }

    public List getMethodCalls() {
        return methodCalls;
    }
}
