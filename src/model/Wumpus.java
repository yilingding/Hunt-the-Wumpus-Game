package model;

import java.awt.Point;

/*+----------------------------------------------------------------------
||
|| public class Wumpus
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  Wumpus boject for the game
||
 ++-----------------------------------------------------------------------*/
public class Wumpus {

	public Point position;
	public char status;

	/*---------------------------------------------------------------------
	|  public Wumpus(WumpusGame theGame)
	|
	|  Purpose:  Constructor for the public Wumpus(WumpusGame theGame)
	*-------------------------------------------------------------------*/
	public Wumpus(WumpusGame theGame) {
		status = 'W';
		position = setPosition(theGame);
	}

	/*---------------------------------------------------------------------
	|   public Point setPosition(WumpusGame theGame)
	|
	|  Purpose:  choose a position on the game map 
	|
	|  Parameters:
	|    WumpusGame theGame
	|         
	|
	|  Returns:  Point 
	*-------------------------------------------------------------------*/
	public Point setPosition(WumpusGame theGame) {
		RandomMove randomPosition = new RandomMove();
		try {
			position = randomPosition.randomSelection(theGame);
			theGame.choose(position.x, position.y, status);
		} catch (IGotNowhereToGoException e) {
		}
		return position;
	}

	/*---------------------------------------------------------------------
	|   public char getStatus()
	|
	|  Purpose:  get the status
	|
	|  Parameters:
	|   None
	|         
	|
	|  Returns:  char
	*-------------------------------------------------------------------*/
	public char getStatus() {
		return status;
	}

	/*---------------------------------------------------------------------
	|   public Point getPosition()
	|
	|  Purpose:  get the position 
	|
	|  Parameters:
	|   None
	|         
	|
	|  Returns:  Point 
	*-------------------------------------------------------------------*/

	public Point getPosition() {
		return position;
	}

}
