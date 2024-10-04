package hotstone.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.Invoker;
import frds.broker.Requestor;
import frds.broker.marshall.json.StandardJSONRequestor;
import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.broker.client.GameClientProxy;
import hotstone.broker.doubles.LocalMethodClientRequestHandler;
import hotstone.broker.doubles.StubGameForBroker;
import hotstone.broker.server.HotStoneGameInvoker;
import hotstone.framework.*;
import hotstone.standard.StandardCard;
import hotstone.standard.StandardHotStoneGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPassByRefGameBroker {
    private Game game;

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
        game = new GameClientProxy(requestor);
    }

    @Test
    public void testJsonGetHero() {
        Hero hero = game.getHero(Player.FINDUS);
        System.out.println(hero);
        assertThat(game.getHero(Player.FINDUS).getType(), is("Baby"));
    }

    @Test
    public void testJsonGetCardInHand(){
        Card card = game.getCardInHand(Player.FINDUS, 0);
        assertThat(card.getName(), is("Tres"));
    }

    @Test
    public void testJsonGetHand(){
        assertThat(((ArrayList)game.getHand(Player.FINDUS)).size(), is(3));
        assertThat(((ArrayList<? extends Card>) game.getHand(Player.FINDUS)).get(0).getName(), is("Tres"));
    }

    @Test
    public void testJSONGetCardInField() {
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS,0));
        Card card = game.getCardInField(Player.FINDUS, 0);
        assertThat(card.getName(), is("Tres"));
    }
    @Test
    public void testJSONGetField() {
        game.playCard(Player.FINDUS, game.getCardInHand(Player.FINDUS, 0));
        assertThat((game.getFieldSize(Player.FINDUS)), is(1));
        assertThat(((ArrayList<? extends Card>) game.getField(Player.FINDUS)).get(0).getName(), is("Tres"));
    }

    @Test
    public void TestJsonAttackCardWithObjectReference() {
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card);
        card = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
        Card card2 = game.getCardInHand(Player.PEDDERSEN, 0);
        game.endTurn();
        assertThat(game.attackCard(Player.FINDUS, card, card2), is(Status.OK));
    }

    @Test
    public void TestJsonAttackHeroWithObjectReference() {
        Card card = game.getCardInHand(Player.FINDUS, 0);
        game.playCard(Player.FINDUS, card);
        card = game.getCardInField(Player.FINDUS, 0);
        game.endTurn();
        game.endTurn();
        assertThat(game.attackHero(Player.FINDUS, card), is(Status.OK));
    }
}
