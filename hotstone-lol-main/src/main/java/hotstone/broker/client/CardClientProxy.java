package hotstone.broker.client;

import frds.broker.ClientProxy;
import frds.broker.Requestor;
import hotstone.broker.common.OperationNames;
import hotstone.framework.Card;
import hotstone.framework.Player;

public class CardClientProxy implements Card, ClientProxy {
    Requestor req;
    String id;
    public CardClientProxy(Requestor requestor, String id) {
        this.req = requestor;
        this.id = id;
    }

    @Override
    public String getName() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_GET_NAME, String.class);
    }

    @Override
    public int getManaCost() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_GET_MANA_COST, int.class);
    }

    @Override
    public int getAttack() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_GET_ATTACK, int.class);
    }

    @Override
    public int getHealth() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_GET_HEALTH, int.class);
    }

    @Override
    public boolean isActive() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_IS_ACTIVE, boolean.class);
    }

    @Override
    public String getEffectDescription() {
        return "bruh";
    }

    @Override
    public Player getOwner() {
        return req.sendRequestAndAwaitReply(id, OperationNames.CARD_GET_OWNER, Player.class);
    }

    @Override
    public String getId() {
        return id;
    }
}
