/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library
 * to be able to check out books, if a copy of the requested book is available.
 */
class Library {

    private static final int AVAILABLE = -1;
    private static final int DOESNT_BELONG_LIBRARY = -1;
    private final int maxBookCapacity, maxPatronCapacity;
    private int maxBorrowedBooks;
    private final Book[] bookStorage;
    private final Patron[] patronsRegistered;
    private final int[] booksPatronBorrowed;
    private final int bookEnd; // Represents ending index of bookStorage.
    private final int patronEnd; // Represents ending index of patronsRegistered and booksPatronBorrowed.
    private static int totalBooksAdded = 0, totalPatronsRegistered = 0;


    /**
     * Creates a new library with the given parameters.
     * @param maxBookCapacity The maximal number of books this library can hold.
     * @param maxBorrowedBooks The maximal number of books this library allows a single patron
     *                         to borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;

        bookEnd = totalBooksAdded + maxBookCapacity;
        patronEnd = totalPatronsRegistered + maxPatronCapacity;

        bookStorage = new Book[bookEnd];
        patronsRegistered = new Patron[patronEnd];
        booksPatronBorrowed = new int[patronEnd];

        totalBooksAdded += maxBookCapacity;
        totalPatronsRegistered += maxPatronCapacity;
    }


    /**
     * Adds the given book to this library, if there is place available,
     * and it isn't already in the library.
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully
     *         added, or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        int bookId = getBookId(book);
        if (bookId != DOESNT_BELONG_LIBRARY) // book was already in the library.
            return bookId;

        for (int i=bookEnd-maxBookCapacity; i<bookEnd; i++) {
            if (!isBookIdValid(i)) { // there is a spot available.
                bookStorage[i] = book;
                return i;
            }
        }

        return DOESNT_BELONG_LIBRARY;
    }


    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return (bookEnd - maxBookCapacity <= bookId) && (bookId < bookEnd) &&
                (bookStorage[bookId] != null);
    }


    /**
     * Returns the non-negative id number of the given book if he is owned by this library, -1 otherwise.
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i=bookEnd-maxBookCapacity; i<bookEnd; i++) {
            if (bookStorage[i] == book)
                return i;
        }
        return DOESNT_BELONG_LIBRARY;
    }


    /**
     * Returns true if the book with the given id is available, false otherwise.
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        return isBookIdValid(bookId) && (bookStorage[bookId].getCurrentBorrowerId() == AVAILABLE);
    }


    /**
     * Registers the given Patron to this library, if there is a spot available.
     * @param patron The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron was successfully
     *         registered or if the patron was already registered. a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        int patronId = getPatronId(patron);
        if (patronId != DOESNT_BELONG_LIBRARY)
            return patronId;

        for (int i=patronEnd-maxPatronCapacity; i<patronEnd; i++) {
            if (!isPatronIdValid(i)) {
                patronsRegistered[i] = patron;
                return i;
            }
        }

        return DOESNT_BELONG_LIBRARY;
    }


    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        return (patronEnd - maxPatronCapacity <= patronId) && (patronId < patronEnd) &&
        (patronsRegistered[patronId] != null);
    }


    /**
     * Returns the non-negative id number of the given patron if he is registered
     * to this library, -1 otherwise.
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to this library,
     *         -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i=patronEnd - maxPatronCapacity; i<patronEnd; i++) {
            if (patronsRegistered[i] == patron)
                return i;
        }
        return DOESNT_BELONG_LIBRARY;
    }


    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id,
     * if this book is available, the given patron isn't already borrowing the maximal number
     * of books allowed, and if the patron will enjoy this book.
     * @param bookId The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if (!isBookAvailable(bookId) || !isPatronIdValid(patronId))
            return false;

        Book book = bookStorage[bookId];
        Patron patron = patronsRegistered[patronId];
        if ((booksPatronBorrowed[patronId] < maxBorrowedBooks) && (patron.willEnjoyBook(book))) {
            book.setBorrowerId(patronId);
            booksPatronBorrowed[patronId]++;
            return true;
        }

        return false;
    }


    /**
     * Return the given book.
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (!isBookIdValid(bookId))
            return;
        Book book = bookStorage[bookId];
        int patronId = book.getCurrentBorrowerId();
        if (patronId != DOESNT_BELONG_LIBRARY) {
            book.returnBook();
            booksPatronBorrowed[patronId]--;
        }
    }


    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given ID will enjoy the most.
     *         Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        if (!isPatronIdValid(patronId))
            return null;
        Patron patron = patronsRegistered[patronId];
        Book result = null;
        int highestBookScore = -1;
        for (Book book: bookStorage) {
            if (book == null)
                continue;
            int bookScore = patron.getBookScore(book);
            /* Make sure that no one borrows the book, the patron will enjoy it
            and the book's score is the highest. */
            if (book.getCurrentBorrowerId() == AVAILABLE && patron.willEnjoyBook(book) &&
                    bookScore > highestBookScore) {
                result = book;
                highestBookScore = bookScore;
            }
        }
        return result;
    }
}
