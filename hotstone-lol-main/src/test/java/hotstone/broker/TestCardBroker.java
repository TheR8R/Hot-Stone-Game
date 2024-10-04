package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.broker.client.CardClientProxy;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestCardBroker {
    private Card card;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here a test stub with canned output
        Game servant = new StandardHotStoneGame(new AlphaFactory());
        // Which is injected into the dedicated Invoker which you must
        // develop
        Invoker invoker = new HotStoneGameInvoker(servant);

        // === Next define the client side of the pattern:
        // the client request handler, the requestor, and the client proxy

        // Instead of a network-based client- and server request handler
        // we make a fake object CRH that talks directly with the injected
        // invoker
        ClientRequestHandler crh =
                new LocalMethodClientRequestHandler(invoker);

        // Which is injected into the standard JSON requestor of the
        // FRDS.Broker library
        Requestor requestor = new StandardJSONRequestor(crh);

        // Which is finally injected into the GameClientProxy that
        // you must develop...
        Game proxy = new GameClientProxy(requestor);
        card = proxy.getCardInHand(Player.FINDUS, 0);
    }

    @Test
    public void TestJsonGetName() {
        assertThat(card.getName(), is("Tres"));
    }

    @Test
    public void TestJsonGetManaCost() {
        assertThat(card.getManaCost(), is(3));
    }

    @Test
    public void TestJsonGetAttack() {
        assertThat(card.getAttack(), is(3));
    }

    @Test
    public void TestJsonGetHealth() {
        assertThat(card.getHealth(), is(3));
    }

    @Test
    public void TestJsonIsActive() {
        assertThat(card.isActive(), is(false));
    }

    @Test
    public void TestJsonGetOwner(){
        assertThat(card.getOwner(), is(Player.FINDUS));
    }

}
