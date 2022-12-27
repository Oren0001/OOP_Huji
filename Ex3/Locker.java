import oop.ex3.spaceship.*;
import java.util.*;

/**
 * Represents lockers. Each locker has it's own capacity to hold items.
 * @author Oren Motiei
 */
public class Locker extends Storage {

    private static final String ERROR_MSG = "Error: Your request cannot be completed at this time.";
    private static final double MAX_UTILIZED_BY_ITEM = 0.5;
    private static final double MAX_UTILIZED_BY_REMAINING = 0.2;
    private final LongTermStorage lts;
    private final int capacity;
    private Item[][] constraints;


    /**
     * Initializes a locker.
     *
     * @param lts         The long-term storage of the ship that contains this locker.
     * @param capacity    The capacity of the locker (maximum storage units).
     * @param constraints A two dimensional array. Each array consists a pair of items that are not
     *                    allowed to reside together in the same locker.
     */
    public Locker(LongTermStorage lts, int capacity, Item[][] constraints) {
        this.lts = lts;
        this.capacity = capacity;
        this.constraints = constraints;
        availableCapacity = capacity;
    }


    /**
     * Adds n items of the given type to the locker.
     *
     * @param item The item that should be added.
     * @param n    The number of items of the given type to be added.
     * @return 0 if the addition is successful and doesn't cause items to be moved to long-term storage.
     *         1 if n* items were moved successfully to the long-term storage.
     *        -1 if n or n* items cannot be added at this time to the locker or the long-term storage.
     *        -2 if constraint is violated.
     */
    public int addItem(Item item, int n) {
        if (constraintViolated(item))
            return -2;
        String type = item.getType();
        int requiredVolume = n * item.getVolume();
        int itemQuantity = n;
        if (requiredVolume <= availableCapacity && n > 0) { // there is enough capacity and n is positive
            if (inventory.containsKey(type)) {
                itemQuantity += inventory.get(type);
                requiredVolume += inventory.get(type) * item.getVolume();
            }
            if (requiredVolume > capacity * MAX_UTILIZED_BY_ITEM) { // more than 50% of the locker's storage
                return moveToLongTermStorage(item, itemQuantity);
            } else { // there is enough capacity and less than 50% of the locker's storage
                availableCapacity -= inventory.containsKey(type) ? item.getVolume() *
                        (itemQuantity - inventory.get(type)) : item.getVolume() * itemQuantity;
                inventory.put(type, itemQuantity);
                return 0;
            }
        } else if (n < 0) {
            System.out.println(ERROR_MSG);
        } else // there isn't enough capacity
            printMessage(1, type, n);
        return -1;
    }


    /*
     * Checks if an item cannot be added to the locker, because a constraint is violated.
     * @param item: an object of Item.
     * @return true if a constraint is violated, false otherwise.
     */
    private boolean constraintViolated(Item item) {
        ArrayList<Item> blackList = new ArrayList<Item>();
        for (Item[] constraint: constraints) {
            if (constraint[0].getType().equals(item.getType()))
                blackList.add(constraint[1]);
            else if (constraint[1].getType().equals(item.getType()))
                blackList.add(constraint[0]);
        }
        for (Item itemsPair: blackList) {
            if (inventory.containsKey(itemsPair.getType())) {
                printMessage(5, item.getType(), 0);
                return true;
            }
        }
        return false;
    }


    /*
     * This method tries to move items to the long-term storage.
     * @param item: The item to be moved to the long-term storage.
     * @param itemQuantity: The total number of items of the given type.
     * @return 1 if the items have been moved successfully to the long-term storage, -1 otherwise.
     */
    private int moveToLongTermStorage(Item item, int itemQuantity){
        String type = item.getType();
        int allowedItems = (int)(MAX_UTILIZED_BY_REMAINING * capacity) / item.getVolume();
        int excessItems = itemQuantity - allowedItems;
        if (lts.addItem(item, excessItems) == 0) {
            availableCapacity -= inventory.containsKey(type) ? item.getVolume() *
                    (allowedItems - inventory.get(type)) : item.getVolume() * allowedItems;
            inventory.put(type, allowedItems);
            printMessage(2,null,0);
            return 1;
        }
        return -1;
    }


    /*
     * This is a helper function to print messages.
     * @param messageType: The type of the message to be printed.
     * @param type: The type of an item.
     * @param n: A number of items of the given type.
     */
    private void printMessage(int messageType, String type, int n) {
        switch (messageType) {
            case 1:
                System.out.println(ERROR_MSG + " Problem: no room for " + n + " items of type " + type);
                return;
            case 2:
                System.out.println("Warning: Action successful, " +
                        "but has caused items to be moved to storage");
                return;
            case 3:
                System.out.println(ERROR_MSG + " Problem: the locker does not contain " +
                        n + " items of type " + type);
                return;
            case 4:
                System.out.println(ERROR_MSG + " Problem: cannot remove a negative number of items of type "
                        + type);
                return;
            case 5:
                System.out.println(ERROR_MSG + " Problem: the locker cannot contain items of type " +
                        type + ", as it contains a contradicting item");
        }
    }


    /**
     * Removes n items of the given type from the locker.
     * @param item The item that should be removed.
     * @param n The number of items of the given type to be removed.
     * @return 0 if n items of the given type were removed from the locker successfully.
     *        -1 if no item was removed.
     */
    public int removeItem(Item item, int n) {
        String type = item.getType();
        if (n < 0) {
            printMessage(4, type,0);
            return -1;
        } else if (n == 0) {
            System.out.println(ERROR_MSG);
            return -1;
        }
        if (!inventory.containsKey(type) || inventory.get(type) < n) {
            printMessage(3, type, n);
            return -1;
        }
        availableCapacity += n * item.getVolume();
        inventory.put(type, inventory.get(type) - n);
        if (inventory.get(type) == 0)
            inventory.remove(type);
        return 0;
    }


    /**
     * @return locker's total capacity.
     */
    public int getCapacity() {
        return capacity;
    }
    
}
