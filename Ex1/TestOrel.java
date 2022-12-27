
/**
 * Created by: Evyatar
 * version 5.0
 * Changes history:
 * 23:07 2020 - Orel -    willEnjoyBook can be >= and not >.
 * Test flow was adjusted accordingly in "test Library methods".
 * <p>
 * 01:37 2018 - Kali -    When adding a patron that was already registered to the library, the
 * return value should by a !negative number! (as far as I understand the API),
 * unlike a book that was added twice - in that case should return existing book
 * id. Changed tests: 8.1, 14, 28.
 * 01:47 24 2018 - Kali -    Reverted changes (8.1, 14) due to forum answer.
 * <p>
 * 21:49 25 2018 - Evyatar - Now support any non-negative int's as ID's for books and patrons. Added two
 * libraries tests. willEnjoyBook has to be > and not >=.
 */
class TestOrel {
    TestOrel() {
        test5();
    }

    public void test5() {

        // set up
        Book book1 = new Book("book1", "author1", 2001,
                2, 3, 1);
        Book book2 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book3 = new Book("book2", "author2", 2002,
                8, 3, 6);
        Book book4 = new Book("book4", "author4", 2004,
                10, 10, 10);

        int book1ID, book2ID, book3ID, book4ID;

        Patron patron1 = new Patron("patron1", "last1", 3,
                5, 1, 22);
        Patron patron2 = new Patron("patron1", "last1", 2,
                1, 1, 0);
        Patron patron3 = new Patron("patron3", "last3", 2,
                1, 1, 0);

        int patron1ID1, patron2ID1, patron1ID2, patron2ID2;

        Library lib = new Library(3, 2, 2);


        // test book
        System.out.println("test Book methods");
        System.out.println();

        System.out.println(book1.stringRepresentation().equals("[book1,author1,2001,6]"));

        System.out.println(book1.getLiteraryValue() == 2 + 3 + 1);

        System.out.println(book1.getCurrentBorrowerId() < 0);

        book1.setBorrowerId(5);
        System.out.println(book1.getCurrentBorrowerId() == 5);
        book1.returnBook();


        // test patron
        System.out.println();

        System.out.println("test Patron methods");
        System.out.println();

        System.out.println("0.1:" + patron1.stringRepresentation().equals("patron1 last1"));

        System.out.println("0.2:" + (patron1.getBookScore(book1) == 3 * 2 + 5 * 3 + 1 * 1));

        System.out.println("0.3:" + patron1.willEnjoyBook(book1)); // 22>=22 [Summer 2020]
        System.out.println("0.4:" + patron1.willEnjoyBook(book2));


        // test library
        System.out.println();

        System.out.println("test Library methods");
        System.out.println();

        System.out.println("1: " + (!lib.isBookIdValid(1)));
        System.out.println("1.1: " + (!lib.isBookIdValid(88)));
        System.out.println("1.2: " + (!lib.isBookAvailable(1)));

        book1ID = lib.addBookToLibrary(book1);
        System.out.println("2: " + (book1ID >= 0));
        System.out.println("3: " + lib.isBookIdValid(book1ID));

        book2ID = lib.addBookToLibrary(book2);
        System.out.println("4: " + (book2ID >= 0));
        System.out.println("4.1: " + (book2ID != book1ID));
        System.out.println("4.2: " + (lib.isBookIdValid(book2ID)));
        System.out.println("4.3: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book, should
        // return the index of original and shouldn't save another copy.

        book3ID = lib.addBookToLibrary(book3); // book with same  arguments
        System.out.println("5: " + (book3ID >= 0 && book3ID != book1ID && book3ID != book2ID));

        System.out.println("6: " + (lib.addBookToLibrary(book4) < 0)); // no room
        System.out.println("6.1: " + (lib.addBookToLibrary(book2) == book2ID)); // add the same book when
        // there is no room.

        System.out.println("7: " + (!lib.isPatronIdValid(1)));
        System.out.println("7.1: " + (!lib.isPatronIdValid(8)));

        patron1ID1 = lib.registerPatronToLibrary(patron1);
        System.out.println("8:" + (patron1ID1 >= 0));
        System.out.println("9:" + (lib.registerPatronToLibrary(patron1) == patron1ID1));
        System.out.println("10: " + (lib.isPatronIdValid(patron1ID1)));

        patron2ID1 = lib.registerPatronToLibrary(patron2);
        System.out.println("11: " + (lib.registerPatronToLibrary(patron2) == patron2ID1));
        System.out.println("12: " + (lib.isPatronIdValid(patron2ID1)));

        System.out.println("13: " + (lib.registerPatronToLibrary(patron3) < 0));
        System.out.println("14: " + (lib.registerPatronToLibrary(patron1) == patron1ID1));

        System.out.println("15: " + (lib.getPatronId(patron2) == patron2ID1));

        // tests for 'borrowBook'
        System.out.println("16: " + (lib.isBookAvailable(book1ID)));

        // actually will enjoy (22>=22)
        System.out.println("17: " + (lib.borrowBook(book1ID, patron1ID1)));

        System.out.println("18: " + (!lib.borrowBook(book1ID, patron2ID1)));   // the book is taken
        System.out.println("19: " + (!lib.isBookAvailable(book1ID)));
        System.out.println("20: " + (lib.borrowBook(book2ID, patron1ID1)));
        System.out.println("21: " + (!lib.borrowBook(80, patron2ID1))); // book id is not valid
        System.out.println("22: " + (!lib.borrowBook(book3ID, 80))); // patron id is not
        // valid
        System.out.println("23: " + (!lib.borrowBook(book2ID, patron1ID1)));
        System.out.println("24: " + (!lib.borrowBook(book3ID, patron1ID1))); // too many books for one patron
        lib.returnBook(book1ID);
        System.out.println("25: " + (lib.borrowBook(book3ID, patron2ID1)));
        System.out.println("26: " + (book1.getCurrentBorrowerId() < 0));


        System.out.println("27: " + (lib.getBookId(lib.suggestBookToPatron(patron2ID1)) == book1ID));
        System.out.println("28: " + (lib.suggestBookToPatron(patron1ID1) == book1));

        book1.comicValue -= 1;

        // null because book2 and book3 are taken and he will not enjoy book1
        System.out.println("29: " + (lib.suggestBookToPatron(patron1ID1) == null));

        // two libraries tests
        Library lib2 = new Library(1, 1, 2);
        System.out.println();
        System.out.println("two libraries tests");
        System.out.println();

        System.out.println("30: " + (!lib2.isBookIdValid(0)));

        book4ID = lib2.addBookToLibrary(book4);
        System.out.println("31: " + (book4ID >= 0));


        patron1ID2 = lib2.registerPatronToLibrary(patron1);
        patron2ID2 = lib2.registerPatronToLibrary(patron2);

        System.out.println("32: " + (patron1ID2 >= 0));
        System.out.println("33: " + (patron2ID2 >= 0));
        System.out.println("34: " + (patron1ID2 != patron2ID2));

        System.out.println("35: " + (lib2.borrowBook(book4ID, patron1ID2))); // patron1 has the max number
        // of books in library1, should be able to borrow in another library.

    }
}
