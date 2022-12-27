package subsections;

/**
 * Thrown when there is an incorrect value of a filter or an order.
 * @author Oren Motiei
 */
public class BadValueException extends SubSectionException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new Exception.
     * @param lineNumber the line number in which the incorrect value appears.
     */
    public BadValueException(int lineNumber) {
        super("Warning in line " + lineNumber);
    }
}
