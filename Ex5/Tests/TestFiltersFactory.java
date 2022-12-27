import filesprocessing.DirectoryProcessor;
import org.junit.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class TestFiltersFactory {

    private static ArrayList<File> filtered1;

    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {

        DirectoryProcessor.main(new String[]{"Ex5/Test_directory" ,"Ex5/Command_files/filter021.flt"});
    }

}
