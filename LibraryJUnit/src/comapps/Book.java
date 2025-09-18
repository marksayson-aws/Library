package comapps;

/**
 * Class for Book with details: title, author,
 * publication year, and rental status.
 */
public class Book {
  /**
   * String for book title.
   */
  private String title;
  /**
   * string for author name.
   */
  private String author;
  /**
   * integer for year published.
   */
  private int yearPublished;
  /**
   * Variables for book details.
   */
  private boolean isRented;

  /**
   * Constructor initializes the book.
   * @param titleParam   title of book
   * @param authorParam  author of book
   * @param yearPublishedParam  year published
   */
  public Book(final String titleParam, final String authorParam,
          final int yearPublishedParam) {
      this.title = titleParam;
      this.author = authorParam;
      this.yearPublished = yearPublishedParam;
      this.isRented = false;  // Default value
  }


  /**
   * Method to mark the book as rented.
   */
  public void rent() {
    isRented = true;  // Sets the rental status to true when rented
  }

  /**
   * Optional getters for accessing the book details and rental status.
   * @return isRented
   */
  public boolean isRented() {
    return isRented;  // Returns rental status (true if rented, false otherwise)
  }

  /**
   * Returns the title of the book.
   * @return title
   */
  public String getTitle() {
    return title;  // Returns the title of the book
  }

  /**
   * Returns the author of the book.
   * @return author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Returns the year the book was published.
   * @return yearPublished
   */
  public int getYearPublished() {
    return yearPublished;
  }

  // No issues found, the class is correctly
  // implementing the required functionality.
  // It properly encapsulates the properties of a book and provides
  // the necessary functionality to rent it.
}

