/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 * @author Oren Motiei
 */
public abstract class SimpleHashSet implements SimpleSet {

    /** Describes the capacity of a newly created hash set. */
    protected static final int INITIAL_CAPACITY = 16;

    /** Describes the lower load factor of a newly created hash set. */
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    /** Describes the higher load factor of a newly created hash set. */
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /** Defines the lowest capacity a table can get */
    protected static final int LOWEST_TABLE_CAPACITY = 1;

    /** Defines how many times the table size will grow */
    protected static final float GROWTH_CONSTANT = 2f;

    /** Defines how many times the table size will decrease */
    protected static final float REDUCTION_CONSTANT = 0.5f;

    private int numOfElements = 0; // current number of elements in the table
    private float lowerLoadFactor;
    private float upperLoadFactor;


    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     * @param upperLoadFactor upperLoadFactor - the upper load factor before rehashing
     * @param lowerLoadFactor lowerLoadFactor - the lower load factor before rehashing
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
    }

    /**
     * Constructs a new hash set with the default capacities given in
     * DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
    }


    /**
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();


    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }


    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }


    /**
     * Clamps hashing indices to fit within the current table capacity
     * (see the exercise description for details)
     * @param index the index before clamping.
     * @return an index properly clamped.
     */
    protected int clamp(int index) {
        return index & (this.capacity() - 1);
    }


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    @Override
    public abstract boolean add(String newValue);


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public abstract boolean contains(String searchVal);


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    @Override
    public abstract boolean delete(String toDelete);


    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return numOfElements;
    }


    /**
     * Updates the number of elements currently in the set.
     * @param size The updated number of elements.
     */
    protected void setSize(int size) {
        numOfElements = size;
    }

}
