package sections;

/**
 * Thrown when there is an incorrect sub-section name, i.e. not FILTER or ORDER.
 */
public class BadSubSectionNameException extends SectionException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new Exception.
     */
    public BadSubSectionNameException() {
        super("ERROR: Bad subsection name\n");
    }
}
