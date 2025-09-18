package comapps;

/**
 * Constructor for NonFictionBook that calls the
 * parent constructor to initialize book details.
 */
public class NonFictionBook extends Book {
  NonFictionBook(final String title, final String author,
          final int yearPublish) {
    super(title, author, yearPublish);
  }

  // No issues found, the class properly extends
  // Book and calls the parent constructor.
  // The NonFictionBook class inherits all properties and methods
  // from Book without the need for modification.
}
