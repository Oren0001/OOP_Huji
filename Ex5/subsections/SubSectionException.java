package subsections;

/**
 * A super-class of exceptions which are related to sub-section errors in the commands file,
 * e.g. incorrect values of filters or orders.
 */
public class SubSectionException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception.
     * @param s The message to be printed when the exception is thrown.
     */
    public SubSectionException(String s) {
        super(s);
    }
}
