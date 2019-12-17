/**
 * class description for Invoice class.
 * this class aggregates the Item class using an internal array of Item.
 * attributes: Item[] itemArray, int numItems
 * constructor: default, predefined with one initial item
 * helper methods: subtotal, salesTax, total
 * methods: length, addItem
 * overrode inherited method: toString
 * 
 * @author zhu15a
 *
 */
public class Invoice {
	
	// attributes
	private Item[] itemArray;
	private int numItems;
	
	// constructors
	/**
	 * default constructor. 
	 */
	public Invoice() {
		itemArray = new Item[10];
		numItems = 0;
	}
	
	/**
	 * constructor with one initial item.
	 * wrote this out of habit, not sure if it's useful
	 * @param init the initial item to be put in the invoice.
	 */
	public Invoice(Item init) {
		this();
		addItem(init);
	}
	
	// helper methods
	/**
	 * @return the subtotal of all the items in the invoice.
	 */
	private double subtotal() {
		double result = 0.0;
		for (int i = 0; i < this.length(); ++i) {
			result += itemArray[i].total();
		}
		return result;
	}
	
	/**
	 * @return the sales tax, at 6.25% rate.
	 */
	private double salesTax() {
		return this.subtotal() * 0.0625;
	}
	
	/**
	 * @return the total amount of this invoice, with tax.
	 */
	private double total() {
		return this.subtotal() + this.salesTax();
	}
	
	// methods
	/**
	 * @return the length of this invoice, which is equal to the number of items in the invoice.
	 */
	public int length() {
		return numItems;
	}
	
	/**
	 * pushes the passed item into the back of the invoice.
	 * @param item the item to be added.
	 * @return true if the operation is successful. for now it is always successful.
	 */
	public boolean addItem(Item item) {
		itemArray[numItems] = new Item(item);
		numItems++;
		return true;
	}
	
	/**
	 * overrides Object.toString()
	 * @return a formatted String representing the invoice.
	 */
	public String toString() {
		StringBuffer output = new StringBuffer();
		output.append(String.format("%-30s%10s%10s%10s", "Item", "Quantity", "Price", "Total"));
		
		// output.append(System.getProperty("line.seperator"));
		// have attempted to use this one but it returns "null" under eclipse run environment.
		
		output.append("\n");  // now using this one instead.
		
		for (int i = 0; i < this.length(); ++i) {
			output.append(itemArray[i].toString());
			output.append("\n");
		}
		
		output.append("\n");
		output.append(String.format("%-30s%30.2f", "Subtotal", this.subtotal()));
		output.append("\n");
		output.append(String.format("%-30s%30.2f", "6.25% sales tax", this.salesTax()));
		output.append("\n");
		output.append(String.format("%-30s%30.2f", "Total", this.total()));
		return output.toString();
	}

}
