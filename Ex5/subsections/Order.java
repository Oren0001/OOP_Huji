package subsections;

import java.io.File;
import java.util.*;

/**
 * Represents a requested order of a section in the commands file.
 * @author Oren Motiei
 */
@FunctionalInterface
public interface Order {

    /**
     * Sorts the files in the given list according to the comparator's behavior.
     * @param list a list of files.
     * @param c a comparator to determine how the files will be sorted.
     */
    void sort(ArrayList<File> list, Comparator<File> c);
}
