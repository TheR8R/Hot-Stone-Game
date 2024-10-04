/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.variants.*;
import hotstone.observer.GameObserver;
import hotstone.observer.ObserverHandler;

import java.util.ArrayList;

import static hotstone.framework.Player.*;


/** This is the 'temporary test stub' in TDD
 * terms: the initial empty but compilable implementation
 * of the game interface.
 *
 * It already includes a bit of FAKE-IT code for the first
 * test case about hand management.
 *
 * Start solving the AlphaStone exercise by
 * following the TDD rythm: pick a one-step-test
 * from your test list, quickly add a test,
 * run it to see it fail, and then modify this
 * implementing class (and supporting classes)
 * to make your test case run. Refactor and repeat.
 *
 * While this is the implementation of Game for
 * the AlphaStone game, you will constantly
 * refactor it over the course of the exercises
 * to become the 'core implementation' which will
 * enable a lot of game variants. This is also
 * why it is not called 'AlphaGame'.
 */
public class StandardHotStoneGame implements Game, MutableGame {

  private Player currentPlayer;
  private HeroStrategies heroStrat;
  private StandardHero findusHero;
  private StandardHero peddersenHero;
  private int turnNumber;
  private ArrayList<Card> findusField;
  private ArrayList<Card> peddersenField;
  private ManaStrategies manaStrategies;
  private WinningStrategies winnerStrat;
  private EffectStrategies effectStrat;
  private DeckStrategies deckStrat;
  private ObserverHandler handler;

  /**
   * Constructor
   */
  public StandardHotStoneGame(AbstractFactory factoryStrat) {
    //factory
    this.deckStrat = factoryStrat.createDeckStrategies();
    this.effectStrat = factoryStrat.createEffectStrategies();
    this.heroStrat = factoryStrat.createHeroStrategies();
    this.manaStrategies = factoryStrat.createManaStrategies();
    this.winnerStrat = factoryStrat.createWinningStrategies();
    //game
    this.currentPlayer = FINDUS;
    turnNumber = 1;
    this.findusHero = new StandardHero();
    initializeHeros(findusHero, FINDUS);
    this.peddersenHero = new StandardHero();
    initializeHeros(peddersenHero, PEDDERSEN);
    this.findusField = new ArrayList<>();
    this.peddersenField = new ArrayList<>();
    findusHero.setMana(manaStrategies.calculateMana(this));
    peddersenHero.setMana(manaStrategies.calculateMana(this));
    this.handler = new ObserverHandler();
  }

  public void initializeHeros(StandardHero hero, Player who){
    hero.setOwner(who);
    hero.setType(heroStrat.setHeroType(who));
    hero.setDeck(deckStrat.initializeDeck(who));
    hero.intializeHand(who);
  }

  /**
   * Accessor method to get the player in turn.
   *
   * @return player whose turn it is.
   */
  @Override
  public Player getPlayerInTurn() {
    return currentPlayer;
  }

  /**
   * Accessor method to get the hero of 'who'
   * PRECONDITION: 'who' is never null
   *
   * @param who the owning player
   * @return the hero of Player 'who'
   */
  @Override
  public Hero getHero(Player who) {
    if (who == FINDUS) {
      return findusHero;
    } else {
      return peddersenHero;
    }
  }

  /**
   * Accessor method to get the winner of the game. Findus always wins when the turn counter goes to 4.
   *
   * @return the player who has won the game.
   */
  @Override
  public Player getWinner() {
    Player winner = winnerStrat.calculateWinner(this);
    if(winner != null) {
      handler.notifyGameWon(winner);
    }
    return winner;
  }

  /**
   * Accessor method to the current turn number. the turn number is modified in the 'endturn()' method, where every time it is findus turn, the counter goes up.
   *
   * @return integer describing current turn number
   */
  @Override
  public int getTurnNumber() {
    return this.turnNumber;
  }

  /**
   * Accessor method to get the amount of cards in the deck of 'who'
   * PRECONDITION: 'who' is never null
   *
   * @param who the player whose deck to inspect
   * @return integer of the amount of cards in deck of 'who'
   */
  @Override
  public int getDeckSize(Player who) {
    return ((MutableHero) getHero(who)).getDeck().size();
  }

  /**
   * Accessor method for a card in the index given, of the hand of 'who'
   * PRECONDITION: 'who' is never null
   *
   * @param who         the player whose hand to inspect
   * @param indexInHand the index of the card to retrieve. MUST be
   *                    0..handsize-1.
   * @return the card on the index given of player 'who' hand.
   */
  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    Hero heroMan = getHero(who);
    return ((MutableHero) heroMan).getHeroHand().get(indexInHand);

  }

  /**
   * Accessor method to get the hand of 'who', with access to the individual cards.
   * PRECONDITION: 'who' is never null
   *
   * @param who the player owning the hand
   * @return an iterable being the hand of 'who'
   */
  @Override
  public Iterable<? extends Card> getHand(Player who) {
    Hero heroHand = getHero(who);
    return ((MutableHero) heroHand).getHeroHand();
  }

  /**
   * Accessor method to get the amount of cards in hand of 'who'
   * PRECONDITION: 'who' is never null
   *
   * @param who the player owning the hand
   * @return integer of the amount of card 'who' has in their hand
   */
  @Override
  public int getHandSize(Player who) {
    Hero heroSize = getHero(who);
    return ((MutableHero) heroSize).getHeroHand().size();
  }

  /**
   * Accessor method to get minion laying in the field.
   * PRECONDITION: 'who' is never null
   *
   * @param who          the player whose field to inspect
   * @param indexInField the index of the card to retrieve. MUST be
   *                     0..fieldsize-1.
   * @return The card on the given index in the field.
   */
  @Override
  public Card getCardInField(Player who, int indexInField) {
    ArrayList<Card> fieldOfWho = (ArrayList<Card>) getField(who);
    return fieldOfWho.get(indexInField);
  }

  /**
   * Accessor method to get the player "who" field as to access each card in the field.
   * PRECONDITION: 'who' is never null
   *
   * @param who the player owning the field
   * @return the field of 'who'
   */
  @Override
  public Iterable<? extends Card> getField(Player who) {
    if (who == FINDUS) {
      return findusField;
    } else {
      return peddersenField;
    }
  }

  /**
   * Accessor method to see that amount of minions in the field of player 'who'
   * PRECONDITION: 'who' is never null
   *
   * @param who the player owning the field
   * @return integer that is the amount of minions that 'who' has in his field.
   */
  @Override
  public int getFieldSize(Player who) {
    ArrayList<Card> theField = (ArrayList<Card>) getField(who);
    return theField.size();
  }

  /**
   * Helper method for endTurn to activate minion.
   * PRECONDITION: 'who' is never null
   *
   * @param who the opponent player, whose turn it just became, who needs his minions in field activated.
   */
  public void activateMinions(Player who) {
    ArrayList<MutableCard> field = (ArrayList<MutableCard>) getField(who);
    for (MutableCard c : field) {
      c.setActive(true);
      handler.notifyCardUpdate(c);
    }
  }

  /**
   * Mutator method to pass the turn to the opponent. It also advances the turn counter, draws a card to the opponents hand and activates the opponent minions in the field.
   */
  @Override
  public void endTurn() {

    //local variables to avoid code duplication
    Player opponent = Utility.computeOpponent(currentPlayer);
    MutableHero opponentHero = (MutableHero) getHero(Utility.computeOpponent(currentPlayer));
    // if the player that ended the turn is Peddersen then the turnCounter increments
    if (opponent == FINDUS) {
      turnNumber += 1;
      //check if Findus wins
      getWinner();
    }
    // update opponent
    currentPlayer = opponent;
    drawFromDeck(opponentHero, 1);
    Card drawnCard = getCardInHand(opponent, 0);
    opponentHero.setMana(manaStrategies.calculateMana(this));
    opponentHero.setActive(true);
    handler.notifyHeroUpdate(currentPlayer);
    handler.notifyTurnChangeTo(currentPlayer);
    activateMinions(opponent);
  }

  /**
   * Mutator method for playing cards. it also calculates appropriate mana to be reduced from the hero, based on a minions manacost.
   * PRECONDITION: 'who' is never null
   *
   * @param who  the player playing the card
   * @param card the card to play to the field.
   * @return returns appropriate status based on if the action is legal
   */
  @Override
  public Status playCard(Player who, Card card) {

    //local variables to avoid code duplication
    int manaCost = card.getManaCost();
    MutableHero whoHero = (MutableHero) getHero(who);
    int heroMana = whoHero.getMana();

    //checks if player has enough mana to play card
    if ((heroMana - manaCost) < 0) {
      return Status.NOT_ENOUGH_MANA;
    } else {
      //update mana of player
      whoHero.reduceMana(manaCost);
      //Field is updated with the card
      ArrayList<Card> field = (ArrayList<Card>) getField(who);
      field.add(0, card);

      //Remove card from hand
      ArrayList<MutableCard> hand = (ArrayList<MutableCard>) getHand(who);
      hand.remove(card);

      //update gui
      handler.notifyPlayCard(who, card);
      handler.notifyHeroUpdate(who);
      //activate card effect
      effectStrat.useCardEffect(this, card);
      return Status.OK;
    }
  }

  /**
   * Mutator method for making minions attack other minions
   *
   * @param playerAttacking the player making the attack
   * @param attackingCard   the card attacking
   * @param defendingCard   the card defending
   * @return return appropriate status based on how legal the attack move is
   */
  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    //local variables to avoid code duplication
    Status checkAttack = checkIfAttackIsLegal(playerAttacking, attackingCard, defendingCard);
    //if statement calling helper method, to determine if it is a legal move.
    if (checkAttack != Status.OK) {
      return checkAttack;
    }
    handler.notifyAttackCard(playerAttacking,attackingCard,defendingCard);
    executeAttackOnCard(attackingCard, defendingCard);
    //extra calculations for epsilonWinnerStrat
    winnerStrat.attackingMinionWinner(playerAttacking,attackingCard);
    getWinner();
    return checkAttack;
  }

  /**
   * mutator method execute the attack of a card
   *
   *
   * @param attackingCard the card attacking
   * @param defendingCard the card defending
   */
  private void executeAttackOnCard(Card attackingCard, Card defendingCard) {
    //damage calculations for minions attacking each other
    int damageA = attackingCard.getAttack();
    int damageD = defendingCard.getAttack();
    changeCardHealth((MutableCard) attackingCard, damageD);
    changeCardHealth((MutableCard) defendingCard, damageA);
    //after a minion attacks it can't attack again
    ((MutableCard) attackingCard).setActive(false);
    handler.notifyCardUpdate(attackingCard);
  }

  public void checkIfMinionDie(Card minion) {
    if (minion.getHealth() <= 0) {
      Player ownerOfMinion = minion.getOwner();
      ((ArrayList<Card>) getField(ownerOfMinion)).remove(minion);
      handler.notifyCardRemove(ownerOfMinion,minion);
    } else {
      handler.notifyCardUpdate(minion);
    }
  }

  /**
   * Mutator method that makes a card attack the enemy hero.
   *
   * @param playerAttacking the player making the attack
   * @param attackingCard   the card attacking
   * @return return appropriate status based on how legal the attack move is
   */
  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    // Minion should not be able to attack if it's inactive.
    Status checkAttack = checkIfAttackIsLegal(playerAttacking, attackingCard, null);
    //if statement calling helper method, to determine if it is a legal move.
    //Defending card is set to null, due to no such parameter needed when hero is attacked.
    if (checkAttack != Status.OK) {
      return checkAttack;
    } else {
      handler.notifyAttackHero(playerAttacking,attackingCard);
      executeAttackOnHero(playerAttacking, attackingCard);
      handler.notifyCardUpdate(attackingCard);
      return Status.OK;
    }
  }

  /**
   * mutator method to execute the attack on
   *
   * @param playerAttacking the player making the attack
   * @param attackingCard the card attacking
   */
  private void executeAttackOnHero(Player playerAttacking, Card attackingCard) {
    MutableHero opponent = (MutableHero) getHero(Utility.computeOpponent(playerAttacking));
    int damage = attackingCard.getAttack();
    reduceHeroHealth(opponent, damage);
    //after a minion attacks it can't attack again
    ((MutableCard) attackingCard).setActive(false);
    //checks if the attacking player has won
  }


  /**
   * Mutator method to activate a hero's power.
   * PRECONDITION: 'who' is never null
   *
   * @param who the player using his/her hero's power
   * @return status ok given player has enough mana, else NOT_ENOUGH_MANA is returned.
   */
  @Override
  public Status usePower(Player who) {
    MutableHero whoHero = (MutableHero) getHero(who);
    Status usePowerCheck = checkIfUsePowerIsLegal(whoHero, who);
    if( usePowerCheck != Status.OK) {
      return usePowerCheck;
    }
    //we passed all the checks which means the use of the hero power is a legal move and we use it
    whoHero.reduceMana(GameConstants.HERO_POWER_COST);
    whoHero.setActive(false);
    handler.notifyUsePower(who);
    handler.notifyHeroUpdate(who);
    return effectStrat.useHeroPower(this);
  }

  private Status checkIfUsePowerIsLegal(Hero whoHero, Player who) {
    boolean whoHeroHasEnoughMana = whoHero.getMana() >= GameConstants.HERO_POWER_COST;
    if (!whoHeroHasEnoughMana) {
      return Status.NOT_ENOUGH_MANA;
    }

    //checks if hero is not active, and returns appropriate status
    boolean whoHeroIsActive = whoHero.isActive();
    if (!whoHeroIsActive) {
      return Status.POWER_USE_NOT_ALLOWED_TWICE_PR_ROUND;
    }

    //checks if it wasn't the player 'who' who activated the hero power, and return appropriate status
    boolean whoIsPlayerInTurn = who == getPlayerInTurn();
    if (!whoIsPlayerInTurn) {
      return Status.NOT_ALLOWED_TO_ACT_ON_BEHALF_OF_OPPONENT;
    }
    return Status.OK;
  }


  /**
   * Helper method to check attacks in order to determine that a given attack on either a card or hero is legal.
   *
   * @param playerAttacking the player attacking
   * @param attackingCard   the card attacking
   * @param defendingCard   the card defending
   * @return appropriate status based on how legal the attack move is
   */
  public Status checkIfAttackIsLegal(Player playerAttacking, Card attackingCard, Card defendingCard) {
    //local variable to avoid code duplication
    Player cardOwner = attackingCard.getOwner();

    //if a card is not active it can't attack
    boolean attackingCardIsActive = attackingCard.isActive();
    if (!attackingCardIsActive) {return Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION;}

    //a card can't attack if the owner of the card isn't the player attacking
    boolean playerAttackingIsOwnerOfCard = playerAttacking == cardOwner;
    if (!playerAttackingIsOwnerOfCard) {return Status.NOT_OWNER;}

    //if a player is attacking without it being his turn.
    boolean playerAttackingIsPlayerInTurn = currentPlayer == playerAttacking;
    if (!playerAttackingIsPlayerInTurn) {return Status.NOT_PLAYER_IN_TURN;}

    if(checkIfAttackIsOnOwnCard(attackingCard, defendingCard)) {return Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION;}

    return Status.OK;
  }

  /**
   * Helper method to clean up checkIfAttackIsLegal
   * @param attackingCard the attacking card
   * @param defendingCard the defending card
   * @return true if the attacking card is attacking defending card and false if it isn't
   */
  private boolean checkIfAttackIsOnOwnCard(Card attackingCard, Card defendingCard){
    // if a defending card is given it's checked that the attacking player is not the owner of the defending card
    boolean defendingCardIsNull = defendingCard == null;
    if (!defendingCardIsNull) {
      //if a player tries to attack his own minions
      boolean defendingCardOwnerIsSameAsAttackingCardOwner = attackingCard.getOwner() == defendingCard.getOwner();
      if (defendingCardOwnerIsSameAsAttackingCardOwner) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addObserver(GameObserver observer) {
    handler.addObserver(observer);
  }

  public void changeCardAttack(MutableCard card, int amount) {
    card.changeAttack(amount);
    handler.notifyCardUpdate(card);
  }

  public void drawFromDeck(MutableHero hero, int amount) {
    hero.drawFromDeck(amount);
    Player who = hero.getOwner();
    handler.notifyCardDraw(who, getCardInHand(who,0));
  }

  public void reduceHeroHealth(MutableHero hero, int amount){
    hero.reduceHealth(amount);
    Player who = hero.getOwner();
    handler.notifyHeroUpdate(who);
    getWinner();
  }


  @Override
  public void changeCardHealth(MutableCard card, int amount) {
    card.changeHealth(amount);
    checkIfMinionDie(card);
  }
}
