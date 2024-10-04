package hotstone.framework;

public interface MutableCard extends Card{

   void setActive(boolean active);

   void changeAttack(int amount);

   void changeHealth(int damage);

}
