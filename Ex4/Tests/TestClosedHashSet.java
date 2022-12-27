import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestClosedHashSet {
    private static String str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12,
            str13, str14, str15, str16, str17, str18;
    private ClosedHashSet closedSet;
    private static List<String> stringList;
    private static ArrayList<String> strings = new ArrayList<String>();


    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        str1 = "Lord vader"; str2 = "Darth maul"; str3 = "Darth sidious";
        str4 = "Kamino";
        str6 = "Anakin skywalker";
        str11 = "Boba fett";
        str5 = "Luke skywalker"; str7 = "Mos Eisley"; str8 = "Darth lol";
        str9 = "General grivous"; str10 = "Count dooku"; str12 = "Jango fett";
        str13 = "Boba flett"; str14 = "Chewbacca"; str15 = "Boosk"; str16 = "Iden versio";
        str17 = "1234"; str18 = "-1234";
        stringList = Arrays.asList(str1, str2, str3, str4, str5, str6, str7,
                str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18);
        strings.addAll(stringList);
    }


    /**
     * Creates objects before each test runs.
     */
    @Before
    public void createObjectsBefore() {
        closedSet = new ClosedHashSet();
    }


    @Test
    public void testAdd() {
        for (String str: strings)
            closedSet.add(str);
    }


    @Test
    public void testIncreaseTableSize() {
        for (int i=0; i<12; i++)
            closedSet.add(strings.get(i));
        assertEquals(16, closedSet.capacity());
        closedSet.add(str13);
        assertEquals(32, closedSet.capacity());
    }


    @Test
    public void testContains() {
        for (String str: strings)
            closedSet.add(str);
        for (String str: strings)
            assertTrue(closedSet.contains(str));
    }


    @Test
    public void testDelete() {
        for (int i=0; i<13; i++)
            closedSet.add(strings.get(i));
        assertEquals(32, closedSet.capacity());
        for (int i=12; i>6; i--)
            closedSet.delete(strings.get(i));
        assertEquals(16, closedSet.capacity());
        closedSet.delete(strings.get(6));
        assertEquals(16, closedSet.capacity());
        for (int i=5; i>-1; i--)
            assertTrue(closedSet.contains(strings.get(i)));
        closedSet.delete(strings.get(5));
        closedSet.delete(strings.get(4));
        closedSet.delete(strings.get(3));
        assertEquals(8, closedSet.capacity());

        /*
        Test collision: strings.get(3), strings.get(5) and strings.get(10) have the same bucket index
                        after clamping.
         */
        closedSet = new ClosedHashSet();
        for (int i=10; i<16; i++)
            closedSet.add(strings.get(i));
        closedSet.add(strings.get(3));
        closedSet.add(strings.get(5));
        closedSet.add(strings.get(10));
        closedSet.delete(strings.get(5));
        assertEquals(7, closedSet.size());
        assertFalse(closedSet.contains(strings.get(5)));
        assertTrue(closedSet.contains(strings.get(10)));
        closedSet.add(strings.get(5));
        closedSet.delete(strings.get(3));
        assertFalse(closedSet.add(strings.get(10)));
        closedSet.delete(strings.get(5));
        assertTrue(closedSet.contains(strings.get(10)));
        closedSet.add(strings.get(5));
        assertTrue(closedSet.contains(strings.get(5)));
        closedSet.add(strings.get(3));
        assertTrue(closedSet.contains(strings.get(3)));
    }


    @Test
    public void testDeletion() {
        closedSet.add("");
        assertTrue(closedSet.contains(""));
        assertTrue(closedSet.delete(""));
        assertFalse(closedSet.contains(""));
    }

}
