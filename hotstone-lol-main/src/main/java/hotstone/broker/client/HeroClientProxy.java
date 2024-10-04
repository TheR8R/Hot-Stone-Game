package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Hero;
import hotstone.framework.Player;

public class HeroClientProxy implements Hero, ClientProxy {
    Requestor req;
    String id;
    public HeroClientProxy(Requestor requestor, String id) {
        this.req = requestor;
        this.id = id;
    }

    @Override
    public int getMana() {
        return req.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_MANA, int.class);
    }

    @Override
    public int getHealth() {
        return req.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_HEALTH, int.class);
    }

    @Override
    public boolean isActive() {
        return req.sendRequestAndAwaitReply(id, OperationNames.HERO_IS_ACTIVE, boolean.class);
    }

    @Override
    public String getType() {
        return req.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_TYPE, String.class);
    }

    @Override
    public Player getOwner() {
        return req.sendRequestAndAwaitReply(id, OperationNames.HERO_GET_OWNER, Player.class);
    }

    @Override
    public String getEffectDescription() {
        return "bruh";
    }

    @Override
    public String getId() {
        return id;
    }
}
