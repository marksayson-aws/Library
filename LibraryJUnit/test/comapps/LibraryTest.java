package comapps;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@TestMethodOrder(OrderAnnotation.class)
class LibraryTest {
    /**
     * For checking string output.
     */
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        // Clear library before each test
        BookRentalSystem.clearLibrary();

        // Redirect System.out to capture printed output
        outContent.reset();
        System.setOut(new PrintStream(outContent));
    }

    @Order(1)
    @DisplayName("Test Case 1: Test Book Creation")
    @Test
    void testBookCreation() {
        final int year1 = 1954;
        final int year2 = 1960;
        final int year3 = 2000;
        final int year4 = 1997;

        // Add books to the library
        BookRentalSystem.addBooks(new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", year1));
        BookRentalSystem.addBooks(new FictionBook(
                "To Kill a Mockingbird", "Harper Lee", year2));
        BookRentalSystem.addBooks(new NonFictionBook(
                "The Tipping Point", "M. Gladwell", year3));
        BookRentalSystem.addBooks(new NonFictionBook(
                "Guns, Germs, and Steel", "Jared Diamond", year4));

        // Check each book
        for (Book b : BookRentalSystem.getLibrary()) {
            assertNotNull(b, "Book should not be null");
            assertFalse(b.isRented(), "Book should not be rented by default");
        }
    }

    @Order(2)
    @DisplayName("Test Case 2: Test Rent Method")
    @Test
    void testRentMethod() {
        // Arrange: create a book
        Book book = new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954);

        // Assert initial state
        assertFalse(book.isRented(), "Book should not be rented initially");

        // Act: call rent()
        book.rent();

        // Assert final state
        assertTrue(book.isRented(),
                "Book should be rented after calling rent()");
    }

    @Order(3)
    @DisplayName("Test Case 3: Test Add Books Method")
    @Test
    void testAddBooksMethod() {
        // Arrange: create 1 fiction and 1 non-fiction book
        BookRentalSystem.addBooks(new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954));
        BookRentalSystem.addBooks(new NonFictionBook(
                "The Tipping Point", "M. Gladwell", 2000));

        // Assert library size
        assertEquals(2, BookRentalSystem.getLibrarySize(),
                "Library should contain 2 books");
    }

    @Order(4)
    @DisplayName("Test Case 4: Test Rent Books with Valid Index")
    @Test
    void testRentBooksWithValidIndex() {
        // Arrange: create 1 fiction and 1 non-fiction book
        Book fbook = new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954);
        Book nonfbook = new NonFictionBook(
                "The Tipping Point", "M. Gladwell", 2000);

        BookRentalSystem.addBooks(fbook);
        BookRentalSystem.addBooks(nonfbook);

        // Act: rent the fiction book (index 0)
        BookRentalSystem.rentBooks(0);

        // Assert rented status
        assertTrue(fbook.isRented(), "Fictional Book should've been rented");
        assertFalse(nonfbook.isRented(),
                "Non-Fictional Book should not be rented");
    }


    @Order(5)
    @DisplayName("Test Case 5: Test Rent Book with Invalid Index")
    @Test
    void testRentBookWithInvalidIndex() {
        // Add a single book
        Book book = new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954);
        BookRentalSystem.addBooks(book);

        // Attempt to rent at an invalid index (e.g., 5)
        BookRentalSystem.rentBooks(5);

        // Capture the output
        String actualOutput = outContent.toString().trim();

        // Check that output contains "Invalid index"
        assertTrue(actualOutput.contains("Invalid index"),
                "Should print an invalid index message");

        // Ensure the valid book is still not rented
        assertFalse(book.isRented(), "Book should not be rented");
    }

    @Order(6)
    @DisplayName("Test Case 6: Test Display All Books")
    @Test
    void testDisplayAllBooks() {
        // Arrange: Add 1 fiction book and 1 non-fiction book
        Book fiction = new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954);
        Book nonFiction = new NonFictionBook(
                "The Tipping Point", "M. Gladwell", 2000);

        BookRentalSystem.addBooks(fiction);
        BookRentalSystem.addBooks(nonFiction);

        // Expected output string
        String expectedOutput =
            "The Lord of the Rings\tJ.R.R. Tolkien\t1954"
        + System.lineSeparator()
        + "The Tipping Point\tM. Gladwell\t2000" + System.lineSeparator();

        // Act: call display
        BookRentalSystem.displayAllBooks();

        // Assert: check captured output
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput,
                "Displayed books should match expected output");
    }

    @Order(7)
    @DisplayName("Test Case 7: Test Display Rented Books")
    @Test
    void testDisplayRentedBooks() {
        // Arrange: redirect System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Book fiction = new FictionBook(
                "The Lord of the Rings", "J.R.R. Tolkien", 1954);
        Book nonFiction = new NonFictionBook(
                "The Tipping Point", "M. Gladwell", 2000);

        BookRentalSystem.addBooks(fiction);
        BookRentalSystem.addBooks(nonFiction);

        // Rent only the non-fiction book
        BookRentalSystem.rentBooks(1);

        // Act: call method (prints to console)
        BookRentalSystem.displayRentedBooks();

        // Expected output
        String expectedOutput =
            "Books rented:" + System.lineSeparator()
            + "The Tipping Point\tM. Gladwell\t2000" + System.lineSeparator();

        // Assert
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    // EXTRA: Testing main method
    @DisplayName("EXTRA: Test Main")
    @Test
    void testMainMethod() {
        BookRentalSystem.main(new String[]{});

        String expected = "The Lord of the Rings\tJ.R.R. Tolkien\t1954"
                + System.lineSeparator()
                + "To Kill a Mockingbird\tHarper Lee\t1960"
                + System.lineSeparator()
                + "The Tipping Point\tM. Gladwell\t2000"
                + System.lineSeparator()
                + "Guns, Germs, and Steel\tJared Diamond\t1997"
                + System.lineSeparator()
                + "Books rented:"
                + System.lineSeparator()
                + "The Lord of the Rings\tJ.R.R. Tolkien\t1954"
                + System.lineSeparator()
                + "The Tipping Point\tM. Gladwell\t2000";
        assertEquals(expected, outContent.toString().trim());
    }



}
