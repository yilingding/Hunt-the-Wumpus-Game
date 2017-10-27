package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.WumpusGame;

/*+----------------------------------------------------------------------
||
|| public class ImageView extends JPanel implements Observer 
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the image view for the game
||
 ++-----------------------------------------------------------------------*/
public class ImageView extends JPanel implements Observer {

	private Image hunter;
	private Image wumpus;
	private Image blood;
	private Image goop;
	private Image ground;
	private Image slime;
	private Image slimePit;
	private WumpusGame game;
	private int playerRow, playerColumn;
	public char[][] graph = new char[10][10];

	public static final int TILE_SIZE = 50;

	/*---------------------------------------------------------------------
	|  public ImageView(WumpusGame game)
	|
	|  Purpose:  Constructor for the  ImageView(WumpusGame game) and read file
	*-------------------------------------------------------------------*/
	public ImageView(WumpusGame game) {
		this.game = game;
		playerRow = game.currentRow;
		playerColumn = game.currentCol;
		initializeArray();

		try {
			hunter = ImageIO.read(new File("images/TheHunter.png"));
			wumpus = ImageIO.read(new File("images/Wumpus.png"));
			blood = ImageIO.read(new File("images/Blood.png"));
			goop = ImageIO.read(new File("images/Goop.png"));
			ground = ImageIO.read(new File("images/Ground.png"));
			slime = ImageIO.read(new File("images/Slime.png"));
			slimePit = ImageIO.read(new File("images/SlimePit.png"));
		} catch (IOException io) {
			System.out.println("error reading file");
			return;
		}

		// Draw the initial game with the player showing at the initial
		// location
		repaint();
	}

	/*---------------------------------------------------------------------
	|   public void initializeArray()
	|
	|  Purpose:  initialze the array 
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	public void initializeArray() {
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				graph[i][k] = '_';
			}
		}
	}

	/*---------------------------------------------------------------------
	|   public void update(Observable observable, Object extraParameter) 
	|
	|  Purpose:  update the imageView everytime the player makes a move 
	|
	|  Parameters:
	|     Observable observable, Object extraParameter
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	@Override
	public void update(Observable observable, Object extraParameter) {
		// TODO 4: Set playerRow and playerColumn so paintComponent
		// knows where to draw the player image
		playerRow = game.currentRow;
		playerColumn = game.currentCol;

		graph[playerRow][playerColumn] = 'o';
		// TODO 5: Make paintComponent draw the panel,
		// However do NOT call paintComponent directly.
		repaint();
		
	}

	/*---------------------------------------------------------------------
	|   public void paintComponent(Graphics g)
	|
	|  Purpose:  repaint the graph
	|
	|  Parameters:
	|   Graphics g
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	public void paintComponent(Graphics g) {
		// Need to call this method to avoid incorrect drawing of this
		// JPanel.
		// 
		Graphics2D g2 = (Graphics2D) g;

		// TODO 6: Cast g to a Graphics2D Object
		// TODO 7: Draw the background image reference by the instance
		// variable "tile" 100 times. The tile image is 50 by 50 pixels.
		if (game.gameResult == 0) {
			for (int r = 0; r < 10; r++) {
				for (int c = 0; c < 10; c++) {
					if (graph[r][c] == '_') {
						Rectangle2D body = // xcoord, ycoord, width, height
								new Rectangle2D.Double(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
						g2.fill(body);
						g2.setPaint(Color.BLACK);
					} else if (graph[r][c] == 'o') {
						g2.drawImage(ground, c * TILE_SIZE, r * TILE_SIZE, null);
						if (game.isBlood(r, c)) {
							g2.drawImage(blood, c * TILE_SIZE, r * TILE_SIZE, null);
						} else if (game.isSlime(r, c)) {
							g2.drawImage(slime, c * TILE_SIZE, r * TILE_SIZE, null);
						} else if (game.isGoop(r, c)) {
							g2.drawImage(goop, c * TILE_SIZE, r * TILE_SIZE, null);
						} else if (game.isWumpus(r, c)) {
							closeGraph();
							gameEnd(g);
							game.gameResult = -1;
							playerRow = game.currentRow;
							playerColumn = game.currentCol;
							g2.drawImage(hunter, playerColumn * TILE_SIZE, game.currentRow * TILE_SIZE, null);
							return;
						} else if (game.isPit(r, c)) {
							closeGraph();
							gameEnd(g);
							game.gameResult = -1;
							playerRow = game.currentRow;
							playerColumn = game.currentCol;
							g2.drawImage(hunter, playerColumn * TILE_SIZE, game.currentRow * TILE_SIZE, null);
							return;
						}

					}
				}

			}

			playerRow = game.currentRow;
			playerColumn = game.currentCol;
			g2.drawImage(hunter, playerColumn * TILE_SIZE, game.currentRow * TILE_SIZE, null);
		} else if (game.gameResult == -1) {
			closeGraph();
			gameEnd(g);
			playerRow = game.currentRow;
			playerColumn = game.currentCol;
			g2.drawImage(hunter, playerColumn * TILE_SIZE, game.currentRow * TILE_SIZE, null);
			g2.setColor(Color.WHITE);
			if (game.shoot == 1) {
				g2.drawString("You don't shoot the Wumpus, so You lost the game", 100, 100);
			}

		} else if (game.gameResult == 1) {
			closeGraph();
			gameEnd(g);
			playerRow = game.currentRow;
			playerColumn = game.currentCol;
			g2.drawImage(hunter, playerColumn * TILE_SIZE, game.currentRow * TILE_SIZE, null);
			g2.setColor(Color.WHITE);
			if (game.shoot == 1) {
				g2.drawString("You shoot the Wumpus, You Win the Game ", 100, 100);
			}
		}
	}

	/*---------------------------------------------------------------------
	|   public String graphtoString()
	|
	|  Purpose:  convert the graph to string 
	|
	|  Parameters:
	|  None
	|         
	|
	|  Returns:  String
	*-------------------------------------------------------------------*/
	public String graphtoString() {
		String result = "";
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++)
				result += " " + graph[r][c] + " ";
			result += "\n";
		}
		return result;
	}

	/*---------------------------------------------------------------------
	|  private void closeGraph()
	|
	|  Purpose:  close the graph after game ends
	|
	|  Parameters:
	|  None
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	private void closeGraph() {
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				graph[i][k] = 'X';
			}
		}
	}

	/*---------------------------------------------------------------------
	| public void gameEnd(Graphics g) 
	|
	|  Purpose: update the graph when the game ends 
	|
	|  Parameters:
	|  Graphics g
	|         
	|
	|  Returns:  none
	*-------------------------------------------------------------------*/
	public void gameEnd(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for (int k = 0; k < 10; k++) {
			for (int h = 0; h < 10; h++) {
				Rectangle2D body = // xcoord, ycoord, width, height
						new Rectangle2D.Double(h * TILE_SIZE, k * TILE_SIZE, TILE_SIZE, TILE_SIZE);
				g2.fill(body);
				g2.setPaint(Color.BLACK);
			}
		}
		g2.setColor(Color.WHITE);
		if (game.shoot == 0) {
			g2.drawString("You lost the game according to the image shown!", 100, 100);
		}
		char[][] map = game.getMap();
		for (int k = 0; k < 10; k++) {
			for (int h = 0; h < 10; h++) {
				if (map[k][h] == 'B') {
					g2.drawImage(blood, h * TILE_SIZE, k * TILE_SIZE, null);
				} else if (map[k][h] == 'S') {
					g2.drawImage(slime, h * TILE_SIZE, k * TILE_SIZE, null);
				} else if (map[k][h] == 'G') {
					g2.drawImage(goop, h * TILE_SIZE, k * TILE_SIZE, null);
				} else if (map[k][h] == 'W') {
					g2.drawImage(wumpus, h * TILE_SIZE, k * TILE_SIZE, null);
				} else if (map[k][h] == 'P') {
					g2.drawImage(slimePit, h * TILE_SIZE, k * TILE_SIZE, null);
				}
			}
		}

		
	}

}
