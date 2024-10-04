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

package hotstone.main;

import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.Varients.betaStrat.BetaFactory;
import hotstone.Varients.deltaStrat.DeltaFactory;
import hotstone.Varients.epsilonStrat.EpsilonFactory;
import hotstone.Varients.etaStrat.EtaFactory;
import hotstone.Varients.gammaStrat.GammaFactory;
import hotstone.Varients.semiStrat.SemiFactory;
import hotstone.Varients.zetaStrat.ZetaFactory;
import hotstone.figuretestcase.doubles.FakeObjectGame;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.RandomIndexStrat;
import hotstone.standard.StandardHotStoneGame;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.HotSeatStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;



/** A single jvm application which uses a 'hotseat' to allow both players to
 * alternate play.
 */
public class HotSeatStone {
  public static void main(String[] args) {
    Game game;
    System.out.println("=== Starting HotSeat on game variant: " + args[0] + " ===");
    // TODO: Do some switching on args[0] to make the right game variant
    switch(args[0]) {
      case ("alpha") -> {
        game = new StandardHotStoneGame(new AlphaFactory());
      }
      case ("beta") -> {
        game = new StandardHotStoneGame(new BetaFactory());
      }
      case ("gamma") ->{
        game = new StandardHotStoneGame(new GammaFactory());
      }
      case ("delta") ->{
        game = new StandardHotStoneGame(new DeltaFactory());
      }
      case ("epsilon") ->{
        game = new StandardHotStoneGame(new EpsilonFactory(new RandomIndexStrat()));
      }
      case ("zeta") -> {
        game = new StandardHotStoneGame(new ZetaFactory());
      }
      case ("eta") -> {
        game = new StandardHotStoneGame(new EtaFactory(new RandomIndexStrat()));
      }
      case ("semi") -> {
        game = new StandardHotStoneGame(new SemiFactory(new RandomIndexStrat()));
      }
      default -> game = new StandardHotStoneGame(new AlphaFactory());
    }
    DrawingEditor editor =
            new MiniDrawApplication( "HotSeat: Variant " + args[0],
                    new HotStoneFactory(game, Player.FINDUS,
                            HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    // TODO: Change to the hotseat state tool
    editor.setTool(new HotSeatStateTool(editor, game));
  }
}
