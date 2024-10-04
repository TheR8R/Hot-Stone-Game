package hotstone.standard;

import hotstone.Varients.PersonalizedDeckStrat;
import hotstone.Varients.alphaStrat.AlphaFactory;
import hotstone.framework.Game;
import hotstone.framework.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Stack;

public class TestPersonalizedDeck {
    private Stack<StandardCard> deck;
    private PersonalizedDeckStrat podo;

    @BeforeEach
    public void setUp() {
        podo = new PersonalizedDeckStrat();
        deck = podo.initializeDeck(Player.FINDUS);
    }

    @Test
    public void findusHasCowCard() {
        assertThat(deck.stream().filter(c -> c.getName().equals("Cow"))
                                .findAny()
                                .orElse(null)
                                .getName(), is("Cow"));
    }

    @Test
    public void findusHas16Cards(){
        assertThat(deck.size(), is(16));
    }

}
