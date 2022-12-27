import java.util.Collection;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common
 * type with the implemented SimpleHashSets.
 * @author Oren Motiei
 */
public class CollectionFacadeSet  implements SimpleSet {

    protected Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection  The Collection to wrap.
     */
    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
    }


    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    @Override
    public boolean add(String newValue) {
        if (collection.contains(newValue))
            return false;
        collection.add(newValue);
        return true;
    }


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    @Override
    public boolean contains(String searchVal) {
        return collection.contains(searchVal);
    }


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    @Override
    public boolean delete(String toDelete) {
        return collection.remove(toDelete);
    }


    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return collection.size();
    }

}
