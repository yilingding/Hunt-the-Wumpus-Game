package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import controller.RunGUI.ArrowKeyListener;
import model.Direction;
import model.WumpusGame;
import view.ImageView;

import view.TextView;

/*+----------------------------------------------------------------------
||
|| public class RunGUI extends JFrame
||
|| Author:  Yiling Ding
||
|| Purpose: This class is to create the GUI for the game
||
 ++-----------------------------------------------------------------------*/
public class RunGUI extends JFrame {

	public static void main(String[] args) {
		RunGUI window = new RunGUI();
		window.setVisible(true);
	}

	private WumpusGame theGame;
	private ImageView imageView;
	private TextView textView;
	JSplitPane splitPane;
	private JPanel currentView;
	private BasicArrowButton up, down, left, right;
	JButton imageViewButton;
	JButton textViewButton;

	/*---------------------------------------------------------------------
	|  public RunGUI()
	|
	|  Purpose:  the constructor for public RunGUI()
	*-------------------------------------------------------------------*/
	public RunGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(650, 520);
		this.setLocation(100, 30);
		initializeGameForTheFirstTime();
		this.setBackground(Color.GREEN);
		textView = new TextView(theGame);
		imageView = new ImageView(theGame);
		theGame.addObserver(imageView);
		theGame.addObserver(textView);

		JPanel controls = new JPanel();
		controls.setSize(150, 520);
		controls.setVisible(true);
		up = new BasicArrowButton(BasicArrowButton.NORTH);
		down = new BasicArrowButton(BasicArrowButton.SOUTH);
		left = new BasicArrowButton(BasicArrowButton.WEST);
		right = new BasicArrowButton(BasicArrowButton.EAST);
		controls.setLayout(new BorderLayout());
		JPanel controls1 = new JPanel();
		controls1.setLayout(new BorderLayout());
		JLabel shot = new JLabel("   Make shot");

		setButtonShoot();
		imageViewButton = new JButton("Image View");
		textViewButton = new JButton("TextView");

		controls.add(imageViewButton, BorderLayout.SOUTH);
		controls.add(textViewButton, BorderLayout.NORTH);

		controls1.add(down, BorderLayout.SOUTH);
		controls1.add(up, BorderLayout.NORTH);
		controls1.add(left, BorderLayout.WEST);
		controls1.add(right, BorderLayout.EAST);
		controls1.add(shot, BorderLayout.CENTER);

		controls.add(controls1, BorderLayout.CENTER);

		splitPane = new JSplitPane();
		splitPane.setSize(650, 520);
		splitPane.setDividerSize(0);
		splitPane.setDividerLocation(150);
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(controls);

		splitPane.setRightComponent(imageView);

		ButtonListener buttonListener = new ButtonListener();
		imageViewButton.addActionListener(buttonListener);
		textViewButton.addActionListener(buttonListener);
		setButtonShoot();
		this.add(splitPane);
		this.addKeyListener(new ArrowKeyListener());

		setFocusable(true);
		requestFocus();

	}

	/*---------------------------------------------------------------------
	|  private class ButtonListener implements ActionListener
	|
	|  Purpose: changing the view 
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == imageViewButton) {
				imageView.setSize(500, 500);
				splitPane.setRightComponent(imageView);
				setFocusable(true);
				requestFocus();
			} else {
				textView.setSize(500, 500);
				splitPane.setRightComponent(textView);
				setFocusable(true);
				requestFocus();
			}

		}

	}

	/*---------------------------------------------------------------------
	|  public void setButtonShoot() 
	|
	|  Purpose:shoot the button  
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	// Add action listeners to shoot in the game.
	public void setButtonShoot() {
		ActionListener buttonShootListener = new ButtonShootListener();
		up.addActionListener(buttonShootListener);
		down.addActionListener(buttonShootListener);
		left.addActionListener(buttonShootListener);
		right.addActionListener(buttonShootListener);
	}

	/*---------------------------------------------------------------------
	|  public void disableButtons()
	|
	|  Purpose:disable shoot the button  
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	// Disable buttons when the game is over.
	public void disableButtons() {
		up.setEnabled(false);
		down.setEnabled(false);
		left.setEnabled(false);
		right.setEnabled(false);
	}

	/*---------------------------------------------------------------------
	|  private class ButtonShootListener implements ActionListener 
	|
	|  Purpose: create the class for the shoot button  
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	// Class for the shoot button listeners
	private class ButtonShootListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (theGame.gameResult != 0) {
				disableButtons();
			}
			if (arg0.getSource() == up) {
				theGame.shoot("up");
			} else if (arg0.getSource() == down)
				theGame.shoot("down");
			else if (arg0.getSource() == left)
				theGame.shoot("left");
			else if (arg0.getSource() == right)
				theGame.shoot("right");
		}
	}

	/*---------------------------------------------------------------------
	|  private void initializeGameForTheFirstTime()
	|
	|  Purpose: initialize the game 
	|
	|  Parameters:
	|     None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	private void initializeGameForTheFirstTime() {
		theGame = new WumpusGame();
	}

	/*---------------------------------------------------------------------
	|  private void setViewTo(JPanel newView)
	|
	|  Purpose: switch the view 
	|
	|  Parameters:
	|    JPanel newView
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	private void setViewTo(JPanel newView) {
		if (currentView != null)
			remove(currentView);
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}

	/*---------------------------------------------------------------------
	|  public class ArrowKeyListener implements KeyListener 
	|  Purpose: making the move when press the key 
	|
	|  Parameters:
	|    None
	|         
	|
	|  Returns:  None
	*-------------------------------------------------------------------*/
	public class ArrowKeyListener implements KeyListener {
		// This is TODO 1:
		//
		// Make key presses send messages to the game. For example, if the
		// user presses the up arrow key, the code should look like this:
		//
		//
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (theGame.gameResult == 0) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					theGame.movePlayer(Direction.NORTH);
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					theGame.movePlayer(Direction.SOUTH);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					theGame.movePlayer(Direction.EAST);
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					theGame.movePlayer(Direction.WEST);
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
