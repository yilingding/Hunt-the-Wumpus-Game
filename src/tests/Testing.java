package tests;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Direction;
import model.IGotNowhereToGoException;
import model.Pits;
import model.RandomMove;
import model.Slime;
import model.Wumpus;
import model.WumpusGame;

/*+----------------------------------------------------------------------
||
|| public class Testing
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the  test method for exaiming the game
||
 ++-----------------------------------------------------------------------*/
public class Testing {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/*
	 * Test for the Wumpus game
	 */
	@Test
	public void test() {
		WumpusGame game = new WumpusGame();

		System.out.println(game.toString());
	}

	/*
	 * Test for the Wumpus game gameResult
	 */
	@Test
	public void test1() {
		WumpusGame game = new WumpusGame();
		game.startNewGame();
		assertEquals(0, game.gameResult);
	}

	/*
	 * Test for the Wumpus game choose
	 */
	@Test
	public void test2() {
		WumpusGame game = new WumpusGame();

		game.choose(1, 2, 'G');

		assertEquals(false, game.choose(1, 2, 'G'));
		System.out.println(game.getMap());
	}

	/*
	 * Test for the Wumpus game choose method
	 */
	@Test
	public void test3() {
		WumpusGame game = new WumpusGame();
		game.clear();

		game.choose(9, 9, 'G');
		game.choose(2, 2, 'S');
		game.choose(3, 2, 'B');
		game.choose(4, 2, 'P');
		game.choose(5, 2, 'W');
		game.choose(6, 2, 'O');
		assertEquals(false, game.isBlood(1, 1));
		assertEquals(true, game.isGoop(9, 9));
		assertEquals(true, game.isSlime(2, 2));
		assertEquals(true, game.isBlood(3, 2));
		assertEquals(true, game.isPit(4, 2));
		assertEquals(true, game.isWumpus(5, 2));
		assertEquals(false, game.isGoop(9, 1));
		assertEquals(false, game.isSlime(3, 3));
		assertEquals(false, game.isBlood(3, 3));
		assertEquals(false, game.isPit(4, 3));
		assertEquals(false, game.isWumpus(5, 3));
		System.out.println("Position" + game.getWumpusPosition().x);
		System.out.println("Position" + game.getWumpusPosition().y);
		System.out.println("Position" + game.getHunterPosition().x);
		System.out.println("Position" + game.getHunterPosition().y);
		assertEquals(false, game.available(10, 10));
		assertEquals(true, game.available(3, 3));
		assertEquals(false, game.available(1, 13));
	}

	/*
	 * Test for the slime and pit class
	 */
	@Test
	public void test4() {
		WumpusGame game = new WumpusGame();

		Pits pit = new Pits(game);
		assertEquals('P', pit.status);
		assertEquals('P', pit.getStatus());
		Point pitPoint = pit.getPosition();
		Slime slime = new Slime(game, pitPoint);
		assertEquals('S', slime.status);
		Point upperLeft = new Point(0, 0);
		Point upperRight = new Point(0, 9);
		Point lowerLeft = new Point(9, 0);
		Point lowerRight = new Point(9, 9);
		Point left = new Point(3, 0);
		Point right = new Point(4, 9);

		Wumpus wumpus = new Wumpus(game);
		assertEquals('W', wumpus.getStatus());
		Slime slime1 = new Slime(game, upperLeft);
		Slime slime2 = new Slime(game, upperRight);
		Slime slime3 = new Slime(game, lowerLeft);
		Slime slime4 = new Slime(game, lowerRight);
		Slime slime5 = new Slime(game, right);
		Slime slime6 = new Slime(game, left);
		Slime slime7 = new Slime(game, new Point(0, 2));
		Slime slime8 = new Slime(game, new Point(9, 2));
		assertEquals('S', slime8.getStatus());
	}

	/*
	 * Test for the room method
	 */
	@Test
	public void test5() {
		WumpusGame game = new WumpusGame();

		int wr = game.getWumpusPosition().x;
		int wc = game.getWumpusPosition().y;

		game.shoot("up");
		game.shoot("down");
		game.shoot("right");
		game.shoot("left");
		game.movePlayer(Direction.NORTH);
		game.movePlayer(Direction.SOUTH);
		game.movePlayer(Direction.EAST);
		game.movePlayer(Direction.WEST);

		while (game.currentCol > 0) {
			game.currentCol--;
		}
		game.movePlayer(Direction.EAST);
		game.movePlayer(Direction.WEST);
		while (game.currentCol < 10) {
			game.currentCol++;
		}
		game.movePlayer(Direction.EAST);
		game.movePlayer(Direction.WEST);

		while (game.currentRow > 0) {
			game.currentRow--;
		}
		game.movePlayer(Direction.NORTH);
		game.movePlayer(Direction.SOUTH);

	}

	/*
	 * Test for the shooting method
	 */
	@Test
	public void test6() {
		WumpusGame game = new WumpusGame();
		int wr = game.getWumpusPosition().x;
		int wc = game.getWumpusPosition().y;

		while (game.currentCol != wc) {
			game.movePlayer(Direction.EAST);
		}
		game.shoot("up");

		assertEquals(1, game.gameResult);

	}

	/*
	 * Test for the shooting method
	 */
	@Test
	public void test9() {
		WumpusGame game = new WumpusGame();
		int wr = game.getWumpusPosition().x;
		int wc = game.getWumpusPosition().y;

		while (game.currentCol != wc) {
			game.movePlayer(Direction.EAST);
		}
		game.shoot("down");

		assertEquals(1, game.gameResult);

	}

	/*
	 * Test for the shooting method
	 */

	@Test
	public void test7() {
		WumpusGame game = new WumpusGame();
		int wr = game.getWumpusPosition().x;
		int wc = game.getWumpusPosition().y;

		while (game.currentRow != wr) {
			game.movePlayer(Direction.SOUTH);
		}
		game.shoot("right");

		assertEquals(1, game.gameResult);

	}
	/*
	 * Test for the shooting method
	 */

	@Test
	public void test8() {
		WumpusGame game = new WumpusGame();
		int wr = game.getWumpusPosition().x;
		int wc = game.getWumpusPosition().y;

		while (game.currentRow != wr) {
			game.movePlayer(Direction.SOUTH);
		}
		game.shoot("left");

		assertEquals(1, game.gameResult);

	}

}
