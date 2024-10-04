package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class StubCardForBroker implements Card, Servant {

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public int getManaCost() {
        return 10;
    }

    @Override
    public int getAttack() {
        return 11;
    }

    @Override
    public int getHealth() {
        return 12;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public String getEffectDescription() {
        return "effect of card";
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }

    @Override
    public String getId() {
        return null;
    }
}
