package sections;

/**
 * Represents a section of the command file which contains a filter and an order.
 * @author Oren Motiei
 */
public class Section {

    private final String filter;
    private final int filterLine;
    private String order = null;

    /**
     * Initializes a new section.
     * @param filterValue The filter's value.
     * @param filterLine The filter's line.
     */
    public Section (String filterValue, int filterLine) {
        filter = filterValue;
        this.filterLine = filterLine;
    }


    /**
     * @return The section's filter name.
     */
    public String getFilter() {
        return filter;
    }


    /**
     * @return The section's order name.
     */
    public String getOrder() {
        return order;
    }


    /**
     * @return the filter's line number in the commands file.
     */
    public int getFilterLine() {
        return filterLine;
    }


    /**
     * @return the order's line number in the commands file.
     */
    public int getOrderLine() {
        return filterLine + 2;
    }


    /**
     * Sets the order's value.
     * @param orderValue the value to set.
     */
    public void setOrder(String orderValue) {
        order = orderValue;
    }
}
