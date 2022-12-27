/**
 * Represents a Drunkard ship.
 * 
 * @author Oren Motiei
 */
public class DrunkardShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round - constantly accelerates, spins and emits bullets.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);
        if (!disabled) {
            shipPhysics.move(true, -1);
            fire(game);
            regenerateEnergy();
        }
    }
}
