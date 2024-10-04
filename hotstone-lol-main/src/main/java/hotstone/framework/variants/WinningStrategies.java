package hotstone.framework.variants;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;

public interface WinningStrategies {
    /**
     * Accessor method to get the winner of current game.
     *
     * @param game the game in which to calculate the winner
     * @return the player who won, or null if no one has won yet.
     */
    Player calculateWinner(Game game);

    void attackingMinionWinner(Player attackingPlayer, Card attackingCard);
}
