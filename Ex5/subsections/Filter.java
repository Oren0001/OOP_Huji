package subsections;

/**
 * Represents filters of the commands file.
 * @param <T> the type of the filter's value.
 */
@FunctionalInterface
public interface Filter<T> {

    /**
     * Checks whether or not the given file value passes the filter.
     * @param value1 the first value of the filter.
     * @param value2 the second value of the filter.
     * @param negation indicates if the opposite filter is required.
     * @param fileValue the file's value which is been filtered.
     * @return true if the file value passed the filter, false otheriwse.
     */
    boolean filePassed(T value1, T value2, T negation, T fileValue);

}
