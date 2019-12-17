import java.util.Scanner;

/**
 * Class Definition for Lab 01, CS212.
 * 
 * @author zhu15a
 * Date    2019/9/20
 */

public class MyClass {
	
	/**
	 * calculates (sqrt(pi*(y^2)) / 2x).
	 * 
	 * @param x as per the formula.
	 * @param y as per the formula.
	 * @return calculated result, rounded to 2 decimal digits.
	 */
	public static double calc(int x, int y) {
		return (Math.round((Math.sqrt((Math.PI * y * y)) / 2 * x) * 100.0) / 100.0);
	}
	
	/**
	 * capitalizes a string.
	 * 
	 * @param src is the string from input. eg: hElLo
	 * @return the source string, capitalized. eg: Hello
	 */
	public static String wordCap(String src) {
		String firstChar = src.substring(0, 1);
		String dest = src.substring(1);
		
		return firstChar.toUpperCase() + dest.toLowerCase();
		
	}
	
	/**
	 * search for the occurrence of a keyword in a given string.
	 * 
	 * @param str is the given source string.
	 * @param kw is the keyword we use to search.
	 * @return true if the keyword is found in the source string, false otherwise.
	 */
	public static boolean findKeyWord(String str, String kw) {
		
		// convert kw into character sequence so it's readable.
		CharSequence kwcs = kw.subSequence(0, kw.length() - 1);
		return str.contains(kwcs);
	}
	
    /**
     * overloaded findKeyWord, with one added parameter.
     * 
     * @param str same as in findKeyWord.
     * @param kw same as in findKeyWord.
     * @param isCaseSen if this is marked false, then this function ignores case.
     * @return true if the keyword is found in the source string, false otherwise.
     */
	public static boolean findKeyWord(String str, String kw, boolean isCaseSen) {
		if (isCaseSen == false) {
			// if case shall be ignored, convert both the string and the keyword into lower case.
			String strNonCase = str.toLowerCase();
			String kwNonCase = kw.toLowerCase();
			
			// search for the keyword.
			CharSequence kwcsNonCase = kwNonCase.subSequence(0, kw.length() - 1);
			return strNonCase.contains(kwcsNonCase);
			
		} else {
			// search for the keyword without case conversion.
			CharSequence kwcs = kw.subSequence(0, kw.length() - 1);
			return str.contains(kwcs);
		}
	}

	/**
	 * the main function. tests the above functions in sequence.
	 * 
	 * @param args place holder.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// test calc(int, int)
		System.out.println("Testing calc(int x, int y). ");
		System.out.print("enter number x :");
		int x = sc.nextInt();
		System.out.print("enter number y :");
		int y = sc.nextInt();
		System.out.println("result is " + calc(x, y));
		
		sc.nextLine();  // flush input stream.
		
		// test wordCap(String).
		System.out.println("Testing wordCap(String src). ");
		System.out.print("enter a string :");
		String str1 = sc.nextLine();
		System.out.println("result is \"" + wordCap(str1) + "\"");
		
		// sc.nextLine();
		
		// test findKeyWord(String, String, boolean).
		System.out.println("Testing findKeyWord(String str, String kw, boolean isCaseSen). ");
		System.out.print("enter a string :");
		String str2 = sc.nextLine();
		System.out.print("enter a keyword :");
		String key = sc.nextLine();
		System.out.println("case sensitive? y/n");
		char op = sc.next().charAt(0);
		
		if (findKeyWord(str2, key, (op == 'y')) == true) {
			System.out.println("the key is found.");
		} else {
			System.out.println("the key is NOT found.");
		}
		
		sc.close();  // close input stream before ending program.

	}

}
