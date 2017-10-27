package model;

import java.awt.Point;

/*+----------------------------------------------------------------------
||
|| public class Slime 
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  Slime boject for the game
||
 ++-----------------------------------------------------------------------*/
public class Slime {
	public char status;

	/*---------------------------------------------------------------------
	|  public Slime(WumpusGame theGame, Point pit)
	|
	|  Purpose:  Constructor for the public Slime(WumpusGame theGame)
	*-------------------------------------------------------------------*/
	public Slime(WumpusGame theGame, Point pit) {
		status = 'S';
		setPosition(theGame, pit);
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
	|  Returns:  Point 
	*-------------------------------------------------------------------*/
	public void setPosition(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		if (x == 0 && y == 0) {
			upperLeft(theGame, pit);
		} else if (x == 9 && y == 0) {
			upperRight(theGame, pit);
		} else if (x == 0 && y == 9) {
			lowerLeft(theGame, pit);
		} else if (x == 9 && y == 9) {
			lowerRight(theGame, pit);
		} else if (x == 0) {
			left(theGame, pit);
		} else if (x == 9) {
			right(theGame, pit);
		} else if (y == 0) {
			north(theGame, pit);
		} else if (y == 9) {
			south(theGame, pit);
		} else {
			normalCase(theGame, pit);
		}

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
	|   public void normalCase(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void normalCase(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y + 1);
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x - 1, y);
		theGame.chooseSlime(x + 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void upperLeft(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void upperLeft(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y + 1);
		theGame.chooseSlime(x + 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void upperRight(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void upperRight(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y + 1);
		theGame.chooseSlime(x - 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void lowerRight(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void lowerRight(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x - 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void lowerLeft(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void lowerLeft(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x + 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void right(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void right(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x - 1, y);
		theGame.chooseSlime(x, y + 1);
	}

	/*---------------------------------------------------------------------
	|  public void lefy(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void left(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x + 1, y);
		theGame.chooseSlime(x, y + 1);
	}

	/*---------------------------------------------------------------------
	|  public void north(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void north(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y + 1);
		theGame.chooseSlime(x + 1, y);
		theGame.chooseSlime(x - 1, y);
	}

	/*---------------------------------------------------------------------
	|  public void south(WumpusGame theGame, Point pit)
	|
	|  Purpose:  choose the slime position according to the pit
	|
	|  Parameters:
	|   WumpusGame theGame, Point pit
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public void south(WumpusGame theGame, Point pit) {
		int x = pit.x;
		int y = pit.y;
		theGame.chooseSlime(x, y - 1);
		theGame.chooseSlime(x + 1, y);
		theGame.chooseSlime(x - 1, y);
	}

}
