package model;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import controller.OurObservable;

/*+----------------------------------------------------------------------
||
|| public class WumpusGame extends Observable
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to run the Game, initialize the game, move the player,
||          include shoot method  and so on. 
||
 ++-----------------------------------------------------------------------*/
public class WumpusGame extends Observable {
	private char[][] board;
	private int size;
	private char nextChar;
	public ArrayList<Pits> pitsList = new ArrayList<Pits>();;
	public int numberOfPits;
	public Hunter hunter;
	public int shoot;
	public Wumpus wumpus;
	public int moveNumber;
	public Blood blood;
	public int currentRow;
	public int currentCol;
	public boolean visit;
	private static int LAST_ROW = 9;
	private static int LAST_COL = 9;
	public int gameResult; // 0 not over, 1 win, -1 lose
	/*---------------------------------------------------------------------
	|  public WumpusGame()
	|
	|  Purpose:  Constructor for the WumpusGame()
	*-------------------------------------------------------------------*/

	public WumpusGame() {
		size = 10;
		gameResult = 0;
		shoot = 0;
		initializeMap();
	}

	/*---------------------------------------------------------------------
	|   public void startNewGame()
	|
	|  Purpose:  Start a new game
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	public void startNewGame() {
		initializeMap();
		size = 10;
		notifyObservers();
	}

	/*---------------------------------------------------------------------
	|   public int size()
	|
	|  Purpose:  size of the game
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	public int size() {
		return size;
	}

	/*---------------------------------------------------------------------
	|   private void initializeMap() 
	|
	|  Purpose: initialize the map
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	private void initializeMap() {
		board = new char[size][size];
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				board[r][c] = '_';

		wumpus = new Wumpus(this);
		blood = new Blood(this, wumpus.getPosition());
		Random rand = new Random();
		numberOfPits = rand.nextInt(3) + 3;
		for (int i = 0; i < numberOfPits; i++) {
			Pits pits = new Pits(this);
			Slime slime = new Slime(this, pits.getPosition());
			pitsList.add(pits);
		}

		hunter = new Hunter(this);
		currentRow = hunter.getPosition().x;
		currentCol = hunter.getPosition().y;
	}

	/*---------------------------------------------------------------------
	|   public boolean choose(int row, int col,char c)
	|
	|  Purpose: choose position on the map 
	|
	|  Parameters:
	|     int row, int col,char c
	|         
	|
	|  Returns:  boolean
	*-------------------------------------------------------------------*/

	public boolean choose(int row, int col, char c) {
		if (board[row][col] != '_')
			return false;
		else {
			nextChar = c;
			board[row][col] = nextChar;
			moveNumber++;
			notifyObservers();
			return true;
		}
	}

	/*---------------------------------------------------------------------
	|   public boolean chooseSlime(int row, int col)
	|
	|  Purpose: choose slime position on the map 
	|
	|  Parameters:
	|     int row, int col,char c
	|         
	|
	|  Returns:  boolean
	*-------------------------------------------------------------------*/
	public boolean chooseSlime(int row, int col) {
		if (board[row][col] == '_') {
			board[row][col] = 'S';
			moveNumber++;
			notifyObservers();
			return true;
		} else if (board[row][col] == 'B') {
			board[row][col] = 'G';
			moveNumber++;
			notifyObservers();
			return true;
		} else {
			return false;
		}

	}

	/*---------------------------------------------------------------------
	|   public void clear()
	|
	|  Purpose: clear the map 
	|
	|  Parameters:
	|   none
	|         
	|
	|  Returns:  boolean
	*-------------------------------------------------------------------*/
	public void clear() {
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				board[r][c] = '_';

	}

	/*---------------------------------------------------------------------
	| public String toString()
	|
	|  Purpose: convert the map to string 
	|
	|  Parameters:
	|   none
	|         
	|
	|  Returns: string
	*-------------------------------------------------------------------*/

	@Override
	public String toString() {
		String result = "";
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++)
				result += " " + board[r][c] + " ";
			result += "\n";
		}
		return result;
	}

	/*---------------------------------------------------------------------
	| public char[][] getMap()
	|
	|  Purpose: convert the map to char array 
	|
	|  Parameters:
	|   none
	|         
	|
	|  Returns: char[][]
	*-------------------------------------------------------------------*/
	public char[][] getMap() {
		return board;
	}

	/*---------------------------------------------------------------------
	| public int maxMovesRemaining()
	|
	|  Purpose: how many position has left 
	|
	|  Parameters:
	|   none
	|         
	|
	|  Returns: int 
	*-------------------------------------------------------------------*/

	public int maxMovesRemaining() {
		int result = 0;
		for (int r = 0; r < size; r++)
			for (int c = 0; c < size; c++)
				if (board[r][c] == '_')
					result++;
		return result;
	}

	/*---------------------------------------------------------------------
	| public boolean isSlime(int r, int c)
	|
	|  Purpose: test whether the current position is slime 
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: boolean
	*-------------------------------------------------------------------*/
	public boolean isSlime(int r, int c) {
		if (board[r][c] == 'S') {
			return true;
		} else {
			return false;
		}
	}

	/*---------------------------------------------------------------------
	| public boolean isBlood(int r, int c)
	|
	|  Purpose: test whether the current position is blood 
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: boolean
	*-------------------------------------------------------------------*/
	public boolean isBlood(int r, int c) {
		if (board[r][c] == 'B') {
			return true;
		} else {
			return false;
		}
	}

	/*---------------------------------------------------------------------
	| public boolean isGoop(int r, int c)
	|
	|  Purpose: test whether the current position is goop
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: boolean
	*-------------------------------------------------------------------*/
	public boolean isGoop(int r, int c) {
		if (board[r][c] == 'G') {
			return true;
		} else {
			return false;
		}
	}

	/*---------------------------------------------------------------------
	| public boolean isWumpus(int r, int c)
	|
	|  Purpose: test whether the current position is wumpus
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: boolean
	*-------------------------------------------------------------------*/
	public boolean isWumpus(int r, int c) {
		if (board[r][c] == 'W') {
			return true;
		} else {
			return false;
		}
	}

	/*---------------------------------------------------------------------
	| public boolean isPit(int r, int c)
	|
	|  Purpose: test whether the current position is pit
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: boolean
	*-------------------------------------------------------------------*/
	public boolean isPit(int r, int c) {
		if (board[r][c] == 'P') {
			return true;
		} else {
			return false;
		}
	}

	/*---------------------------------------------------------------------
	| public void shoot(String dir)
	|
	|  Purpose: the hunter will shoor according to the given direction 
	|
	|  Parameters:
	|   String dir
	|         
	|
	|  Returns: None
	*-------------------------------------------------------------------*/
	public void shoot(String dir) {
		if (dir.compareTo("up") == 0) {
			int c = hunter.position.y;
			int wc = wumpus.position.y;
			if (currentCol == wc) {
				gameResult = 1;
			} else {
				gameResult = -1;
			}
		} else if (dir.compareTo("down") == 0) {
			int c = hunter.position.y;
			int wc = wumpus.position.y;
			if (currentCol == wc) {
				gameResult = 1;
			} else {
				gameResult = -1;
			}
		} else if (dir.compareTo("right") == 0) {
			int c = hunter.position.x;
			int wc = wumpus.position.x;
			if (currentRow == wc) {
				gameResult = 1;
			} else {
				gameResult = -1;
			}

		} else if (dir.compareTo("left") == 0) {
			int c = hunter.position.x;
			int wc = wumpus.position.x;
			if (currentRow == wc) {
				gameResult = 1;
			} else {
				gameResult = -1;
			}
		}
		shoot = 1;
		setChanged();
		notifyObservers();
	}

	/*---------------------------------------------------------------------
	| public boolean available(int r, int c)
	|
	|  Purpose: examine whether the current position is available 
	|
	|  Parameters:
	|   int r, int c
	|         
	|
	|  Returns: Boolean
	*-------------------------------------------------------------------*/

	public boolean available(int r, int c) {
		if (r > 9 || r < 0) {
			return false;
		}
		if (c > 9 || c < 0) {
			return false;
		}
		return board[r][c] == '_';
	}

	/*---------------------------------------------------------------------
	|  public Point getHunterPosition()
	|
	|  Purpose: get the hunter's position
	|
	|  Parameters:
	|   void
	|         
	|
	|  Returns: Point
	*-------------------------------------------------------------------*/

	public Point getHunterPosition() {
		return hunter.getPosition();
	}

	/*---------------------------------------------------------------------
	|  public Point getWumpusPosition()
	|
	|  Purpose: get the wumpus's position
	|
	|  Parameters:
	|   void
	|         
	|
	|  Returns: Point
	*-------------------------------------------------------------------*/
	public Point getWumpusPosition() {
		return wumpus.getPosition();
	}
	/*---------------------------------------------------------------------
	|  public void movePlayer(Direction direction)
	|
	|  Purpose: move the player on the map 
	|
	|  Parameters:
	|   Direction direction
	|         
	|
	|  Returns: none
	*-------------------------------------------------------------------*/

	public void movePlayer(Direction direction) {
		if (direction == Direction.NORTH) {
			currentRow--;
		} else if (direction == Direction.EAST) {
			currentCol++;
		} else if (direction == Direction.SOUTH) {
			currentRow++;
		} else if (direction == Direction.WEST) {
			currentCol--;
		}
		// Allow wrap around
		if (currentCol < 0)
			currentCol = LAST_COL;
		if (currentCol > LAST_COL)
			currentCol = 0;

		if (currentRow < 0)
			currentRow = LAST_ROW;
		if (currentRow > LAST_ROW)
			currentRow = 0;
		// With java.util.Observable, you must send yourself a setChanged
		// message. If you don't, notifyObservers does nothing
		setChanged();
		notifyObservers();
	}

}
