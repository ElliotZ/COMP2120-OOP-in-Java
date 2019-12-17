/**
 * Class definition of the Book class. extends Item, implements Comparable by comparing the author name.
 * @author zhu15a
 * Date: Nov 19, 2019
 */
public class Book extends Item implements Comparable<Book> {
	/**
	 * Serial Version UID as of Nov 19, 2019.
	 */
	private static final long serialVersionUID = 20191119001L;
	
	// attributes
	private String title;  // non-empty
	private String author;  // non-empty
	private int year;  // 0 to 3500;
	
	// constructors
	/**
	 * Default constructor. <br>
	 *  - Sets title to "foo", author to "bar", and year to 1900. <br>
	 *  - Sets super class fields Title to "Book", price to 0.0, and quantity to 0.
	 */
	public Book() {
		super.setTitle("Book");
		super.setPrice(0.0);
		super.setQuantity(0);
		this.title = "foo";
		this.author = "bar";
		year = 1900;
	}
	
	/**
	 * overloaded constructor.
	 * @param title non-empty string.
	 * @param author non-empty string.
	 * @param year int between 0-3500.
	 * @param price positive double
	 * @param quantity positive or 0 int.
	 */
	public Book(String title, String author, int year, double price, int quantity) {
		this();
		if (isValidString(title) && isValidString(author) && isValidYear(year)) {
			this.title = title;
			this.author = author;
			this.year = year;
		}
		super.setPrice(price);
		super.setQuantity(quantity);
	}
	
	// helper methods
	/**
	 * @param str a String.
	 * @return true if str is non-empty. false otherwise.
	 */
	private boolean isValidString(String str) {
		return !str.isEmpty();
	}
	
	/**
	 * @param year an int.
	 * @return true if year is between 0 and 3500. false otherwise.
	 */
	private boolean isValidYear(int year) {
		return year > 0 && year < 3500;
	}
	
	//methods
	/**
	 * @return the title of this book.
	 */
	public String getBookTitle() {
		return this.title;
	}
	
	/**
	 * @return the author of this book.
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * @return the year of this book.
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * @return sample return <br>
	 *         Item ID: 2 <br>
	 *         Item type: Book <br>
	 *         Price: $19.99 <br>
	 *         Quantity: 2 <br>
	 *         Description: Lewis Carroll, "Alice in Wonderland" (1865)
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" +
			   "Description: " + this.getAuthor() + ", \"" + this.getBookTitle() + "\" (" + this.getYear() + ") ";

	}

	/* (non-Javadoc)
	 * @see Item#Display()
	 */
	@Override
	public String Display() {
		System.out.println(this);
		return this.toString();
	}

	/* (non-Javadoc)
	 * @see Comparable#compareTo()
	 */
	@Override
	public int compareTo(Book o) {
		return this.author.compareTo(o.getAuthor());
	}

}
