package comapps;

import java.util.ArrayList;

/**
 * The BookRentalSystem class manages a library of books.
 * It allows adding books, renting books,
 * and displaying all books or only rented books.
 * Code Reviewer: Christine Marasigan, Trainee No.8, ACTION B40A
 */
public final class BookRentalSystem {

  // Private constructor to prevent instantiation of the BookRentalSystem class.
  // This is used to ensure that the class functions like a
  // singleton or utility class.
  // Fix indentation
  private BookRentalSystem() { }

  /**
   * Static list to hold the library of books.
   */
  private static ArrayList<Book> library = new ArrayList<>();

  /**
   * Adds Book to library.
   * @param book
   */
  public static void addBooks(final Book book) {
    library.add(book);  // Adds the book to the library list
  }

  /**
   * Rents a book based on its index in the library.
   * @param index
   */
  public static void rentBooks(final int index) {
    if (index < library.size()) {
      library.get(index).rent();
    } else {
      // If the index is invalid, print an error message.
      System.out.println("Invalid index: " + index);
    }
  }

  /**
   * Clears the library, typically used for resetting the library during tests.
   */
  public static void clearLibrary() {
    library.clear();  // Clears the entire library list
  }

  /**
   * Gets the current size of the library.
   * @return library
   */
  public static int getLibrarySize() {
    return library.size();  // Returns the number of books in the library
  }

  /**
   * Returns the entire list of books in the library.
   * @return library
   */
  public static ArrayList<Book> getLibrary() {
    return library;  // Returns the list of books in the library
  }

  /**
   * Displays all books in the library.
   * Prints each book's title, author, and publication year.
   */
  public static void displayAllBooks() {
    for (Book b : library) {
      System.out.println(b.getTitle() + "\t"
      + b.getAuthor() + "\t" + b.getYearPublished());
    }
  }

  /**
   * Displays only the rented books.
   * Prints each rented book's title, author, and publication year.
   */
  public static void displayRentedBooks() {
    System.out.println("Books rented:");
    for (Book b : library) {
      if (b.isRented()) {  // Checks if the book is rented
        System.out.println(b.getTitle() + "\t"
        + b.getAuthor() + "\t" + b.getYearPublished());
      }
    }
  }

  /**
   * Main method used to test adding, renting,
   * and displaying books in the system.
   * It adds books to the library, rents a
   * couple of them, and displays the results.
   * @param args
   */
  public static void main(final String[] args) {
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

    // Rent books based on their index in the library
    BookRentalSystem.rentBooks(0); // Renting "The Lord of the Rings"
    BookRentalSystem.rentBooks(2); // Renting "The Tipping Point"

    // Display all books in the library
    displayAllBooks();

    // Display only rented books
    displayRentedBooks();
  }
}
