import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestOpenStudent2 {

    private OpenHashSet openHashSet;

    private String [] words;

    private String [] longWords;

    private String [] duplicates;

    @Before
    public void setup(){
        openHashSet = new OpenHashSet();
        words = "this is a test lets hope it works".split(" ");
        longWords = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16".split(" ");
        duplicates = "1 1 1 1 2 2 2 3 3 3 4 4 4 5 5".split(" ");
    }


    @Test
    public void checkCapacity1(){
        assertEquals("defualt ctor capacity is 16",16, openHashSet.capacity());
    }

    @Test
    public void checkCapacity3(){
        for (int i = 0; i < 12; i++)
            openHashSet.add(String.valueOf(i));
        assertEquals("capacity shouldnt increase condition still applies" +
                     "12/16 <= 0.75",16, openHashSet.capacity());
    }

    @Test
    public void checkCapacity2(){
        for (int i = 0; i < 12; i++)
            openHashSet.add(String.valueOf(i));
        openHashSet.add(String.valueOf(12));
        assertEquals("capacity should increase condition doesnt apply" +
                     "13/16 > 0.75", 32, openHashSet.capacity());
    }


    @Test
    public void checkCapacity4(){
        for (int i = 0; i < 5; i++)
            openHashSet.add(String.valueOf(i));
        assertEquals("capacity shouldnt increase condition still applies" +
                     "5/16 <= 0.75",16,
                     openHashSet.capacity());
    }

    @Test
    public void checkCapacity5(){
        for (int i = 0; i < 12; i++)
            openHashSet.add(String.valueOf(i));
        openHashSet.add(String.valueOf(12));
        for (int i = 0; i < 5; i++)
            openHashSet.delete(String.valueOf(i));
        assertEquals("capacity should stay 32 condition still apply" +
                     "8/32 >= 0.25", 32, openHashSet.capacity());
        openHashSet.delete("5");
        assertEquals("capacity should decrease to 16 " +
                     "condition doesnt apply 7/32 <= 0.25",16, openHashSet.capacity());
    }

    @Test
    public void checkAdd1(){
        assertTrue("check your return value!", openHashSet.add("1"));
    }

    @Test
    public void checkAdd2(){
        openHashSet.add("1");
        assertTrue("2 isnt it the set it should work", openHashSet.add("2"));
    }

    @Test
    public void checkAdd3(){
        openHashSet.add("1");
        assertFalse("1 is already in the set you cannot add it again!", openHashSet.add("1"));
    }

    @Test
    public void checkAdd4(){
        openHashSet.add("1");
        openHashSet.delete("1");
        assertTrue("1 was added and then deleted so adding again should work!",
                   openHashSet.add("1"));
    }

    @Test
    public void checkAdd5(){
        for (int i = 0; i < 11; i++)
            openHashSet.add(String.valueOf(i));
        assertTrue("11 is not in the set it should add / return true",openHashSet.add("11"));
    }

    @Test
    public void checkAdd6(){
        for (int i = 0; i < 12; i++)
            openHashSet.add(String.valueOf(i));
        assertTrue("13 is not in the set it should add / return true", openHashSet.add("13"));
    }

    @Test
    public void checkAdd7(){
        for (int i = 0; i < 12; i++)
            openHashSet.add(String.valueOf(i));
        assertFalse("11 is already in the set!",openHashSet.add("11"));
    }

    @Test
    public void checkSize1(){
        openHashSet.add("1");
        assertEquals("added 1 item size must be 1",1, openHashSet.size());
    }

    @Test
    public void checkSize2(){
        openHashSet.add("1");
        openHashSet.add("1");
        assertEquals("no duplicates! size should remain 1",1, openHashSet.size());
    }

    @Test
    public void checkSize3(){
        openHashSet.add("1");
        openHashSet.delete("1");
        assertEquals("added and deleted size should be 0",0, openHashSet.size());
    }

    @Test
    public void checkSize4(){
        openHashSet.add("1");
        openHashSet.delete("1");
        openHashSet.add("1");
        assertEquals("add delete add size should be 1",1, openHashSet.size());
    }

    @Test
    public void checkSize5(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        assertEquals("added 13 items size should be 13",13, openHashSet.size());
    }

    @Test
    public void checkSize6(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            openHashSet.delete(String.valueOf(i));
        assertEquals("added 13 items and deleted them size should be 0",0, openHashSet.size());
    }

    @Test
    public void checkDelete1(){
        openHashSet.add("1");
        assertTrue("should delete 1, check return values",openHashSet.delete("1"));
    }

    @Test
    public void checkDelete2(){
        openHashSet.add("1");
        assertFalse("no such item 2 in the set",openHashSet.delete("2"));
    }

    @Test
    public void checkDelete3(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        assertTrue("should delete 12",openHashSet.delete("12"));
    }
    @Test
    public void checkDelete4(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertTrue("should delete "+i,openHashSet.delete(String.valueOf(i)));
    }

    @Test
    public void checkContains1(){
        assertFalse("no item added cant contain stuff", openHashSet.contains("1"));
    }

    @Test
    public void checkContains2(){
        openHashSet.add("1");
        assertTrue("added 1 should be in ds",openHashSet.contains("1"));
    }

    @Test
    public void checkContains3(){
        openHashSet.add("1");
        openHashSet.add("1");
        assertTrue("added 1 should be in ds",openHashSet.contains("1"));
    }

    @Test
    public void checkContains4(){
        openHashSet.add("1");
        openHashSet.add("2");
        assertTrue("added 2 should be in ds",openHashSet.contains("2"));
    }

    @Test
    public void checkContains5(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        assertTrue("added 12 should be in ds",openHashSet.contains("12"));
    }

    @Test
    public void checkContains6(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertTrue(i+" should be in ds",openHashSet.contains("12"));
    }
    @Test
    public void checkContains7(){
        for (int i = 0; i < 13; i++)
            openHashSet.add(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            openHashSet.delete(String.valueOf(i));
        for (int i = 0; i < 13; i++)
            assertFalse(i+" was removed so it cant be in ds",openHashSet.contains("12"));
    }

    @Test
    public void checkNonDefualtCtor1(){
        openHashSet = new OpenHashSet(0.5f,0.5f);
        for (int i = 0; i < 8; i++)
            openHashSet.add(String.valueOf(i));
        assertEquals("when upper and lower = 0.5f" +
                     " 8/16 <= 0.5 so need to stay 16",16, openHashSet.capacity());
    }

    @Test
    public void checkNonDefualtCtor2(){
        openHashSet = new OpenHashSet(0.5f,0.5f);
        for (int i = 0; i < 9; i++)
            openHashSet.add(String.valueOf(i));
        assertEquals("when upper and lower = 0.5f" +
                     " 9/16 > 0.5 so need to be 32",32, openHashSet.capacity());
    }

    @Test
    public void checkNonDefualtCtor3(){
        openHashSet = new OpenHashSet(0.5f,0.5f);
        for (int i = 0; i < 9; i++)
            openHashSet.add(String.valueOf(i));
        openHashSet.delete("0");
        assertEquals("when upper and lower = 0.5f" +
                     " 8/32 < 0.5 so need to be 16",16, openHashSet.capacity());
    }

    @Test
    public void checkStringCtor1(){
        openHashSet = new OpenHashSet(words);
        for (String i : words)
            assertTrue("the String array Ctor has this word check why it didnt add!"
                       ,openHashSet.contains(i));
        assertEquals(words.length ,openHashSet.size());
    }

    @Test
    public void checkStringCtor2(){
        openHashSet = new OpenHashSet(words);
        assertFalse("1 wasnt in words",openHashSet.contains("1"));
    }

    @Test
    public void checkStringCtor3(){
        openHashSet = new OpenHashSet(longWords);
        for (String i : longWords)
            assertTrue("the String array Ctor has this word check why it didnt add!"
                    , openHashSet.contains(i));
    }

    @Test
    public void checkStringCtor4(){
        openHashSet = new OpenHashSet(longWords);
        assertEquals(longWords.length, openHashSet.size());
        assertEquals(32, openHashSet.capacity());
    }

    @Test
    public void checkStringCtor5(){
        openHashSet = new OpenHashSet(duplicates);
        for (int i = 1; i< 6; i++)
            assertTrue(openHashSet.contains(String.valueOf(i)));
    }

    @Test
    public void checkStringCtor6(){
        openHashSet = new OpenHashSet(duplicates);
        assertEquals(5, openHashSet.size());
        assertEquals(16, openHashSet.capacity());
    }

}