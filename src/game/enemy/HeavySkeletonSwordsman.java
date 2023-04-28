package game.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.behaviour.AttackBehaviour;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;
import game.weapon.Grossmesser;

import java.util.HashMap;
import java.util.Map;

/**
 *  Spooky, spooky skeleton
 *  Created by: Loo Li Shen
 *  Modified by: Lee Sing Yuan
 */
public class HeavySkeletonSwordsman extends Enemy{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int counter;


    public HeavySkeletonSwordsman() {
        super("Heavy Skeleton Swordsman", 'q', 153);
        behaviours.put(FollowBehaviour.behaviorCode(), new FollowBehaviour());
        behaviours.put(AttackBehaviour.behaviorCode(), new AttackBehaviour());
        behaviours.put(WanderBehaviour.behaviorCode(), new WanderBehaviour());
        this.addCapability(PileOfBones.PILE_OF_BONES);
        this.addCapability(Status.SKELETON);
        this.addWeaponToInventory(new Grossmesser());
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // this means that, the HSS has used its skill of pile of bones
        // so it's removed to allow checking of is it dead or should he become a pile of bones
        if (!this.hasCapability(PileOfBones.PILE_OF_BONES)) {

            // change the display and start the counter
            this.setDisplayChar('o');
            counter++;

            // if the HSS has been un - attacked for 3 rounds, revive
            if (counter == 3) {

                // give the HSS his skill back so that can be used again if he died again
                this.addCapability(PileOfBones.PILE_OF_BONES);

                // reset his display
                this.setDisplayChar('q');

                //resetting his HP
                this.resetMaxHp(getMaxHp());

                // reset the counter so can be used again
                counter = 0;

            }

        }

        // if the HSS has the skill, means he hasnt died and should perform one of the following actions
        else {

            // follow has the highest precedence
            // checks if HSS has this behaviour
            if(behaviours.containsKey(FollowBehaviour.behaviorCode())){
                Action action = behaviours.get(FollowBehaviour.behaviorCode()).getAction(this, map);

                // if the behaviour exist but cant do anything like follow anyone or player
                // it will return null so that can execute other behaviors
                if (action != null) {
                    return action;
                }
            }

            // attack has the second highest precedence
            // check if HSS if it has this behavior
            if (behaviours.containsKey(AttackBehaviour.behaviorCode())) {

                // if it has this behaviour, get the action for this behavior
                Action action = behaviours.get(AttackBehaviour.behaviorCode()).getAction(this, map);

                // if the behaviour exist but cant do anything like attack anyone,
                // it will return null so that can execute other behaviors
                if (action != null) {
                    return action;
                }
            }

            // wander has the lowest precedence
            // check if HSS if it has this behavior
            if (behaviours.containsKey(WanderBehaviour.behaviorCode())) {

                // get the action for this behavior
                Action action = behaviours.get(WanderBehaviour.behaviorCode()).getAction(this, map);

                if (action != null) {
                    return action;
                }
            }
        }
        return new DoNothingAction();
    }

}

