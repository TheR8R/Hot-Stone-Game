package hotstone.broker.service;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Hero;

public interface NameService {

    /** Put a Card into the name service under given id
     *
     * @param objectId ID of the object
     * @param card the servant object
     */
    void putCard(String objectId, Card card);

    /** Get a Card
     *
     * @param objectId the id of the servant object to get.
     * @return the Card with this id.
     */
    Card getCard(String objectId);

    /** Put a game into the name service under given id
     *
     * @param objectId ID of the object
     * @param hero the servant object
     */
    void putHero(String objectId, Hero hero);

    /** Get a Hero.
     *
     * @param objectId the id of the servant object to get
     * @return the servant object
     */
    Hero getHero(String objectId);
}
