/**
 * hello friends! ive written some dumby tests, hope it helps and you enjoy it!! you should run it on
 * jetBrain's IDEA, youll probably need to install JUNIT as an add-on for the IJ hope you manage it out JUNIT
 * is explained in the model page at the link 101 TESTING or something like that you should read about that
 * there but i got no further explanation about this shit so sorry. <3<3<3 GREAT THANK YOU TO OREL THE BEST OF
 * THE BEST WHO HELPED ME DETECT PROBLEMS IN THIS BECAUSE I AM EFFIN' DONE. <3<3<3 XOXO always here for you to
 * help, rina
 */

import static org.junit.Assert.*;

public class TestRina {

    void deleteLibraryContent() {
        book0.returnBook();
        book1.returnBook();
        book2.returnBook();
        book3.returnBook();
        book4.returnBook();
        book5.returnBook();
    }

    static Book book1 = new Book("Lolita", "Vladimir Nabokov", 1955, 2,
            8, 5);
    static Book book1copy1 = new Book("Lolita", "Vladimir Nabokov", 1955, 2,
            8, 5);

    static Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, 2,
            6, 8);

    static Book book3 = new Book("1984", "George Orwell", 1955, 1,
            5, 9);
    static Book book3copy1 = new Book("1984", "George Orwell", 1955, 1,
            5, 9);
    static Book book3copy2 = new Book("1984", "George Orwell", 1955, 1,
            5, 9);
    static Book book3copy3 = new Book("1984", "George Orwell", 1955, 1,
            5, 9);

    static Book book4 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1955, 5,
            7, 4);
    static Book book4copy1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1955, 5,
            7, 4);
    static Book book4copy2 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1955, 5,
            7, 4);

    static Book book5 = new Book("Pride and Prejudice", "Jane Austen", 1955, 3,
            10, 0);

    static Book book0 = new Book("A Story Of Fake Grades", "Bicyle Man", 2020, 0,
            0, 0);
    static Book book0copy1 = new Book("A Story Of Fake Grades", "Bicyle Man", 2020, 0,
            0, 0);
    static Book book0copy2 = new Book("A Story Of Fake Grades", "Bicyle Man", 2020, 0,
            0, 0);
    static Book book0copy3 = new Book("A Story Of Fake Grades", "Bicyle Man", 2020, 0,
            0, 0);


    /**
     * test for BOOK class tests each book out of 5 books.
     */
    @org.junit.Test
    public void getLiteraryValueTest() {

        assertEquals(15, book1.getLiteraryValue());

        assertEquals(16, book2.getLiteraryValue());

        assertEquals(15, book3.getLiteraryValue());

        assertEquals(16, book4.getLiteraryValue());

        assertEquals(13, book5.getLiteraryValue());

        assertEquals(0, book0.getLiteraryValue());

        book0.returnBook();
        book1.returnBook();
        book2.returnBook();
        book3.returnBook();
        book4.returnBook();
        book5.returnBook();
    }


    @org.junit.Test
    public void BorrowerIdTest() {

        // after init of books, it should be -1 because no one borrowed it yet.
        assertEquals(-1, book1.getCurrentBorrowerId());
        assertEquals(-1, book2.getCurrentBorrowerId());
        assertEquals(-1, book3.getCurrentBorrowerId());
        assertEquals(-1, book4.getCurrentBorrowerId());
        assertEquals(-1, book5.getCurrentBorrowerId());

        // borrowing starts
        book3.setBorrowerId(309495587);
        assertEquals(309495587, book3.getCurrentBorrowerId());
        assertEquals(309495587, book3.currentBorrowerId);

        book4.setBorrowerId(298283275);
        assertEquals(298283275, book4.getCurrentBorrowerId());
        assertEquals(298283275, book4.currentBorrowerId);

        // we have returned it
        book3.returnBook();
        assertEquals(-1, book3.getCurrentBorrowerId());
        assertEquals(-1, book3.currentBorrowerId);

        // even without borrowing, check what happens
        book5.returnBook();
        assertEquals(-1, book5.getCurrentBorrowerId());
        assertEquals(-1, book5.currentBorrowerId);

        deleteLibraryContent();
    }

    @org.junit.Test
    public void printBookTest() {
        final String book1String = "[Lolita,Vladimir Nabokov,1955,15]";
        assertEquals(book1String, book1.stringRepresentation());

        final String book2String = "[To Kill a Mockingbird,Harper Lee,1960,16]";
        assertEquals(book2String, book2.stringRepresentation());

        final String book3String = "[1984,George Orwell,1955,15]";
        assertEquals(book3String, book3.stringRepresentation());

        final String book4String = "[The Great Gatsby,F. Scott Fitzgerald,1955,16]";
        assertEquals(book4String, book4.stringRepresentation());

        final String book5String = "[Pride and Prejudice,Jane Austen,1955,13]";
        assertEquals(book5String, book5.stringRepresentation());

        deleteLibraryContent();
    }

    static Patron p1 = new Patron("Timothy", "Arwall", 5, 6, 2, 10);

    static Patron p2 = new Patron("Sam", "Grey", 5, 9, 2, 23);

    static Patron p3 = new Patron("Polly", "Marshel", 5, 7, 2, 67);

    static Patron p4 = new Patron("Patty", "Pivot", 5, 6, 2, 15);

    static Patron p5 = new Patron("Iam", "SoTired", 5, 1, 2, 54);

    static Patron p6 = new Patron("X Æ A-12", "  Musk", 10, 10, 10, 0);

    /**
     * test for PATRON class checks each out of 6 patrons.
     */
    @org.junit.Test
    public void printPatronTest() {
        final String patron1String = "Timothy Arwall";
        assertEquals(patron1String, p1.stringRepresentation());

        final String patron2String = "Sam Grey";
        assertEquals(patron2String, p2.stringRepresentation());

        final String patron3String = "Polly Marshel";
        assertEquals(patron3String, p3.stringRepresentation());

        final String patron4String = "Patty Pivot";
        assertEquals(patron4String, p4.stringRepresentation());

        final String patron5String = "Iam SoTired";
        assertEquals(patron5String, p5.stringRepresentation());

        final String patron6String = "X Æ A-12   Musk";
        assertEquals(patron6String, p6.stringRepresentation());
    }

    @org.junit.Test
    public void getBookScoreTest() {

        assertEquals(0, p1.getBookScore(book0));
        assertEquals(68, p1.getBookScore(book1));
        assertEquals(62, p1.getBookScore(book2));
        assertEquals(53, p1.getBookScore(book3));
        assertEquals(75, p1.getBookScore(book4));
        assertEquals(75, p1.getBookScore(book5));

        assertEquals(0, p2.getBookScore(book0));
        assertEquals(92, p2.getBookScore(book1));
        assertEquals(80, p2.getBookScore(book2));
        assertEquals(68, p2.getBookScore(book3));
        assertEquals(96, p2.getBookScore(book4));
        assertEquals(105, p2.getBookScore(book5));

        assertEquals(0, p3.getBookScore(book0));
        assertEquals(76, p3.getBookScore(book1));
        assertEquals(68, p3.getBookScore(book2));
        assertEquals(58, p3.getBookScore(book3));
        assertEquals(82, p3.getBookScore(book4));
        assertEquals(85, p3.getBookScore(book5));

        assertEquals(0, p4.getBookScore(book0));
        assertEquals(68, p4.getBookScore(book1));
        assertEquals(62, p4.getBookScore(book2));
        assertEquals(53, p4.getBookScore(book3));
        assertEquals(75, p4.getBookScore(book4));
        assertEquals(75, p4.getBookScore(book5));

        assertEquals(0, p5.getBookScore(book0));
        assertEquals(28, p5.getBookScore(book1));
        assertEquals(32, p5.getBookScore(book2));
        assertEquals(28, p5.getBookScore(book3));
        assertEquals(40, p5.getBookScore(book4));
        assertEquals(25, p5.getBookScore(book5));

        assertEquals(0, p6.getBookScore(book0));
        assertEquals(150, p6.getBookScore(book1));
        assertEquals(160, p6.getBookScore(book2));
        assertEquals(150, p6.getBookScore(book3));
        assertEquals(160, p6.getBookScore(book4));
        assertEquals(130, p6.getBookScore(book5));
    }

    @org.junit.Test
    public void willEnjoyBookTest() {
        assertFalse(p1.willEnjoyBook(book0));
        assertTrue(p1.willEnjoyBook(book1));
        assertTrue(p1.willEnjoyBook(book2));
        assertTrue(p1.willEnjoyBook(book3));
        assertTrue(p1.willEnjoyBook(book4));
        assertTrue(p1.willEnjoyBook(book5));

        assertFalse(p2.willEnjoyBook(book0));
        assertTrue(p2.willEnjoyBook(book1));
        assertTrue(p2.willEnjoyBook(book2));
        assertTrue(p2.willEnjoyBook(book3));
        assertTrue(p2.willEnjoyBook(book4));
        assertTrue(p2.willEnjoyBook(book5));

        assertFalse(p3.willEnjoyBook(book0));
        assertTrue(p3.willEnjoyBook(book1));
        assertTrue(p3.willEnjoyBook(book2));
        assertFalse(p3.willEnjoyBook(book3));
        assertTrue(p3.willEnjoyBook(book4));
        assertTrue(p3.willEnjoyBook(book5));


        assertFalse(p4.willEnjoyBook(book0));
        assertTrue(p4.willEnjoyBook(book1));
        assertTrue(p4.willEnjoyBook(book2));
        assertTrue(p4.willEnjoyBook(book3));
        assertTrue(p4.willEnjoyBook(book4));
        assertTrue(p4.willEnjoyBook(book5));

        assertFalse(p5.willEnjoyBook(book0));
        assertFalse(p5.willEnjoyBook(book1));
        assertFalse(p5.willEnjoyBook(book2));
        assertFalse(p5.willEnjoyBook(book3));
        assertFalse(p5.willEnjoyBook(book4));
        assertFalse(p5.willEnjoyBook(book5));

        assertTrue(p6.willEnjoyBook(book0));
        assertTrue(p6.willEnjoyBook(book1));
        assertTrue(p6.willEnjoyBook(book2));
        assertTrue(p6.willEnjoyBook(book3));
        assertTrue(p6.willEnjoyBook(book4));
        assertTrue(p6.willEnjoyBook(book5));
    }

    static Library lib1 = new Library(3, 1, 3);

    /**
     * two checks for library class objects.
     */

    @org.junit.Test
    public void testlib1() {
        // maxCap = 3, maxBorrow = 1, maxPatron = 3

        // we haven't put any books yet, should be empty.
        assertFalse(lib1.isBookIdValid(5));
        assertFalse(lib1.isBookIdValid(3));
        assertFalse(lib1.isBookIdValid(-1));
        assertFalse(lib1.isBookIdValid(0));


        assertEquals(lib1.addBookToLibrary(book1), lib1.getBookId(book1));
        assertEquals(lib1.addBookToLibrary(book1),
                lib1.getBookId(book1)); // we already added so can't add again, prev index return.
        assertEquals(lib1.addBookToLibrary(book1copy1), lib1.getBookId(book1copy1));
        assertEquals(lib1.addBookToLibrary(book2), lib1.getBookId(book2));

        boolean checkMinus = (lib1.addBookToLibrary(book5) < 0);
        assertTrue(checkMinus); // no more place left to add
        checkMinus = (lib1.addBookToLibrary(book4copy1) < 0);
        assertTrue(checkMinus); // no more place left to add

       /* // taken -  | non taken - 0,1,2
        assertEquals(0, lib1.getBookId(book1));
        assertEquals(1, lib1.getBookId(book1copy1));
        assertEquals(2, lib1.getBookId(book2)); */

        checkMinus = lib1.getBookId(book5) < 0;
        assertTrue(checkMinus);


        assertEquals(lib1.registerPatronToLibrary(p1), lib1.getPatronId(p1));
        assertEquals(lib1.registerPatronToLibrary(p2), lib1.getPatronId(p2));
        assertEquals(lib1.registerPatronToLibrary(p3), lib1.getPatronId(p3));

        checkMinus = lib1.registerPatronToLibrary(p3) < 0;
        assertFalse(checkMinus);
        checkMinus = lib1.registerPatronToLibrary(p4) < 0;
        assertTrue(checkMinus);
        checkMinus = lib1.registerPatronToLibrary(p5) < 0;
        assertTrue(checkMinus);

        int pat5 = lib1.getPatronId(p5);
        assertFalse(lib1.isPatronIdValid(pat5));

        assertFalse(lib1.isPatronIdValid(-1));

        int pat1 = lib1.getPatronId(p1);
        assertTrue(lib1.isPatronIdValid(pat1));

        int pat2 = lib1.getPatronId(p2);
        assertTrue(lib1.isPatronIdValid(pat2));

        int pat3 = lib1.getPatronId(p3);
        assertTrue(lib1.isPatronIdValid(pat3));

        int book1Index = lib1.getBookId(book1);
        int book2Index = lib1.getBookId(book1copy1);
        int book3Index = lib1.getBookId(book2);
        int bookNoneIndex = lib1.getBookId(book5);

        assertTrue(lib1.borrowBook(book1Index, pat1));
        assertFalse(lib1.borrowBook(book2Index, pat1)); // can't take anymore, reached
        // patron's
        // capicity
        assertFalse(lib1.borrowBook(book1Index, pat2)); // can't take because book 0 is taken

        // taken -  0(0)| non taken - 1,2

        assertTrue(lib1.borrowBook(book2Index, pat2));

        // taken -  0(0),1(2) | non taken - 2
        lib1.returnBook(book1Index);

        // taken -  1(2) | non taken - 0, 2
        assertFalse(lib1.borrowBook(book1Index, pat2)); // 2 has taken book 1 already, reached cap

        // taken -  1(2) | non taken - 2,0
        assertTrue(lib1.isBookIdValid(book1Index)); // exists in our libraray

        lib1.returnBook(0); // nothing happens

        assertTrue(lib1.isBookIdValid(book1Index)); // exists
        assertTrue(lib1.isBookIdValid(book2Index)); // exists
        assertTrue(lib1.isBookIdValid(book3Index)); // exists
        assertFalse(lib1.isBookIdValid(-4)); // nonesense
        assertFalse(lib1.isBookIdValid(bookNoneIndex)); // nonesense

        assertNull(lib1.suggestBookToPatron(-1)); // should be positive int
        assertNull(lib1.suggestBookToPatron(-4));

        assertEquals(book1, lib1.suggestBookToPatron(pat2));
        assertEquals(book1, lib1.suggestBookToPatron(pat1));


        // depends on how you actually picked the maximum
        assertEquals("[Lolita,Vladimir Nabokov,1955,15]",
                lib1.suggestBookToPatron(pat1).stringRepresentation());

        deleteLibraryContent();
    }

    static Library lib2 = new Library(20, 2, 10);

    @org.junit.Test
    public void testlib2() {
        // maxCap = 20, maxBorrow = 2, maxPatron = 10

        book1.returnBook();
        book2.returnBook();
        book3.returnBook();
        book1copy1.returnBook();
        book5.returnBook();
        book4copy1.returnBook();

        assertEquals(lib2.addBookToLibrary(book1), lib2.getBookId(book1));
        assertEquals(lib2.addBookToLibrary(book1copy1), lib2.getBookId(book1copy1));
        assertEquals(lib2.addBookToLibrary(book2), lib2.getBookId(book2));
        assertEquals(lib2.addBookToLibrary(book3copy1), lib2.getBookId(book3copy1));
        assertEquals(lib2.addBookToLibrary(book3), lib2.getBookId(book3));
        assertEquals(lib2.addBookToLibrary(book4), lib2.getBookId(book4));
        assertEquals(lib2.addBookToLibrary(book5), lib2.getBookId(book5));
        assertEquals(lib2.addBookToLibrary(book0), lib2.getBookId(book0));

        // taken - | non-taken - 0,1,2,3,4,5,6,7
        assertEquals(lib2.registerPatronToLibrary(p1), lib2.getPatronId(p1));
        assertEquals(lib2.registerPatronToLibrary(p2), lib2.getPatronId(p2));
        assertEquals(lib2.registerPatronToLibrary(p3), lib2.getPatronId(p3));
        assertEquals(lib2.registerPatronToLibrary(p4), lib2.getPatronId(p4));
        assertEquals(lib2.registerPatronToLibrary(p5), lib2.getPatronId(p5));
        assertEquals(lib2.registerPatronToLibrary(p6), lib2.getPatronId(p6));
        assertNotEquals(lib2.registerPatronToLibrary(p1), lib2.getPatronId(p4));

       /* assertEquals(1, lib2.registerPatronToLibrary(p2));
        assertEquals(2, lib2.registerPatronToLibrary(p3));
        assertEquals(3, lib2.registerPatronToLibrary(p4));
        assertEquals(4, lib2.registerPatronToLibrary(p5));
        assertEquals(5, lib2.registerPatronToLibrary(p6));*/

        Patron pNone = new Patron("pickles", "in a jar", 5, 5, 5, 34);
        int pat6 = lib2.getPatronId(p6);
        int pat5 = lib2.getPatronId(p5);
        int pat4 = lib2.getPatronId(p4);
        int pat3 = lib2.getPatronId(p3) ;
        int pat2 = lib2.getPatronId(p2);
        int pat1 = lib2.getPatronId(p1);
        int pat0 = lib2.getPatronId(pNone);

        assertTrue(lib2.isPatronIdValid(pat5));
        assertFalse(lib2.isPatronIdValid(-4));
        assertFalse(lib2.isPatronIdValid(pat0));

        int book1Index = lib2.getBookId(book1);
        int book2Index = lib2.getBookId(book1copy1);
        int book3Index = lib2.getBookId(book2);
        int book4Index = lib2.getBookId(book3copy1);
        int book5Index = lib2.getBookId(book3);
        int book6Index = lib2.getBookId(book4);
        int book7Index = lib2.getBookId(book5);
        int book8Index = lib2.getBookId(book0);



        assertTrue(lib2.borrowBook(book1Index, pat1));
        // taken - 0(0) | non-taken - 1,2,3,4,5,6,7
        assertFalse(lib2.borrowBook(book1Index, pat2)); // book already borrowed
        // taken - 0(0) | non-taken - 1,2,3,4,5,6,7
        assertTrue(lib2.borrowBook(book4Index, pat1));
        assertFalse(lib2.borrowBook(book6Index, pat1)); // taken enough already


        assertTrue(lib2.borrowBook(book2Index, pat2)); // can take book

        // depends on how you actually picked the maximum
        assertEquals("[The Great Gatsby,F. Scott Fitzgerald,1955,16]",
                lib2.suggestBookToPatron(pat1).stringRepresentation());
        assertEquals("[Pride and Prejudice,Jane Austen,1955,13]",
                lib2.suggestBookToPatron(pat2).stringRepresentation());
        assertEquals("[Pride and Prejudice,Jane Austen,1955,13]",
                lib2.suggestBookToPatron(pat3).stringRepresentation());
        assertEquals("[The Great Gatsby,F. Scott Fitzgerald,1955,16]",
                lib2.suggestBookToPatron(pat4).stringRepresentation());

        // does not
        assertNull(lib2.suggestBookToPatron(pat5));
        assertNull(lib2.suggestBookToPatron(-5));
        assertNull(lib2.suggestBookToPatron(pat0));

        deleteLibraryContent();
    }
}
