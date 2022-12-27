import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;


public class TestStudent1 {

    /** Determines if these tests run on all the words in the files data1.txt, data2.txt or part of
     * them */
    private static boolean allWords = false;

    private static int partOfWordsLength = 10000;

    private static String NEG_NUM = "-13170890158";

    private static String ZEROS_NUM = "0000000";

    private static OpenHashSet openHashSet = new OpenHashSet();

    private static ClosedHashSet closedHashSet = new ClosedHashSet();

    private static CollectionFacadeSet javaTreeSet = new CollectionFacadeSet(new TreeSet<String>());

    private static CollectionFacadeSet javaLinkedList = new CollectionFacadeSet(new LinkedList<String>());

    private static CollectionFacadeSet javaHashSet = new CollectionFacadeSet(new HashSet<String>());

    private static String[] wordsData1;

    private static String[] wordsData2;

    @BeforeClass
    public static void initWords(){
        if(allWords){
            wordsData1 = Ex4Utils.file2array("Ex4/data1.txt");
            wordsData2 = Ex4Utils.file2array("Ex4/data2.txt");
        }
        else{
            String[] allWords1 = Ex4Utils.file2array("Ex4/data1.txt");
            String[] allWords2 = Ex4Utils.file2array("Ex4/data2.txt");
            wordsData1 = new String[partOfWordsLength];
            wordsData2 = new String[partOfWordsLength];
            for(int i = 0; i < partOfWordsLength; i++) wordsData1[i] = allWords1[i];
            for(int i = 0; i < partOfWordsLength; i++) wordsData2[i] = allWords2[i];
        }
    }

    /**
     * Fills collections object with given array of strings. (Tests add function)
     */
    private void enterIntoCollectionFacade(SimpleSet obj, String[] words){
        for(int i = 0; i < words.length; i++){
            Assert.assertTrue(obj.add(words[i]));
        }
    }

    /**
     * Tests simple API functions:size,contains, add(for duplicates), and delete.
     * @param obj - object to test. OpenHashSet/ClosedHashSet/TreeSet/ect.
     * @param words - array of words entered into data structure object.
     */
    private void testOnData(SimpleSet obj, String[] words)
    {
        Assert.assertEquals(obj.size(), words.length);
        for(int i = 0; i < words.length; i++) Assert.assertTrue(obj.contains(words[i]));
        Assert.assertFalse(obj.contains(ZEROS_NUM)); //string not in data{1/2}.txt
        Assert.assertFalse(obj.contains(NEG_NUM)); //another string not in data{1/2}.txt
        for(int i = 0; i < 100; i++) Assert.assertFalse(obj.add(words[i])); // no duplicates
        for(int i = 0; i < 100; i++) Assert.assertTrue(obj.delete(words[i]));
        for(int i = 0; i < 100; i++) Assert.assertFalse(obj.delete(words[i])); //deleted
        for(int i = 0; i < 100; i++) Assert.assertFalse(obj.contains(words[i])); //deleted
        Assert.assertEquals(obj.size(), words.length - 100);
    }

    /////////////////SIMPLE OPEN_HASH_SET TESTS
    @Test
    public void testOpenHashSet1(){
        openHashSet = new OpenHashSet(wordsData1); //creates using data constructor
        testOnData(openHashSet, wordsData1);
    }
    @Test
    public void testOpenHashSet2(){
        openHashSet = new OpenHashSet(wordsData2); //creates using data constructor
        testOnData(openHashSet, wordsData2);
    }

    /////////////////SIMPLE CLOSED_HASH_SET TESTS
    @Test
    public void testClosedHashSet1(){
        closedHashSet = new ClosedHashSet(wordsData1); //creates using data constructor
        testOnData(closedHashSet, wordsData1);
}

    @Test
    public void testClosedHashSet2(){
        closedHashSet = new ClosedHashSet(wordsData2); //creates using data constructor
        testOnData(closedHashSet, wordsData2);
    }

    /////////////////SIMPLE LINKED_LIST_SET TESTS
    @Test
    public void testCollectionLinkedList1(){
        javaLinkedList = new CollectionFacadeSet(new LinkedList<String>());
        enterIntoCollectionFacade(javaLinkedList, wordsData1);
        testOnData(javaLinkedList, wordsData1);
    }
    @Test
    public void testCollectionLinkedList2(){
        javaLinkedList = new CollectionFacadeSet(new LinkedList<String>());
        enterIntoCollectionFacade(javaLinkedList, wordsData2);
        testOnData(javaLinkedList, wordsData2);
    }

    /////////////////SIMPLE TREE_SET TESTS
    @Test
    public void testCollectionTreeSet1(){
        javaTreeSet = new CollectionFacadeSet(new TreeSet<String>());
        enterIntoCollectionFacade(javaTreeSet, wordsData1);
        testOnData(javaTreeSet, wordsData1);
    }
    @Test
    public void testCollectionTreeSet2(){
        javaTreeSet = new CollectionFacadeSet(new TreeSet<String>());
        enterIntoCollectionFacade(javaTreeSet, wordsData2);
        testOnData(javaTreeSet, wordsData2);
    }

    /////////////////SIMPLE HASH_SET TESTS
    @Test
    public void testCollectionHashSet1(){
        javaHashSet = new CollectionFacadeSet(new HashSet<String>());
        enterIntoCollectionFacade(javaHashSet, wordsData1);
        testOnData(javaHashSet, wordsData1);
    }
    @Test
    public void testCollectionHashSet2(){
        javaHashSet = new CollectionFacadeSet(new HashSet<String>());
        enterIntoCollectionFacade(javaHashSet, wordsData2);
        testOnData(javaHashSet, wordsData2);
    }


}
