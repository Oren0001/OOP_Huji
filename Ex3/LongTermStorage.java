import oop.ex3.spaceship.*;
import java.util.*;

/**
 * Represents a long-term storage of a spaceship.
 * @author Oren Motiei
 */
public class LongTermStorage extends Storage {

    private static final int CAPACITY = 1000;

    public LongTermStorage() {
        availableCapacity = CAPACITY;
    }


    /**
     * Adds n items of the given type to the long-term storage.
     * @param item The item to add to the long-term storage.
     * @param n The number of items of the given type to be added.
     * @return 0 if the addition is successful.
     *        -1 if n items cannot be added at this time.
     */
    public int addItem(Item item, int n) {
        String type = item.getType();
        int requiredVolume = n * item.getVolume();
        if (requiredVolume <= availableCapacity && n > 0) {
            if (inventory.containsKey(type))
                inventory.put(type, inventory.get(type) + n);
            else
                inventory.put(type, n);
            availableCapacity -= requiredVolume;
            return 0;
        } else {
            System.out.println("Error: Your request cannot be completed at this time. " +
                    "Problem: no room for " + n + " items of type " + type);
            return -1;
        }
    }


    /**
     * Resets the long-term storage's inventory.
     */
    public void resetInventory() {
        availableCapacity = CAPACITY;
        inventory.clear();
    }


    /**
     * @return the long-term storage's total capacity.
     */
    public int getCapacity() {
        return CAPACITY;
    }

}
