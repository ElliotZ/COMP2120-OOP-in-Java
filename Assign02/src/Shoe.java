/**
 * Class definition of the Shoe class. extends Item, implements Comparable by comparing size.
 * @author zhu15a
 * Date: Nov 19, 2019
 */
public class Shoe extends Item implements Comparable<Shoe> {
	/**
	 * Serial Version UID as of Nov 19, 2019.
	 */
	private static final long serialVersionUID = 20191119003L;
	
	// attributes
	private String colour;  // any value from the following: white, silver, red, beige, brown, blue, black, pink
	private double size;  // between 0 and 15
	
	// constructors
	/**
	 * Default constructor. <br>
	 *  - Sets color to "white" and size to 1.0. <br>
	 *  - Sets super class fields Title to "Shoe", price to 0.0, and quantity to 0.
	 */
	public Shoe() {
		super.setTitle("Shoe");
		super.setPrice(0.0);
		super.setQuantity(0);
		this.colour = "white";
		this.size = 1.0;
	}
	
	/**
	 * overloaded constructor.
	 * @param colour any value from the following: white, silver, red, beige, brown, blue, black, pink
	 * @param size double between 0 and 15
	 * @param price non-negative double
	 * @param quantity non-negative int
	 */
	public Shoe(String colour, double size, double price, int quantity) {
		this();
		if (isValidColour(colour) && isValidSize(size)) {
			this.colour = colour;
			this.size = size;
		}
		super.setPrice(price);
		super.setQuantity(quantity);
	}
	
	// helper methods
	/**
	 * all valid values: white, silver, red, beige, brown, blue, black, pink
	 * @param colour a String.
	 * @return true if colour is one of the valid values. false otherwise.
	 */
	public boolean isValidColour(String colour) {
		return colour.contentEquals("white") 
				|| colour.contentEquals("silver") 
				|| colour.contentEquals("red") 
				|| colour.contentEquals("beige") 
				|| colour.contentEquals("brown") 
				|| colour.contentEquals("blue")
				|| colour.contentEquals("black") 
				|| colour.contentEquals("pink");
	}
	
	/**
	 * @param size a double
	 * @return true if size is between 0 and 15. false otherwise.
	 */
	public boolean isValidSize(double size) {
		return size > 0.0 && size < 15.0;
	}
	
	// methods
	/**
	 * @return the colour of the shoes.
	 */
	public String getColour() {
		return this.colour;
	}
	
	/**
	 * @return the size of the shoes.
	 */
	public double getSize() {
		return this.size;
	}

	/**
	 * @return sample return <br>
	 *         Item ID: 4 <br>
	 *         Item type: Shoe <br>
	 *         Price: $249.99 <br>
	 *         Quantity: 1 <br>
	 *         Description: Size 9.5 white shoes. <br>
	 */
	@Override
	public String toString() {
		return super.toString() + "\n" +
			   "Description: Size " + this.getSize() + " " + this.getColour() + " shoes.";

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
	public int compareTo(Shoe o) {
		if (this.getSize() - o.getSize() < 0) {
			return -1;
		} else if (this.getSize() - o.getSize() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
