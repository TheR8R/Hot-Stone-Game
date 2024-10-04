/*
 * Copyright (C) 2022. Henrik Bærbak Christensen, Aarhus University.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package hotstone.figuretestcase.doubles;

import hotstone.framework.Hero;
import hotstone.framework.MutableHero;
import hotstone.framework.Player;
import hotstone.standard.GameConstants;
import hotstone.standard.StandardCard;



import java.util.ArrayList;
import java.util.Stack;

/** A Test Double Stub implementation of Hero to facilitateUI testing
 *  and development.
 *
 *  Note that there is a bit of 'fake object' behaviour as mana
 *  and active status can be modified.
 */
public class StubHero implements Hero, MutableHero {
  private int mana = 1;
  private boolean active = false;
  private Stack<StandardCard> heroDeck;
  private ArrayList<StandardCard> hand;

  @Override
  public int getMana() {
    return mana;
  }

  @Override
  public int getHealth() {
    return 7;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public String getType() {
    return GameConstants.BABY_HERO_TYPE;
  }

  @Override
  public Player getOwner() {
    return Player.FINDUS;
  }

  @Override
  public String getEffectDescription() {
    return "Din mor";
  }

  public void deltaMana(int delta) {
    mana += delta;
  }

  @Override
  public void setMana(int mana) {

  }

  @Override
  public void reduceMana(int amount) {

  }

  @Override
  public void reduceHealth(int amount) {

  }

  public void setActive(boolean newValue) {
    active = newValue;
  }

  @Override
  public void drawFromDeck(int amount) {
    if(heroDeck.isEmpty()) {
      reduceHealth(GameConstants.HERO_HEALTH_PENALTY_ON_EMPTY_DECK);
    } else {
      for(int i = 0; i < amount; i++) {
        StandardCard cardDrawn = this.heroDeck.pop();
        hand.add(0, cardDrawn);
      }
    }
  }

  @Override
  public void setType(String type) {

  }

  @Override
  public void setDeck(Stack<StandardCard> deck) {

  }

  @Override
  public void setOwner(Player who) {

  }

  @Override
  public Stack<StandardCard> getDeck() {
    return null;
  }

  @Override
  public ArrayList<StandardCard> getHeroHand() {
    return null;
  }

  @Override
  public void intializeHand(Player who) {

  }

  @Override
  public String getId() {
    return null;
  }
}
