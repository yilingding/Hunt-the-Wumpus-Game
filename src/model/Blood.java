package model;

import java.awt.Point;

/*+----------------------------------------------------------------------
||
|| public class Blood
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  Blood boject for the game
||
 ++-----------------------------------------------------------------------*/
public class Blood {

	public char status;

	/*---------------------------------------------------------------------
	|  public Blood(WumpusGame theGame)
	|
	|  Purpose:  Constructor for the public Blood(WumpusGame theGame, Point wumpus)
	*-------------------------------------------------------------------*/
	public Blood(WumpusGame theGame, Point wumpus) {
		status = 'B';
		setPosition(theGame, wumpus);
	}

	/*---------------------------------------------------------------------
	|   public Point setPosition(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose a position on the game map 
	|
	|  Parameters:
	|    WumpusGame theGame
	|         
	|
	|  Returns:  void
	*-------------------------------------------------------------------*/
	private void setPosition(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.choose((10 + x - 1) % 10, y, 'B');
		theGame.choose((10 + x - 2) % 10, y, 'B');
		theGame.choose((10 + x + 1) % 10, y, 'B');
		theGame.choose((10 + x + 2) % 10, y, 'B');

		theGame.choose(x, (10 + y - 1) % 10, 'B');
		theGame.choose(x, (10 + y - 2) % 10, 'B');
		theGame.choose(x, (10 + y + 1) % 10, 'B');
		theGame.choose(x, (10 + y + 2) % 10, 'B');
		theGame.choose((10 + x - 1) % 10, (10 + y - 1) % 10, 'B');
		theGame.choose((10 + x + 1) % 10, (10 + y + 1) % 10, 'B');
		theGame.choose((10 + x - 1) % 10, (10 + y + 1) % 10, 'B');
		theGame.choose((10 + x + 1) % 10, (10 + y - 1) % 10, 'B');
	}
}
