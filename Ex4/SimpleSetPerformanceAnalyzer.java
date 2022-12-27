import java.util.*;

/**
 * Measures the run-times of SimpleSet's methods for each of the following data structures: OpenHashSet,
 * ClosedHashSet, java's TreeSet, java's LinkedList and java's HashSet.
 */
public class SimpleSetPerformanceAnalyzer {

    private enum Tests {ADD_DATA1, ADD_DATA2, CONTAINS_HI1, CONTAINS_NEGATIVE, CONTAINS_23, CONTAINS_HI2}
    private enum Sets {OPEN_HASH_SET, CLOSED_HASH_SET, TREE_SET, LINKED_LIST, HASH_SET}
    private static final int MANY_ITERATIONS = 70000;
    private static final int FEW_ITERATIONS = 7000;
    private static String[] data1 = Ex4Utils.file2array("Ex4/data1.txt");
    private static String[] data2 = Ex4Utils.file2array("Ex4/data2.txt");


    /*
     * A factory method to create all the requested data structures.
     * @param sets: Contains all the data structures that need to be crated.
     * @return An array list of all the requested data structures.
     */
    private static ArrayList<SimpleSet> simpleSetFactory(ArrayList<Sets> sets) {
        ArrayList<SimpleSet> dataStructures = new ArrayList<>();
        for (Sets set: sets) {
            switch (set) {
                case OPEN_HASH_SET:
                    dataStructures.add(new OpenHashSet());
                    break;
                case CLOSED_HASH_SET:
                    dataStructures.add(new ClosedHashSet());
                    break;
                case TREE_SET:
                    dataStructures.add(new CollectionFacadeSet(new TreeSet<>()));
                    break;
                case LINKED_LIST:
                    dataStructures.add(new CollectionFacadeSet(new LinkedList<>()));
                    break;
                case HASH_SET:
                    dataStructures.add(new CollectionFacadeSet(new HashSet<>()));
                    break;
            }
        } return dataStructures;
    }


    /*
    Adds all the words in the data, one by one, to the given sets.
    Prints the time it took in milliseconds.
     */
    private static void measureAddingWords(String[] data, ArrayList<Sets> sets) {
        ArrayList<SimpleSet> dataStructures = simpleSetFactory(sets);
        for (SimpleSet dataStructure: dataStructures) {
            long timeBefore = System.nanoTime();
            for (String datum : data) {
                dataStructure.add(datum);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(difference / 1000000);
        }
        System.out.println();
    }


    /*
    Checks if the string is contained in the sets initialized with the given data.
    Prints the time it took in nanoseconds.
     */
    private static void measureContains(String[] data, ArrayList<Sets> sets, String str) {
        ArrayList<SimpleSet> dataStructures = simpleSetFactory(sets);
        for (SimpleSet dataStructure: dataStructures) {
            for (String datum : data)
                dataStructure.add(datum);
        }
        for (int i=0; i<dataStructures.size(); i++) {
            int iterations = (sets.get(i) == Sets.LINKED_LIST) ? FEW_ITERATIONS : MANY_ITERATIONS;
            if (iterations == MANY_ITERATIONS) {
                for (int j = 0; j<iterations; j++)
                    dataStructures.get(i).contains(str);
            }
            long timeBefore = System.nanoTime();
            for (int j=0; j<iterations; j++) {
                dataStructures.get(i).contains(str);
            }
            long difference = System.nanoTime() - timeBefore;
            System.out.println(difference / iterations);
        }
        System.out.println();
    }


    /*
     * Runs the chosen tests.
     * @param tests: Contains all the test that needs to be run.
     * @param sets: Contains all the data structures that the tests should run on.
     */
    private static void runMeasurements(ArrayList<Tests> tests, ArrayList<Sets> sets) {
        for (Tests test: tests) {
            switch (test) {
                case ADD_DATA1:
                    measureAddingWords(data1, sets);
                    break;
                case ADD_DATA2:
                    measureAddingWords(data2, sets);
                    break;
                case CONTAINS_HI1:
                    measureContains(data1, sets, "hi");
                    break;
                case CONTAINS_NEGATIVE:
                    measureContains(data1, sets, "-13170890158");
                    break;
                case CONTAINS_23:
                    measureContains(data2, sets, "23");
                    break;
                case CONTAINS_HI2:
                    measureContains(data2, sets, "hi");
            }
        }
    }



    /**
     * The main method that runs the measurements.
     */
    public static void main(String[] args) {
        ArrayList<Tests> tests = new ArrayList<>();
        /* Comment to disable some of these tests */
        tests.add(Tests.ADD_DATA1);
        tests.add(Tests.ADD_DATA2);
        tests.add(Tests.CONTAINS_HI1);
        tests.add(Tests.CONTAINS_NEGATIVE);
        tests.add(Tests.CONTAINS_23);
        tests.add(Tests.CONTAINS_HI2);

        ArrayList<Sets> sets = new ArrayList<>();
        /* Comment to disable some of these sets */
        sets.add(Sets.OPEN_HASH_SET);
        sets.add(Sets.CLOSED_HASH_SET);
        sets.add(Sets.TREE_SET);
        sets.add(Sets.LINKED_LIST);
        sets.add(Sets.HASH_SET);

        if (tests.size() != 0 && sets.size() != 0)
            runMeasurements(tests, sets);
    }

}
