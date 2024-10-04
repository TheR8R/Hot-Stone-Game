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

/**
 * Skeleton class for AlphaStone test cases
 *
 *    This source code is from the book
 *      "Flexible, Reliable Software:
 *        Using Patterns and Agile Development"
 *      2nd Edition
 *    Author:
 *      Henrik Bærbak Christensen
 *      Department of Computer Science
 *      Aarhus University
 */

import hotstone.Varients.alphaStrat.*;

import hotstone.framework.*;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class for alphastone
 */
public class TestAlphaStone {
  private Game game;
  private Card dosF;
  private Card unoF;
  private Card tresF;
  private Card dosP;
  private Card unoP;
  /** Fixture for AlphaStone testing.  */
  @BeforeEach
  public void setUp() {
    //game = new StandardHotStoneGame(new AlphaManaStrat(), new AlphaWinnerStrat(), new AlphaHeroStrat(), new AlphaDeckStrat(), new AlphaEffectStrat());
    game = new StandardHotStoneGame(new AlphaFactory());
    dosF = game.getCardInHand(Player.FINDUS, 1);
    unoF = game.getCardInHand(Player.FINDUS, 2);
    tresF = game.getCardInHand(Player.FINDUS, 0);
    dosP = game.getCardInHand(Player.PEDDERSEN, 1);
    unoP = game.getCardInHand(Player.PEDDERSEN, 2);
  }

  /**
   * test if we start with the right cards in hand, that being Uno, Dos and Tres
   */
  @Test
  public void shouldHaveUnoDosTresCardsInitially() {
    Card tres = game.getCardInHand(Player.FINDUS, 0);
    Card dos = game.getCardInHand(Player.FINDUS, 1);
    Card uno = game.getCardInHand(Player.FINDUS, 2);
    assertThat(tres.getName(), is(GameConstants.TRES_CARD));
    assertThat(dos.getName(), is(GameConstants.DOS_CARD));
    assertThat(uno.getName(), is(GameConstants.UNO_CARD));
  }

  /**
   * Test if Findus always starts.
   */
  @Test
    public void firstPlayerIsFindus() {
    assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
  }

  /**
   * Test if endTurn() actually changes the current player in turn.
   */
    @Test
    public void findusEndsTurnNowIsPeddersenTurn() {
    game.endTurn();
    assertThat(game.getPlayerInTurn(), is(Player.PEDDERSEN));
    }

  /**
   * Test if endTurn() also changes back to the first player in turn.
   */
  @Test
    public void peddersenEndsTurnNowIsFindusTurn() {
      game.endTurn();
      game.endTurn();
      assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
    }

  /**
   * Checks if Findus draws the right amount of cards in the beginning.
   */
    @Test
    public void findusShouldStartWith3Cards() {
      assertThat(game.getHandSize(Player.FINDUS), is(3));
    }

  /**
   * Checks if the Dos card has been initialized correctly.
   */
  @Test
    public void dosHas222() {
      assertThat(dosF.getAttack(), is(2));
      assertThat(dosF.getHealth(), is(2));
      assertThat(dosF.getManaCost(), is(2));
    }

  /**
   * checks if Uno card has been initialized correctly.
   */
  @Test
  public void unoHas111(){
    assertThat(unoF.getHealth(), is(1));
    assertThat(unoF.getManaCost(), is(1));
    assertThat(unoF.getAttack(), is(1));
  }

  /**
   * Test if Findus can play Uno from his hand.
   */
  @Test
    public void findusIsAllowedToPlayUno() {
      assertThat(game.playCard(Player.FINDUS, unoF), is(Status.OK));
    }

  /**
   * Test if Uno appears in the field after Findus has played it.
   */
  @Test
    public void findusPlaysUnoAndItAppearsInField() {
      assertThat(game.playCard(Player.FINDUS, unoF), is(Status.OK));
      assertThat(game.getCardInField(Player.FINDUS, 0).getName(), is(GameConstants.UNO_CARD));
    }

  /**
   * Check if Peddersen loses 2 mana when he plays Dos card.
   */
    @Test
    public void peddersenPlaysDosAndLoses2Mana() {
      assertThat(game.playCard(Player.PEDDERSEN, dosP), is(Status.OK));
      assertThat(game.getHero(Player.PEDDERSEN).getMana(), is(1));
    }

  /**
   * Check if Peddersen still has 3 cards after findus Plays one Card.
   */
  @Test
    public void peddersenHas3CardsAfterFindusPlaysACard(){
      game.playCard(Player.FINDUS, unoF);
      assertThat(game.getHandSize(Player.PEDDERSEN), is(3));
      assertThat(game.getHandSize(Player.FINDUS), is(2));
  }

  /**
   * Peddersen wins when findus health is 0.
   */
    @Test
    public void peddersenWinsWhenFindusHealthIs0() {
      ((StandardHero) game.getHero(Player.FINDUS)).reduceHealth(20);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      game.endTurn();
      game.endTurn();
      game.attackHero(Player.PEDDERSEN, unoP);
      assertThat(game.getWinner(), is(Player.PEDDERSEN));
    }

  /**
   * Checks After 4 turns Findus Wins.
   */
    @Test
    public void after4TurnsFindusWins() {
      for(int i = 0; i < 6; i++) {
        game.endTurn();
      }
      assertThat(game.getTurnNumber(), is(4));
      assertThat(game.getWinner(), is(Player.FINDUS));
    }

  /**
   * Checks if both heroes start with 21 Health.
   */
    @Test
    public void bothHeroesStartAt21Health() {
      assertThat(game.getHero(Player.FINDUS).getHealth(), is(21));
      assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(21));

    }

  /**
   * Checks if the field is empty at the start of the game.
   */
    @Test
    public void fieldSizeStartsAt0() {
      assertThat(game.getFieldSize(Player.FINDUS), is(0));
    }

  /**
   * Checks if the field size gets bigger when cards are played.
   */
    @Test
    public void fieldSizeUpdatesWhenCardIsAdded() {
      assertThat(game.playCard(Player.FINDUS, unoF), is(Status.OK));
      assertThat(game.getFieldSize(Player.FINDUS), is(1));
    }

  /**
   * checks if uno appears in the right index on the field.
   */
  @Test
    public void unoIsInIndex0InFindusField() {
      assertThat(game.playCard(Player.FINDUS, unoF), is(Status.OK));
      assertThat(game.getCardInField(Player.FINDUS, 0), is(unoF));
    }

  /**
   * Checks if there are 4 cards in the deck when the game starts.
   */
    @Test
    public void deckSizeAtStartOfGameIs4() {
      assertThat(game.getDeckSize(Player.FINDUS), is(4));
      assertThat(game.getDeckSize(Player.PEDDERSEN), is(4));
    }

  /**
   * Checks when peddersen ends his turn, then findus draws 1 card from his own deck.
   */
  @Test
    public void whenPeddersenEndsHisTurnFindusDraws1Card() {
      game.endTurn();
      game.endTurn();
      assertThat(game.getHandSize(Player.FINDUS), is(4));
      assertThat(game.getDeckSize(Player.FINDUS), is(3));
    }

  /**
   * Checks when findus ends his turn, then Peddersen draws 1 card from his own deck.
   */
    @Test
    public void whenFindusEndsHisTurnPeddersenDraws1Card() {
      game.endTurn();
      assertThat(game.getHandSize(Player.PEDDERSEN), is(4));
      assertThat(game.getDeckSize(Player.PEDDERSEN), is(3));
    }

  /**
   * Checks if Findus handSize decreases when he plays a card.
   */
    @Test
    public void findusHandGets1SmallerWhenHePlaysACard() {
       game.playCard(Player.FINDUS, unoF);
       assertThat(game.getHandSize(Player.FINDUS), is(2));
    }

  /**
   * Checks if The Uno card is removed from the hand when played.
   */
  @Test
    public void unoCardDoesNotExistInHandAfterplayed(){
    game.playCard(Player.FINDUS, unoF);
    for (Card c: game.getHand(Player.FINDUS)) {
      assert (c != unoF);
    }
  }

  /**
   * Checks if the field updates correctly when cards are played.
   */
    @Test
    public void whenFindusPlaysUnoAndDosTheFieldUpdatesCorrectly() {
      game.playCard(Player.FINDUS, unoF);
      game.playCard(Player.FINDUS, dosF);
      for (Card c: game.getField(Player.FINDUS)) {
        assert((c == unoF) || (c == dosF));
      }
    }

  /**
   * Check if cards played by findus appears on his own field and not on peddersens, and that peddersens minions don't appear on findus' field.
   */
    @Test
    public void whenPeddersenPlaysCardAfterFindusPlaysCardTheCorrectCardIsOnTheField() {
      game.playCard(Player.FINDUS, unoF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, dosP);
      assertThat(game.getCardInField(Player.FINDUS, 0), is(unoF));
      assertThat(game.getCardInField(Player.PEDDERSEN, 0), is(dosP));
    }

  /**
   * Check that cards always appear to the left in the field when played.
   */
  @Test
    public void cardsAppearToTheLeftInFieldWhenPlayed() {
      game.playCard(Player.FINDUS, unoF);
      assertThat(game.getCardInField(Player.FINDUS, 0), is(unoF));
      game.playCard(Player.FINDUS, dosF);
      assertThat(game.getCardInField(Player.FINDUS, 0), is(dosF));
    }

  /**
   * Checks that if findus used all his mana, he can't play another card.
   */
  @Test
    public void afterPlayingTresCantPlayDos(){
      assertThat(game.playCard(Player.FINDUS, tresF), is(Status.OK));
      game.playCard(Player.FINDUS, tresF);
      assertThat(game.playCard(Player.FINDUS, dosF), is(Status.NOT_ENOUGH_MANA));
    }

  /**
   * Check that minions can't attack as soon as they are played.
   */
    @Test
    public void whenCardIsPlayedItIsInactive() {
      game.playCard(Player.FINDUS, dosF);
      assertThat(dosF.isActive(), is(false));
    }

  /**
   * checks that after findus plays a minion, he can attack with it when it becomes his turn again.
   */
  @Test
    public void inSecondTurnCardIsActive() {
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.endTurn();
      assertThat(dosF.isActive(), is(true));
    }

  /**
   * Checks if cards can attack each other.
   */
    @Test
    public void findusCanAttackUnoWithDos() {
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      game.endTurn();
      assertThat(game.attackCard(Player.FINDUS, dosF, unoP), is(Status.OK));
    }

  /**
   * Testing for attack with inactive minion.
   */
  @Test
    public void peddersenCantAttackWithDosOnFirstTrun() {
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      assertThat(game.attackCard(Player.PEDDERSEN, dosP, unoF), is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
    }

  /**
   * Testing that you can't attack your own minions.
   */
  @Test
    public void findusCantAttackHisOwnMinions() {
      game.playCard(Player.FINDUS, unoF);
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.endTurn();
      assertThat(game.attackCard(Player.FINDUS, unoF, dosF), is(Status.ATTACK_NOT_ALLOWED_ON_OWN_MINION));
    }

  /**
   * Checks if minions can attack heroes.
   */
  @Test
    public void minionsCanAttackHeroes(){
      game.playCard(Player.FINDUS, unoF);
      game.endTurn();
      game.endTurn();
      assertThat(game.attackHero(Player.FINDUS, unoF), is(Status.OK));

    }

  /**
   * Checks if peddersen lose health after being attacked.
   */

    @Test
    public void afterFindusAttacksPeddersenHeLosesHealth(){
      game.playCard(Player.FINDUS, unoF);
      game.endTurn();
      game.endTurn();
      game.attackHero(Player.FINDUS, unoF);
      assertThat(game.getHero(Player.PEDDERSEN).getHealth(), is(20));
    }
  /**
   * Checks if findus lose health after being attacked.
   */
    @Test
    public void afterPeddersenAttacksFindusHeLosesHealth(){
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      game.endTurn();
      game.endTurn();
      game.attackHero(Player.PEDDERSEN, unoP);
      assertThat(game.getHero(Player.FINDUS).getHealth(), is(20));
    }

    /**
     * checks if inactive minions can attack heroes.
     */
    @Test
    public void inactiveMinionCantAttackHero(){
      game.playCard(Player.FINDUS, unoF);
      assertThat(game.attackHero(Player.FINDUS, unoF), is(Status.ATTACK_NOT_ALLOWED_FOR_NON_ACTIVE_MINION));
  }

  /**
     * Test getOwner in StandardCard.
     */
    @Test
    public void findusMinionBelongsToFindus() {
      game.playCard(Player.FINDUS, unoF);
      assertThat(unoF.getOwner(), is(Player.FINDUS));
    }

  /**
   * Test getOwner in StandardCard for the purpose of triangulation.
   */
  @Test
    public void peddersensMinionBelongsToPeddersen() {
      game.endTurn();
      game.playCard(Player.PEDDERSEN, dosP);
      assertThat(dosP.getOwner(), is(Player.PEDDERSEN));
    }
  /**
   * checks if minions attack each other, they both lose health.
   */
  @Test
    public void afterMinionAttackTheyBothLoseHealth(){
    Card fourF = new StandardCard(GameConstants.GREEN_SALAD_CARD, Player.FINDUS,2,2,3, null);
    Card fourP = new StandardCard(GameConstants.GREEN_SALAD_CARD, Player.PEDDERSEN,2,2,3, null);
    game.playCard(Player.FINDUS, fourF);
    game.endTurn();
    game.playCard(Player.PEDDERSEN, fourP);
    game.endTurn();
    game.attackCard(Player.FINDUS, fourF, fourP);
    assertThat(fourF.getHealth(), is(1));
    assertThat(fourP.getHealth(), is(1));
  }
  /**
   * Checks that after a minion attacks a minion it becomes inactive.
   */
  @Test
    public void afterAMinionAttacksAnotherMinionStatusIsInactive(){
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      game.endTurn();
      game.attackCard(Player.FINDUS, dosF, unoP);
      assertThat(dosF.isActive(), is(false));
    }

  /**
   * Checks that after a minion attacks a hero it becomes inactive.
   */
  @Test
    public void whenAMinionAttacksAHeroStatusIsInactive(){
    game.playCard(Player.FINDUS, dosF);
    game.endTurn();
    game.endTurn();
    game.attackHero(Player.FINDUS, dosF);
    assertThat(dosF.isActive(), is(false));
  }

  /**
   * Checks if a minion defending is removed from the field, if its health is 0 or below.
   */
  @Test
    public void whenDefendingMinionsHealthGetsBelow0ItsRemovedFromTheField() {
      game.playCard(Player.FINDUS, dosF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, unoP);
      game.endTurn();
      game.attackCard(Player.FINDUS, dosF, unoP);
      assertThat(game.getFieldSize(Player.PEDDERSEN), is(0));
    }

  /**
   * Checks if attacking minions are removed from the field if its health is 0 or below.
   */
    @Test
    public void whenAttackingMinionsHealthGetsBelow0ItsRemovedFromTheField() {
      game.playCard(Player.FINDUS, unoF);
      game.endTurn();
      game.playCard(Player.PEDDERSEN, dosP);
      game.endTurn();
      game.attackCard(Player.FINDUS, unoF, dosP);
      assertThat(game.getFieldSize(Player.FINDUS), is(0));
    }

  /**
   * Checks if cards drawn from the deck appear at index 0, that is to the left, in the hand.
   */
    @Test
    public void whenFindusDrawsACardItappearsAtIndex0InHand() {
      game.endTurn();
      game.endTurn();
      assertThat(game.getCardInHand(Player.FINDUS, 0).getName(), is(GameConstants.CUATRO_CARD));
    }

  /**
   * Checks if Findus is allowed to attack with minions when it isn't his turn.
   */
    @Test
    public void findusCantAttackWhenItIsntHisTurn() {
      game.playCard(Player.FINDUS, unoF);
      game.endTurn();
      game.endTurn();
      game.endTurn();
      assertThat(game.attackHero(Player.FINDUS, unoF), is(Status.NOT_PLAYER_IN_TURN));
    }

  /**
   * Checks if Findus' herotype is baby.
   */
  @Test
    public void findusHeroTypeIsBaby() {
      assertThat(game.getHero(Player.FINDUS).getType(), is(GameConstants.BABY_HERO_TYPE));
    }

  /**
   * Checks if Findus can use his heropower.
   */
  @Test
    public void findusUseHeroPowerIsCuteDoesNothing() {
    assertThat(game.usePower(Player.FINDUS), is(Status.OK));
    }

  /**
   * Checks if mana is deducted from a players total when they use their heropower.
   */
  @Test
    public void cuteCosts2Mana() {
      game.usePower(Player.FINDUS);
      assertThat(game.getHero(Player.FINDUS).getMana(), is(1));
    }

  /**
   * Checks if the heropower is usable in the beginning of the game.
   */
    @Test
    public void heroIsActiveWhenGameStarts() {
      assertThat(game.getHero(Player.FINDUS).isActive(), is(true));
    }

  /**
   * Checks if the heropower is set to Inactive when used.
   */
  @Test
    public void heroBecomesInactiveWhenPowerIsUsed() {
      game.usePower(Player.FINDUS);
      assertThat(game.getHero(Player.FINDUS).isActive(), is(false));
    }

  /**
   * Checks if the herotype is consistant throughout a game.
   */
  @Test
    public void heroTypeDoesntChange() {
      for (int i = 0; i < 4; i++) {
        assertThat(game.getHero(Player.FINDUS).getType(), is("Baby"));
        assertThat(game.getHero(Player.PEDDERSEN).getType(), is("Baby"));
        game.endTurn();

      }
    }

  /**
   * Checks if the player has enough mana to use the hero power
   */
  @Test
  public void cantUseHeroPowerIfMissingMana(){
    game.playCard(Player.FINDUS, dosF);
    assertThat(game.usePower(Player.FINDUS), is(Status.NOT_ENOUGH_MANA));
  }

  /**
   * Checks if findus is the owner of his hero. (redundent method being tested in alphastone)
   */
  @Test
  public void findusIstheOwnerOfHisHero(){
    assertThat(game.getHero(Player.FINDUS).getOwner(), is(Player.FINDUS));
  }

  @Test
  public void peddersenCantUseHeroPowerOnFindusTurn() {
    assertThat(game.usePower(Player.PEDDERSEN), is(Status.NOT_ALLOWED_TO_ACT_ON_BEHALF_OF_OPPONENT));
  }

  @Test
  public void findusHeroDescriptionWorks(){
    Hero findusHero = game.getHero(Player.FINDUS);
    assertThat(findusHero.getEffectDescription(), is("Is Cute"));
  }

  @Test
  public void peddersenHeroDescriptionWorks(){
    Hero peddersenHero = game.getHero(Player.PEDDERSEN);
    assertThat(peddersenHero.getEffectDescription(), is("Is Cute"));
  }





}
