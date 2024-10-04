package hotstone.broker.client;

import frds.broker.Requestor;
import frds.broker.ipc.http.UriTunnelClientRequestHandler;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.common.BrokerConstants;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.DualStateTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

public class GameClient {
    public static void main(String[] args) {
        String host = "localhost";
        Player whoToPlay = Player.FINDUS;
        String gameId = "singleton";

        if(args.length != 2) {
            System.out.println("bruh");
            System.exit(0);
        } else {
            host = args[0];

            whoToPlay = Player.PEDDERSEN;
            if(args[1].equals("findus") || args[1].equals("Findus")) {
                whoToPlay = Player.FINDUS;
            }

        }
        UriTunnelClientRequestHandler clientRequestHandler
                = new UriTunnelClientRequestHandler(host, BrokerConstants.HOTSTONE_PORT,
                false, BrokerConstants.HOTSTONE_TUNNEL_PATH);
        Requestor requestor = new StandardJSONRequestor(clientRequestHandler);

        Game game = new GameClientProxy(requestor);

        HotStoneFactory factory = new HotStoneFactory(game, whoToPlay,
                HotStoneDrawingType.OPPONENT_MODE);

        DrawingEditor editor =
                new MiniDrawApplication( "HotSeat: Variant " + args[0], factory );
        editor.open();
        editor.setTool(new DualStateTool(editor, game, whoToPlay));
    }


}
