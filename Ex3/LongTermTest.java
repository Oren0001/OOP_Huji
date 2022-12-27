import oop.ex3.spaceship.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Tests the different methods of the LongTermStorage's API.
 * @author Oren Motiei
 */
public class LongTermTest {

    private static final int LTS_CAPACITY = 1000;
    private LongTermStorage lts;
    private static Item baseballBat, helmetSize1, helmetSize3, sporesEngine;

    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        baseballBat = ItemFactory.createSingleItem("baseball bat");
        helmetSize1 = ItemFactory.createSingleItem("helmet, size 1");
        helmetSize3 = ItemFactory.createSingleItem("helmet, size 3");
        sporesEngine = ItemFactory.createSingleItem("spores engine");
    }


    /**
     * Creates objects before each test runs.
     */
    @Before
    public void createObjectsBefore() {
        lts = new LongTermStorage();
    }

    /*----=  The following code snippet will test the method addItem  =-----*/

    /**
     * Tests cases where items cannot be added.
     */
    @Test
    public void testNoItemsAdded() {
        assertEquals(-1, lts.addItem(baseballBat,-10));
        assertEquals(-1,lts.addItem(sporesEngine,101));
        lts.addItem(sporesEngine,99);
        assertEquals(-1,lts.addItem(helmetSize3,3));
    }


    /**
     * Tests if a given item was added successfully to the inventory.
     */
    @Test
    public void testSuccessfulAddition () {
        assertEquals(0,lts.addItem(baseballBat,50));
        assertEquals(0,lts.addItem(helmetSize3,100));
        assertEquals(0,lts.addItem(sporesEngine,40));
    }

    /*----=  End of addItem test  =-----*/


    /**
     * Tests the method resetInventory.
     */
    @Test
    public void testResetInventory() {
        lts.addItem(sporesEngine,100);
        lts.resetInventory();
        assertEquals(0,lts.addItem(baseballBat,5));
        assertEquals(0,lts.addItem(sporesEngine,99));
        lts.resetInventory();
        assertEquals(0,lts.addItem(sporesEngine,100));
    }


    /**
     * Tests the method getItemCount.
     */
    @Test
    public void testGetItemCount() {
        assertEquals(0,lts.getItemCount(baseballBat.getType()));
        lts.addItem(baseballBat,55);
        lts.addItem(sporesEngine,20);
        lts.addItem(helmetSize3, 50);
        assertEquals(55,lts.getItemCount(baseballBat.getType()));
        assertEquals(20,lts.getItemCount(sporesEngine.getType()));
        assertEquals(50,lts.getItemCount(helmetSize3.getType()));
        lts.resetInventory();
        assertEquals(0,lts.getItemCount(helmetSize3.getType()));
        assertEquals(0,lts.getItemCount(helmetSize1.getType()));
    }


    /**
     * Tests the method getInventory.
     */
    @Test
    public void testGetInventory() {
        lts.addItem(sporesEngine,20);
        lts.addItem(baseballBat,100);
        lts.addItem(helmetSize3,100);
        lts.addItem(helmetSize1,33);
        Map<String, Integer> inventory = lts.getInventory();
        assertEquals(4,inventory.size());
        assertEquals(20,(int) inventory.get(sporesEngine.getType()));
        assertEquals(100,(int) inventory.get(baseballBat.getType()));
        assertEquals(100,(int) inventory.get(helmetSize3.getType()));
        assertEquals(33,(int) inventory.get(helmetSize1.getType()));
        lts.resetInventory();
        assertEquals(0,inventory.size());
    }


    /**
     * Tests the method getCapacity.
     */
    @Test
    public void testGetCapacity() {
        lts.addItem(sporesEngine, 30);
        assertEquals(LTS_CAPACITY,lts.getCapacity());
        lts.resetInventory();
        assertEquals(LTS_CAPACITY,lts.getCapacity());
        lts.addItem(baseballBat,200);
        assertEquals(LTS_CAPACITY,lts.getCapacity());
    }


    /**
     * Test the method getAvailableCapacity.
     */
    @Test
    public void testGetAvailableCapacity() {
        lts.addItem(helmetSize1,33);
        assertEquals(901,lts.getAvailableCapacity());
        lts.addItem(baseballBat,250);
        assertEquals(401,lts.getAvailableCapacity());
        lts.resetInventory();
        assertEquals(LTS_CAPACITY,lts.getAvailableCapacity());
        lts.addItem(sporesEngine,100);
        assertEquals(0,lts.getAvailableCapacity());
    }
}
