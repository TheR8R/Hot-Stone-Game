/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.broker.client;

import com.google.gson.reflect.TypeToken;
import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.BrokerConstants;
import hotstone.broker.common.OperationNames;
import hotstone.framework.*;
import hotstone.observer.GameObserver;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardHero;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/** Template/starter code for your ClientProxy of Game.
 */
public class GameClientProxy implements Game, ClientProxy {
  Requestor req;
  public GameClientProxy(Requestor requestor) {
    this.req = requestor;
  }

  @Override
  public int getTurnNumber() {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_TURN_NUMBER, int.class);
  }

  @Override
  public Player getPlayerInTurn() {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_PLAYER_IN_TURN, Player.class);
  }

  @Override
  public Hero getHero(Player who) {
    String id = req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_HERO, String.class, who);
    if(id != null) {
      return new HeroClientProxy(req, id);
    } else {
      return null;
    }

  }

  @Override
  public Player getWinner() {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_WINNER, Player.class);
  }

  @Override
  public int getDeckSize(Player who) {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_DECK_SIZE, int.class, who);
  }

  @Override
  public Card getCardInHand(Player who, int indexInHand) {
    String id = req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_CARD_IN_HAND, String.class, who, indexInHand);
    if (id != null) {
      return new CardClientProxy(req, id);
    } else {
      return null;
    }
  }

  @Override
  public Iterable<? extends Card> getHand(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> idList = req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_HAND, collectionType, who);
    List<CardClientProxy> returnList = new ArrayList<>();
    for(String s: idList) {
      returnList.add(new CardClientProxy(req, s));
    }
    return returnList;
  }

  @Override
  public int getHandSize(Player who) {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_HAND_SIZE, int.class, who);
  }

  @Override
  public Card getCardInField(Player who, int indexInField) {
    String id = req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_CARD_IN_FIELD, String.class, who, indexInField);
    if(id != null) {
      return new CardClientProxy(req, id);
    } else {
      return null;
    }
  }

  @Override
  public Iterable<? extends Card> getField(Player who) {
    Type collectionType = new TypeToken<List<String>>(){}.getType();
    List<String> idList = req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_FIELD, collectionType, who);
    List<CardClientProxy> returnList = new ArrayList<>();
    for(String s: idList) {
      returnList.add(new CardClientProxy(req, s));
    }
    return returnList;
  }

  @Override
  public int getFieldSize(Player who) {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_GET_FIELD_SIZE, int.class, who);
  }

  @Override
  public void endTurn() {
    req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_END_OF_TURN, String.class);
  }

  @Override
  public Status playCard(Player who, Card card) {
    String id = card.getId();
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_PLAY_CARD, Status.class, who, id);
  }

  @Override
  public Status attackCard(Player playerAttacking, Card attackingCard, Card defendingCard) {
    String cardId1 = attackingCard.getId();
    String cardId2 = defendingCard.getId();
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_ATTACK_CARD, Status.class, playerAttacking, cardId1, cardId2);
  }

  @Override
  public Status attackHero(Player playerAttacking, Card attackingCard) {
    String id = attackingCard.getId();
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_ATTACK_HERO, Status.class, playerAttacking, id);
  }

  @Override
  public Status usePower(Player who) {
    return req.sendRequestAndAwaitReply("singleton", OperationNames.GAME_USE_POWER, Status.class, who);
  }

  @Override
  public void addObserver(GameObserver observer) {

  }
}
