import java.text.DecimalFormat;

/**
 * Class definition of the GiftCard class. extends Item, implements Comparable by comparing the label.
 * @author zhu15a
 * Date: Nov 19, 2019
 */
public class GiftCard extends Item implements Comparable<GiftCard> {
	/**
	 * Serial Version UID as of Nov 19, 2019.
	 */
	private static final long serialVersionUID = 20191119002L;
	
	// attributes
	private double label;  // any value from the following: 5.00, 10.00, 20.00, 50.00, 100.00, 200.00, 500.00
	private String manufacturer;  // non-empty
	
	// constructors
	/**
	 * Default constructor. <br>
	 *  - Sets manufacturer to "foo" and label to 5.0. <br>
	 *  - Sets super class fields Title to "Gift card", price to 0.0, and quantity to 0.
	 */
	public GiftCard() {
		super.setTitle("Gift card");
		super.setPrice(0.0);
		super.setQuantity(0);
		this.label = 5.0;
		this.manufacturer = "foo";
	}
	
	/**
	 * overloaded constructor.
	 * @param label any value from the following: 5.00, 10.00, 20.00, 50.00, 100.00, 200.00, 500.00
	 * @param manufacturer non-empty string
	 * @param price non-negative double
	 * @param quantity non-negative int
	 */
	public GiftCard(double label, String manufacturer, double price, int quantity) {
		this();
		if (isValidLabel(label) && isValidMftr(manufacturer)) {
			this.label = label;
			this.manufacturer = manufacturer;
		}
		super.setPrice(price);
		super.setQuantity(quantity);
	}
	
	// helper methods
	/**
	 * all valid values: 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0.
	 * @param label a double.
	 * @return true if label is one of the valid values. false otherwise.
	 */
	public boolean isValidLabel(double label) {
		return label == 5.0 || label == 10.0 || label == 20.0 || label == 50.0
				|| label == 100.0 || label == 200.0 || label == 500.0;
	}
	
	/**
	 * @param manufacturer a String.
	 * @return true if manufacturer is non-empty, false otherwise.
	 */
	public boolean isValidMftr(String manufacturer) {
		return !manufacturer.isEmpty();
	}

	// methods
	/**
	 * @return the value on the gift card label
	 */
	public double getLabel() {
		return this.label;
	}
	
	/**
	 * @return the manufacturer who issued the gift card
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	/**
	 * @return sample return <br>
	 *         Item ID: 3 <br>
	 *         Item type: Gift card <br>
	 *         Price: $49.99 <br>
	 *         Quantity: 1 <br>
	 *         Description: Amazon, $50.00 <br>
	 */
	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return super.toString() + "\n" +
			   "Description: " + this.getManufacturer() + ", $" + formatter.format(this.getLabel());

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
	public int compareTo(GiftCard o) {
		if (this.getLabel() - o.getLabel() < 0) {
			return -1;
		} else if (this.getLabel() - o.getLabel() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
