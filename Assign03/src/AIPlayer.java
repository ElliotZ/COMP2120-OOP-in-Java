import java.util.LinkedList;
import java.util.Random;

/**
 * Class AIPlayer. It is a Tic-Tac-Toe AI that has 3 difficulty settings - easy, normal,
 * and unbeatable. Hopefully unbeatable is really unbeatable.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public class AIPlayer extends Player implements Global {
	// constants
	public static int EASY = 1;
	public static int NORMAL = 2;
	public static int UNBEATABLE = 3;
	
	// attributes
	private int difficulty;  // 1 - easy; 2 - normal; 3 - unbeatable
	
	// constructor
	/**
	 * AIPlayer doesn't have a default constructor.
	 * @param symbol specify the symbol used by this player.
	 * @param name should be "CPU".
	 * @param board a reference to the game board it's playing on.
	 * @param difficulty settings for difficulty. Use specified global constant for this.
	 */
	public AIPlayer(int symbol, String name, Board board, int difficulty) {
		this.setSymbol(symbol);
		this.setName(name);
		this.setBoard(board);
		this.difficulty = difficulty;
	}
	
	// internal methods
	/**
	 * tries to sense the block that potentially wins or loses the game.
	 * @param win use true for sensing winning blocks. use false for sensing a losing one.
	 * @return an int array that contains the coordinates for that block.
	 */
	private int[] sense(boolean win) {
		int[] ans = {-1, -1};
		int target = win ? 1 : -1;  // modify sensing conditions. -1 * symbol = opposite symbol.
		
		// Diagonal check first. Realistically speaking this is the most important case.
		int currentDiag = 0;
		int emptyCell = 0;
		for (int i = 0; i < BOARD_SIZE; ++i) {
			currentDiag += this.getBoard().getGrid()[i][i].getState();
			// this doesn't work if board size is not equal to winning condition
			if (this.getBoard().getGrid()[i][i].getState() == EMPTY) emptyCell = i;
		}
		if (currentDiag == (WIN_NUM - 1) * target * this.getSymbol()) {
			ans[0] = emptyCell;
			ans[1] = emptyCell;
			return ans;  // if game board is to be expanded then this part has to be rewritten.
		}
		// Reverse diagonal check
		currentDiag = 0;
		emptyCell = 0;
		for (int i = 0; i < BOARD_SIZE; ++i) {
			currentDiag += this.getBoard().getGrid()[(BOARD_SIZE - 1) - i][i].getState();
			// this doesn't work if board size is not equal to winning condition
			if (this.getBoard().getGrid()[(BOARD_SIZE - 1) - i][i].getState() == EMPTY) emptyCell = i;
		}
		if (currentDiag == (WIN_NUM - 1) * target * this.getSymbol()) {
			ans[0] = 2 - emptyCell;
			ans[1] = emptyCell;
			return ans;
		}
		
		// Horizontal
		for (int i = 0; i < BOARD_SIZE; ++i) {
			int currentRow = 0;
			int emptyBlock = 0;
			for (int j = 0; j < BOARD_SIZE; ++j) {
				currentRow += this.getBoard().getGrid()[i][j].getState();
				// this doesn't work if board size is not equal to winning condition
				if (this.getBoard().getGrid()[i][j].getState() == EMPTY) emptyBlock = j;
			}
			if (currentRow == (WIN_NUM - 1) * target * this.getSymbol()) {
				ans[0] = i;
				ans[1] = emptyBlock;
				return ans;
			}
		}
		
		// Vertical
		for (int i = 0; i < BOARD_SIZE; ++i) {
			int currentCol = 0;
			int emptyBlock = 0;
			for (int j = 0; j < BOARD_SIZE; ++j) {
				currentCol += this.getBoard().getGrid()[j][i].getState();
				// this doesn't work if board size is not equal to winning condition
				if (this.getBoard().getGrid()[j][i].getState() == EMPTY) emptyBlock = j;
			}
			if (currentCol == (WIN_NUM - 1) * target * this.getSymbol()) {
				ans[0] = emptyBlock;
				ans[1] = i;
				return ans;
			}
		}
		
		// returns default value if nothing.
		return ans;
	}
	
	/**
	 * tries to calculate the winning and losing weight for a given block. if a certain block 
	 * doesn't have any opposite pieces in its row, column or diagonal (if applicable), then 
	 * the weight increases. if there is friendly pieces in its row, col or diag, then the weight 
	 * further increases. <br><br>
	 * vice versa for the losing weight.
	 * @param x row number of block
	 * @param y col number of block
	 * @return an int array that contains the winning and losing weight for a given block
	 */
	private int[] weight(int x, int y) {
		int[] ret = new int[2];
		
		int wins = 0;
		int oppositeWins = 0;
		
		// horizontal check
		boolean oppositeExists = false;
		boolean pcExists = false;
		for (int i = 0; i < BOARD_SIZE; ++i) {
			if (this.getBoard().getGrid()[x][i].getState() == -1 * this.getSymbol()) {
				oppositeExists = true;
				break;
			}
		}
		for (int i = 0; i < BOARD_SIZE; ++i) {
			if (this.getBoard().getGrid()[x][i].getState() == this.getSymbol()) {
				pcExists = true;
				break;
			}
		}
		if (!oppositeExists) ++wins;
		if (!pcExists) ++oppositeWins;
		if (pcExists) ++wins;
		if (oppositeExists) ++oppositeWins;
		
		// vertical check
		oppositeExists = false;
		pcExists = false;
		for (int i = 0; i < BOARD_SIZE; ++i) {
			if (this.getBoard().getGrid()[i][y].getState() == -1 * this.getSymbol()) {
				oppositeExists = true;
				break;
			}
		}
		for (int i = 0; i < BOARD_SIZE; ++i) {
			if (this.getBoard().getGrid()[i][y].getState() == this.getSymbol()) {
				pcExists = true;
				break;
			}
		}
		if (!oppositeExists) ++wins;
		if (!pcExists) ++oppositeWins;
		if (pcExists) ++wins;
		if (oppositeExists) ++oppositeWins;
		
		// if at a corner or center, check diagonals
		
		if (x == y || x + y == 2) {
			oppositeExists = false;
			pcExists = false;
			for (int i = 0; i < BOARD_SIZE; ++i) {
				if ((x == 0 && y == 0 ) || (x == 2 && y == 2)) {
					if (this.getBoard().getGrid()[i][i].getState() == -1 * this.getSymbol()) {
						oppositeExists = true;
						break;
					}
				} else {
					if (this.getBoard().getGrid()[(BOARD_SIZE - 1) - i][i].getState() == -1 * this.getSymbol()) {
						oppositeExists = true;
						break;
					}
				}
			}
			for (int i = 0; i < BOARD_SIZE; ++i) {
				if ((x == 0 && y == 0 ) || (x == 2 && y == 2)) {
					if (this.getBoard().getGrid()[i][i].getState() == this.getSymbol()) {
						pcExists = true;
						break;
					}
				} else {
					if (this.getBoard().getGrid()[(BOARD_SIZE - 1) - i][i].getState() == this.getSymbol()) {
						pcExists = true;
						break;
					}
				}
			}
			if (!oppositeExists) ++wins;
			if (!pcExists) ++oppositeWins;
			if (pcExists) ++wins;
			if (oppositeExists) ++oppositeWins;
			
			// if at center, check the other diagonal
			if (x == 1 && y == 1) {
				oppositeExists = false;
				pcExists = false;
				for (int i = 0; i < BOARD_SIZE; ++i) {
					if (this.getBoard().getGrid()[i][i].getState() == -1 * this.getSymbol()) {
						oppositeExists = true;
						break;
					}
				}
				for (int i = 0; i < BOARD_SIZE; ++i) {
					if (this.getBoard().getGrid()[i][i].getState() == this.getSymbol()) {
						pcExists = true;
						break;
					}
				}
				if (!oppositeExists) ++wins;
				if (!pcExists) ++oppositeWins;
				if (pcExists) ++wins;
				if (oppositeExists) ++oppositeWins;
			}
			
		}
		
		ret[0] = wins;
		ret[1] = oppositeWins;
		return ret;
	}
	
	/**
	 * use greed algorithm to try to find optimal block for next move, utilizing the weights 
	 * of the blocks. <br><br>
	 * if more than one block have the same weights, then this will return a random one 
	 * from those blocks.
	 * @return an array representing the coordinates of the block.
	 */
	private int[] greedIsGood() {
		int[] ans = new int[2];
		
		// construct weight map
		LinkedList<WeightNode> weightMap = new LinkedList<WeightNode>();
		for (int i = 0; i < BOARD_SIZE; ++i) {
			for (int j = 0; j < BOARD_SIZE; ++j) {
				WeightNode current = new WeightNode(i, j);
				int[] temp = this.weight(i, j);
				if (this.getBoard().blockIsEmpty(i, j)) {
					current.weight = temp[0];
					current.opposite_weight = temp[1];
				}
				weightMap.add(current);
			}
		}
		weightMap.sort(null);  // sorts nodes in ascending order
		
		// choose from the nodes with the highest weight
		while (weightMap.peekFirst().compareTo(weightMap.peekLast()) < 0) {
			weightMap.pollFirst();
		}  // occupied blocks have a weight of -1 and will be discarded.
		
		// get a random one
		Random rand = new Random();
		WeightNode result = weightMap.get(rand.nextInt(weightMap.size()));
		ans[0] = result.x;
		ans[1] = result.y;
		
		return ans;
	}
	
	/**
	 * makes a move at random. 
	 * @return an array representing a random legal move, i. e. the block returned will not be
	 * occupied.
	 */
	private int[] randomMove() {
		int[] ans = new int[2];
		
		// construct weight map
		LinkedList<WeightNode> weightMap = new LinkedList<WeightNode>();
		for (int i = 0; i < BOARD_SIZE; ++i) {
			for (int j = 0; j < BOARD_SIZE; ++j) {
				WeightNode current = new WeightNode(i, j);
				if (this.getBoard().blockIsEmpty(i, j)) {
					current.weight = 1;  // doesn't actually calculate weight
					current.opposite_weight = 1;
				}
				weightMap.add(current);
			}
		}
		weightMap.sort(null);  // sorts nodes in ascending order
		
		// choose from the nodes with the highest weight
		while (weightMap.peekFirst().compareTo(weightMap.peekLast()) < 0) {
			weightMap.pollFirst();
		}  // occupied blocks have a weight of -1 and will be discarded.
		
		// get a random one
		Random rand = new Random();
		WeightNode result = weightMap.get(rand.nextInt(weightMap.size()));
		ans[0] = result.x;
		ans[1] = result.y;
		
		return ans;
	}
	
	// methods
	/**
	 * makes a move.<br>
	 *  - at EASY, make a completely random move.<br>
	 *  - at NORMAL, will try to defend and win the game.<br>
	 *  - at UNBEATABLE, will try to make the most optimal move possible.<br>
	 * @return a reference to the board it's playing on.
	 */
	@Override
	public Board play() {
		int[] coord = new int[2];
		
		if (this.difficulty == UNBEATABLE) {
			coord = sense(true);
			if (coord[0] == -1) coord = sense(false);
			if (coord[0] == -1) coord = greedIsGood();
		} else if (this.difficulty == NORMAL) {
			coord = sense(true);
			if (coord[0] == -1) coord = sense(false);
			if (coord[0] == -1) coord = randomMove();
		} else {  // this. difficulty == EASY
			coord = randomMove();
		}
		
		System.out.println();
		System.out.println(this.getName() + " has decided to make a move at " + 
							String.valueOf((char) (coord[0] + 'A')) + (coord[1] + 1) + ".");
		if (DEBUG) System.out.println(this);
		this.getBoard().makeMove(coord[0], coord[1], this);
		return this.getBoard();
	}
	
	/**
	 * for debug purposes.
	 * @return a string containing results of all the sensing methods. also contains the string
	 * from its super class Player.
	 */
	@Override
	public String toString() {
		String ret = super.toString();
		int[] senseWin = this.sense(true);
		int[] senseLoss = this.sense(false);
		int[] greedMove = this.greedIsGood();
		ret.concat("\nsenseWin: " + senseWin[0] + ", " + senseWin[1] + "\n" +
				"senseLoss:" + senseLoss[0] + ", " + senseLoss[1] + "\n" +
				"adjacent: " + greedMove[0] + ", " + greedMove[1]);
		return ret;
	}

}
