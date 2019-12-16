import java.util.*;

/**
 * Class definition of the palindrome class. it can be constructed with a source string no longer than 20 characters.
 * it is used to determine whether the source string is a palindrome, a mirror string, or both.
 * once instantiated, the palindrome object is immutable. hence, the default constructor, while implemented, should not be used
 * under normal circumstances.
 * @author zhu15a
 * Date: Oct 27, 2019
 */
public class palindrome {
	// attributes
	private char[] buffer;
	private int size;

	// static map for storing mirror character table
	private static HashMap<Character, Character> mirrorChars = createMap();  // constant

	// constructors
	/**
	 * default constructor. sets buffer to empty, with a capacity of 20 characters.
	 */
	public palindrome() {
		buffer = new char[20];
		size = 0;
	}

	/**
	 * overloaded constructor. reads a String and converts it into upper case before storing it into buffer.
	 * as with the default constructor, the capacity of buffer is set to 20.
	 * note that this is the only method that allows the buffer to be set, i.e. palindrome as a data type is immutable.
	 * @param src the String to be stored.
	 */
	public palindrome(String src) {
		this();
		if (isValidString(src)) {
			for (int i = 0; i < src.length(); ++i) {  // read src one character at a time.
				buffer[i] = src.toUpperCase().charAt(i);
			}
			size = src.length();
		}
	}

	// static methods
	/**
	 * helper method for initializing the mirror character table.
	 * @return a reference to the created HashMap.
	 */
	private static HashMap<Character, Character> createMap() {
		HashMap<Character, Character> ret = new HashMap<Character, Character>(22);
		ret.put('A', 'A');
		ret.put('E', '3');
		ret.put('H', 'H');
		ret.put('I', 'I');
		ret.put('J', 'L');
		ret.put('L', 'J');
		ret.put('M', 'M');
		ret.put('O', 'O');
		ret.put('S', '2');
		ret.put('T', 'T');
		ret.put('U', 'U');
		ret.put('V', 'V');
		ret.put('W', 'W');
		ret.put('X', 'X');
		ret.put('Y', 'Y');
		ret.put('Z', '5');
		ret.put('1', '1');
		ret.put('2', 'S');
		ret.put('3', 'E');
		ret.put('5', 'Z');
		ret.put('8', '8');
		return ret;
	}

	// helper methods
	/**
	 * verifies the source String.
	 * @param src the String to be verified.
	 * @return true if src is not empty, no longer than 20 characters and does not contain '0'. false otherwise.
	 */
	private boolean isValidString(String src) {
		return !src.isEmpty() && src.length() <= 20 && !src.contains("0");
	}

	// methods
	/**
	 * @return true if the String stored in buffer is a palindrome. false otherwise.
	 */
	public boolean isPalin() {
		for (int i = 0; i < (this.size / 2); ++i) {  // compare head to tail.
			if (buffer[i] != buffer[this.size - i - 1]) return false;
		}
		return true;
	}

	/**
	 * @return true if the String stored in buffer is a mirror string. false otherwise.
	 */
	public boolean isMirror() {
		for (int i = 0; i < this.size; ++i) {  // compare head to tail, checking with mirror character table.
			if ((mirrorChars.get(buffer[i]) == null)
					|| mirrorChars.get(buffer[i]) != buffer[this.size - i - 1]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * overrides Object.toString().
	 * @return returns a String containing the source String, converted to upper case, and whether it is a palindrome, a mirror string, or both.
	 */
	public String toString() {
		StringBuilder buffer = new StringBuilder(20);

		for (int i = 0; i < this.size; ++i) {
			buffer.append(this.buffer[i]);
		}

		// if (this.size == 0) return "something went wrong. please check input.";

		if (isPalin() && isMirror()) {
			return buffer + " -- is a mirrored palindrome.";
		} else if (isPalin()) {
			return buffer + " -- is a regular palindrome.";
		} else if (isMirror()) {
			return buffer + " -- is a mirrored string.";
		} else {
			return buffer + " -- is not a palindrome.";
		}
	}
}
