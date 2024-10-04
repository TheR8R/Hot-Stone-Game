package hotstone.framework;

import hotstone.observer.GameObserver;
import hotstone.standard.StandardHero;

import java.util.PriorityQueue;
import java.util.Queue;

public class Transcript implements MutableGame{

    MutableGame decoratee;

    public Transcript(MutableGame tGame) {
        this.decoratee = tGame;
    }


    @Override
    public Player getPlayerInTurn() {
        System.out.println("Current player is " + decoratee.getPlayerInTurn());
        return decoratee.getPlayerInTurn();
    }

    @Override
    public Hero getHero(Player who) {
        return decoratee.getHero(who);
    }

    @Override
    public Player getWinner() {
        System.out.println("The winner is " + decoratee.getWinner());
        return decoratee.getWinner();
    }

    @Override
    public int getTurnNumber() {
        System.out.println("Current turn is " + decoratee.getTurnNumber());
        return decoratee.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return decoratee.getDeckSize(who);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return decoratee.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return decoratee.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return decoratee.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return decoratee.getCardInField(who, indexInField);
    }

    @Override
    public Iterable<? extends Card> getField(Player who) {
        return null;
    }

    @Override
    public int getFieldSize(Player who) {
        return 0;
    }

    @Override
    public void endTurn() {
        Player playerEndingTurn = decoratee.getPlayerInTurn();
        Player newPlayer = Utility.computeOpponent(playerEndingTurn);
        System.out.println(playerEndingTurn + " Has ended their turn");
        System.out.println(newPlayer + " has drawn " + ((MutableHero)decoratee.getHero(newPlayer)).getDeck().peek().getName());
        decoratee.endTurn();
    }

    @Override
    public Status playCard(Player who, Card card) {
        System.out.println(decoratee.getPlayerInTurn() + " plays card " + decoratee.getCardInField(who, 0));
        return decoratee.playCard(who, card);
    }

    @Override
    public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
        System.out.println(playerAttacking + " attacks " + defendingCard + " with " + attackingCard);
        return decoratee.attackCard(playerAttacking, attackingCard, defendingCard);
    }

    @Override
    public Status attackHero(Player playerAttacking, Card attackingCard) {
        System.out.println(playerAttacking + " is attacking " + Utility.computeOpponent(playerAttacking) + " with " + attackingCard);
        return decoratee.attackHero(playerAttacking, attackingCard);
    }

    @Override
    public Status usePower(Player who) {
        System.out.println(who + " used their hero power");
        return usePower(who);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }



    @Override
    public void initializeHeros(StandardHero hero, Player who) {

    }

    @Override
    public void activateMinions(Player who) {

    }

    @Override
    public void checkIfMinionDie(Card minion) {
        System.out.println(minion + " is dead");
    }   

    @Override
    public Status checkIfAttackIsLegal(Player playerAttacking, Card attackingCard, Card defendingCard) {
        return checkIfAttackIsLegal(playerAttacking,attackingCard,defendingCard);
    }

    @Override
    public void changeCardAttack(MutableCard card, int amount) {
        System.out.println(card + "'s attack is changed with " + amount);
        decoratee.changeCardAttack(card, amount);
    }

    @Override
    public void drawFromDeck(MutableHero hero, int amount) {
        System.out.println(hero.getOwner() + " has drawn " + amount + " cards");
        decoratee.drawFromDeck(hero, amount);
    }

    @Override
    public void reduceHeroHealth(MutableHero hero, int amount) {
        System.out.println(hero.getOwner() + "'s health is reduced by " + amount);
        decoratee.reduceHeroHealth(hero, amount);
    }

    @Override
    public void changeCardHealth(MutableCard card, int amount) {
        System.out.println(card + "'s health is changed with " + amount);
        decoratee.changeCardHealth(card, amount);
    }


}
