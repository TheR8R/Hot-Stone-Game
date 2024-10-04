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

package hotstone.broker.main;

import frds.broker.ClientRequestHandler;
import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.common.BrokerConstants;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

import java.sql.SQLOutput;

public class HotStoneStoryTest {

  public static void main(String[] args)  {
    // Get the name of the host from the commandline parameters
    String host = args[0];
    // and execute the story test, talking to the server with that name
    new HotStoneStoryTest(host);
  }

  public HotStoneStoryTest(String host) {
    // Create the client side Broker roles
    UriTunnelClientRequestHandler clientRequestHandler
            = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
            false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
    Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

    Game game = new GameClientProxy(requestor);
    testSimpleMethods(game);



  }

  private void testSimpleMethods(Game game) {
    System.out.println("=== Testing pass-by-value methods of Game ===");
    System.out.println(" --> Game turnNumber     " + game.getTurnNumber());
    System.out.println(" --> Game winner         " + game.getWinner());
    System.out.println(" --> Game getDeckSizer   " + game.getDeckSize(Player.FINDUS));
    System.out.println(" --> Game getFieldSize   " + game.getFieldSize(Player.FINDUS));
    System.out.println(" --> Game getPlayerInTurn " + game.getPlayerInTurn());
    //System.out.println(" --> Game EndTurn        " + game.endTurn());
    System.out.println(" --> Game playCard       " + game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0)));
    System.out.println(" --> Game attackCard     " + game.attackCard(Player.FINDUS, null, null));
    System.out.println(" --> Game attackHero     " + game.attackHero(Player.FINDUS, null));
    System.out.println(" --> Game usePower       " + game.usePower(Player.FINDUS));
    // TODO - add calls to the rest of the implemented methods
    System.out.println("=== End ===");
  }
}
