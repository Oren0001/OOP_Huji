import java.lang.Math;

/**
 * Represents an Aggressive ship that pursues other ships and tries to fire at them.
 *
 * @author Oren Motiei
 */
public class AggressiveShip extends SpaceShip {

    private static final double ANGLE_TO_FIRE = 0.21;


    /**
     * Does the actions of this ship for this round - Always accelerates and turns towards the nearest ship.
     * If it's angle to the nearest ship is less that 0.21 radians, it will attempt to fire.
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
            if (Math.abs(angleToClosest) < ANGLE_TO_FIRE)
                fire(game);
            regenerateEnergy();
        }
    }

}
