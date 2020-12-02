package edu.fau.COT4930;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * draw class extends JPanel
 * this is the main panel for
 * games graphical user interface.
 * 
 * @author Daniel Leach
 */
public class Draw extends JPanel {
	/**
	 * @serial  The class has a serial ID
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Card> playerHand;
	private ArrayList<Card> dealerHand;
	private Card cardBack;
	boolean gameRunning;
	private String playerPoints = "";
	private String dealerPoints = "";
	private String message = "";
	private String wins = "";
	private String loses = "";
	private String ties = "";
	private String name = "";
	 
	/**
	 * sets the the player name
	 * @param name represents the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	/** 
	 * sets a list of card objects for the player hand
	 * @param playerHand represents the players hand
	 */
	public void setPlayerHand(ArrayList<Card> playerHand) {
		this.playerHand = playerHand;
	}
	/**
	 * sets a list of card objects for the dealer hand
	 * @param dealerHand represents the dealers hand
	 */
	public void setDealerHand(ArrayList<Card> dealerHand) {
		this.dealerHand = dealerHand;
	}
	/**
	 * sets the message to be displayed on screen
	 * @param message represents the messages for winning,losing,or ties
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * sets the current players points to be displayed 
	 * @param playerPoints represents the players hand value 
	 */
	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = Integer.toString(playerPoints);
	}
	/**
	 * sets the current dealer points to be displayed
	 * @param dealerPoints	represents the dealer hand value
	 */
	public void setDealerPoints(int dealerPoints) {
		this.dealerPoints = Integer.toString(dealerPoints);
	}
	/**
	 * sets boolean variable to tell if game is running
	 * @param gameRunning represents if game is running
	 */
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}
	/**
	 * sets the card back for the game 
	 * @param cardBack represents the card back image
	 */
	public void setCardback(Card cardBack) {
		this.cardBack = cardBack;
	}
	/**
	 * sets the number of win points
	 * @param wins represents the total of player wins
	 */
	public void setWins(int wins) {
		this.wins = Integer.toString(wins);
	}
	/**
	 * sets the number of lost points
	 * @param loses represents the total of player loses
	 */
	public void setLoses(int loses) {
		this.loses = Integer.toString(loses);
	}
	/**
	 * sets the number of tie points
	 * @param ties represents the total of ties
	 */
	public void setTie(int ties) {
		this.ties = Integer.toString(ties);
	}
	
	/**
	 * draws all labels, points, messages, and cards
	 * to the main game container
	 * 
	 */
	public void paintComponent(Graphics g) {
		// draw color of background to the panel
		g.setColor(new Color(39,119,20));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// draw player Label
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Player: ",20,500);

		// draw player points
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString(playerPoints,100,500);
		
		// draw dealer Label
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Dealer: ",20,50);

		// draw dealer points
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString(dealerPoints,100,50);
		
		// player name Label
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.setColor(new Color(0,0,0));
		g.drawString(name, 480, 50);
		
		// draw wins label
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Wins: ",700,78);
		
		// draw wins
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString(wins,770,78);
		
		// draw loses label
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Loses: ",700,128);
		
		// draw loses
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString(loses,770,128);
		
		// draw ties label
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString("Ties: ",700,178);
		// draw ties
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(0, 0, 0));
		g.drawString(ties,770,178);
		/**
		 * Draws the players hand to the screen
		 */
		// draw player hand
		if(playerHand != null) {
			for(int i = 0; i < playerHand.size();i++) {
				Image image = playerHand.get(i).getImage();
				g.drawImage(image,(i+45 % 13) * 30, 300, this);
			}
		}
		/** 
		 * Draws the dealers hand to the screen based on
		 * whos turn it is.
		 */
		// draw dealer hand
		if(dealerHand!=null) {
			for(int i = 0; i < dealerHand.size();i++) {
				Image image;
				if(gameRunning) {
					if(i == 0) {
						image = cardBack.getImage();
					}else {
						image = dealerHand.get(i).getImage();
					}
				}else {
					image = dealerHand.get(i).getImage();
				}
				
				g.drawImage(image,(i+45 % 13) * 30,100, this);
				
			}
		}
		
		/**
		 * draws the end result after win,loss,tie.
		 */
		// draw Results
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.setColor(new Color(50, 94, 237));
		g.drawString(message,230,80);
		
	}
}


