import oop.ex3.spaceship.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a spaceship which has a name, crew members, and storage lockers.
 * @author Oren Motiei
 */
public class Spaceship {

    private final String name;
    private final int[] crewIDs;
    private int numOfLockers;
    private int availableLockers;
    private Item[][] constraints;
    private final Locker[] lockers;
    final Map<Integer, ArrayList<Locker>> idToLockers = new HashMap<Integer, ArrayList<Locker>>();
    private final LongTermStorage lts = new LongTermStorage();


    /**
     * Initializes a new spaceship.
     * @param name The name of the spaceship.
     * @param crewIDs An array of integers that represent crew members IDs.
     * @param numOfLockers A non-negative integer that represents the allowed number of lockers on the ship.
     * @param constraints A two dimensional array. Each array consists a pair of items that are not
     *                    allowed to reside together in the same locker.
     */
    public Spaceship (String name, int[] crewIDs, int numOfLockers, Item[][] constraints) {
        this.name = name;
        this.crewIDs = crewIDs;
        this.numOfLockers = numOfLockers;
        this.constraints = constraints;
        lockers = new Locker[numOfLockers];
        availableLockers = numOfLockers;
    }


    /**
     * @return the long-term storage of this ship.
     */
    public LongTermStorage getLongTermStorage() {
        return lts;
    }


    /**
     * Creates a new Locker object if all the conditions are met.
     * @param crewID The crew member's ID which will possess the new locker.
     * @param capacity The capacity of the new locker.
     * @return  0 if the locker was created successfully.
     *         -1 if the ID is invalid.
     *         -2 if the given capacity does not meet the Locker class requirements.
     *         -3 if the spaceship already contains the allowed number of lockers.
     */
    public int createLocker(int crewID, int capacity) {
        found: {
            for (int id: crewIDs) {
                if (crewID == id)
                    break found;
            }
            return -1;
        }
        if (capacity < 0)
            return -2;
        if (availableLockers == 0)
            return -3;
        addLocker(crewID,capacity);
        return 0;
    }


    /*
     * Adds a new locker to the spaceship's storage which is associated with a crew member with the given id
     * @param crewID The crew member's ID which will possess the new locker.
     * @param capacity The capacity of the new locker.
     */
    private void addLocker(int crewID, int capacity) {
        Locker locker = new Locker(lts, capacity, constraints);
        if (idToLockers.containsKey(crewID))
            idToLockers.get(crewID).add(locker);
        else {
            ArrayList<Locker> array = new ArrayList<Locker>();
            array.add(locker);
            idToLockers.put(crewID, array);
        }
        for (int i=0; i<numOfLockers; i++) {
            if (lockers[i] == null)
                lockers[i] = locker;
        }
        availableLockers -= 1;
    }


    /**
     * @return an array with the crew's ids.
     */
    public int[] getCrewIDs() {
        return crewIDs;
    }


    /**
     * @return an array of the lockers.
     */
    public Locker[] getLockers() {
        return lockers;
    }

}
