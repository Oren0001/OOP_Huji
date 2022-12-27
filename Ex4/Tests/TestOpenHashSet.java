import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestOpenHashSet {

    private static String str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12,
            str13, str14, str15, str16, str17, str18;
    private OpenHashSet openHashSet;
    private static List<String> stringList;
    private static ArrayList<String> strings = new ArrayList<String>();


    /**
     * Creates objects once before all the tests are run.
     */
    @BeforeClass
    public static void createObjectsBeforeClass() {
        str1 = "Lord vader"; str2 = "Darth maul"; str3 = "Darth sidious"; str4 = "Kamino";
        str5 = "Luke skywalker"; str6 = "Anakin skywalker"; str7 = "Mos Eisley"; str8 = "Darth lol";
        str9 = "General grivous"; str10 = "Count dooku"; str11 = "Boba fett"; str12 = "Jango fett";
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
        openHashSet = new OpenHashSet();
    }


    @Test
    public void testAdd() {
        for (int i=0; i<12; i++)
            openHashSet.add(strings.get(i));
        for (int i=0; i<12; i++)
            assertTrue(openHashSet.contains(strings.get(i)));
        openHashSet.add(str1);
        assertEquals(12, openHashSet.size());

        openHashSet.add(str13);
        assertEquals(32, openHashSet.capacity());
    }


    @Test
    public void testDelete() {
        openHashSet.add(str1);
        assertTrue(openHashSet.delete(str1));
        assertFalse(openHashSet.contains(str1));
        for (String string: strings)
            openHashSet.add(string);
        for (String string: strings) {
            assertTrue(openHashSet.delete(string));
            assertFalse(openHashSet.contains(string));
        }
        assertEquals(0, openHashSet.size());
    }


    @Test
    public void testReduceTableSize() {
        openHashSet.add(str1);
        assertEquals(16,openHashSet.capacity());
        openHashSet.add(str2);
        assertEquals(16,openHashSet.capacity());
        openHashSet.delete(str2);
        assertEquals(8,openHashSet.capacity());
        openHashSet.delete(str1);
        assertEquals(4,openHashSet.capacity());
        openHashSet.add(str1);
        assertEquals(4,openHashSet.capacity());
        openHashSet.delete(str1);
        assertEquals(2,openHashSet.capacity());
        openHashSet.add(str1);
        assertEquals(2,openHashSet.capacity());
        openHashSet.delete(str1);
        assertEquals(1,openHashSet.capacity());
    }


    @Test
    public void ShowMeTable() {
        for (int i=0; i<16; i++)
            openHashSet.add(strings.get(i));
    }

}
