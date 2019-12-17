import java.util.Scanner;


/**
 * class description of the CashRegister class.
 * contains the main function. asks the user to input the details of 3 items, then calculates the
 * subtotal and total bill amount.
 * 
 * @author zhu15a
 *
 */
public class CashRegister {
	public static void main(String[] args) {
		
		Invoice invoice = new Invoice();
		Item temp;
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 3; ++i) {
			String itemName;
			int itemQuantity;
			double itemPrice;
			
			// prompt input
			System.out.println("Input name of item " + (i + 1) + ":");
			itemName = sc.nextLine();
			System.out.println("Input quantity of item " + (i + 1) + ":");
			itemQuantity = sc.nextInt();
			System.out.println("Input price of item " + (i + 1) + ":");
			itemPrice = sc.nextDouble();
			
			// instantiate item and push into back of invoice
			temp = new Item(itemName, itemQuantity, itemPrice);
			invoice.addItem(temp);
			sc.nextLine();
		}
		
		// output
		System.out.println();
		System.out.println();
		System.out.println("Your Bill:");
		System.out.println(invoice);
		
		// close input stream.
		sc.close();

	}

}
