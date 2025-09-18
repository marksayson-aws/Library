package comapps;

/**
 * Constructor for FictionBook that calls the
 * parent constructor to initialize book details.
 */
public class FictionBook extends Book {
  FictionBook(final String title, final String author, final int yearPublish) {
    super(title, author, yearPublish);
  }

  // No issues found, the class properly extends
  // Book and calls the parent constructor.
  // The FictionBook class inherits all properties and methods from
  // Book without the need for modification.
}
