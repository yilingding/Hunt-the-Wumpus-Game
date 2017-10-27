package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.OurObserver;
import model.WumpusGame;

/*+----------------------------------------------------------------------
||
|| public class TextView extends JPanel implements Observer 
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the text view for the game
||
 ++-----------------------------------------------------------------------*/
public class TextView extends JPanel implements Observer {
	private WumpusGame game;
	JTextArea area = new JTextArea();
	JLabel warningMessage;
	private char[][] graph = new char[10][10];
	private int playerRow, playerColumn;
	private boolean gameEnd;

	// Constructor
	/*---------------------------------------------------------------------
	|  public TextView(WumpusGame map
	|
	|  Purpose:  Constructor for the  public TextView(WumpusGame map
	*-------------------------------------------------------------------*/
	public TextView(WumpusGame map) {
		game = map;
		gameEnd = false;
		playerRow = game.currentRow;
		playerColumn = game.currentCol;
		initializeArray();
		initializeAreaPanel();
	}

	/*---------------------------------------------------------------------
	|   private void initializeAreaPanel()
	|
	|  Purpose:  initialze the panel
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	private void initializeAreaPanel() {
		this.setBackground(Color.GREEN);
		Font myFont = new Font("Courier", Font.PLAIN, 25);
		area.setFont(myFont);
		String output = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				output = output + "[X]";
			}
			output = output + "\n";
		}
		area.setText(output);
		warningMessage = new JLabel("This part will show the hint information");
		warningMessage.setPreferredSize(new Dimension(250, 200));
		this.add(area);
		this.add(warningMessage);
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
	|   public String gameEnd()
	|
	|  Purpose: the string when the game ends 
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  String
	*-------------------------------------------------------------------*/

	public String gameEnd() {
		char[][] text = game.getMap();
		int row = game.currentRow;
		int col = game.currentCol;
		String output = "";
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < 10; k++) {
				if (text[i][k] == '_') {
					output = output + "[ ]";
				} else if (row == i && col == k) {
					output = output + "[O]";
				} else if (text[i][k] == 'O') {
					output = output + "[ ]";
				} else {
					output = output + "[" + text[i][k] + "]";
				}
			}
			output = output + "\n";
		}
		return output;
	}

	/*---------------------------------------------------------------------
	|   public void update(Observable observable, Object extraParameter) 
	|
	|  Purpose:  update the textView everytime the player makes a move 
	|
	|  Parameters:
	|     Observable observable, Object extraParameter
	|         
	|
	|  Returns:  Nothing.
	*-------------------------------------------------------------------*/
	@Override
	public void update(Observable o, Object arg) {
		playerRow = game.currentRow;
		playerColumn = game.currentCol;
		graph[playerRow][playerColumn] = 'o';
		String output = "";
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (graph[r][c] == '_') {
					output = output + "[X]";
				} else if (graph[r][c] == 'o') {
					if (r == playerRow && c == playerColumn) {
						output = output + "[O]";
						if (game.isBlood(r, c)) {
							warningMessage.setText("I smell something foul");
						} else if (game.isSlime(r, c)) {
							warningMessage.setText("There is bottomless pit around");
						} else if (game.isGoop(r, c)) {
							warningMessage.setText("I smell something foul and bottomless pit is around");
						} else if (game.isWumpus(r, c)) {
							warningMessage.setText("you walked into the Wumpus' room");
							output = gameEnd();
							area.setText(output);
							
							return;
						} else if (game.isPit(r, c)) {
							warningMessage.setText("You walked into a bottomless pits");
							output = gameEnd();
							area.setText(output);
							return;
						} else {
							warningMessage.setText("");
						}
					} else if (game.isBlood(r, c)) {
						output = output + "[B]";
					} else if (game.isSlime(r, c)) {
						output = output + "[S]";
					} else if (game.isGoop(r, c)) {
						output = output + "[G]";
					} else if (game.isWumpus(r, c)) {
						output = gameEnd();
						area.setText(output);
						return;
					} else if (game.isPit(r, c)) {
						output = gameEnd();
						area.setText(output);
						return;
					} else {
						output = output + "[ ]";

					}

				}
			}
			output = output + "\n";
		}

		area.setText(output);

		if (game.gameResult == -1) {
			area.setText(gameEnd());
			if (game.shoot == 1) {
				warningMessage.setBackground(Color.white);
				warningMessage.setText("You don't shoot the Wumpus,You lost..");
			} else {
				warningMessage.setBackground(Color.white);
				warningMessage.setText("You lost the game according to the TextView shown");
			}
		} else if (game.gameResult == 1) {
			area.setText(gameEnd());
			warningMessage.setText("You shoot the Wumpus, You Win...");
		

		}
	}
}
