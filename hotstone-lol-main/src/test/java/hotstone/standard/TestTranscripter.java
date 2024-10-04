package hotstone.standard;

import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.framework.MutableGame;
import hotstone.framework.Player;
import hotstone.framework.Transcript;
import hotstone.framework.variants.IndexingStrategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestTranscripter {

    private MutableGame game;
    private Transcript transcript;
    private MutableGame origin;

    @BeforeEach
    public void setUp() {
        this.game = new StandardHotStoneGame(new AlphaFactory());
        this.origin = game;
        this.transcript = new Transcript(game);
        game = transcript;
    }

    @Test
    public void transOfGetCurrentPlayerWorks() {
        game.getPlayerInTurn();
    }

    @Test
    public void transOfEndTurnWorks() {
        game.endTurn();
    }

    @Test
    public void transOnAndOff(){
        game = transcript;
        game.endTurn();
        game = origin;
        game.endTurn();
        assertThat(game.getPlayerInTurn(), is(Player.FINDUS));
        game = transcript;
        game.endTurn();
    }

}
