package sections;

/**
 * The super-class of exceptions which are related to section errors in the commands file,
 * e.g. bad sub-section name and bad format of the section.
 * @author Oren Motiei
 */
public class SectionException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new exception.
     * @param s The message to be printed when the exception is thrown.
     */
    public SectionException(String s) {
        super(s);
    }
}
