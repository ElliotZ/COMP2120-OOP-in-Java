import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Class Definition of the Item class. implements Serializable.
 * @author zhu15a 
 * Date: Nov 19, 2019
 */
public abstract class Item implements Serializable {
	/**
	 * Serial Version UID as of Nov 19, 2019.
	 */
	private static final long serialVersionUID = 20191119000L;
	
	// attributes
	private static int item_count = 0;  // auto incremented
	private int item_id;
	private double price;  // 0 or positive
	private int quantity;  // 0 or positive
	private String Title;  // non-empty;
	
	// constructor
	/**
	 * default constructor. <br>
	 *  - auto increments the counter and assigns item_id. <br>
	 *  - does nothing else. <br>
	 */
	public Item() {
		item_id = ++item_count;
	}
	
	//helper methods
	/**
	 * @param price the price to be checked.
	 * @return true if price is non-negative. false otherwise.
	 */
	private boolean isValidPrice(double price) {
		return !(price < 0);
	}
	
	/**
	 * @param quantity the quantity to be checked.
	 * @return true if quantity is non-negative. false otherwise.
	 */
	private boolean isValidQty(int quantity) {
		return !(quantity < 0);
	}
	
	/**
	 * @param title the title to be checked.
	 * @return true if title is non-empty. false otherwise.
	 */
	private boolean isValidTitle(String title) {
		return !title.isEmpty();
	}
	
	// methods
	/**
	 * Use with caution. This method is only intended to be used to set the item_count after objects of this class is deserialized.
	 * @param count number of items that has been deserialized.
	 * @return current value of item_count.
	 */
	public static int setItemCount(int count) {
		Item.item_count = count;
		return Item.item_count;
	}
	
	public static int getItemCount() {
		return Item.item_count;
	}
	
	/**
	 * @return the current item_id.
	 */
	public int getID() {
		return item_id;
	}
	
	/**
	 * @param price the price to be set.
	 * @return current value of this.price.
	 */
	public double setPrice(double price) {
		if (isValidPrice(price)) this.price = price;
		return this.price;
	}
	
	/**
	 * @return current value of this.price.
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * @param quantity the quantity to be set.
	 * @return current value of this.quantity.
	 */
	public int setQuantity(int quantity) {
		if (isValidQty(quantity)) this.quantity = quantity;
		return this.quantity;
	}
	
	/**
	 * @return current value of this.quantity.
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/**
	 * @param title the title (type of item) to be set.
	 * @return current value of this.title.
	 */
	public String setTitle(String title) {
		if (isValidTitle(title)) this.Title = title;
		return this.Title;
	}
	
	/**
	 * @return current value of this.title, which is actually the type of this item.
	 */
	public String getTitle() {
		return this.Title;
	}
	
	/**
	 * Displays the item on the screen.
	 * @return the displayed string.
	 */
	public abstract String Display();
	
	/**
	 * removes (quantity) number of items from inventory. <br>
	 * if quantity is larger than this.quantity, sets this.quantity to 0. <br>
	 * if quantity is less than 0, throws IllegalArgumentException. <br>
	 * @param quantity the number of items to be removed.
	 * @return the remaining quantity.
	 */
	public int Purchase(int quantity) throws IllegalArgumentException {
		if (quantity >= 0 && quantity <= this.quantity) {
			this.quantity -= quantity;
		} else {
			throw new IllegalArgumentException("Quantity must be between 0 and current inventory quantity!");
		}
		return this.quantity;
	}
	
	/**
	 * @return sample return <br>
	 *         Item ID: 1 <br>
	 *         Item type: foo <br>
	 *         Price: $0.00 <br>
	 *         Quantity: 0 <br>
	 */
	@Override
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return "Item ID: " + this.getID() + "\n" +
			   "Item type: " + this.getTitle() + "\n" +
			   "Price: $" + formatter.format(this.getPrice()) + "\n" +
			   "Quantity: " + this.getQuantity();
	}
}
