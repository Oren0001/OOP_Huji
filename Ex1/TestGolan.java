public class TestGolan {
    TestGolan() {
        test1();
    }

    void test1() {
        Book book = new Book("test", "golan", 2020, 5, 6, 7);
        Book bookTest = new Book("test2", "golan", 2020, 5, 6, 7);
        int borrowerId = book.getCurrentBorrowerId();
        int value = book.getLiteraryValue();
        String str = book.stringRepresentation();
        if (borrowerId != -1 || value != 18 || !str.equals("[test,golan,2020,18]")) {
            System.out.println("test 1 failed");
            return;
        }
        System.out.println("test 1 Done");

        Patron golan = new Patron("golan", "shabi", 1, 2, 3, 100);
        Patron yam = new Patron("yam", "fine", 1, 1, 1, 1);
        Patron maor = new Patron("maor", "mizrahi", 1, 2, 3, 1);
        boolean enjoyment = golan.willEnjoyBook(book) || !yam.willEnjoyBook(book);
        boolean score = golan.getBookScore(book) != 38 || yam.getBookScore(book) != 18;
        if (enjoyment || score || !golan.stringRepresentation().equals("golan shabi")) {
            System.out.println("test 2 failed");
            return;
        }
        System.out.println("test 2 Done");

        Library library = new Library(1, 1, 2);
        int golanId = library.registerPatronToLibrary(golan);
        int yamId = library.registerPatronToLibrary(yam);
        int maorId = library.registerPatronToLibrary(maor);
        if (golanId < 0 || yamId < 0 || maorId >= 0) {
            System.out.println("test 3 failed");
            return;
        }

        System.out.println("test 3 Done");
        golanId = library.registerPatronToLibrary(golan);
        yamId = library.registerPatronToLibrary(yam);
        if (golanId < 0 || yamId < 0) {
            System.out.println("test 4 failed");
            return;
        }

        System.out.println("test 4 Done");

        boolean validPatron = library.isPatronIdValid(golanId) && library.isPatronIdValid(yamId);
        boolean notValidPatron = library.isPatronIdValid(yamId + golanId + 1);
        if (!validPatron || notValidPatron) {
            System.out.println("test 5 failed");
            return;
        }

        System.out.println("test 5 Done");
        int bookId = library.addBookToLibrary(book);
        boolean validBook = library.isBookIdValid(bookId);
        boolean notValidBook = library.isBookIdValid(bookId + 1);
        if (!validBook || notValidBook) {
            System.out.println("test 6 failed");
            return;
        }

        System.out.println("test 6 Done");
        bookTest.setBorrowerId(book.getCurrentBorrowerId());
        if (golanId != library.getPatronId(golan) || bookId != library.getBookId(book) || library.getBookId(bookTest) != -1
                || library.getPatronId(maor) != -1) {
            System.out.println("test 7 failed");
            return;
        }

        System.out.println("test 7 Done");


        if (!library.isBookAvailable(bookId) || !library.borrowBook(bookId, yamId) || library.isBookAvailable(bookId)) {
            System.out.println("test 8 failed");
            return;
        }

        System.out.println("test 8 Done");

        library.returnBook(bookId);
        if (!library.isBookAvailable(bookId)) {
            System.out.println("test 9 failed");
            return;
        }

        System.out.println("test 9 Done");


        if (library.suggestBookToPatron(golanId) != null || library.suggestBookToPatron(yamId) != book) {
            System.out.println("test 10 failed");
            return;
        }

        System.out.println("test 10 Done");
    }
}
