import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests the different methods of the Spaceship's API.
 * @author Oren Motiei
 */
public class SpaceshipTest {

    private static final int LTS_CAPACITY = 1000;
    private Spaceship ship1, ship2, ship3;
    private static int[] ship1CrewIDs, ship2CrewIDs, ship3CrewIDs;
    private static Item[][] constraints;
    private static Item sporesEngine;

    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        constraints = ItemFactory.getConstraintPairs();
        ship1CrewIDs = new int[]{1, 2, 3, 4, 5};
        ship2CrewIDs = new int[]{6,7,8,9};
        ship3CrewIDs = new int[]{0, -1, -2, -3,-4};
        sporesEngine = ItemFactory.createSingleItem("spores engine");
    }


    /**
     * Creates objects before each test runs.
     */
    @Before
    public void createObjectsBefore() {
        ship1 = new Spaceship("finalizer", ship1CrewIDs,5, constraints);
        ship2 = new Spaceship("republic cruiser", ship2CrewIDs,0, constraints);
        ship3 = new Spaceship("dreadnought", ship3CrewIDs,45, constraints);
    }


    /**
     * Tests the method getLongTermStorage.
     */
    @Test
    public void testGetLongTermStorage() {
        assertEquals(LTS_CAPACITY, ship3.getLongTermStorage().getAvailableCapacity());
        ship3.createLocker(-2,50);
        for (Locker locker: ship3.getLockers()){
            if (locker != null) {
                locker.addItem(sporesEngine,4);
                assertEquals(970, ship3.getLongTermStorage().getAvailableCapacity());
                break;
            }
        }
    }

    /*----=  The following code snippet will test the method createLocker  =-----*/

    /**
     * Tests cases where the given id of a crew member is invalid.
     */
    @Test
    public void testInvalidID() {
        assertEquals(-1,ship1.createLocker(0,200));
        assertEquals(-1,ship2.createLocker(-5,5));
        assertEquals(-1,ship3.createLocker(1,0));
    }


    /**
     * Tests cases where the given capacity does not meet the Locker class requirements.
     */
    @Test
    public void testInvalidCapacity() {
        assertEquals(-2,ship1.createLocker(1,-1));
        assertEquals(-2,ship2.createLocker(8,-10));
        assertEquals(-2,ship3.createLocker(-2,-5));
    }


    /**
     * Tests cases where the spaceship already contains the allowed number of lockers.
     */
    @Test
    public void testMaxLockersReached() {
        for (int i=1; i<6; i++)
            ship1.createLocker(i,i);
        assertEquals(-3,ship1.createLocker(3,50));
        assertEquals(-3,ship1.createLocker(1,0));
        assertEquals(-3,ship2.createLocker(9,1));
    }


    /**
     * Tests cases where the locker was created successfully.
     */
    @Test
    public void testCreatedSuccessfully() {
        for (int i=1; i<46; i++)
            assertEquals(0, ship3.createLocker(-(i % 5), i));
    }

    /*----=  End of createLocker test  =-----*/

    /**
     * Tests the method getCrewIDs.
     */
    @Test
    public void testGetCrewIDs() {
        ship1.createLocker(2, 20);
        ship3.createLocker(-2,30);
        assertEquals(ship1CrewIDs, ship1.getCrewIDs());
        assertEquals(ship2CrewIDs, ship2.getCrewIDs());
        assertEquals(ship3CrewIDs, ship3.getCrewIDs());
    }


    /**
     * Tests the method getLockers.
     */
    @Test
    public void testGetLockers() {
        assertEquals(45,ship3.getLockers().length);
        ship3.createLocker(-4,3333);
        for (Locker locker: ship3.getLockers()) {
            if (locker != null) {
                assertEquals(3333, locker.getCapacity());
                break;
            }
        }
        assertEquals(0,ship2.getLockers().length);
    }
}
