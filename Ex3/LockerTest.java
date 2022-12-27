import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Tests the different methods of the Locker's API.
 * @author Oren Motiei
 */
public class LockerTest {

    private Locker locker1, locker2, locker3, locker4;
    private static Item baseballBat, helmetSize1, helmetSize3, sporesEngine, football;
    private static Item[][] constraints;

    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        baseballBat = ItemFactory.createSingleItem("baseball bat");
        helmetSize1 = ItemFactory.createSingleItem("helmet, size 1");
        helmetSize3 = ItemFactory.createSingleItem("helmet, size 3");
        sporesEngine = ItemFactory.createSingleItem("spores engine");
        football = ItemFactory.createSingleItem("football");
        constraints = ItemFactory.getConstraintPairs();
    }


    /**
     * Creates objects before each test runs.
     */
    @Before
    public void createObjectsBefore() {
        locker1 = new Locker(new LongTermStorage(),20, constraints);
        locker2 = new Locker(new LongTermStorage(),0, constraints);
        locker3 = new Locker(new LongTermStorage(),50,constraints);
        locker4 = new Locker(new LongTermStorage(), 2200, constraints);

    }

    /**
     * Tests cases where items cannot be added.
     */
    @Test
    public void testNoItemsAdded() {
        assertEquals(-1,locker1.addItem(baseballBat, -3));
        assertEquals(-1,locker1.addItem(sporesEngine,3));
        assertEquals(-1,locker2.addItem(baseballBat,1));
        assertEquals(-1,locker2.addItem(baseballBat,-100));
    }


    /*----=  The following code snippet will test the method addItem  =-----*/

    /**
     * Tests if a given item was added successfully to a locker,
     * and no items were moved to long-term storage.
     */
    @Test
    public void testSuccessfulAddition() {
        assertEquals(0,locker1.addItem(baseballBat,2));
        assertEquals(0,locker1.addItem(helmetSize1,2));
        assertEquals(0,locker1.addItem(sporesEngine,1)); // exactly 50% of storage
    }


    /**
     * Tests if items were moved successfully to the long-term storage.
     */
    @Test
    public void testMovedToLTS() {
        assertEquals(1,locker3.addItem(sporesEngine,3));
        assertEquals(1,locker3.addItem(baseballBat,13));
    }


    /**
     * Tests if an attempt to move items to the long-term storage failed,
     * because there wasn't enough capacity.
     */
    @Test
    public void testFailedAddingLTS () {
        locker4.addItem(sporesEngine,142);
        assertEquals(-1, locker4.addItem(helmetSize3,260));
    }


    /**
     * Tests cases where a constraint is violated, and therefore the item cannot be added to the locker.
     */
    @Test
    public void testConstraintViolated() {
        locker3.addItem(baseballBat, 10);
        assertEquals(-2, locker3.addItem(football, 1));
        locker1.addItem(football,1);
        assertEquals(-2, locker1.addItem(baseballBat, 1));
    }

    /*----=  End of addItem test  =-----*/
    /*----=  The following code snippet will test the method removeItem  =-----*/

    /**
     * Tests if items were removed successfully from the locker.
     */
    @Test
    public void testSuccessfulRemove() {
        locker1.addItem(baseballBat, 1);
        locker1.addItem(helmetSize1,1);
        locker1.addItem(helmetSize3,2);
        assertEquals(0,locker1.removeItem(baseballBat,1));
        assertEquals(0,locker1.removeItem(helmetSize1,1));
        assertEquals(0,locker1.removeItem(helmetSize3,2));
        locker4.addItem(sporesEngine,44);
        assertEquals(0,locker4.removeItem(sporesEngine,4));
        assertEquals(0,locker4.removeItem(sporesEngine,5));
        assertEquals(0,locker4.removeItem(sporesEngine,2));
        assertEquals(0,locker4.removeItem(sporesEngine,3));
        assertEquals(0,locker4.removeItem(sporesEngine,30));
    }


    /**
     * Tests cases where no items should be removed.
     */
    @Test
    public void testNoItemsRemoved () {
        assertEquals(-1,locker1.removeItem(baseballBat, -5));
        locker3.addItem(baseballBat,5);
        locker3.addItem(sporesEngine,4);
        assertEquals(-1,locker3.removeItem(baseballBat, 6));
        assertEquals(-1,locker3.removeItem(sporesEngine, 200));
        assertEquals(-1,locker3.removeItem(helmetSize1, 1));
        assertEquals(-1,locker2.removeItem(baseballBat, 1));
    }

    /*----=  End of removeItem test  =-----*/


    /**
     * Tests the method getItemCount.
     */
    @Test
    public void testGetItemCount () {
        locker4.addItem(sporesEngine,142);
        assertEquals(44,locker4.getItemCount("spores engine"));
        locker4.addItem(helmetSize3,260);
        assertEquals(0,locker4.getItemCount("helmet, size 3"));
        locker4.addItem(baseballBat, 550);
        assertEquals(550,locker4.getItemCount("baseball bat"));
        assertEquals(0,locker2.getItemCount("helmet, size 3"));
    }


    /**
     * Tests the method getInventory.
     */
    @Test
    public void testGetInventory() {
        locker3.addItem(baseballBat, 5);
        locker3.addItem(helmetSize3, 2);
        locker3.addItem(sporesEngine,2);
        locker3.addItem(helmetSize1,3);
        Map<String, Integer> inventory3 = locker3.getInventory();
        assertEquals(4, inventory3.size());
        assertEquals(5, (int) inventory3.get(baseballBat.getType()));
        assertEquals(2, (int) inventory3.get(helmetSize3.getType()));
        assertEquals(2, (int) inventory3.get(sporesEngine.getType()));
        assertEquals(3, (int) inventory3.get(helmetSize1.getType()));
        Map<String, Integer> inventory2 = locker2.getInventory();
        assertEquals(0, inventory2.size());
        locker3.removeItem(baseballBat, 5);
        locker3.removeItem(helmetSize3,2);
        assertEquals(2, inventory3.size());
        locker3.removeItem(sporesEngine, 2);
        locker3.removeItem(helmetSize1, 3);
        assertEquals(0, inventory3.size());
    }


    /**
     * Tests the method getCapacity.
     */
    @Test
    public void testGetCapacity() {
        locker1.addItem(baseballBat,5);
        locker1.removeItem(baseballBat,3);
        locker3.addItem(baseballBat,5);
        locker3.removeItem(baseballBat,3);
        locker4.addItem(baseballBat,5);
        locker4.removeItem(baseballBat,3);
        assertEquals(20,locker1.getCapacity());
        assertEquals(0,locker2.getCapacity());
        assertEquals(50,locker3.getCapacity());
        assertEquals(2200, locker4.getCapacity());
    }


    /**
     * Tests the method getAvailableCapacity.
     */
    @Test
    public void testGetAvailableCapacity() {
        locker4.addItem(sporesEngine,142);
        assertEquals(1760,locker4.getAvailableCapacity());
        locker4.addItem(helmetSize3, 200);
        assertEquals(760,locker4.getAvailableCapacity());
        locker4.addItem(baseballBat,380);
        assertEquals(0,locker4.getAvailableCapacity());
        locker4.removeItem(sporesEngine,34);
        assertEquals(340,locker4.getAvailableCapacity());
        locker4.removeItem(baseballBat,380);
        assertEquals(1100,locker4.getAvailableCapacity());
        locker4.removeItem(sporesEngine,10);
        assertEquals(1200,locker4.getAvailableCapacity());
        locker4.removeItem(helmetSize3, 200);
        assertEquals(2200,locker4.getAvailableCapacity());
    }
}