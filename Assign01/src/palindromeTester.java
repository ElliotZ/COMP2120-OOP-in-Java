import java.util.*;

/**
 * class definition of the palindromeTester class.
 * @author zhu15a
 * Date: Oct 30, 2019
 */
public class palindromeTester {
	/**
	 * main function.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// read input from System.in until EOF is reached.
		do {
			palindrome p = new palindrome(sc.next());
			System.out.println(p);
		} while (sc.hasNext());  // EOF

		sc.close();
	}

}
