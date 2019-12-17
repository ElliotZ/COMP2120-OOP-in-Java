import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Class definition of the Shopping Cart class. Uses a LinkedList to manage multiple Items.
 * @author zhu15a
 * Date: Nov 19, 2019
 */
public class ShoppingCart implements Serializable {
	/**
	 * Serial Version UID as of Nov 19, 2019.
	 */
	private static final long serialVersionUID = 20191119004L;
	
	// attributes
	private LinkedList<Item> itemList;
	
	// constructor
	/**
	 * default constructor. constructs an empty list.
	 */
	public ShoppingCart() {
		itemList = new LinkedList<Item>();
	}
	
	// methods
	/**
	 * @return number of items in cart.
	 */
	public int getSize() {
		return itemList.size();
	}
	
	/**
	 * @return total sum of price of all the items in cart.
	 */
	public double getTotalPrice() {
		double total = 0.0;
		for (Item cur : itemList) {
			total += cur.getPrice() * cur.getQuantity();
		}
		return total;
	}
	
	/**
	 * displays all the items in cart.
	 */
	public void DisplayAll() {
		for (Item cur : itemList) {
			System.out.println();
			System.out.println(cur);
			System.out.println("==================================================================");
		}
		System.out.println();
		System.out.println(this);
	}
	
	/**
	 * displays all the books in cart, in the order of author name.
	 */
	public void DisplayBooks() {
		LinkedList<Item> temp = new LinkedList<Item>();
		
		for (Item cur : itemList) {
			if (cur.getTitle().contentEquals("Book")) temp.add(cur);
		}
		temp.sort(null);
		
		System.out.println("Displaying all books in the cart, in the order of author name.");
		for (Item cur : temp) {
			System.out.println();
			System.out.println(cur);
			System.out.println("==================================================================");
		}
	}
	
	/**
	 * displays all the gift cards in cart, in the order of label.
	 */
	public void DisplayGiftCards() {
		LinkedList<Item> temp = new LinkedList<Item>();
		
		for (Item cur : itemList) {
			if (cur.getTitle().contentEquals("Gift card")) temp.add(cur);
		}
		temp.sort(null);
		
		System.out.println("Displaying all gift cards in the cart, in the order of label.");
		for (Item cur : temp) {
			System.out.println();
			System.out.println(cur);
			System.out.println("==================================================================");
		}
	}
	
	/**
	 * displays all the shoes in cart, in the order of size.
	 */
	public void DisplayShoes() {
		LinkedList<Item> temp = new LinkedList<Item>();
		
		for (Item cur : itemList) {
			if (cur.getTitle().contentEquals("Shoe")) temp.add(cur);
		}
		temp.sort(null);
		
		System.out.println("Displaying all shoes in the cart, in the order of size.");
		for (Item cur : temp) {
			System.out.println();
			System.out.println(cur);
			System.out.println("==================================================================");
		}
	}
	
	/**
	 * appends an item to the end of shopping cart.
	 * @param o the item to be added
	 * @return true (as specified by Collection.add(E))
	 */
	public boolean addItem(Item o) {
		return itemList.add(o);
	}
	
	/**
	 * tries to delete an item using its id. 
	 * @param itemID the id of the item to be deleted.
	 * @return true if the item is found and deleted. false if id doesn't exist.
	 */
	public boolean deleteItem(int itemID) {
		boolean idExists = false;
		
		for (Item cur : itemList) {
			if (cur.getID() == itemID) {
				idExists = true;
				return itemList.remove(cur);
			}
		}
		
		return idExists;
	}
	
	/**
	 * tries to purchase an item using its id and a specified quantity. if quantity is larger 
	 * than the total number of items in the cart, this would print an error message. 
	 * <br>
	 * if the quantity of items remaining in the cart is 0 after the operation, then this would
	 * delete the item from the cart as well.
	 * @param itemID the id of the item to purchase.
	 * @param quantity the specified quantity.
	 * @return true if the item is successfully purchased. false if the id doesn't exist.
	 */
	public boolean purchaseItem(int itemID, int quantity) {
		boolean idExists = false;
		
		for (Item cur : itemList) {
			if (cur.getID() == itemID) {
				idExists = true;
				try {
					cur.Purchase(quantity);
				} catch (IllegalArgumentException e) {
					System.err.println(e.getMessage());
					return false;
				}
			
				if (cur.getQuantity() == 0) {
					return itemList.remove(cur);
				} else {
					break;
				}
			}
		}
		
		return idExists;
	}
	
	/* (non-Javadoc)
	 * returns a string containing the number of items in the cart and the total price.
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		return "There is " + this.getSize() + " items in the cart.\n" +
	           "Total price is $" + formatter.format(this.getTotalPrice());
	}

}
