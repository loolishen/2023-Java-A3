package game.enemy;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.weapon.CrabSlam;

/**
 * Crab Rave!
 *
 * Created by: Adrian Kristanto
 * Modified by: Lee Sing Yuan
 */
public abstract class Crab extends Enemy {

    /**
     * The constructor for Crab parent
     * @param initName name
     * @param initDisplay UI char
     * @param initHp hit points
     */
    public Crab(String initName, char initDisplay, int initHp) {
        super(initName,initDisplay,initHp);
        this.addCapability(ActorTypes.CRAB);
    }
}