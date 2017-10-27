package model;

import java.awt.Point;

/*+----------------------------------------------------------------------
||
|| public class Hunter
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  Hunter boject for the game
||
 ++-----------------------------------------------------------------------*/
public class Hunter {
	public Point position;
	public char status;
	/*---------------------------------------------------------------------
	|  public Hunter(WumpusGame theGame)
	|
	|  Purpose:  Constructor for the public Hunter(WumpusGame theGame)
	*-------------------------------------------------------------------*/

	public Hunter(WumpusGame theGame) {
		status = 'O';
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
			// TODO Auto-generated catch block
		}
		return position;
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
