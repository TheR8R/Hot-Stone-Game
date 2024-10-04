package hotstone.broker.doubles;

import frds.broker.Servant;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class StubHeroForBroker implements Hero, Servant {

    @Override
    public int getMana() {
        return 3;
    }

    @Override
    public int getHealth() {
        return 4;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public String getType() {
        return "bruh";
    }

    @Override
    public Player getOwner() {
        return Player.FINDUS;
    }

    @Override
    public String getEffectDescription() {
        return "Effect";
    }

    @Override
    public String getId() {
        return "oivneroignewroi";

    }
}
