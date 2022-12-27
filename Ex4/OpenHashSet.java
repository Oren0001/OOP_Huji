import java.util.LinkedList;

/**
 * A hash-set based on chaining.
 * @author Oren Motiei
 */
public class OpenHashSet extends SimpleHashSet {

    private LinkedListWrapper[] table = new LinkedListWrapper[INITIAL_CAPACITY];

    /*--------------------------=  Constructors  =--------------------------*/

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
    }


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
    }


    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public OpenHashSet(String[] data) {
        super();
        for (String str: data)
            this.add(str);
    }

    /*--------------------------=  End of Constructors  =--------------------------*/

    /*
    A wrapper-class which wraps up a LinkedList<String>
     */
    private static class LinkedListWrapper {
        private final LinkedList<String> chain = new LinkedList<String>();
    }


    /**
     * @return The current capacity (number of cells) of the table.
     */
    @Override
    public int capacity() {
        return table.length;
    }


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (contains(newValue))
            return false;
        int bucketIndex = clamp(newValue.hashCode());
        if (table[bucketIndex] == null)
            table[bucketIndex] = new LinkedListWrapper();
        table[bucketIndex].chain.add(newValue);
        setSize(size() + 1);
        double loadFactor = (double)size() / table.length;
        if (loadFactor > getUpperLoadFactor())
            changeTableSize(GROWTH_CONSTANT);
        return true;
    }


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        int bucketIndex = clamp(searchVal.hashCode());
        if (table[bucketIndex] != null)
            return table[bucketIndex].chain.contains(searchVal);
        return false;
    }


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        int bucketIndex = clamp(toDelete.hashCode());
        if (table[bucketIndex] != null && table[bucketIndex].chain.remove(toDelete)) {
            setSize(size() - 1);
            if (table[bucketIndex].chain.size() == 0)
                table[bucketIndex] = null;
            double loadFactor = (double)size() / table.length;
            if (loadFactor < getLowerLoadFactor())
                changeTableSize(REDUCTION_CONSTANT);
            return true;
        }
        return false;
    }


    /*
     * Increases or decreases the table size whenever the load factor surpasses the upperLoadFactor,
     * or goes below the lowerLoadFactor, respectively.
     * @param num The number to be multiplied by the capacity of the table.
     */
    private void changeTableSize(float num) {
        int capacity = (int)(table.length * num);
        if (capacity < LOWEST_TABLE_CAPACITY)
            capacity = LOWEST_TABLE_CAPACITY;
        LinkedListWrapper[] oldTable = table;
        table = new LinkedListWrapper[capacity];
        setSize(0);
        for (LinkedListWrapper bucket: oldTable) {
            if (bucket != null) {
                for (String str: bucket.chain)
                    add(str);
            }
        }
    }

}
