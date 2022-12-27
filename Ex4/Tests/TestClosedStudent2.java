import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestClosedStudent2 {


    private ClosedHashSet closedHashSet;

    private String [] words;

    private String [] longWords;

    private String [] duplicates;

    @Before
    public void setup(){
        closedHashSet = new ClosedHashSet();
        words = "this is a test lets hope it works".split(" ");
        longWords = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16".split(" ");
        duplicates = "1 1 1 1 2 2 2 3 3 3 4 4 4 5 5".split(" ");
    }


    @Test
    public void checkCapacity1(){
        assertEquals("defualt ctor capacity is 16", 16, closedHashSet.capacity());
    }

    @Test
    public void checkCapacity3(){
        for (int i = 0; i < 12; i++)
            closedHashSet.add(String.valueOf(i));
        assertEquals("capacity shouldnt increase condition still applies" +
                     "12/16 <= 0.75", 16, closedHashSet.capacity());
    }

    @Test
    public void checkCapacity2(){
        for (int i = 0; i < 12; i++)
            closedHashSet.add(String.valueOf(i));
        closedHashSet.add("12");
        assertEquals("capacity should increase condition doesnt apply" +
                     "13/16 > 0.75", 32, closedHashSet.capacity());
    }


    @Test
    public void checkCapacity4(){
        for (int i = 0; i < 5; i++)
            closedHashSet.add(String.valueOf(i));
        assertEquals("capacity shouldnt increase condition still applies" +
                     "5/16 <= 0.75", 16,
                     closedHashSet.capacity());
    }

    @Test
    public void checkCapacity5(){
        for (int i = 0; i < 12; i++)
            closedHashSet.add(String.valueOf(i));
        closedHashSet.add(String.valueOf(12));
        for (int i = 0; i < 5; i++)
            closedHashSet.delete(String.valueOf(i));
        assertEquals("capacity should stay 32 condition still apply" +
                     "8/32 >= 0.25", 32, closedHashSet.capacity());
        closedHashSet.delete("5");
        assertEquals("capacity should decrease to 16 " +
                     "condition doesnt apply 7/32 <= 0.25", 16, closedHashSet.capacity());
    }

    @Test
    public void checkAdd1(){
        assertTrue("check your return value!", closedHashSet.add("1"));
    }

    @Test
    public void checkAdd2(){
        closedHashSet.add("1");
        assertTrue("2 isnt it the set it should work", closedHashSet.add("2"));
    }

    @Test
    public void checkAdd3(){
        closedHashSet.add("1");
        assertFalse("1 is already in the set you cannot add it again!", closedHashSet.add("1"));
    }

    @Test
    public void checkAdd4(){
        closedHashSet.add("1");
        closedHashSet.delete("1");
        assertTrue("1 was added and then deleted so adding again should work!",
                   closedHashSet.add("1"));
    }

    @Test
    public void checkAdd5(){
        for (int i = 0; i < 11; i++)
            closedHashSet.add(String.valueOf(i));
        assertTrue("11 is not in the set it should add / return true", closedHashSet.add("11"));
    }

    @Test
    public void checkAdd6(){
        for (int i = 0; i < 12; i++)
            closedHashSet.add(String.valueOf(i));
        assertTrue("13 is not in the set it should add / return true", closedHashSet.add("13"));
    }

    @Test
    public void checkAdd7(){
        for (int i = 0; i < 12; i++)
            closedHashSet.add(String.valueOf(i));
        assertFalse("11 is already in the set!", closedHashSet.add("11"));
    }

    @Test
    public void checkSize1(){
        closedHashSet.add("1");
        assertEquals("added 1 item size must be 1", 1, closedHashSet.size());
    }

    @Test
    public void checkSize2(){
        closedHashSet.add("1");
        closedHashSet.add("1");
        assertEquals("no duplicates! size should remain 1", 1, closedHashSet.size());
    }

    @Test
    public void checkSize3(){
        closedHashSet.add("1");
        closedHashSet.delete("1");
        assertEquals("added and deleted size should be 0", 0, closedHashSet.size());
    }

    @Test
    public void checkSize4(){
        closedHashSet.add("1");
        closedHashSet.delete("1");
        closedHashSet.add("1");
        assertEquals("add delete add size should be 1", 1, closedHashSet.size());
    }

    @Test
    public void checkSize5(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        assertEquals("added 13 items size should be 13", 13, closedHashSet.size());
    }

    @Test
    public void checkSize6(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            closedHashSet.delete(String.valueOf(i));
        assertEquals("added 13 items and deleted them size should be 0", 0, closedHashSet.size());
    }

    @Test
    public void checkDelete1(){
        closedHashSet.add("1");
        assertTrue("should delete 1, check return values", closedHashSet.delete("1"));
    }

    @Test
    public void checkDelete2(){
        closedHashSet.add("1");
        assertFalse("no such item 2 in the set", closedHashSet.delete("2"));
    }

    @Test
    public void checkDelete3(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        assertTrue("should delete 12", closedHashSet.delete("12"));
    }
    @Test
    public void checkDelete4(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertTrue("should delete "+i, closedHashSet.delete(String.valueOf(i)));
    }

    @Test
    public void checkContains1(){
        assertFalse("no item added cant contain stuff", closedHashSet.contains("1"));
    }

    @Test
    public void checkContains2(){
        closedHashSet.add("1");
        assertTrue("added 1 should be in ds", closedHashSet.contains("1"));
    }

    @Test
    public void checkContains3(){
        closedHashSet.add("1");
        closedHashSet.add("1");
        assertTrue("added 1 should be in ds", closedHashSet.contains("1"));
    }

    @Test
    public void checkContains4(){
        closedHashSet.add("1");
        closedHashSet.add("2");
        assertTrue("added 2 should be in ds", closedHashSet.contains("2"));
    }

    @Test
    public void checkContains5(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        assertTrue("added 12 should be in ds", closedHashSet.contains("12"));
    }

    @Test
    public void checkContains6(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertTrue(i+" should be in ds", closedHashSet.contains("12"));
    }
    @Test
    public void checkContains7(){
        for (int i = 0; i < 13; i++)
            closedHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            closedHashSet.delete(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertFalse(i+" was removed so it cant be in ds", closedHashSet.contains("12"));
    }

    @Test
    public void checkNonDefualtCtor1(){
        closedHashSet = new ClosedHashSet(0.5f, 0.5f);
        for (int i = 0; i < 8; i++)
            closedHashSet.add(String.valueOf(i));
        assertEquals("when upper and lower = 0.5f" +
                     " 8/16 <= 0.5 so need to stay 16", 16, closedHashSet.capacity());
    }

    @Test
    public void checkNonDefualtCtor2(){
        closedHashSet = new ClosedHashSet(0.5f, 0.5f);
        for (int i = 0; i < 9; i++)
            closedHashSet.add(String.valueOf(i));
        assertEquals("when upper and lower = 0.5f" +
                     " 9/16 > 0.5 so need to be 32", 32, closedHashSet.capacity());
    }

    @Test
    public void checkNonDefualtCtor3(){
        closedHashSet = new ClosedHashSet(0.5f, 0.5f);
        for (int i = 0; i < 9; i++)
            closedHashSet.add(String.valueOf(i));
        closedHashSet.delete("0");
        assertEquals("when upper and lower = 0.5f" +
                     " 8/32 < 0.5 so need to be 16", 16, closedHashSet.capacity());
    }

    @Test
    public void checkStringCtor1(){
        closedHashSet = new ClosedHashSet(words);
        for (String i : words)
            assertTrue("the String array Ctor has this word check why it didnt add!"
                    , closedHashSet.contains(i));
        assertEquals(words.length , closedHashSet.size());
    }

    @Test
    public void checkStringCtor2(){
        closedHashSet = new ClosedHashSet(words);
        assertFalse("1 wasnt in words", closedHashSet.contains("1"));
    }

    @Test
    public void checkStringCtor3(){
        closedHashSet = new ClosedHashSet(longWords);
        for (String i : longWords)
            assertTrue("the String array Ctor has this word check why it didnt add!"
                    , closedHashSet.contains(i));
    }

    @Test
    public void checkStringCtor4(){
        closedHashSet = new ClosedHashSet(longWords);
        assertEquals(longWords.length, closedHashSet.size());
        assertEquals(32, closedHashSet.capacity());
    }

    @Test
    public void checkStringCtor5(){
        closedHashSet = new ClosedHashSet(duplicates);
        for (int i = 1; i< 6; i++)
            assertTrue(closedHashSet.contains(String.valueOf(i)));
    }

    @Test
    public void checkStringCtor6(){
        closedHashSet = new ClosedHashSet(duplicates);
        assertEquals(5, closedHashSet.size());
        assertEquals(16, closedHashSet.capacity());
    }
}