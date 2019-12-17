/**
 * Class Board. Contains and maintains blocks on the game board.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 *
 */
public class Board implements Global {
	// attributes
	private Block[][] grid;
	private int game_state;  // 0 - empty; 1 - P1 wins; 2 - P2 wins; 3 - draw
	private int empty_cell_count;
	
	// constructor
	/**
	 * default constructor. initializes the game board to be empty.
	 */
	public Board() {
		this.grid = new Block[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; ++i) {
			for (int j = 0; j < BOARD_SIZE; ++j) {
				grid[i][j] = new Block();
			}
		}
		this.game_state = EMPTY;
		this.empty_cell_count = 9;
	}
	
	// helper methods
	/**
	 * determines whether a given symbol has won on this board horizontally.
	 * @param symbol the specified symbol (type of game piece).
	 * @return true if a horizontal match is found. false otherwise.
	 */
	private boolean horizontalWin(int symbol) {
		
		for (int i = 0; i < BOARD_SIZE; ++i) {  // i is x - letter rep
			int currentRow = 0;
			
			for (int j = 0; j < BOARD_SIZE; ++j) {
				currentRow += grid[i][j].getState();
			}
			
			if (currentRow == symbol * WIN_NUM) return true;
		}
		return false;
	}
	
	/**
	 * determines whether a given symbol has won on this board vertically.
	 * @param symbol the specified symbol (type of game piece).
	 * @return true if a vertical match is found. false otherwise.
	 */
	private boolean verticalWin(int symbol) {
		
		for (int i = 0; i < BOARD_SIZE; ++i) {  // i is y - number rep
			int currentCol = 0;
			
			for (int j = 0; j < BOARD_SIZE; ++j) {
				currentCol += grid[j][i].getState();
			}
			
			if (currentCol == symbol * WIN_NUM) return true;
		}
		return false;
	}
	
	/**
	 * determines whether a given symbol has won on this board diagonally.
	 * @param symbol the specified symbol (type of game piece).
	 * @return true if a diagonal match is found. false otherwise.
	 */
	private boolean diagonalWin(int symbol) {
		int currentDiag = 0;
		
		// left-top to right-down
		for (int i = 0; i < BOARD_SIZE; ++i) {
			currentDiag += grid[i][i].getState();
		}
		if (currentDiag == symbol * WIN_NUM) return true;
		
		// right-top to left-down
		currentDiag = 0;
		for (int i = 0; i < BOARD_SIZE; ++i) {
			currentDiag += grid[(BOARD_SIZE - 1) - i][i].getState();
		}
		if (currentDiag == symbol * WIN_NUM) return true;
		
		return false;
	}
	
	// methods
	/**
	 * lets a player make a move.
	 * @param x the letter part of the block number. A is 0, B is 1, and C is 2.
	 * @param y the number part of the block number (minus 1).
	 * @param p the player who makes a move
	 * @return the state of the game board, after the move has been made.
	 */
	public int makeMove(int x, int y, Player p) {
		--this.empty_cell_count;
		grid[x][y].setState(p.getSymbol());
		return this.getState();
	}
	
	/**
	 * lets a player know if a given block is empty or not.
	 * @param x row number of a given block
	 * @param y col number of a given block
	 * @return true if the specified block is empty. false otherwise.
	 */
	public boolean blockIsEmpty(int x, int y) {
		return grid[x][y].getState() == EMPTY;
	}
	
	/**
	 * @return a reference to the internal grid (2D array) of blocks.
	 */
	public Block[][] getGrid() {
		return this.grid;
	}
	
	/**
	 * calculates the current state of the game board and returns it. Can either be
	 * EMPTY (indefinite), XWIN, OWIN, or DRAW (no one wins). (use global constants)
	 * @return the current state of the game board.
	 */
	public int getState() {
		
		if (diagonalWin(X) || 
			horizontalWin(X) || 
			verticalWin(X)) {
			
			this.game_state = XWIN;
			
		} else if (diagonalWin(O) || 
				   horizontalWin(O) || 
				   verticalWin(O)) {
			
			this.game_state = OWIN;
			
		} else {
			if (this.empty_cell_count == 0) this.game_state = DRAW;
		}
		
		return this.game_state;
	}
	
}
