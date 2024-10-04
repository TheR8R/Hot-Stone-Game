package hotstone.standard;

import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.Varients.epsilonStrat.EpsilonFactory;
import hotstone.Varients.etaStrat.EtaFactory;
import hotstone.Varients.gammaStrat.GammaFactory;
import hotstone.doubles.GameObserverSpy;
import hotstone.framework.Card;
import hotstone.framework.Player;
import hotstone.utility.FixedIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestGameObserver {

    StandardHotStoneGame game;
    private GameObserverSpy observer;
    private Card dosF;
    private Card unoF;
    private Card tresP;
    private Card dosP;
    private Card unoP;
    private List<String> methodCalls;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaFactory());
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        dosF = game.getCardInHand(Player.FINDUS, 1);
        unoF = game.getCardInHand(Player.FINDUS, 2);
        tresP = game.getCardInHand(Player.PEDDERSEN, 0);
        dosP = game.getCardInHand(Player.PEDDERSEN, 1);
        unoP = game.getCardInHand(Player.PEDDERSEN, 2);
        //game = new StandardHotStoneGame(new DeltaManaStrat(), new AlphaWinnerStrat(), new AlphaHeroStrat(), new DeltaDeckStrat(), new AlphaEffectStrat());
    }

    @Test
    public void endTurnIsHandledCorrectly() {
        game.endTurn();
        assertThat(observer.getMethodCalls().get(0), is("onCardDraw"));
        assertThat(observer.getlastMethodCall(), is("onTurnChangeTo"));
    }

    @Test
    public void playCardIsHandledCorrectly() {
        game.playCard(Player.FINDUS, dosF);
        assertThat(observer.getMethodCalls().get(0), is("onCardPlay"));
        assertThat(observer.getMethodCalls().get(1), is("onHeroUpdate"));
    }

    @Test
    public void attackCardIsHandledCorrectly() {

        game.playCard(Player.FINDUS, dosF);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.endTurn();
        game.attackCard(Player.FINDUS,dosF,unoP);
        assertThat(methodCalls.get(methodCalls.size()-4), is("onAttackCard"));
        assertThat(methodCalls.get(methodCalls.size()-3), is("onCardUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onCardRemove"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardUpdate"));
    }

    @Test
    public void attackCardIsHandledCorrectly2() {

        game.playCard(Player.FINDUS, unoF);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, dosP);
        game.endTurn();
        game.attackCard(Player.FINDUS,unoF,dosP);
        assertThat(methodCalls.get(methodCalls.size()-4), is("onAttackCard"));
        assertThat(methodCalls.get(methodCalls.size()-3), is("onCardRemove"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onCardUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardUpdate"));
    }

    @Test
    public void attackHeroIsHandledCorrectly(){

        game.playCard(Player.FINDUS, unoF);
        game.endTurn();
        game.endTurn();
        game.attackHero(Player.FINDUS, unoF);
        assertThat(methodCalls.get(methodCalls.size()-3), is("onAttackHero"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardUpdate"));

    }

    @Test
    public void cardEffectsIsHandledCorrectlyTomatoSalad(){
        game = new StandardHotStoneGame(new EtaFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        StandardCard tomatoSaladF = new StandardCard(GameConstants.TOMATO_SALAD_CARD, Player.FINDUS,2,2,2, null);
        game.playCard(Player.FINDUS, unoF);
        game.playCard(Player.FINDUS, tomatoSaladF);
        assertThat(methodCalls.get(methodCalls.size()-3), is("onCardPlay"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardUpdate"));

    }
    
    @Test
    public void cardEffectsIsHandledCorrectlyNoodleSoup() {
        game = new StandardHotStoneGame(new EtaFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        StandardCard noodleSoupF = new StandardCard(GameConstants.NOODLE_SOUP_CARD, Player.FINDUS, 4, 5, 3, null);
        game.playCard(Player.FINDUS, unoF);
        game.playCard(Player.FINDUS, noodleSoupF);
        assertThat(methodCalls.get(methodCalls.size()-3), is("onCardPlay"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardDraw"));
    }

    @Test
    public void cardEffectsIsHandledCorrectlyChickenCurry() {
        game = new StandardHotStoneGame(new EtaFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        StandardCard chickenCurryP = new StandardCard(GameConstants.CHICKEN_CURRY_CARD, Player.PEDDERSEN, 4, 5, 3, null);
        game.playCard(Player.FINDUS, unoF);
        game.endTurn();
        game.playCard(Player.PEDDERSEN, chickenCurryP);
        assertThat(methodCalls.get(methodCalls.size()-3), is("onCardPlay"));
        assertThat(methodCalls.get(methodCalls.size()-2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size()-1), is("onCardRemove"));
    }

    @Test
    public void usePowerEffectIsHandledCorrectlyThaiChef() {
        game = new StandardHotStoneGame(new GammaFactory());
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        game.usePower(Player.FINDUS);
        System.out.println(methodCalls);
        assertThat(methodCalls.get(methodCalls.size() - 3), is("onUsePower"));
        assertThat(methodCalls.get(methodCalls.size() - 2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size() - 1), is("onHeroUpdate"));

    }

    @Test
    public void usePowerEffectIsHandledCorrectlyDanishChef() {
        game = new StandardHotStoneGame(new GammaFactory());
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        game.endTurn();
        game.usePower(Player.PEDDERSEN);
        assertThat(methodCalls.get(methodCalls.size() - 4), is("onUsePower"));
        assertThat(methodCalls.get(methodCalls.size() - 3), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size() - 2), is("onCardPlay"));
        assertThat(methodCalls.get(methodCalls.size() -1), is("onHeroUpdate"));
    }

    @Test
    public void usePowerEffectIsHandledCorrectlyFrenchChef() {
        game = new StandardHotStoneGame(new EpsilonFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        game.endTurn();
        game.playCard(Player.PEDDERSEN, dosP);
        game.endTurn();
        game.usePower(Player.FINDUS);
        assertThat(methodCalls.get(methodCalls.size() - 3), is("onUsePower"));
        assertThat(methodCalls.get(methodCalls.size() - 2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size() - 1), is("onCardRemove"));
    }

    @Test
    public void usePowerEffectIsHandledCorrectlyFrenchChef2() {
        game = new StandardHotStoneGame(new EpsilonFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        game.endTurn();
        game.playCard(Player.PEDDERSEN, tresP);
        game.endTurn();
        game.usePower(Player.FINDUS);
        assertThat(methodCalls.get(methodCalls.size() - 3), is("onUsePower"));
        assertThat(methodCalls.get(methodCalls.size() - 2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size() - 1), is("onCardUpdate"));
    }

    @Test
    public void usePowerEffectIsHandledCorrectlyItalianChef() {
        game = new StandardHotStoneGame(new EpsilonFactory(new FixedIndex()));
        observer = new GameObserverSpy();
        game.addObserver(observer);
        methodCalls = observer.getMethodCalls();

        game.endTurn();
        game.playCard(Player.PEDDERSEN, unoP);
        game.usePower(Player.PEDDERSEN);
        assertThat(methodCalls.get(methodCalls.size() - 3), is("onUsePower"));
        assertThat(methodCalls.get(methodCalls.size() - 2), is("onHeroUpdate"));
        assertThat(methodCalls.get(methodCalls.size() - 1), is("onCardUpdate"));
    }
}
