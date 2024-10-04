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

package hotstone.broker.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import frds.broker.Invoker;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotstone.broker.common.OperationNames;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.broker.service.InMemoryNameService;
import hotstone.broker.service.NameService;
import hotstone.framework.*;
import hotstone.observer.ObserverHandler;
import hotstone.standard.StandardCard;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotStoneGameInvoker implements Invoker {

  private Gson gson;
  private Game serv;
  private InMemoryNameService nameService;


  public HotStoneGameInvoker(Game servant) {
    gson = new Gson();
    this.serv = servant;
    this.nameService = new InMemoryNameService();
  }


  @Override
  public String handleRequest(String request) {
    // Do the demarshalling

    RequestObject requestObject =
            gson.fromJson(request, RequestObject.class);
    JsonArray array =
            JsonParser.parseString(requestObject.getPayload()).getAsJsonArray();

    ReplyObject reply = null;

    String objectId = requestObject.getObjectId();

      if(requestObject.getOperationName().contains(OperationNames.GAME_PREFIX)) {
       switch (requestObject.getOperationName()) {
         case (OperationNames.GAME_GET_TURN_NUMBER) -> {
           int turn = serv.getTurnNumber();
           reply = new ReplyObject(HttpServletResponse.SC_CREATED,
                   gson.toJson(turn));
         }
         case (OperationNames.GAME_GET_WINNER) -> {
           Player winner = serv.getWinner();
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(winner));
         }
         case OperationNames.GAME_GET_PLAYER_IN_TURN -> {
           Player inTurn = serv.getPlayerInTurn();
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(inTurn));
         }
         case OperationNames.GAME_GET_DECK_SIZE -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           int size = serv.getDeckSize(who);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(size));
         }
         case OperationNames.GAME_GET_HAND_SIZE -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           int hSize = serv.getHandSize(who);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(hSize));
         }
         case OperationNames.GAME_GET_FIELD_SIZE -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           int size = serv.getFieldSize(who);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(size));
         }
         case OperationNames.GAME_END_OF_TURN -> {
           serv.endTurn();
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, null);
         }
         case OperationNames.GAME_PLAY_CARD -> {
           String id = gson.fromJson(array.get(1), String.class);
           Card card = lookUpCard(id);
           Player who = gson.fromJson(array.get(0), Player.class);
           Status stat = serv.playCard(who, card);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(stat));
         }
         case OperationNames.GAME_ATTACK_CARD -> {
           String aCardId = gson.fromJson(array.get(1), String.class);
           String  dCardId = gson.fromJson(array.get(2), String.class);
           Card aCard = lookUpCard(aCardId);
           Card dCard = lookUpCard(dCardId);
           Player who = gson.fromJson(array.get(0), Player.class);
           Status stat = serv.attackCard(who, aCard, dCard);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(stat));
         }
         case OperationNames.GAME_ATTACK_HERO -> {
           String cardId = gson.fromJson(array.get(1), String.class);
           Card card = lookUpCard(cardId);
           Player who = gson.fromJson(array.get(0), Player.class);
           Status stat = serv.attackHero(who, card);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(stat));
         }
         case OperationNames.GAME_USE_POWER -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           Status status = serv.usePower(who);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(status));
         }
         case OperationNames.GAME_GET_HERO -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           Hero hero = serv.getHero(who);
           nameService.putHero(hero.getId(), hero);
           reply =  new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(hero.getId()));
         }
         case OperationNames.GAME_GET_CARD_IN_HAND -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           int n = gson.fromJson(array.get(1), int.class);
           Card card = serv.getCardInHand(who, n);
           nameService.putCard(card.getId(), card);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(card.getId()));
         }
         case OperationNames.GAME_GET_HAND -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           List<Card> hand = (List<Card>) serv.getHand(who);
           List<String> idList = new ArrayList<>();
           for(Card c: hand) {
             nameService.putCard(c.getId(), c);
             idList.add(c.getId());
           }
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(idList));
         }
         case OperationNames.GAME_GET_CARD_IN_FIELD -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           int index = gson.fromJson(array.get(1), int.class);
           Card card = serv.getCardInField(who, index);
           nameService.putCard(card.getId(), card);
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(card.getId()));
         }
         case OperationNames.GAME_GET_FIELD -> {
           Player who = gson.fromJson(array.get(0), Player.class);
           List<Card> hand = (List<Card>) serv.getField(who);
           List<String> idList = new ArrayList<>();
           for(Card c: hand) {
             nameService.putCard(c.getId(), c);
             idList.add(c.getId());
           }
           reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(idList));
         }


           default -> throw new IllegalStateException("Unexpected value: " + OperationNames.GAME_PREFIX);
       }
      }
      else if(requestObject.getOperationName().contains(OperationNames.CARD_PREFIX)) {
        Card cardServ = lookUpCard(objectId);
        switch (requestObject.getOperationName()) {
          case OperationNames.CARD_GET_MANA_COST -> {
            int manaCost = cardServ.getManaCost();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(manaCost));
          }
          case OperationNames.CARD_GET_ATTACK -> {
            int cardAttack = cardServ.getAttack();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(cardAttack));
          }
          case OperationNames.CARD_GET_HEALTH -> {
            int health = cardServ.getHealth();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(health));
          }
          case OperationNames.CARD_GET_OWNER -> {
            Player who = cardServ.getOwner();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(who));
          }
          case OperationNames.CARD_GET_NAME -> {
            String name = cardServ.getName();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(name));
          }
          case OperationNames.CARD_IS_ACTIVE -> {
            boolean active = cardServ.isActive();
            reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(active));
          }
        }
      }
      else if(requestObject.getOperationName().contains(OperationNames.HERO_PREFIX)) {
        Hero heroServ = lookUpHero(objectId);
          switch (requestObject.getOperationName()) {
            case OperationNames.HERO_GET_MANA -> {
              int mana = heroServ.getMana();
              reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(mana));
            }
            case OperationNames.HERO_GET_HEALTH -> {
              int health = heroServ.getHealth();
              reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(health));
            }
            case OperationNames.HERO_GET_OWNER -> {
              Player who = heroServ.getOwner();
              reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(who));
            }
            case OperationNames.HERO_IS_ACTIVE -> {
              boolean active = heroServ.isActive();
              reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(active));
            }
            case OperationNames.HERO_GET_TYPE -> {
              String type = heroServ.getType();
              reply = new ReplyObject(HttpServletResponse.SC_CREATED, gson.toJson(type));
            }

        }
      }

      return gson.toJson(reply);
  }
  private Card lookUpCard(String objectId) {
    return nameService.getCard(objectId);
  }
  private Hero lookUpHero(String objectId) {
    return nameService.getHero(objectId);
  }
}
