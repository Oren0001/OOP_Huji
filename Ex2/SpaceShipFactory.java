/**
 * Creates all the spaceship objects according to the command line arguments.
 *
 * @author Oren Motiei
 */
public class SpaceShipFactory {

    private static final String HUMAN_SHIP = "h";
    private static final String RUNNER_SHIP = "r";
    private static final String BASHER_SHIP = "b";
    private static final String AGGRESSIVE_SHIP = "a";
    private static final String DRUNKARD_SHIP = "d";
    private static final String SPECIAL_SHIP = "s";


    /**
     * Creates the spaceships for the game.
     * @param args the command line arguments.
     * @return An array of spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] ships = new SpaceShip[args.length];
        for (int i=0; i<args.length; i++) {
            if (args[i].equals(HUMAN_SHIP)) // I preferred using switch, but it is available from level 7
                ships[i] = new HumanShip();
            else if (args[i].equals(AGGRESSIVE_SHIP))
                ships[i] = new AggressiveShip();
            else if (args[i].equals(RUNNER_SHIP))
                ships[i] = new RunnerShip();
            else if (args[i].equals(BASHER_SHIP))
                ships[i] = new BasherShip();
            else if (args[i].equals(DRUNKARD_SHIP))
                ships[i] = new DrunkardShip();
            else if (args[i].equals(SPECIAL_SHIP))
                ships[i] = new SpecialShip();
        }
        return ships;
    }

}
