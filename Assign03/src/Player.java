/**
 * Abstract Class Player. maintains basic attributes of a player - a symbol (type of game piece they hold),
 * a name, and a reference to the game board they're playing on.
 * @author zhu15a <br>
 * Date: Nov 27, 2019
 */
public abstract class Player implements Global {
	// attributes
	private int symbol;
	private String name;
	private Board board;
	
	// constructor
	/**
	 * only serves to initialize everything. when constructing subclasses, everything must be specified,
	 * i. e. no default values.
	 */
	public Player() {
		this.setSymbol(EMPTY);
		this.setName("");
		this.setBoard(null);
	}
	
	// methods
	/**
	 * players make a move with this method. directly modifies the board referenced in this class.
	 * @return a reference to the game board they're playing on.
	 */
	public abstract Board play();

	/**
	 * @return the symbol
	 */
	public int getSymbol() {
		return this.symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public int setSymbol(int symbol) {
		this.symbol = symbol;
		return this.symbol;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public String setName(String name) {
		this.name = name;
		return this.name;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * @param board the board to set
	 */
	public Board setBoard(Board board) {
		this.board = board;
		return this.board;
	}
	
	/**
	 * for debug purposes.
	 * @return a String that contains the name and symbol of the player.
	 */
	public String toString() {
		return "Player name: " + this.getName() + "\n" +
				"Player Symbol:" + (symbol == X ? "X" : "O");
	}

}
