package model;

import java.awt.Point;
import java.util.Random;

/*+----------------------------------------------------------------------
||
|| public class RandomMove
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  RandomMove boject for the game
||
 ++-----------------------------------------------------------------------*/
public class RandomMove {

	public static Random generator;

	/*---------------------------------------------------------------------
	| public RandomMove()
	|
	|  Purpose:  Constructor for the public RandomMove()
	*-------------------------------------------------------------------*/
	public RandomMove() {
		generator = new Random();
	}

	/*---------------------------------------------------------------------
	|  public Point randomSelection(WumpusGame theGame)
	|
	|  Purpose:  random select the position on the game map 
	|
	|  Parameters:
	|    WumpusGame theGame
	|         
	|
	|  Returns:  Point 
	*-------------------------------------------------------------------*/
	public Point randomSelection(WumpusGame theGame) throws IGotNowhereToGoException {
		boolean set = false;
		while (!set) {
			if (theGame.maxMovesRemaining() == 0)
				throw new IGotNowhereToGoException(" -- Hey there programmer, the board is filled");
			// Otherwise, try to randomly find an open spot
			int row = generator.nextInt(theGame.size());
			int col = generator.nextInt(theGame.size());
			if (theGame.available(row, col)) {
				set = true;
				return new Point(row, col);
			}
		}
		return null;
	}

}
