import java.util.*;
import java.io.*;

/**
 * Testing class.
 * @author zhu15a
 * Date: Nov 19, 2019
 */
public class EStore {
	
	/**
	 * maintains a list of items entered from the user by means of an interactive menu.
     * The user can:
     *  - Add item to the inventory, entering its type (book or gift card, etc), and necessary attributes along with quantity.
     *  - Display all items.
     *  - Display only books sorted by author name.
     *  - Display only gifts sorted by label.
     *  - Display only shoes sorted by size
     *  - Delete an item by item_id
     *  - Purchase an item by removing the purchased quantity from the inventory
     *  - Save items to a file, and loading them back, using object serialization. 
     *  
     * Currently only supports one shopping cart and doesn't support rogue items (i.e. items that are not in a cart).
	 */
	@SuppressWarnings("resource")  // since i don't own system.in, there's no need to close it.
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		int op = 0;
		
		// Main Menu
		do {
			System.out.println("Please choose your operation.");
			System.out.println("1. Add item(s) to cart");
			System.out.println("2. Purchase an item");
			System.out.println("3. Delete an item");
			System.out.println("4. Display all items");
			System.out.println("5. Display only books sorted by author name.");
			System.out.println("6. Display only gift cards sorted by label.");
			System.out.println("7. Display only shoes sorted by size.");
			System.out.println("8. Save cart to a file.");
			System.out.println("9. Load cart from a file. (Current cart will be discarded!)");
			System.out.println("Press any other key to exit.");
			
			Scanner sc1 = new Scanner(System.in);
			
			// quit on any illegal input
			try {
				op = sc1.nextInt();
			} catch (InputMismatchException e) {
				break;
			}
			
			// add item
			if (op == 1) {
				Item temp = inputItem();
				if (!(temp == null)) {
					cart.addItem(temp);
				} else {
					continue;
				}
				
			// purchase item
			} else if (op == 2) {
				Scanner sc2 = new Scanner(System.in);  // new scanner to prevent unread data interfering
				System.out.println("Please enter the id of the item.");
				int id = sc2.nextInt();
				System.out.println("Please enter the quantity of purchase.");
				int qty = sc2.nextInt();
				if (cart.purchaseItem(id, qty)) {
					System.out.println("Item purchase successful.");
				} else {
					System.out.println("Item doesn't exist!");
				}
				
			// delete item
			} else if (op == 3) {
				Scanner sc3 = new Scanner(System.in);  // new scanner to prevent unread data interfering
				System.out.println("Please enter the id of the item.");
				int id = sc3.nextInt();
				if (cart.deleteItem(id)) {
					System.out.println("Item deletion successful.");
				} else {
					System.out.println("Item doesn't exist!");
				}
				
			// display all items
			} else if (op == 4) {
				cart.DisplayAll();
				
			// display all books sorted by author
			} else if (op == 5) {
				cart.DisplayBooks();
				
			// display all gift cards sorted by label
			} else if (op == 6) {
				cart.DisplayGiftCards();
				
			// display all shoes sorted by size
			} else if (op == 7) {
				cart.DisplayShoes();
				
			// save cart to file
			} else if (op == 8) {
				try {
					if (SaveCart(cart)) {
						System.out.println("Cart saved to cart.sav successfully.");
					}
				} catch (IOException e) {
					System.err.println("Something went wrong...");
					e.printStackTrace();
				}
				
			// read cart from file
			} else if (op == 9) {
				try {
					ShoppingCart temp = LoadCart();
					if (temp != null) {
						cart = temp;
						System.out.println("Loaded cart from cart.sav successfully.");	
					}
				} catch (IOException e) {
					System.err.println("Something went wrong...");
					e.printStackTrace();
				}
			}
			
		} while (op > 0 && op < 10);
		
	}
	
	/**
	 * prompts the user for item input. user is responsible for keeping the input mostly legal,
	 * otherwise the user may find some input fields falling back into default values.
	 * @return the respective type of item that the user has input.
	 */
	public static Item inputItem() {
		@SuppressWarnings("resource")  // since i don't own system.in, there's no need to close it.
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the type of item.");
		String itemType = sc.nextLine();
		
		// determine item type
		if (itemType.contentEquals("Book")) {
			
			System.out.println("Please sequentially enter the title, the author, the year of the book,\n"
					+ "the price, and the quantity, split with \":\".");
			String temp = sc.nextLine();
			
			// split input into tokens
			String arr[] = temp.split(":");
			for (int i = 0; i < arr.length; ++i) {
				arr[i] = arr[i].trim();  // get rid of white spaces so parser doesn't throw exceptions
			}
			
			// parse numbers
			int temp1 = Integer.parseInt(arr[2]);
			double temp2 = Double.parseDouble(arr[3]);
			int temp3 = Integer.parseInt(arr[4]);
			
			// title, author, year, price, quantity
			return new Book(arr[0], arr[1], temp1, temp2, temp3);
			
		} else if (itemType.contentEquals("Gift card")) {
			
			System.out.println("Please sequentially enter the amount on the label, the manufacturer,\n"
					+ "the price, and the quantity, split with \":\".");
			String temp = sc.nextLine();
			
			// see above
			String arr[] = temp.split(":");
			for (int i = 0; i < arr.length; ++i) {
				arr[i] = arr[i].trim();
			}
			
			double temp1 = Double.parseDouble(arr[0]);
			double temp2 = Double.parseDouble(arr[2]);
			int temp3 = Integer.parseInt(arr[3]);
			
			// label, manufacturer, price, quantity
			return new GiftCard(temp1, arr[1], temp2, temp3);
			
		} else if (itemType.contentEquals("Shoe")) {
			
			System.out.println("Please sequentially enter the colour of the shoe, the size,\n"
					+ "the price, and the quantity, split with \":\".");
			String temp = sc.nextLine();
			
			// see above
			String arr[] = temp.split(":");
			for (int i = 0; i < arr.length; ++i) {
				arr[i] = arr[i].trim();
			}
			
			double temp1 = Double.parseDouble(arr[1]);
			double temp2 = Double.parseDouble(arr[2]);
			int temp3 = Integer.parseInt(arr[3]);
			
			// colour, size, price, quantity
			return new Shoe(arr[0], temp1, temp2, temp3);
			
		} else {
			
			System.out.println("Invalid item type.");
			return null;
		}
		
	}

	/**
	 * saves the current shopping cart and Item.item_count into a file.
	 * @param cart the ShoppingCart to be saved.
	 * @return true if the save is completed without error. false otherwise.
	 * @throws IOException if any IO error happens in the process.
	 */
	public static boolean SaveCart(ShoppingCart cart) throws IOException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("cart.sav");
		} catch (FileNotFoundException e) {
			System.err.println("File not Found or cannot be created!");
			return false;
		}
		
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			System.err.println("IO error while writing stream header!");
			e.printStackTrace();
			fos.close();
			return false;
		}
		
		try {
			oos.writeInt(Item.getItemCount());
			oos.writeObject(cart);
		} catch (IOException e) {
			System.err.println("IO error while writing Object!");
			e.printStackTrace();
			oos.close();
			return false;
		}
		
		oos.close();
		return true;
	}
	
	/**
	 * reads a ShoppingCart from a file. Also sets Item.item_count to the saved value.
	 * @return the ShoppingCart instance generated from the file.
	 * @throws IOException if any IO error happens in the process.
	 */
	public static ShoppingCart LoadCart() throws IOException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ShoppingCart cart = null;
		
		try {
			fis = new FileInputStream("cart.sav");
		} catch (FileNotFoundException e) {
			System.err.println("File not Found or cannot be created!");
			return cart;
		}
		
		try {
			ois = new ObjectInputStream(fis);
		} catch (IOException e) {
			System.err.println("IO error while writing stream header!");
			e.printStackTrace();
			fis.close();
			return cart;
		}
		
		try {
			Item.setItemCount(ois.readInt());
			cart = (ShoppingCart) ois.readObject();
		} catch (IOException e) {
			System.err.println("IO error while writing Object!");
			e.printStackTrace();
			ois.close();
			return cart;
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found!");
			e.printStackTrace();
			ois.close();
			return cart;
		}
		
		ois.close();
		return cart;
	}
}
