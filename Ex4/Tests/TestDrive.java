/**
 * Tester for ex3.
 * Created by:Evyatar
 * version 1.1
 * <p>
 * You are more then welcome to improve the tester - add tests, fix bugs, improve the current ones or make
 * the tester more accessible. If you do so, update the changelog below.
 * <p>
 * Changelog:
 * 1.0 - 29/4/18 - Evyatar - Created.
 * 1.1 - 29/4/18 - Evyatar - Add the capacity tests
 */

//הסבר על הטסטר -
// א. בטסטר זה נשתמש בפריימוורק של junit, בשלב הראשון אתם צריכים לייבא את הספרייה של junit.
// איך מייבאים? - שמים את הסמן על השגיעה בשורה 49 ואז alt+enter
// אחרי זה, פשוט תריצו את ה	מחלקה הזאת כדי להפעיל את כל הטסטים.

// אם אתם רוצים להפעיל טסט ספציפי, תלחצו על הסימן הירוק שליד מספר השורה של אותו הטסט

// יש לנו 12  טסטים -
// טסטים (1-4) למחלקה OpenHashSet
// טסטים (5-8) למחלקה ClosednHashSet
// טסטים (9-12) למחלקה CollectionFacadeSet

// הטסטים עבור כל מחלקה הם:
// sanity test - משחק עם הוספה ומחיקה של איבר אחד מהקבוצה
// big numbers -  משחק עם הוספה, מחיקה, ושאר הפונציות על מספר גדול של מחרוזות
// random  -  משחק עם הרבה סטרינגים רנדומליים
// capacity - בודק את תקינות הגודל של המערך

// אם עבור מחלקה מסויימת, רק חלק מהטסטים עוברים, מומלץ לנסות לתקן אותם ע"י סדר זה^.
// למשל רק כש"sanity test" עובר, לנסות לתקן את "big numbers"
// בפועל, הטסטים לא תלויים אחד בשני

// * כל טסט מנסה את כל הסוגים של הבנאים
// עבור facade כל טסט רץ על שלושת מבני הנתונים שאמורים להיות נתמכים (TreeSet, linkedList, HashSet)

// * שימו לב שהטסטר לא בודק את המחלקה SimpleSetPerformanceAnalyzer.

// אם אתם רוצים לוותר על אחד מהטסטים יש לכם שתי אופציות:
// או להסתיר את הפונקציה באמצצאות "//" הישן והטוב
// או להחליף את test@ ב Ignore@


import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;


import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDrive {

	// ----------------------openHashSet tests---------------------------

	/**
	 * call the function sanityTest 3 times, each time with a OpenHashSet with a different constructor.
	 * see sanityTest doc.
	 */
	@Test
	public void openHashSet_1_sanity_check() {
		sanityTest(new OpenHashSet());
		sanityTest(new OpenHashSet(0.60f, 0.40f));
		java.lang.String[] data1 = {};
		sanityTest(new OpenHashSet(data1));
	}

	/**
	 * call the function aLotOfStrings_test 3 times, each time with a OpenHashSet with a different constructor.
	 * see aLotOfStrings_test doc
	 */
	@Test
	public void openHashSet_2_big_numbers() {
		aLotOfStrings_test(new OpenHashSet());
		OpenHashSet o = new OpenHashSet(1f, 0.5f);
		aLotOfStrings_test(o);
		aLotOfStrings_test(o);
		aLotOfStrings_test(new OpenHashSet(0.95f, 0.05f));
		aLotOfStrings_test(new OpenHashSet(1f, 0f));
		aLotOfStrings_test(new OpenHashSet(0.50f, 0.50f));
		java.lang.String[] data1 = {};
		aLotOfStrings_test(new OpenHashSet(data1));
	}

	/**
	 * call the function random_one_by_one and then the functions random_insert_in_constructor.
	 * see there docs.
	 */
	@Test
	public void openHashSet_3_random() {
		random_one_by_one(new OpenHashSet());
		random_one_by_one(new OpenHashSet(0.60f, 0.40f));
		random_one_by_one(new OpenHashSet(0.95f, 0.05f));
		random_one_by_one(new OpenHashSet(1f, 0f));
		random_one_by_one(new OpenHashSet(0.50f, 0.50f));

		random_insert_in_constructor(0);
	}

	/**
	 * call the function capacity_test.
	 * see capacity_test docs.
	 */
	@Test
	public void openHashSet_4_capacityTest() {
		capacity_test(new OpenHashSet());
	}


	// ----------------------closedHashSet tests---------------------------

	/**
	 * call the function sanityTest 3 times, each time with a ClosedHashSet with a different constructor.
	 * see sanityTest doc.
	 */
	@Test
	public void ClosedHashSet_5_sanity_check() {
		sanityTest(new ClosedHashSet());
		sanityTest(new ClosedHashSet(0.60f, 0.40f));
		java.lang.String[] data1 = {};
		sanityTest(new ClosedHashSet(data1));
	}

	/**
	 * call the function aLotOfStrings_test 3 times, each time with a ClosedHashSet with a different constructor.
	 * see aLotOfStrings_test doc
	 */
	@Test
	public void ClosedHashSet_6_big_numbers() {

		aLotOfStrings_test(new ClosedHashSet());
		aLotOfStrings_test(new ClosedHashSet(0.60f, 0.40f));
		aLotOfStrings_test(new ClosedHashSet(0.95f, 0.05f));
		aLotOfStrings_test(new ClosedHashSet(1f, 0f));
		aLotOfStrings_test(new ClosedHashSet(0.50f, 0.50f));
		java.lang.String[] data1 = {};
		aLotOfStrings_test(new ClosedHashSet(data1));
	}

	/**
	 * call the function random_one_by_one and then the functions random_insert_in_constructor.
	 * see there docs.
	 */
	@Test
	public void ClosedHashSet_7_random() {
		random_one_by_one(new ClosedHashSet());
		random_insert_in_constructor(1);
	}

	/**
	 * call the function capacity_test.
	 * see capacity_test docs.
	 */
	@Test
	public void ClosedHashSet_8_capacityTest() {
		capacity_test(new ClosedHashSet());
	}


	// ----------------------facade tests---------------------------

	/**
	 * call the function sanityTest 3 times, each time with a CollectionFacadeSet with a different type of
	 * collections.
	 * see sanityTest doc.
	 */
	@Test
	public void facade_9_sanity_check() {
		sanityTest(new CollectionFacadeSet(new HashSet<>()));
		sanityTest(new CollectionFacadeSet(new LinkedList<>()));
		sanityTest(new CollectionFacadeSet(new TreeSet<>()));
	}

	/**
	 * call the function aLotOfStrings_test 3 times, each time with a CollectionFacadeSet with a different type of
	 * collections.
	 * see aLotOfStrings_test doc
	 */
	@Test
	public void facade_10_big_numbers() {
		aLotOfStrings_test(new CollectionFacadeSet(new HashSet<>()));
		aLotOfStrings_test(new CollectionFacadeSet(new LinkedList<>()));
		aLotOfStrings_test(new CollectionFacadeSet(new TreeSet<>()));
	}


	/**
	 * call the function random_one_by_one 3 times, each time with a CollectionFacadeSet with a different type of
	 * collections.
	 * see there docs.
	 */
	@Test
	public void facade_11_random() {
		random_one_by_one(new CollectionFacadeSet(new HashSet<>()));
		random_one_by_one(new CollectionFacadeSet(new LinkedList<>()));
		random_one_by_one(new CollectionFacadeSet(new TreeSet<>()));
	}

	/**
	 * call the function random_insert_in_constructor 3 times, each time with a CollectionFacadeSet with a
	 * different type of collections.
	 * see random_insert_in_constructor doc.
	 */
	@Ignore
	public void facade_12_random() {
		random_insert_in_constructor(2); // linkedList
		random_insert_in_constructor(3); // TreeSet
		random_insert_in_constructor(4); // HashSet
	}


	// ----------------------helper functions---------------------------

	/*
	simple test for the given SimpleSet, add one string, remove it, ....
	 */
	private void sanityTest(SimpleSet hashSet) {
		final String s = "";

		//add empty string test
		assertEquals("there is nothing inside", 0, hashSet.size());
		assertFalse("there is nothing inside", hashSet.contains(s));
		assertTrue("add an item", hashSet.add(s));
		assertTrue("now the string should exists", hashSet.contains(s));
		assertEquals("after we added an item, the size should be 1", 1, hashSet.size());
		assertFalse("try to add the string when its already inside", hashSet.add(s));
		assertTrue("delete the string", hashSet.delete(s));
		assertFalse("now, the set should not contain the string", hashSet.contains(s));
		assertFalse("try to delete it again, should return false", hashSet.delete(s));
		assertEquals("the size should return to 0.", 0, hashSet.size());

		assertTrue("add an item", hashSet.add(s));
		assertEquals("after we added an item, the size should be 1", 1, hashSet.size());
		assertTrue("now the string should exists", hashSet.contains(s));
		assertTrue("delete the string", hashSet.delete(s));
		assertEquals("the size should return to 0.", 0, hashSet.size());

	}

	/*
	add 1000 strings to the given SimpleSet and then remove them. the strings are "1", "2", "3"....
	 */
	private void aLotOfStrings_test(SimpleSet hashSet) {
		int iterations;  // if you fail this, try different values, I found that sometimes you can find
		// problems this way.
//		iterations = 3;
//		iterations = 5;
//		iterations = 23;
//		iterations = 100;
		iterations = 1000;

		//addition
		for (int i = 0; i < iterations; i++) {
			//(Integer.toString(i)): 1-> "1"

			// size
			assertEquals(i, hashSet.size());

			// should not contain it now
			assertFalse("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// should be able to add it now
			assertTrue("problem with the string " + i, hashSet.add(Integer.toString(i)));

			// should not be able to add it now
			assertFalse("problem with the string " + i, hashSet.add(Integer.toString(i)));

			// should contain it now
			assertTrue("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// size
			assertEquals(i + 1, hashSet.size());
		}

		// deletion
		for (int i = 0; i < iterations; i++) {
			// check the size
			assertEquals("problem with the string " + i, iterations - i, hashSet.size());

			// should contain it now
			assertTrue("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// should be able to delete it now
			assertTrue("problem with the string " + i, hashSet.delete(Integer.toString(i)));

			// should not be able to delete it now
			assertFalse("problem with the string " + i, hashSet.delete(Integer.toString(i)));

			// should not contain it now
			assertFalse("problem with the string " + i, hashSet.contains(Integer.toString(i)));

			// size
			assertEquals("problem with the string " + i, iterations - i - 1, hashSet.size());
		}
	}

	/*
	add 1000 strings to the given SimpleHashSet and then remove them. check if the capacity is as expected.
	 */
	private void capacity_test(SimpleHashSet hashSet) {
		int iterations = 1000;

		//addition
		for (int i = 0; i < iterations; i++) {
			hashSet.add(Integer.toString(i));

			if (hashSet.size() > 4) {
				assertTrue("problem with the string " + i,
						0.25 <= (double) hashSet.size() / hashSet.capacity());
			}
			assertTrue("problem with the string " + i,
					(double) hashSet.size() / hashSet.capacity() <= 0.75);
		}

		//deletion
		for (int i = 0; i < iterations; i++) {
			hashSet.delete(Integer.toString(i));
			if (i < iterations - 1)
				assertTrue("problem with the string " + i,
						0.25 <= (double) hashSet.size() / hashSet.capacity());
			assertTrue("problem with the string " + i,
					(double) hashSet.size() / hashSet.capacity() <= 0.75);
		}


	}


	/*
	add 1000 strings to the given SimpleSet and then remove them. the strings are generated randomly.
	to test if the functions behave as they should we compare the results to a java's HashSet.
	 */
	private void random_one_by_one(SimpleSet myHashSet) {

		int numOfStrings = 1000;

		// generate an array of random strings
		String[] randomStrings = generateRandomStrings(numOfStrings);

		// a regular HashSet (implemented by java) to compare wish "myHashSet".
		HashSet<String> classicSet = new HashSet<>();

		// add the string one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.add(randomStrings[i]), myHashSet.add(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains them
		for (int i = 0; i < numOfStrings; i++) {
			assertTrue("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}

		// remove the strings one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.remove(randomStrings[i]), myHashSet.delete(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains them (now it should not)
		for (int i = 0; i < numOfStrings; i++) {
			assertFalse("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}
	}

	/*
	add 1000 strings to the given SimpleSet in the constructor, and then remove them one by one. the strings
	are generated randomly.
	to test if the functions behave as they should, we compare the results to a java's HashSet.

	@param - typeOfSet - 0-5 (each represents type of SimpleSe. any other value will fail the test.
	 */
	private void random_insert_in_constructor(int typeOfSet) {

		// generate an array of random strings
		int numOfStrings = 1000;
		String[] randomStrings = generateRandomStrings(numOfStrings);

		// a regular HashSet (implemented by java) to compare wish "myHashSet".
		HashSet<String> classicSet = new HashSet<>(Arrays.asList(randomStrings));


		SimpleSet myHashSet;
		switch (typeOfSet) {
			case 0:
				myHashSet = new OpenHashSet(randomStrings);
				break;
			case 1:
				myHashSet = new ClosedHashSet(randomStrings);
				break;
			case 2:
				myHashSet = new CollectionFacadeSet(new LinkedList<>(Arrays.asList(randomStrings)));
				break;
			case 3:
				myHashSet = new CollectionFacadeSet(new TreeSet<>(Arrays.asList(randomStrings)));
				break;
			case 4:
				myHashSet = new CollectionFacadeSet(new HashSet<>(Arrays.asList(randomStrings)));
				break;
			default:
				myHashSet = null;
				fail();
		}


		// check if the size of the set is as it should be
		assertEquals(classicSet.size(), myHashSet.size());


		// check if the set contains them
		for (int i = 0; i < numOfStrings; i++) {
			assertTrue("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}

		// remove the strings one by one
		for (int i = 0; i < numOfStrings; i++) {
			assertEquals("problem with string number " + i,
					classicSet.remove(randomStrings[i]), myHashSet.delete(randomStrings[i]));
			assertEquals(classicSet.size(), myHashSet.size());
		}

		// check if the set contains the strings (now it should not)
		for (int i = 0; i < numOfStrings; i++) {
			assertFalse("problem with string number " + i,
					myHashSet.contains(randomStrings[i]));
		}
	}

	/*
	helper function
	generate an array of random strings,
	 */
	private String[] generateRandomStrings(int numOfStrings) {
		Random random = new Random();
		String[] randomStrings = new String[numOfStrings];
		for (int i = 0; i < numOfStrings; i++) {
			byte[] array = new byte[random.nextInt(10)]; // max size of strings
			random.nextBytes(array);
			randomStrings[i] = new String(array, Charset.forName("UTF-8"));
		}
		return randomStrings;
	}


}
