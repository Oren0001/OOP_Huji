/**
 * A hash-set based on closed-hashing with quadratic probing.
 * @author Oren Motiei
 */
public class ClosedHashSet extends SimpleHashSet {

    private static final int NOT_FOUND = -1;
    private static final String DELETED = new String("");
    private String[] table = new String[INITIAL_CAPACITY];

    /*--------------------------=  Constructors  =--------------------------*/

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
    }


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
    }


    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public ClosedHashSet(String[] data) {
        super();
        for (String str: data)
            this.add(str);
    }

    /*--------------------------=  End of constructors  =--------------------------*/

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
        int hashCode = newValue.hashCode();
        for (int i=0; i<table.length; i++) { // start probing
            int bucketIndex = clamp(hashCode + (i + i*i)/2);
            if (table[bucketIndex] == null || table[bucketIndex] == DELETED) {
                table[bucketIndex] = newValue;
                setSize(size() + 1);
                double loadFactor = (double)size() / table.length;
                if (getUpperLoadFactor() == 1 ?
                        loadFactor >= getUpperLoadFactor() : loadFactor > getUpperLoadFactor())
                    changeTableSize(GROWTH_CONSTANT);
                return true;
            }
        }
        return false;
    }


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return findValue(searchVal) != NOT_FOUND;
    }


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        int bucketIndex = findValue(toDelete);
        if (bucketIndex != NOT_FOUND) {
            table[bucketIndex] = DELETED;
            setSize(size() - 1);
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
     * @param num: The number to be multiplied by the capacity of the table.
     */
    private void changeTableSize(float num) {
        int capacity = (int)(table.length * num);
        if (capacity < LOWEST_TABLE_CAPACITY)
            capacity = LOWEST_TABLE_CAPACITY;
        String[] oldTable = table;
        table = new String[capacity];
        setSize(0);
        for (String str: oldTable) {
            if (str != null && str != DELETED)
                add(str);
        }
    }


    /*
     * Looks for the given value in the set.
     * Returns it's index if found, -1 otherwise.
     */
    private int findValue(String str) {
        int hashCode = str.hashCode();
        for (int i=0; i<table.length; i++) {
            int bucketIndex = clamp(hashCode + (i + i*i)/2);
            if (table[bucketIndex] == null)
                return NOT_FOUND;
            else if (table[bucketIndex].equals(str) && table[bucketIndex] != DELETED)
                return bucketIndex;
        }
        return NOT_FOUND;
    }

}
