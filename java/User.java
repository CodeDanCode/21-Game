package edu.fau.COT4930;

import java.util.ArrayList;
/**
 * user class that extends player class
 * this is used to store player hand
 * and assign rank values based on the 
 * card added to the hand this is done 
 * to keep track of current total in hand.
 * 
 * @author Daniel Leach
 */
public class User extends Player {
	
	private int total = 0;
	private int rank = 0;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	/**
	 * the constructor extends the player class
	 * @see Player
	 */
	public User() {
		super();
	}
	
	// add card method
	/**
	 * The addCard method adds a card to the user hand
	 * and gives it a value and a rank
	 * @param card represents a card object from the deck class
	 */
	public void  addCard(Card card) {
		int value = card.getValue();
		hand.add(card);
		if(value == 14) {
			rank = 11;
		}if(value == 14 && (total + rank > 21)) {
			rank = 1;
		}if(value == 11 || value == 12  || value == 13) {
			rank = 10;
		}
		else if(value <= 10) {
			rank = value;
		}
		total = total + rank;
	}
	
	/**
	 * The getHand method retrieves the list of 
	 * cards in the user hand
	 * @return hand represents the cards in the user hand
	 */
	// getters for hand,total, and rank
	public ArrayList<Card> getHand(){
		return hand;
	}
	/**
	 * The getTotal method retrieves the total value
	 * in the player/dealer hand
	 * @return total represents the total of point for player/dealer
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * getRank method retrieves the rank of the card
	 * @return rank represents the rank of the card in the player/dealer hand
	 */
	public int getRank() {
		return rank;
	}
	
}
