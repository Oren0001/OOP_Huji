import java.lang.Math;

/**
 * Represents a Runner ship which attempts to run away from the fight.
 *
 * @author Oren Motiei
 */
public class RunnerShip extends SpaceShip {

    private static final double DISTANCE_TO_TELEPORT = 0.25;
    private static final double ANGLE_TO_TELEPORT = 0.23;


    /**
     * Does the actions of this ship for this round - Always accelerates and turns away from the
     * closest ship. If the nearest ship is closer than 0.25 units, and if its angle to the Runner
     * is less than 0.23 radians, the Runner will attempt to teleport.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);
        if (!disabled) {
            SpaceShip closestShip = game.getClosestShipTo(this);
            if (closestShip.shipPhysics.distanceFrom(this.shipPhysics) < DISTANCE_TO_TELEPORT
                    && Math.abs(closestShip.shipPhysics.angleTo(this.shipPhysics)) < ANGLE_TO_TELEPORT)
                teleport();
            runFromClosest(closestShip);
            regenerateEnergy();
        }
    }


    /*
     * Turns away from the closest ship.
     * @param closestShip The closest ship to the Runner.
     */
    private void runFromClosest (SpaceShip closestShip) {
        double angleToClosest = this.shipPhysics.angleTo(closestShip.shipPhysics);
        if (angleToClosest < 0)
            shipPhysics.move(true, 1);
        else if (angleToClosest > 0)
            shipPhysics.move(true, -1);
        else
            shipPhysics.move(true, 0);
    }

}
