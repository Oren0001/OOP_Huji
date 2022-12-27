import oop.ex2.*;

/**
 * Represents a human controlled ship.
 *
 * @author Oren Motiei
 */
public class HumanShip extends SpaceShip {

    /**
     * Does the actions of this ship for this round - Accelerate and turn, 'd' to fire a shot,
     * 's' to turn on the shield and 'a' to teleport.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    @Override
    public void doAction(SpaceWars game) {
        super.doAction(game);
        setImage(GameGUI.SPACESHIP_IMAGE);
        if (!disabled) {
            if (game.getGUI().isTeleportPressed())
                teleport();
            moveShip(game);
            if (game.getGUI().isShieldsPressed())
                shieldOn();
            if (game.getGUI().isShotPressed())
                fire(game);
            regenerateEnergy();
        }
    }


    /*
     * Moves the ship in accordance with the user input - left-arrow and right-arrow to turn
     * and up-arrow to accelerate.
     * @param game The game object to which this ship belongs.
     */
    private void moveShip(SpaceWars game) {
        if (game.getGUI().isUpPressed() && game.getGUI().isLeftPressed())
            shipPhysics.move(true, 1);
        else if (game.getGUI().isUpPressed() && game.getGUI().isRightPressed())
            shipPhysics.move(true,-1);
        else if (game.getGUI().isUpPressed() && !game.getGUI().isLeftPressed() &&
                !game.getGUI().isRightPressed())
            shipPhysics.move(true, 0);
        else if (!game.getGUI().isUpPressed() && game.getGUI().isLeftPressed() &&
                !game.getGUI().isRightPressed())
            shipPhysics.move(false,1);
        else if (!game.getGUI().isUpPressed() && !game.getGUI().isLeftPressed() &&
                game.getGUI().isRightPressed())
            shipPhysics.move(false,-1);
        else
            shipPhysics.move(false,0);
    }

}
