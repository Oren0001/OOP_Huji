package sections;

/**
 * Thrown when there is an incorrect format of the commands file, e.g. no Order or Filter sub-sections.
 * @author Oren Motiei
 */
public class BadFormatException extends SectionException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception.
     */
    public BadFormatException() {
        super("ERROR: Bad format of Commands File\n");
    }
}
