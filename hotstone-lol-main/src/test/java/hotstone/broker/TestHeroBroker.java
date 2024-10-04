package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.client.HeroClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubCardForBroker;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.doubles.StubHeroForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Hero;
import hotstone.framework.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHeroBroker {
        private Hero hero;

    @BeforeEach
    public void setup() {
        // === We start at the server side of the Broker pattern:
        // define the servant, next the invoker

        // Given a Servant game, here a test stub with canned output
        Game servant = new StubGameForBroker();
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
        // you must develop..
        Game proxy = new GameClientProxy(requestor);
        hero = proxy.getHero(Player.FINDUS);
    }

    @Test
    public void TestJsonGetMana() {
        assertThat(hero.getMana(), is(3));
    }

    @Test
    public void TestJsonGetHealth() {
        assertThat(hero.getHealth(), is(4));
    }

    @Test
    public void TestJsonIsActive() {
        assertThat(hero.isActive(), is(true));
    }

    @Test
    public void TestJsonGetType() {
        assertThat(hero.getType(), is("bruh"));
    }

    @Test
    public void TestJsonGetOwner() {
        assertThat(hero.getOwner(), is(Player.FINDUS));
    }


}
