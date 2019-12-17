/**
 * class description of Item class.
 * attributes: String name, int quantity, double price.
 * constructors: default, predefined, copy.
 * methods: getters and setters for attributes, total
 * overrode inherited method: toString
 * 
 * @author zhu15a
 *
 */
public class Item {
	
    // attributes
	private String name;
	private int quantity;
	private double price;
	
	// constructors
	
	/**
	 * default constructor
	 */
	public Item() {
		setName("");
		setQuantity(0);
		setPrice(0.0);
	}
	
	/**
	 * predefined constructor with parameters.
	 * @param name the name to set
	 * @param quantity the quantity to set
	 * @param price the price to set
	 */
	public Item(String name, int quantity, double price) {
		setName(name);
		setQuantity(quantity);
		setPrice(price);
	}
	
	/**
	 * copy constructor.
	 * @param item the item from which this constructor copies.
	 */
	public Item(Item item) {
		setName(item.getName());
		setQuantity(item.getQuantity());
		setPrice(item.getPrice());
	}
	
	// methods

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set. the input string should be normally shorter than 30 chars.
	 * if a string longer than 30 chars is used as the parameter, it will be truncated.
	 */
	public void setName(String name) {
		if (name.length() <= 30) {
		    this.name = name;
		} else {
			this.name = name.substring(0, 30);
		}
	}

	/**
	 * @return the quantity.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return total price for this item category, i.e. price * quantity
	 */
	public double total() {
		return this.getPrice() * this.getQuantity();
	}
	
	/**
	 * overrides Object.toString().
	 * 
	 * @return a formatted string to represent this object.
	 */
	public String toString() {
		return String.format("%-30s%10d%10.2f%10.2f",
				this.getName(), this.getQuantity(), this.getPrice(), this.total());
	}
	
}
