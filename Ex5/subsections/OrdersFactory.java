package subsections;

import sections.Section;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Creates all the possible orders of the commands file, and uses them to sort the files.
 * @author Oren Motiei
 */
public class OrdersFactory {

    private static final String ABSOLUTE_ORDER = "abs";
    private static final String TYPE_ORDER = "type";
    private static final String SIZE_ORDER = "size";
    private static final String FIRST_SUBSECTION = "FILTER";
    private final Order sorter = FilesMergeSort.getInstance();
    private Section section;
    private ArrayList<File> filteredFiles;

    /**
     * Initializes a new orders factory.
     * @param section a section object which contains a specific order.
     * @param filteredFiles the filtered files to sort.
     */
    public OrdersFactory(Section section, ArrayList<File> filteredFiles) {
        this.section = section;
        this.filteredFiles = filteredFiles;
    }


    /**
     * Creates a new order according to the section's demand,
     * and uses it to sort the given filtered files.
     */
    public void executeOrder() {
        String order = section.getOrder();
        boolean reverse = order.endsWith("#REVERSE");
        if (reverse)
            order = order.substring(0, order.length() - 8);
        try {
            switch (order) {
                case ABSOLUTE_ORDER:
                    orderByAbs(reverse);
                    return;
                case TYPE_ORDER:
                    orderByType(reverse);
                    return;
                case SIZE_ORDER:
                    orderBySize(reverse);
                    return;
                default:
                    orderByAbs(reverse);
                    if (!section.getOrder().equals(FIRST_SUBSECTION))
                        throw new BadFilterNameException(section.getOrderLine());
            }
        } catch (SubSectionException e) {
            System.err.println(e.getMessage());
        }
    }


    /*
     * Sorts the filtered files by their absolute name, going from 'a' to 'z'.
     * @param reverse: true if the sorter should go from 'z' to 'a', false to go from 'a' to 'z'.
     */
    private void orderByAbs(boolean reverse) {
        Comparator<File> c = new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return reverse ? file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) :
                        file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            }
        };
        sorter.sort(filteredFiles, c);
    }


    /*
     * Sorts the filtered files by their type, going from 'a' to 'z'.
     * @param reverse: true if the sorter should go from 'z' to 'a', false to go from 'a' to 'z'.
     */
    private void orderByType(boolean reverse) {
        class TypeComparator implements Comparator<File> {
            @Override
            public int compare(File file1, File file2) {
                String file1Type = ".", file2Type = ".";
                Pattern p = Pattern.compile("\\.[\\w/\\s-]+$");
                Matcher m1 = p.matcher(file1.getName());
                Matcher m2 = p.matcher(file2.getName());
                if (m1.find()) {
                    String match = file1.getName().substring(m1.start(), m1.end());
                    file1Type = (m1.start() == 0) ? "" : match;
                }
                if (m2.find()) {
                    String match = file2.getName().substring(m2.start(), m2.end());
                    file2Type = (m2.start() == 0) ? "" : match;
                } if (file1Type.equals(file2Type))
                    return reverse ? file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) :
                            file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
                return reverse ? file2Type.compareTo(file1Type) : file1Type.compareTo(file2Type);
            }
        }
        sorter.sort(filteredFiles, new TypeComparator());
    }


    /*
     * Sorts the filtered files by their size, going from smallest to largest.
     * @param reverse: true if the sorter should go from the largest to the smallest,
     *                 false to go from the smallest to the largest.
     */
    private void orderBySize(Boolean reverse) {
        class SizeComparator implements Comparator<File> {
            @Override
            public int compare(File file1, File file2) {
                if (file1.length() == file2.length())
                    return reverse ? file2.getAbsolutePath().compareTo(file1.getAbsolutePath()) :
                            file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
                return reverse ? Long.compare(file2.length(), file1.length()) :
                        Long.compare(file1.length(), file2.length());
            }
        }
        sorter.sort(filteredFiles, new SizeComparator());
    }

}
