package game.weapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A weapon used by skeleton
 * It deals 185 damage with 88% hit rate
 * Buying and selling are from the player's POV
 * Created by: Lee Sing Yuan
 * @author Lee Sing Yuan
 * Modified by:
 *
 */
public class Scimitar extends WeaponItem implements Purchasable,Sellable{
    private int buyingPrice;
    private int sellingPrice;

    /**
     * Constructor
     */
    public Scimitar() {

        super("Scimitar", 'S', 185, "slashes", 88);
        addCapability(WeaponSkill.AREA_ATTACK);
        buyingPrice = 600;
        sellingPrice = 100;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public int getSellingPrice() {
        return sellingPrice;
    }


    @Override
    public int getPurchasePrice() {
        return buyingPrice;
    }
}