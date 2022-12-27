/**
 * Represents a Basher ship which attempts to collide with other ships.
 *
 * @author Oren Motiei
 */
public class BasherShip extends SpaceShip {

    private static final double DISTANCE_TO_ACTIVATE_SHIELD = 0.19;


    /**
     * Does the actions of this ship for this round - Always accelerates and turns towards the closest ship.
     * If it gets within a distance of 0.19 units (inclusive) from another ship,
     * it will attempt to activate it's shields.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);
        if (!disabled) {
            SpaceShip closestShip = game.getClosestShipTo(this);
            double angleToClosest = this.shipPhysics.angleTo(closestShip.shipPhysics);
            turnToClosest(angleToClosest);
            if (this.shipPhysics.distanceFrom(closestShip.shipPhysics) <= DISTANCE_TO_ACTIVATE_SHIELD)
                shieldOn();
            regenerateEnergy();
        }
    }

}
