package filesprocessing;

import sections.*;

import java.io.*;
import java.util.*;

/**
 * Parses a commands file - a text file which is composed of one or more sections. Each section is composed
 * of two sub-sections: a filter and an order.
 * @author Oren Motiei
 */
public class CommandReader {

    private static final String FIRST_SUBSECTION = "FILTER";
    private static final String SECOND_SUBSECTION = "ORDER";
    private final BufferedReader file;

    /**
     * Initializes a new object which is able to parse the commands file.
     * @param file The commands file.
     */
    public CommandReader(Reader file) {
        this.file = new BufferedReader(file);
    }


    /**
     * Parses the command file.
     * @return An array list of sections. Each section is composed of a filter and an order.
     * @throws IOException if errors occur while accessing the commands file.
     * @throws SectionException if there is a bad sub-section name or a bad format of the commands file.
     */
    ArrayList<Section> parseFile() throws IOException, SectionException {
        ArrayList<Section> sections = new ArrayList<>();
        int lineNumber = 1;
        String line;
        boolean filter = false, filterValue = false, order = false;
        while ((line = file.readLine()) != null) {
            if (!filter) {
                checkFilter(line);
                filter = true;
            } else if (!filterValue) {
                sections.add(new Section(line, lineNumber));
                filterValue = true;
            } else if (!order) {
                checkOrder(line);
                order = true;
            } else {
                if (line.equals(FIRST_SUBSECTION))
                    sections.get(sections.size() - 1).setOrder(FIRST_SUBSECTION);
                else {
                    sections.get(sections.size() - 1).setOrder(line);
                    filter = false;
                }
                filterValue = false; order = false;
            } lineNumber++;
        }
        if (sections.size() > 0 && sections.get(sections.size() - 1).getOrder() == null && order)
            sections.get(sections.size() - 1).setOrder(FIRST_SUBSECTION);
        if ((filter && !order) ||
                (sections.size() > 0 && sections.get(sections.size() - 1).getOrder() == null && !order))
            throw new BadFormatException();
        return sections;
    }


    /*
     * Tests whether or not the sub-section name is 'FILTER'.
     * @param line: The line number of this sub-section.
     * @throws BadSubSectionException: if the sub-section name is incorrect.
     */
    private void checkFilter(String line) throws BadSubSectionNameException {
        if (!line.equals(FIRST_SUBSECTION))
            throw new BadSubSectionNameException();
    }


    /*
     * Tests whether or not the sub-section name is 'ORDER'.
     * @param line: The line number of this sub-section.
     * @throws BadSubSectionException: if the sub-section name is incorrect.
     */
    private void checkOrder(String line) throws BadSubSectionNameException {
        if (!line.equals(SECOND_SUBSECTION))
            throw new BadSubSectionNameException();
    }

}
