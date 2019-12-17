import java.io.*;
import java.util.*;

/**
 * @author zhu15a
 *
 */
public class NameList {
	
	public static void main(String[] args) {
		LinkedList<Record> nameList = new LinkedList<Record>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int op = 0;
		
		do {  // main menu
			System.out.println("1. enter new entries");
			System.out.println("2. save to file");
			System.out.println("3. read from file");
			System.out.println("4. edit entry");
			System.out.println("press any other key to exit");
			
			try {
				op = sc.nextInt();
			} catch (InputMismatchException e) {  // quit when non number chars are entered
				break;
			}
			
			if (op == 1) {  // sequentially input multiple entries one line at a time
				nameList = promptInput(nameList);
			} else if (op == 2) {  // save file
				if (saveList(nameList) == true) {
					System.out.println("Save Successful!");
				} else {
					System.out.println("Something went wrong.");
				}
			} else if (op == 3) {  // read from file
				LinkedList<Record> temp = loadList();
				if (temp != null) {
					nameList = temp;
					System.out.println("Load Successful!");
				} else {
					System.out.println("Something went wrong.");
				}
				
				temp = new LinkedList<Record>();  // semi-deep copy of the original list for displaying sorted list
				
				for (Record cur : nameList) {
					temp.add(cur);
				}
				temp.sort(null);
				
				for (Record cur : temp) {
					System.out.println(cur);
				}
				
			} else if (op == 4) {
				if (nameList.size() != 0) {
					nameList = editEntry(nameList);
				} else {
					System.out.println("Error: list is empty.");
				}
			}
			
		} while (op > 0 && op < 5);

	}
	
	/**
	 * prompts user for input of entries. keeps going until a set symbol ("-1") is entered.
	 * @param nl original list to be manipulated.
	 * @return a referenced to the mutated list.
	 */
	public static LinkedList<Record> promptInput(LinkedList<Record> nl) {
		String inputLine;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("please enter a new entry in one line, each field delimited by \",\"");
			System.out.println("enter -1 to exit.");
			inputLine = sc.nextLine();
			String[] fields = inputLine.split(",");
			
			for (String str : fields) {
				str = str.trim();
			}
			
			if (fields.length == 3) {
				nl.add(new Record(fields[0], fields[1], fields[2]));
			} else {
				System.out.println("invalid input, entry discarded.");
			}
			
		} while (!inputLine.contentEquals("-1"));
		
		return nl;
	}
	
	/**
	 * saves name list to a file in text format.
	 * @param nl the list to be saved.
	 * @return true if save is successful, false otherwise
	 */
	public static boolean saveList(LinkedList<Record> nl) {
		try (FileOutputStream fos = new FileOutputStream("name_list.txt")) {
			for (Record rec : nl) {
				fos.write(rec.toString().getBytes());
				fos.write("\n".getBytes());
			}
		} catch (FileNotFoundException fnfe) {
			return false;
		} catch (IOException ioe) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * load name list from a text file. 
	 * user is responsible to make sure text file is valid, otherwise this function may produce undefined behaviour.
	 * given that if every line can be parsed into 3 fields, the program will still be able to run normally.
	 * delimiter is ','.
	 * @return a reference to the LL parsed from the text file, null if at any point reading fails.
	 */
	public static LinkedList<Record> loadList() {
		LinkedList<Record> ret = new LinkedList<Record>();
		try (FileInputStream fis = new FileInputStream("name_list.txt");
					 Scanner fsc = new Scanner(fis)) {
			while (fsc.hasNextLine()) {
				String inputLine = fsc.nextLine();
				String[] fields = inputLine.split(",");
				
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].trim();
				}
				
				if (fields.length == 3) {
					ret.add(new Record(fields[0], fields[1], fields[2]));
				} else {
					throw new IOException("Wrong input!");
				}
			}
		} catch (FileNotFoundException fnfe) {
			return null;
		} catch (IOException ioe) {
			return null;
		}
		
		return ret;
	}
	
	/**
	 * lets the user view and modify any entry in the list.
	 * @param nl the list to be manipulated.
	 * @return a reference to the mutated linked list.
	 */
	public static LinkedList<Record> editEntry(LinkedList<Record> nl) {
		String inputLine;
		int op = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);  // system.in does not belong to me, therefore closing is skipped
		ListIterator<Record> it = nl.listIterator();
		
		do {  // submenu)
			System.out.println("current entry is :");
			System.out.println(it.next() + "\n===============================================================");
			System.out.println("1. next entry");
			System.out.println("2. prev entry");
			System.out.println("3. edit current");
			System.out.println("press any other key to abort editing.");
			
			try {  // quit if any other key is pressed.
				op = sc.nextInt();
			} catch (InputMismatchException e) {
				break;
			}
			
			// since line 169 moves the iterator ahead, no need to move iterator if op is found.
			if (op == 1) {
				if (it.hasNext()) {
					continue;
				} else {
					System.out.println("Error: already last entry!");
					it.previous();
				}
			} else if (op == 2) {  // previous is another story tho
				it.previous();
				if (it.hasPrevious()) {
					it.previous();
				} else {
					System.out.println("Error: already first entry!");
				}
			} else if (op == 3) {
				System.out.println("please enter a new entry in one line, each field delimited by \",\"");
				@SuppressWarnings("resource")
				Scanner sc2 = new Scanner(System.in);
				inputLine = sc2.nextLine();
				String[] fields = inputLine.split(",");
				
				for (int i = 0; i < fields.length; ++i) {
					fields[i] = fields[i].trim();
				}
				
				if (fields.length == 3) {
					Record temp = new Record(fields[0], fields[1], fields[2]);
					it.remove();
					it.add(temp);
				} else {
					System.out.println("invalid input, entry discarded.");
				}
				it.previous();
			}
			
		} while (op > 0 && op < 4);
		
		return nl;
	}

}
