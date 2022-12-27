import oop.ex3.spaceship.*;
import java.util.*;

/**
 * Represents a storage which can hold items, and provide information regarding it's condition,
 * e.g. a storage capacity.
 */
public abstract class Storage {

    protected int availableCapacity;
    protected final Map<String, Integer> inventory = new HashMap<String, Integer>();

    /**
     * Adds n items of the given type to the storage.
     * @param item The item to add to the storage.
     * @param n The number of items of the given type to be added.
     * @return an int value which indicates if the operation succeeded or not.
     */
    public abstract int addItem(Item item, int n);


    /**
     * @return storage's total capacity.
     */
    public abstract int getCapacity();


    /**
     * @param type A type of an item.
     * @return the number of items of the given type that the storage contains.
     */
    public int getItemCount(String type) {
        if (inventory.containsKey(type))
            return inventory.get(type);
        return 0;
    }


    /**
     * @return a map of all the item types contained in the storage, and their respective quantities.
     */
    public Map<String, Integer> getInventory() {
        return inventory;
    }


    /**
     * @return storage's available capacity.
     */
    public int getAvailableCapacity() {
        return availableCapacity;
    }

}
