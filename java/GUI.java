package edu.fau.COT4930;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.*;

/**
 * this is the main class for the blackjack game.
 * this class has several subclasses which mostly
 * handles events in the game as well as save
 * and load functionality
 * 
 * @author Daniel Leach
 *
 */
public class GUI{
	
	private static final int Soft = 17;
	private Deck deck;
	private int sizeX = 1000;
	private int sizeY = 600;
	private JFrame frame;
	private Draw contentPanel;
	private boolean gameRunning;
	private User player;
	private User dealer;
	private Driver driver;
	private String message = "";
	private int wins = 0;
	private int loses = 0;
	private int ties = 0;
	private JPanel MainMenu;
	private JPanel loadMenu;
	private SaveState save1;
	private SaveState save2;
	private SaveState save3;
	private SaveState loadState1;
	private SaveState loadState2;
	private SaveState loadState3;
	private boolean choose1 = false;
	private boolean choose2 = false;
	private boolean choose3 = false;
	private boolean firstPlay = true;
	private JPanel load_1;
	private JPanel load_2;
	private JPanel load_3;
	private JButton loadStats;
	
	/**
	 * Main method for loading game
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI gui = new GUI();
					gui.loadState();
					gui.init();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * loadState method loads games from .dat files
	 * loads the file bases on player choice
	 */
	public void loadState(){
		// loads first save file
		try
		{	
			ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("Player1.dat"));
			loadState1 = (SaveState) in1.readObject();
			in1.close();
		}
		catch (SecurityException e)
		{
			System.out.println("Serialization restore error 1");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Serialization restore error 2");
		}
		catch (IOException e)
		{
			SaveState save1 = new SaveState(wins,loses,ties,"Empty");
			try
			{
				ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("Player1.dat"));
				out1.writeObject(save1);					
				out1.close();
			}
			catch (SecurityException em)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException em)
			{
				System.out.println("Serialization save error 2");
			}
		}

		// loads second save file
		try
		{
			
			ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("Player2.dat"));	
			loadState2 = (SaveState) in2.readObject();
			in2.close();
		
		}
		catch (SecurityException e)
		{
			System.out.println("Serialization restore error 1");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Serialization restore error 2");
		}
		catch (IOException e)
		{
			SaveState save2 = new SaveState(wins,loses,ties,"Empty");
			try
			{
				ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("Player2.dat"));
				out2.writeObject(save2);					
				out2.close();
			}
			catch (SecurityException em)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException em)
			{
				System.out.println("Serialization save error 2");
			}
		}

		// loads third save file
		try
		{
			ObjectInputStream in3 = new ObjectInputStream(new FileInputStream("Player3.dat"));
			loadState3 = (SaveState) in3.readObject();
			in3.close();
		}
		catch (SecurityException e)
		{
			System.out.println("Serialization restore error 1");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Serialization restore error 2");
		}
		catch (IOException e)
		{
			SaveState save3 = new SaveState(wins,loses,ties,"Empty");
			try
			{
				ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("Player3.dat"));
				out3.writeObject(save3);					
				out3.close();
			}
			catch (SecurityException em)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException em)
			{
				System.out.println("Serialization save error 2");
			}	
		}
		
	}
	
	/**
	 * Save method saves the current game
	 * allows to player to choose which file to save the game in
	 * 
	 * @param Name represents the player name entered by player
	 */
	public void Save(String Name) {
		
		save1 = new SaveState(wins,loses,ties,Name);
		save2 = new SaveState(wins,loses,ties,Name);
		save3 = new SaveState(wins,loses,ties,Name);
		
		// if a file is chosen then that file is saved
		if(choose1) {
			try
			{
				ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("Player1.dat"));
				out1.writeObject(save1);					
				out1.close();
			}
			catch (SecurityException e)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException e)
			{
				System.out.println("Serialization save error 2");
			}

		}
		if(choose2) {
			try
			{
				
				ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("Player2.dat"));
				out2.writeObject(save2);
				out2.close();
				
			}
			catch (SecurityException e)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException e)
			{
				System.out.println("Serialization save error 2");
			}
		}
		if(choose3) {
			try
			{
				ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("Player3.dat"));
				out3.writeObject(save3);
				out3.close();
			}
			catch (SecurityException e)
			{
				System.out.println("Serialization save error 1");
			}
			catch (IOException e)
			{
				System.out.println("Serialization save error 2");
			}
		}
		

	}
	
	/**
	 * The init method is the main GUI that initializes 
	 * the containers that are necessary for making the game
	 * this also creates all the necessary buttons for the main container
	 */
	public void init() {
		// create frame
		loadState();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150,50,sizeX,sizeY);
		frame.setResizable(false);
		
		// create main menu container
		MainMenu = new JPanel();
		MainMenu.setBackground(new Color(39,119,20));
		MainMenu.setBounds(0,0,1000,600);
		MainMenu.setLayout(null);
		
		// create load container
		loadMenu = new JPanel();
		loadMenu.setBackground(new Color(39,119,20));
		loadMenu.setBounds(0,0,1000,600);
		loadMenu.setLayout(null);
		
		// create game container
		contentPanel = new Draw();
		contentPanel.setBounds(0,0,1000,600);
		contentPanel.setLayout(null);
		
		// set frame layout and main container to the frame
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(MainMenu);
		
		// file menu bar 
		JMenuBar MB = new JMenuBar();
		frame.setJMenuBar(MB);
		
		// file menu
		JMenu menu = new JMenu("Menu");
		//menu.setHorizontalAlignment(SwingConstants.CENTER);
		MB.add(menu);
		
		// file menu items
		JMenuItem item_1 = new JMenuItem("New Game");
		item_1.addActionListener(new NewGameListener());
		menu.add(item_1);
		
		JMenuItem item_2 = new JMenuItem("Help");
		item_2.addActionListener(new HelpListener());
		menu.add(item_2);
		
		JMenuItem item_3 = new JMenuItem("Save Game");
		//menu1.setHorizontalAlignment(SwingConstants.CENTER);
		item_3.addActionListener(new SaveListener());
		menu.add(item_3);
		
		JMenuItem item_4 = new JMenuItem("load Game");
		item_4.addActionListener(new loadListener());
		menu.add(item_4);
		
				
		JMenuItem item_5 = new JMenuItem("Exit");
		item_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {				
				System.exit(0);
			}
		});
		menu.add(item_5);
		
		// main menu label
		JLabel menuLabel = new JLabel();
		menuLabel.setBackground(new Color(0,0,0));
		menuLabel.setFont(new Font("Courier new",Font.BOLD,60));
		menuLabel.setText("21 Card Game");
		menuLabel.setBounds(275,150,435,60);
		MainMenu.add(menuLabel);
				
		//new game button on main menu panel
		JButton mainNew = new JButton("New Game");
		mainNew.setFocusPainted(false);
		mainNew.setBorder(BorderFactory.createLineBorder(Color.gray));
		mainNew.setHorizontalAlignment(SwingConstants.CENTER);
		mainNew.setFont(new Font("Courier new", Font.BOLD,22));
		mainNew.setBounds(300, 400, 115, 40);
		mainNew.setBackground(Color.YELLOW);
		MainMenu.add(mainNew);
				
		//Load button on main menu panel
		JButton load = new JButton("Load");
		load.setFocusPainted(false);
		load.setBorder(BorderFactory.createLineBorder(Color.gray));
		load.setHorizontalAlignment(SwingConstants.CENTER);
		load.setFont(new Font("Courier new", Font.BOLD,22));
		load.setBounds(575, 400, 95, 40);
		load.setBackground(Color.YELLOW);
		MainMenu.add(load);
		
		// load menu label
		JLabel loadLabel = new JLabel();
		loadLabel.setBackground(new Color(0,0,0));
		loadLabel.setFont(new Font("Arial",Font.BOLD,30));
		loadLabel.setText("Load Game");
		loadLabel.setBounds(400,50,380,40);
		loadMenu.add(loadLabel);
				
		// load file 1 label
		JLabel loadSelect1 = new JLabel();
		loadSelect1.setBackground(new Color(0,0,0));
		loadSelect1.setFont(new Font("Arial",Font.BOLD,20));
		loadSelect1.setText(loadState1.getName()+ "  Wins: " + loadState1.getWins() + "  Loses: " + loadState1.getLoses() + "  Ties: "+loadState1.getTies());
		loadSelect1.setBounds(300,100,380,40);
		loadMenu.add(loadSelect1);
		
		// when panels are clicked, background color is changed
		// load file 1 panel
		load_1 = new JPanel();
		load_1.setBackground(Color.LIGHT_GRAY);
		load_1.setFont(new Font("Arial",Font.BOLD,20));
		load_1.setBounds(300,100,380,40);
		load_1.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent e) { 
	        	  choose1 = true;
	        	  choose2 = false;
	        	  choose3 = false;
	        	  changeColor(choose1,choose2,choose3);
	            } 
	          }); 
		loadMenu.add(load_1);
		
		// load file 2 label
		JLabel loadSelect2 = new JLabel();
		loadSelect2.setBackground(new Color(0,0,0));
		loadSelect2.setFont(new Font("Arial",Font.BOLD,20));
		loadSelect2.setText(loadState2.getName()+ "  Wins: " + loadState2.getWins() + "  Loses: " + loadState2.getLoses() + "  Ties: "+loadState2.getTies());
		loadSelect2.setBounds(300,150,380,40);
		loadMenu.add(loadSelect2);
		
		// load file 2 panel
		load_2 = new JPanel();
		load_2.setBackground(Color.LIGHT_GRAY);
		load_2.setFont(new Font("Arial",Font.BOLD,20));
		load_2.setBounds(300,150,380,40);
		load_2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				choose2 = true;
				choose1 = false;
				choose3 = false;
				changeColor(choose1,choose2,choose3);
			}
		});
		loadMenu.add(load_2);
	
		// load file 3 label
		JLabel loadSelect3 = new JLabel();
		loadSelect3.setBackground(new Color(0,0,0));
		loadSelect3.setFont(new Font("Arial",Font.BOLD,20));
		loadSelect3.setText(loadState3.getName()+ "  Wins: " + loadState3.getWins() + "  Loses: " + loadState3.getLoses() + "  Ties: "+loadState3.getTies());
		loadSelect3.setBounds(300,200,380,40);
		loadMenu.add(loadSelect3);
		
		// load file 3 panel
		load_3 = new JPanel();
		load_3.setBackground(Color.LIGHT_GRAY);
		load_3.setFont(new Font("Arial",Font.BOLD,20));
		load_3.setBounds(300,200,380,40);
		load_3.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				choose3 = true;
				choose2 = false;
				choose1 = false;
				changeColor(choose1,choose2,choose3);
				
			}
		});
		loadMenu.add(load_3);
		
		// new game button to load container
		JButton loadNew = new JButton("New Game");
		loadNew.setFocusPainted(false);
		loadNew.setBorder(BorderFactory.createLineBorder(Color.gray));
		loadNew.setHorizontalAlignment(SwingConstants.CENTER);
		loadNew.setFont(new Font("Courier new", Font.BOLD,22));
		loadNew.setBounds(300, 400, 115, 40);
		loadNew.setBackground(Color.YELLOW);
		loadMenu.add(loadNew);
				
		// load button to load container
		loadStats = new JButton("Load");
		loadStats.setFocusPainted(false);
		loadStats.setBorder(BorderFactory.createLineBorder(Color.gray));
		loadStats.setHorizontalAlignment(SwingConstants.CENTER);
		loadStats.setFont(new Font("Courier new", Font.BOLD,22));
		loadStats.setBounds(575, 400, 95, 40);
		loadStats.setBackground(Color.YELLOW);
		loadStats.setEnabled(false);
		loadMenu.add(loadStats);
		
		// add hit button to panel
		JButton Hit = new JButton("Hit");
		Hit.setFocusPainted(false);
		Hit.setBorder(BorderFactory.createLineBorder(Color.gray));
		Hit.setHorizontalAlignment(SwingConstants.CENTER);
		Hit.setFont(new Font("Courier new", Font.BOLD,22));
		Hit.setBounds(650, 250, 95, 40);
		Hit.setBackground(Color.YELLOW);
		contentPanel.add(Hit);
		
		//add stand button to panel
		JButton Stay = new JButton("Stay");
		Stay.setFocusPainted(false);
		Stay.setBorder(BorderFactory.createLineBorder(Color.gray));
		Stay.setHorizontalAlignment(SwingConstants.CENTER);
		Stay.setFont(new Font("Courier new", Font.BOLD,22));
		Stay.setBounds(760, 250, 95, 40);
		Stay.setBackground(Color.YELLOW);
		contentPanel.add(Stay);
		
		//add play button to panel
		JButton game = new JButton(" Play ");
		game.setFocusPainted(false);
		game.setBorder(BorderFactory.createLineBorder(Color.gray));
		game.setHorizontalAlignment(SwingConstants.CENTER);
		game.setFont(new Font("Courier new", Font.BOLD,22));
		game.setBounds(695, 320, 115, 40);
		game.setBackground(Color.YELLOW);
		contentPanel.add(game);
		
		//Main Menu event listener
		mainNew.addActionListener(new ChangeMenu());
		load.addActionListener(new ChangeLoad());
		
		//load menu event listener
		loadNew.addActionListener(new ChangeMenu());
		loadStats.addActionListener(new loadGame());
		
		//game event listeners
		Hit.addActionListener(new HitListener());
		Stay.addActionListener(new standListener());
		game.addActionListener(new ChangeMenu());
		
		frame.setVisible(true);	
	}
	
	/**
	 * The setupGame method resets the game
	 */
	private void setupGame() {		
		// resets game
		deck = new Deck(contentPanel,"cards.gif");		
		player = new User();
		dealer = new User();
		driver = new Driver();
		message = "";
		gameRunning = true;
		contentPanel.setMessage(message);
		frame.repaint();
	}
	
	/**
	 * The changeColor method changes the color of the load
	 * file panels once change file is selected the load button
	 * is enabled 
	 * 
	 * @param choose1 represents load file 1
	 * @param choose2 represents load file 2
	 * @param choose3 represents load file 3
	 */
	private void changeColor(boolean choose1,boolean choose2,boolean choose3) {
		if(choose1) {
			load_1.setBackground(Color.blue);
			load_2.setBackground(Color.LIGHT_GRAY);
			load_3.setBackground(Color.LIGHT_GRAY);
			loadStats.setEnabled(true);
		}else if(choose2) {
			load_1.setBackground(Color.LIGHT_GRAY);
			load_2.setBackground(Color.BLUE);
			load_3.setBackground(Color.LIGHT_GRAY);
			loadStats.setEnabled(true);
		}else if(choose3) {
			load_1.setBackground(Color.LIGHT_GRAY);
			load_2.setBackground(Color.LIGHT_GRAY);
			load_3.setBackground(Color.BLUE);
			loadStats.setEnabled(true);
		}else {
			load_1.setBackground(Color.LIGHT_GRAY);
			load_2.setBackground(Color.LIGHT_GRAY);
			load_3.setBackground(Color.LIGHT_GRAY);
			loadStats.setEnabled(false);
		}
		
		frame.repaint();
	}
	
	/**
	 * The DealCard method deals initial cards 
	 * for the game and checks for a win,loss or tie
	 * on deal
	 */
	
	private void DealCard() {
	// deals the initial cards.
		firstPlay = false;
		if(gameRunning) {
			gameRunning = false;		
		}
		if(!gameRunning) {
			setupGame();
			deck.shuffle();			
	// deal the first 2 cards to player and dealer
			for(int i = 0; i < 2; i++) {
				player.addCard(deck.getNextCardInDeck());
				dealer.addCard(deck.getNextCardInDeck());
			}
			contentPanel.setDealerPoints(dealer.getRank());
	// check if player hit 21 and if player wins or is a tie		
			if(driver.check_21(player)) {
				if(driver.checkWinner(player, dealer) == Driver.Winner.TIE) {
					message = "Blackjack! TIE";
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					ties++;
					contentPanel.setTie(ties);
					gameRunning = false;
				}else {
					message = "Blackjack! You Win!";
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					wins++;
					contentPanel.setWins(wins);				
					gameRunning = false;
				}
			}
			contentPanel.setDealerHand(dealer.getHand());
			contentPanel.setPlayerHand(player.getHand());
			contentPanel.setCardback(deck.getCardBack());
			contentPanel.setPlayerPoints(player.getTotal());
			contentPanel.setGameRunning(gameRunning);
			frame.repaint();
		}
	}
	
	/**
	 * The ChangeMenu inner class implements the ActionListener
	 * for creating the main game screen this works in the
	 * the start menu or the load menu when 
	 * 
	 * @see ActionListener
	 * @see GUI.ChangeMenu
	 */
	class ChangeMenu implements ActionListener{
		/*
		 *ChangeMenu class creates the main game screen.
		 */
		// change current panel to play Game
		public void actionPerformed(ActionEvent event) {
			frame.getContentPane().remove(MainMenu);
			frame.getContentPane().remove(loadMenu);
			frame.getContentPane().add(contentPanel);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
			// if game first played set all stats to 0 and name to Player			
			if(firstPlay) {
				contentPanel.setName("Player");
				contentPanel.setWins(wins);
				contentPanel.setLoses(loses);
				contentPanel.setTie(ties);
				firstPlay = false;
			}
			DealCard();
			frame.repaint();
		}
	}
	
	/**
	 * The ChangeLoad subclass implements the ActionListener
	 * for changing the start screen to the load screen
	 * when the load button is clicked on the start menu.
	 * 
	 * @see ActionListener
	 */
	class ChangeLoad implements ActionListener{
		// change container to load screen
		public void actionPerformed(ActionEvent event) {
			frame.getContentPane().remove(MainMenu);
			frame.getContentPane().add(loadMenu);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
			frame.repaint();
		}
	}
	
	/**
	 * The loadGame subclass implements the ActionListener
	 * for loading the selected game from the load screen
	 * when the load button is clicked on the load screen.
	 * 
	 * @see ActionListener
	 */
	class loadGame implements ActionListener{
		// play from load container
		public void actionPerformed(ActionEvent event) {
			frame.getContentPane().remove(MainMenu);
			frame.getContentPane().remove(loadMenu);
			frame.getContentPane().add(contentPanel);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
			// if this is the first play from load
			// set all the stats to that files stats 
			if(firstPlay) {					
				if(choose1) {
					wins = loadState1.getWins();
					loses = loadState1.getLoses();
					ties = loadState1.getTies();
					contentPanel.setName(loadState1.getName());
					contentPanel.setWins(loadState1.getWins());
					contentPanel.setLoses(loadState1.getLoses());
					contentPanel.setTie(loadState1.getTies());
				}else if(choose2) {
					wins = loadState2.getWins();
					loses = loadState2.getLoses();
					ties = loadState2.getTies();
					contentPanel.setName(loadState2.getName());
					contentPanel.setWins(loadState2.getWins());
					contentPanel.setLoses(loadState2.getLoses());
					contentPanel.setTie(loadState2.getTies());
				}else if(choose3) {
					wins = loadState3.getWins();
					loses = loadState3.getLoses();
					ties = loadState3.getTies();
					contentPanel.setName(loadState3.getName());
					contentPanel.setWins(loadState3.getWins());
					contentPanel.setLoses(loadState3.getLoses());
					contentPanel.setTie(loadState3.getTies());
				}
				
				firstPlay = false;
			}
			DealCard();
			frame.repaint();
		}
	}
	/**
	 * The NewGameListener subclass implements ActionListener
	 * for creating a new game when the new game
	 * button is clicked.
	 * @see ActionListener
	 */
	class NewGameListener implements ActionListener{
		// creates a new game if from the JFrame menu
		public void actionPerformed(ActionEvent event) {
			frame.getContentPane().remove(MainMenu);
			frame.getContentPane().remove(loadMenu);
			frame.getContentPane().add(contentPanel);
			frame.getContentPane().invalidate();
			frame.getContentPane().validate();
			wins = 0;
			loses = 0;
			ties = 0;
			contentPanel.setWins(wins);
			contentPanel.setLoses(loses);
			contentPanel.setTie(ties);
			DealCard();
		}
	}
	 /**
	  * The HitListener subclass implements the ActionListener
	  * for adding another card to the player hand
	  * when the hit button is clicked.
	  * 
	  * @see ActionListener
	  */
	class HitListener implements ActionListener{
		// draws another card for the player
		public void actionPerformed(ActionEvent event) {
			// if its the players turn, add a card and check for bust
			if(gameRunning) {				
				player.addCard(deck.getNextCardInDeck());
				if(driver.checkBust(player)) {
					message = "Player Busted! You Lose!";
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					loses++;
					contentPanel.setLoses(loses);
					gameRunning = false;
				}
				contentPanel.setPlayerHand(player.getHand());
				contentPanel.setPlayerPoints(player.getTotal());
				contentPanel.setGameRunning(gameRunning);
				frame.repaint();
			}
			
		}
	}
	
	/**
	 * The standListener subclass implements the ActionListener
	 * when the stand button is clicked in the main game. 
	 * this button ends the player turn when pressed and 
	 * start dealers turn. If game is running change to not running
	 * check if dealer has a soft hand if so draw a card
	 * compare dealer and player points for winner
	 * after winner is declared display message
	 * 
	 * @see ActionListener
	 */
	class standListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			Driver.Winner winner;
			if(gameRunning) {
				gameRunning = false;				
				while(dealer.getTotal() <= Soft) {
					dealer.addCard(deck.getNextCardInDeck());
					contentPanel.setDealerPoints(dealer.getTotal());
				}
				if(driver.checkBust(dealer)) {
					message = "Dealer Busted! You Win!";
					contentPanel.setMessage(message);
					contentPanel.setGameRunning(gameRunning);
					wins++;
					contentPanel.setWins(wins);
					frame.repaint();
				}else {
					winner = driver.checkWinner(player, dealer);
					switch(winner) {
					case PLAYER: message = "Higher Hand! You Win!";	
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					wins++;
					contentPanel.setWins(wins);
								 break;
					case DEALER: message = "Lower Hand! You Lose!";
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					loses++;
					contentPanel.setLoses(loses);
								 break;
					case TIE:	 message = "Dealer/Player Tie!";
					contentPanel.setDealerPoints(dealer.getTotal());
					contentPanel.setMessage(message);
					ties++;
					contentPanel.setTie(ties);
								 break;
					default:break;
					}
					contentPanel.setGameRunning(gameRunning);
					frame.repaint();
				}
			}
		}
	}
	
	/**
	 * The HelpListener subclass implements the ActionListener
	 * when the help button is selected from the menu bar.
	 * This action displays a small window with game 
	 * rules and how to play.
	 * 
	 * @see ActionListener
	 */
	class HelpListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			JFrame HelpPop = new JFrame("Help");
			HelpPop.setBounds(400,200,515, 300);
			HelpPop.dispatchEvent(new WindowEvent(HelpPop, WindowEvent.WINDOW_CLOSING));
			HelpPop.setResizable(false);
			HelpPop.setVisible(true);
			
			// Text Area
			JTextArea HelpText = new JTextArea(0,30);
			HelpText.append(
			"\nRules:\n   Blackjack is played with one or more standard 52-card decks, with each\n" + 
				"   denomination assigned a point value. The cards 2 through 10 are worth \n"+
				"   their face value. Kings, Queens, and Jacks are each worth 10, and Aces \n"+
				"   may be used as either 1 or 11. The object for the player is to draw cards\n"+
				"   totaling closer to 21, without going over. to win you must beat the dealers\n"+
				"   cards or get a score of 21. If you or the dealers total is over 21 you then\n"+
				"   lose or bust.\n\n"+
				
				"Play:\n   When loading a new game the player recieves 2 cards facing up and the\n"+ 
				"   dealer recieves 2 cards with one facing down. The player goes first,\n"+
				"   drawing more cards until they reach closer to 21. If the player or \n"+
				"   dealer goes over 21 they loose. Wins, loses, and ties are kept through out\n"+
				"   the players game session\n\n"+
				
				
				"Saving:\n   To save the game follow theses instructions. First click on the Menu bar\n"+
				"   in the top left corner of the window. Move cursor onto the drop down\n"+ 
				"   and click the Save Button. A popup window will appear. select one of\n"+
				"   the 3 save files. You can choose to save the game with the selected name\n"+
				"   or rename it in the text box.\n\n"+
				
				"Loading:\n   You can load a game from the main menu button or load a game from\n"+
				"   the Start Page by pressing the Load Button. Both methods work the\n"+
				"   same. To load a game choosing Load Button from the Start Page.\n"+
				"   Click on th Load Button, a new screen will appear with three files.\n"+
				"   Select on of the file and click the Load button on on this screen. \n"+
				"   The game will be loaded with the saved data from that file. To load a\n"+
				"   Game from the Main Menu button in the left hand corner of the window\n"+
				"   select that button, move the cursor to the Load Button. A popup menu\n"+
				"   will appear with three files that can be loaded. Choose 1 and click\n"+
				"   the load button. The game will start with the saved data.\n"
				
			);
			
			HelpText.setBounds(315,150,462,226);			
			HelpText.setBackground(Color.white);
			HelpText.setEditable(false);
			HelpText.setCaretPosition(HelpText.getDocument().getLength());
			HelpText.setFont(new Font("Serif", Font.ROMAN_BASELINE, 16));
			HelpText.setCaretPosition(0);
			JScrollPane scroll = new JScrollPane(HelpText,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			
			HelpPop.add(scroll);
		}
	}
	
	/**
	 * The SaveListener class implements the ActionListener
	 * for saving the game. This action displays a small
	 * window for selecting a save file. When file is selected
	 * the current name of the file will be used unless changed
	 * by the user.
	 * 
	 * @see ActionListener
	 */
	class SaveListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			loadState();
			JFrame savePop = new JFrame("Save");
			savePop.setBounds(400,200,500,300);
			savePop.dispatchEvent(new WindowEvent(savePop, WindowEvent.WINDOW_CLOSING));
			savePop.setResizable(false);
			JPanel savePanel = new JPanel();
			savePanel.setLayout(null);
			savePanel.setBounds(0,0,500,300);
			savePanel.setBackground(new Color(212, 214, 214));
			savePop.getContentPane().setLayout(null);
			savePop.getContentPane().add(savePanel);
			savePop.setVisible(true);
			// creates the textfield for the save window
			JTextField textField = new JTextField();
			textField.addMouseListener(new MouseAdapter(){
	            @Override
	            public void mouseClicked(MouseEvent e){
	                textField.setText("");
	                
	            }
	        });
			textField.setBounds(32, 27, 209, 20);
			savePanel.add(textField);
			textField.setColumns(10);
			// creates the submit button 
			JButton Submit = new JButton("Submit");
			Submit.setEnabled(false);
			Submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					String textName = textField.getText();
					if(textName == "") {
						textField.setText("");
						textField.requestFocusInWindow();
					}
					else if(textName != "" && choose1 == true) {
						Save(textName);
						savePop.dispose();
						
					}else if(textName != "" && choose2 == true) {
						Save(textName);
						savePop.dispose();
					
					}else if(textName != "" && choose3 == true) {
						Save(textName);
						savePop.dispose();
					}
				}
			});
			
			Submit.setBounds(250,27,110,23);
			Submit.setFocusPainted(false);
			Submit.setBorder(BorderFactory.createLineBorder(Color.gray));
			savePanel.add(Submit);
			// shows saved file 1
			JLabel loadSelect1 = new JLabel();
			loadSelect1.setBackground(new Color(0,0,0));
			loadSelect1.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect1.setText(loadState1.getName()+ "  Wins: " + loadState1.getWins() + "  Loses: " + loadState1.getLoses() + "  Ties: "+loadState1.getTies());
			loadSelect1.setBounds(20,100,380,40);
			savePanel.add(loadSelect1);
			// save state panels
			load_1 = new JPanel();
			load_1.setBackground(Color.LIGHT_GRAY);
			load_1.setFont(new Font("Arial",Font.BOLD,20));
			load_1.setBounds(20,100,380,40);
			load_1.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent e) { 
		        	  choose1 = true;
		        	  choose2 = false;
		        	  choose3 = false;
		        	  changeColor(choose1,choose2,choose3);
		        	  Submit.setEnabled(true);
		        	  textField.setText(loadState1.getName());
		        	  
		        	  savePop.repaint();
		            } 
		          }); 
			savePanel.add(load_1);
			// shows save file 2
			JLabel loadSelect2 = new JLabel();
			loadSelect2.setBackground(new Color(0,0,0));
			loadSelect2.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect2.setText(loadState2.getName()+ "  Wins: " + loadState2.getWins() + "  Loses: " + loadState2.getLoses() + "  Ties: "+loadState2.getTies());
			loadSelect2.setBounds(20,150,380,40);
			savePanel.add(loadSelect2);
			load_2 = new JPanel();
			load_2.setBackground(Color.LIGHT_GRAY);
			load_2.setFont(new Font("Arial",Font.BOLD,20));
			load_2.setBounds(20,150,380,40);
			load_2.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					choose2 = true;
					choose1 = false;
					choose3 = false;
					changeColor(choose1,choose2,choose3);
					Submit.setEnabled(true);
					textField.setText(loadState2.getName());
					
					savePop.repaint();
				}
			});
			savePanel.add(load_2);
			// shows save file 3
			JLabel loadSelect3 = new JLabel();
			loadSelect3.setBackground(new Color(0,0,0));
			loadSelect3.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect3.setText(loadState3.getName()+ "  Wins: " + loadState3.getWins() + "  Loses: " + loadState3.getLoses() + "  Ties: "+loadState3.getTies());
			loadSelect3.setBounds(20,200,380,40);
			savePanel.add(loadSelect3);
			load_3 = new JPanel();
			load_3.setBackground(Color.LIGHT_GRAY);
			load_3.setFont(new Font("Arial",Font.BOLD,20));
			load_3.setBounds(20,200,380,40);
			load_3.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					choose3 = true;
					choose2 = false;
					choose1 = false;
					changeColor(choose1,choose2,choose3);
					Submit.setEnabled(true);
					textField.setText(loadState3.getName());
					
					savePop.repaint();
				}
			});
			savePanel.add(load_3);
		}
	}
	
	/**
	 * The loadListener implements the ActionListener 
	 * for loading a game.This action displays a 
	 * small window with the three load files
	 * 
	 * @see ActionListener
	 */
	class loadListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			// calls the load state method
			loadState();
			// creates the small load window
			JFrame loadPop = new JFrame("Load Game");
			loadPop.setBounds(400,200,500,300);
			loadPop.dispatchEvent(new WindowEvent(loadPop, WindowEvent.WINDOW_CLOSING));
			loadPop.setResizable(false);
			// creates the jpanel for the load window
			JPanel loadPanel = new JPanel();
			loadPanel.setLayout(null);
			loadPanel.setBounds(0,0,500,300);
			loadPanel.setBackground(new Color(212, 214, 214));
			loadPop.getContentPane().setLayout(null);
			loadPop.getContentPane().add(loadPanel);
			loadPop.setVisible(true);
			// creates the load label in the load window
			JLabel loadLabel = new JLabel("Load Game");
			loadLabel.setBackground(new Color(0,0,0));
			loadLabel.setFont(new Font("Arial",Font.BOLD,20));
			loadLabel.setBounds( 185,10,150,40);
			loadPanel.add(loadLabel);
			// creates the load button
			JButton Submit = new JButton("Submit");
			Submit.setEnabled(false);
			Submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					frame.getContentPane().remove(MainMenu);
					frame.getContentPane().remove(loadMenu);
					frame.getContentPane().add(contentPanel);
					frame.getContentPane().invalidate();
					frame.getContentPane().validate();
					// based on the choice the file will load
					if(choose1) {
						wins = loadState1.getWins();
						loses = loadState1.getLoses();
						ties = loadState1.getTies();
						contentPanel.setName(loadState1.getName());
						contentPanel.setWins(loadState1.getWins());
						contentPanel.setLoses(loadState1.getLoses());
						contentPanel.setTie(loadState1.getTies());
					}else if(choose2) {
						wins = loadState2.getWins();
						loses = loadState2.getLoses();
						ties = loadState2.getTies();
						contentPanel.setName(loadState2.getName());
						contentPanel.setWins(loadState2.getWins());
						contentPanel.setLoses(loadState2.getLoses());
						contentPanel.setTie(loadState2.getTies());
					}else if(choose3) {
						wins = loadState3.getWins();
						loses = loadState3.getLoses();
						ties = loadState3.getTies();
						contentPanel.setName(loadState3.getName());
						contentPanel.setWins(loadState3.getWins());
						contentPanel.setLoses(loadState3.getLoses());
						contentPanel.setTie(loadState3.getTies());
					}
					
					DealCard();
					frame.repaint();
					loadPop.dispose();
				}
			});

			Submit.setBounds(185,210,110,23);
			Submit.setFocusPainted(false);
			Submit.setBorder(BorderFactory.createLineBorder(Color.gray));
			loadPanel.add(Submit);
			// creates the labels from the first file to be loaded
			JLabel loadSelect1 = new JLabel();
			loadSelect1.setBackground(new Color(0,0,0));
			loadSelect1.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect1.setText(loadState1.getName()+ "  Wins: " + loadState1.getWins() + "  Loses: " + loadState1.getLoses() + "  Ties: "+loadState1.getTies());
			loadSelect1.setBounds(50,50,380,40);
			loadPanel.add(loadSelect1);
			
			// save state panels
			load_1 = new JPanel();
			load_1.setBackground(Color.LIGHT_GRAY);
			load_1.setFont(new Font("Arial",Font.BOLD,20));
			load_1.setBounds(50,50,380,40);
			load_1.addMouseListener(new MouseAdapter() { 
		          public void mousePressed(MouseEvent e) { 
		        	  choose1 = true;
		        	  choose2 = false;
		        	  choose3 = false;
		        	  changeColor(choose1,choose2,choose3);
		        	  Submit.setEnabled(true);
		        	  loadPop.repaint();
		            } 
		          }); 
			loadPanel.add(load_1);
			// creates the labels for the second file to be loaded
			JLabel loadSelect2 = new JLabel();
			loadSelect2.setBackground(new Color(0,0,0));
			loadSelect2.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect2.setText(loadState2.getName()+ "  Wins: " + loadState2.getWins() + "  Loses: " + loadState2.getLoses() + "  Ties: "+loadState2.getTies());
			loadSelect2.setBounds(50,100,380,40);
			loadPanel.add(loadSelect2);
			load_2 = new JPanel();
			load_2.setBackground(Color.LIGHT_GRAY);
			load_2.setFont(new Font("Arial",Font.BOLD,20));
			load_2.setBounds(50,100,380,40);
			load_2.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					choose2 = true;
					choose1 = false;
					choose3 = false;
					changeColor(choose1,choose2,choose3);
					Submit.setEnabled(true);
				
					loadPop.repaint();
				}
			});
			loadPanel.add(load_2);
			// creates the labels for the third file to be loaded
			JLabel loadSelect3 = new JLabel();
			loadSelect3.setBackground(new Color(0,0,0));
			loadSelect3.setFont(new Font("Arial",Font.BOLD,20));
			loadSelect3.setText(loadState3.getName()+ "  Wins: " + loadState3.getWins() + "  Loses: " + loadState3.getLoses() + "  Ties: "+loadState3.getTies());
			loadSelect3.setBounds(50,150,380,40);
			loadPanel.add(loadSelect3);
			load_3 = new JPanel();
			load_3.setBackground(Color.LIGHT_GRAY);
			load_3.setFont(new Font("Arial",Font.BOLD,20));
			load_3.setBounds(50,150,380,40);
			load_3.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					choose3 = true;
					choose2 = false;
					choose1 = false;
					changeColor(choose1,choose2,choose3);
					Submit.setEnabled(true);
					loadPop.repaint();
				}
			});
			loadPanel.add(load_3);
		}
	}
}

