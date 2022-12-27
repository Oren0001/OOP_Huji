import java.lang.Math;

/**
 * Represents a Special ship. In practice, It's a peaceful ship which attacks only when it feels threatened.
 *
 * @author Oren Motiei
 */
public class SpecialShip extends SpaceShip {

    private static final double ANGLE_TO_FIRE = 0.18;
    private static final double DISTANCE_TO_ACTIVATE_SHIELD = 0.19;
    private SpaceShip target = null;
    private int targetInitialDeathCount;
    private SpaceWars game;


    /**
     * Does the actions of this ship for this round - It constantly floats in the space, unless it gets hit
     * (by a shot or a collision) by an opponent. When this happens, it starts pursuing the closest ship,
     * and when it's angle to the closest ship is less than 0.18 radians, it will attempt to fire.
     * If it gets within a distance of 0.19 units from the opponent, it will attempt to activate
     * its shields. In addition, if the SpecialShip collides with the opponent while its shields
     * are activated, it disables the opponent - i.e. it won't be able to move at all for a period
     * of 35 rounds. At last, it stops pursuing the opponent only when it dies.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);
        this.game = game;
        if (target != null && targetInitialDeathCount < target.getDeathCount())
            target = null;
        if (target != null) {
            double angleToClosest = this.shipPhysics.angleTo(target.shipPhysics);
            turnToClosest(angleToClosest);
            if (this.shipPhysics.distanceFrom(target.shipPhysics) < DISTANCE_TO_ACTIVATE_SHIELD)
                shieldOn();
            if (Math.abs(angleToClosest) < ANGLE_TO_FIRE)
                fire(game);
        } else
            shipPhysics.move(false,0);
        regenerateEnergy();
    }


    /**
     * This method is called by the SpaceWars game object when ever this ship gets hit by a shot.
     */
    @Override
    public void gotHit() {
        super.gotHit();
        if (target == null) {
            target = game.getClosestShipTo(this);
            targetInitialDeathCount = target.getDeathCount();
        }
    }


    /**
     * This method is called every time a collision with this ship occurs.
     */
    @Override
    public void collidedWithAnotherShip() {
        super.collidedWithAnotherShip();

        if (getLastRoundShieldOn() == getCurrentRound()) { //freeze the opponent, if the shield is activated
            SpaceShip closestShip = game.getClosestShipTo(this);
            closestShip.disabled = true;
            closestShip.setRoundDisabled(getCurrentRound());
        }
        if (target == null) { // start attacking the opponent
            target = game.getClosestShipTo(this);
            targetInitialDeathCount = target.getDeathCount();
        }
    }

}
