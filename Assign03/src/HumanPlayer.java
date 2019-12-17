import java.util.Scanner;

/**
 * Class HumanPlayer. lets a human player play tic-tac-toe.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public class HumanPlayer extends Player implements Global {
	// no additional attributes
	
	// constructor
	/**
	 * HumanPlayer doesn't have a default constructor.
	 * @param symbol specify the symbol used by this player.
	 * @param name should not be empty. this must be ensured before calling the constructor.
	 * @param board a reference to the game board it's playing on.
	 */
	public HumanPlayer(int symbol, String name, Board board) {
		this.setSymbol(symbol);
		this.setName(name);
		this.setBoard(board);
	}

	/**
	 * interacts with the player when he's making a move. if an illegal input occurs
	 * or the player is trying to make an illegal move, he will input again.
	 * @return a reference to the game board it's playing on.
	 */
	@Override
	public Board play() {
		@SuppressWarnings("resource")  // no need to close System.in
		Scanner sc = new Scanner(System.in);
		boolean flag = false;
		
		do {
			
			System.out.println("Please enter the Block number (such as A1) you wish to set your piece:");
			String input = sc.nextLine();
			if (input.length() > 2) {
				System.out.println("Illegal input!");
				continue;
			}
			
			int x, y;
			
			x = (int) (input.charAt(0) - 'A');
			if (x < 0 || x > (BOARD_SIZE - 1)) {
				System.out.println("Illegal input!");
				continue;
			}
			
			try {
				y = Integer.parseInt(input.substring(1));
				--y;
			} catch (NumberFormatException e) {
				System.out.println("Illegal input!");
				continue;
			}
			if (y < 0 || y > (BOARD_SIZE - 1)) {
				System.out.println("Illegal input!");
				continue;
			}
			
			if (!this.getBoard().blockIsEmpty(x, y)) {
				System.out.println("This block is not Empty!");
				continue;
			} else {
				this.getBoard().makeMove(x, y, this);
				flag = true;
			} 
			
		} while (!flag);
		
		return this.getBoard();
	}

}
