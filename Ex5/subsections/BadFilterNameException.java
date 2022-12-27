package subsections;

/**
 * Thrown when there is an incorrect filter name, e.g. 'betweeen' instead of 'between'.
 * @author Oren Motiei
 */
public class BadFilterNameException extends SubSectionException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception.
     * @param lineNumber the line number in which the incorrect filter name appears.
     */
    public BadFilterNameException(int lineNumber) {
        super("Warning in line " + lineNumber);
    }
}
