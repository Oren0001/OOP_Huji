package subsections;

import sections.Section;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

/**
 * Creates all the possible filters of the commands file, and uses them to filter files.
 * @author Oren Motiei
 */
public class FiltersFactory {

    private static final String GREATER_THAN_FILTER = "greater_than";
    private static final String BETWEEN_FILTER = "between";
    private static final String SMALLER_THAN_FILTER = "smaller_than";
    private static final String FILE_FILTER = "file";
    private static final String CONTAINS_FILTER = "contains";
    private static final String PREFIX_FILTER = "prefix";
    private static final String SUFFIX_FILTER = "suffix";
    private static final String WRITABLE_FILTER = "writable";
    private static final String EXECUTABLE_FILTER = "executable";
    private static final String HIDDEN_FILTER = "hidden";
    private static final String ALL_FILTER = "all";
    private static final int BYTES_CONVERSION = 1024;
    private final Section section;
    private final ArrayList<File> files;

    /**
     * Initializes a new filters factory.
     * @param section a section object which contains a specific filter.
     * @param files files to filter.
     */
    public FiltersFactory(Section section, ArrayList<File> files) {
        this.section = section;
        this.files = files;
    }


    /**
     * Creates a new filter according to the section's demand,
     * and uses it to filter the files of the given path.
     * @return an array list of the filtered files.
     */
    public ArrayList<File> executeFilter() {
        String filter = section.getFilter();
        Pattern p = Pattern.compile("[\\w./-]+");
        Matcher m = p.matcher(filter);
        try {
            if (m.lookingAt()) {
                String filterName = filter.substring(m.start(), m.end());
                String filterValues = filter.substring(m.end());
                switch (filterName) {
                    case GREATER_THAN_FILTER:
                        Filter<Double> isGreaterThan = (value1, value2, neg, size)
                                -> (neg == 0) == (value1 < size);
                        return filterBySize(filterValues, files, isGreaterThan);
                    case BETWEEN_FILTER:
                        Filter<Double> isBetween = (low, up, neg, size)
                                -> (neg == 0) ? (low <= size && size <= up) : (low > size || size > up);
                        return filterBetween(filterValues, files, isBetween);
                    case SMALLER_THAN_FILTER:
                        Filter<Double> isSmallerThan = (value1, value2, neg, size)
                                -> (neg == 0) == (value1 > size);
                        return filterBySize(filterValues, files, isSmallerThan);
                    case FILE_FILTER:
                        Filter<String> isSameName = (value1, value2, neg, fileName)
                                -> (neg == null) == (value1.equals(fileName));
                        return filterByString(filterValues, files, isSameName);
                    case CONTAINS_FILTER:
                        if (filterValues.equals("#"))
                            return files;
                        else if (filterValues.equals("##NOT"))
                            return new ArrayList<>();
                        Filter<String> isContained = (value1, value2, neg, fileName)
                                -> (neg == null) == fileName.contains(value1);
                        return filterByString(filterValues, files, isContained);
                    case PREFIX_FILTER:
                        Filter<String> isPrefix = (value1, value2, neg, fileName)
                                -> (neg == null) == fileName.startsWith(value1);
                        return filterByString(filterValues, files, isPrefix);
                    case SUFFIX_FILTER:
                        Filter<String> isSuffix = (value1, value2, neg, fileName)
                                -> (neg == null) == fileName.endsWith(value1);
                        return filterByString(filterValues, files, isSuffix);
                    case WRITABLE_FILTER:
                    case EXECUTABLE_FILTER:
                    case HIDDEN_FILTER:
                        Filter<String> isAttribute = (value1, value2, neg, hasAttribute)
                                -> (neg == null) == value1.equals(hasAttribute);
                        return filterByAttributes(filterName, filterValues, files, isAttribute);
                    case ALL_FILTER:
                        return filterByAll(filterValues, files);
                    default:
                        throw new BadFilterNameException(section.getFilterLine());
                }
            } else
                throw new BadFilterNameException(section.getFilterLine());
        } catch (SubSectionException e) {
            System.err.println(e.getMessage());
        } return files;
    }


    /*
     * Filters the given files according to their size. A file passes the filter if it's size is
     * strictly greater or smaller than the given value - s.
     * @param s: the values of the filter.
     * @param files: files to filter.
     * @param filter: a filter object to filter the files by their size.
     * @return: an array list of the filtered files.
     * @throws BadValueException: if s - the values, are incorrect.
     */
    private ArrayList<File> filterBySize(String s, ArrayList<File> files, Filter<Double> filter)
            throws BadValueException {
        ArrayList<File> filteredFiles = new ArrayList<>();
        Pattern p = Pattern.compile("(?:#\\d*\\.?\\d+|#\\d+\\.?\\d*)(?:#NOT)?");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            String[] matches = s.substring(m.start()+1, m.end()).split("#");
            double value = Double.parseDouble(matches[0]);
            Double negation = 0.0;
            if (matches.length == 2)
                negation = 1.0;
            for (File file: files) {
                double fileSize = (double)file.length() / BYTES_CONVERSION;
                if (filter.filePassed(value, null, negation, fileSize))
                    filteredFiles.add(file);
            } return filteredFiles;
        } else
            throw new BadValueException(section.getFilterLine());
    }


    /*
     * Filters the given files according to their size. A file passes the filter if it's size is between
     * the given values - s.
     * @param s: the values of the filter.
     * @param files: files to filter.
     * @param filter: a filter object to filter the files by their size.
     * @return: an array list of the filtered files.
     * @throws BadValueException: if s - the values, are incorrect.
     */
    private ArrayList<File> filterBetween(String s, ArrayList<File> files, Filter<Double> filter)
            throws BadValueException {
        ArrayList<File> filteredFiles = new ArrayList<>();
        Pattern p = Pattern.compile("(?:#\\d+\\.?\\d*#\\d+\\.?\\d*|#\\d+\\.?\\d*#\\d*\\.?\\d+|" +
                                       "#\\d*\\.?\\d+#\\d+\\.?\\d*|#\\d*\\.?\\d+#\\d*\\.?\\d+)(?:#NOT)?");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            String[] matches = s.substring(m.start()+1, m.end()).split("#");
            double low = Double.parseDouble(matches[0]);
            double up = Double.parseDouble(matches[1]);
            if (up < low)
                throw new BadValueException(section.getFilterLine());
            Double negation = 0.0;
            if (matches.length == 3)
                negation = 1.0;
            for (File file: files) {
                double fileSize = (double)file.length() / BYTES_CONVERSION;
                if (filter.filePassed(low, up, negation, fileSize))
                    filteredFiles.add(file);
            } return filteredFiles;
        } else
            throw new BadValueException(section.getFilterLine());
    }


    /*
     * Filters the given files according to the file name. E.g. a file can pass the filter if
     * s - the value, is contained in the file name.
     * @param s: the values of the filter.
     * @param files: files to filter.
     * @param filter: a filter object to filter the files by their file name.
     * @return: an array list of the filtered files.
     * @throws BadValueException: if s - the values, are incorrect.
     */
    private ArrayList<File> filterByString(String s, ArrayList<File> files, Filter<String> filter)
            throws BadValueException {
        ArrayList<File> filteredFiles = new ArrayList<>();
        Pattern p = Pattern.compile("(?:#[\\w./\\s-]+)(?:#NOT)?");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            String[] matches = s.substring(m.start()+1, m.end()).split("#");
            String value = matches[0];
            String negation = null;
            if (matches.length == 2)
                negation = "true";
            for (File file: files) {
                if (filter.filePassed(value, null, negation, file.getName()))
                    filteredFiles.add(file);
            } return filteredFiles;
        } else
            throw new BadValueException(section.getFilterLine());
    }


    /*
     * Filters the given files according to their attributes. E.g. a file can pass the filter if
     * it has writing permission.
     * @param filterName: the filter's name.
     * @param s: a string of "YES" or "NO".
     * @param files: files to filter.
     * @param filter: a filter object to filter the files by their attributes.
     * @return: an array list of the filtered files.
     * @throws BadValueException: if s - the values, are incorrect.
     */
    private ArrayList<File> filterByAttributes(String filterName, String s, ArrayList<File> files,
                                            Filter<String> filter)
            throws BadValueException {
        ArrayList<File> filteredFiles = new ArrayList<>();
        Pattern p = Pattern.compile("#(?:YES|NO)(?:#NOT)?");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            String[] matches = s.substring(m.start()+1, m.end()).split("#");
            String value = matches[0];
            String negation = null;
            if (matches.length == 2)
                negation = "true";
            for (File file: files) {
                if (filter.filePassed(value, null, negation, getFileAttribute(filterName, file)))
                    filteredFiles.add(file);
            } return filteredFiles;
        } else
            throw new BadValueException(section.getFilterLine());
    }


    /*
     * @return 'YES' if file is able to do the attribute, 'NO' otherwise.
     */
    private String getFileAttribute(String filterName, File file) {
        switch (filterName) {
            case WRITABLE_FILTER:
                return file.canWrite() ? "YES" : "NO";
            case EXECUTABLE_FILTER:
                return file.canExecute() ? "YES" : "NO";
            case HIDDEN_FILTER:
                return file.isHidden() ? "YES" : "NO";
            default:
                return null;
        }
    }


    /*
     * @param s: the value of the filter.
     * @param files: files to filter.
     * @return: if s - the value, contains "#NOT" it returns an empty array list, otherwise all the files.
     * @throws BadValueException: if s - the values, are incorrect.
     */
    private ArrayList<File> filterByAll(String s, ArrayList<File> files) throws BadValueException {
        Pattern p = Pattern.compile("(?:#NOT)?");
        Matcher m = p.matcher(s);
        if (m.matches()) {
            if (s.substring(m.start(), m.end()).equals("#NOT"))
                return new ArrayList<>();
            else
                return files;
        } else
            throw new BadValueException(section.getFilterLine());
    }

}
