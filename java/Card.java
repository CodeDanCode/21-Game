package edu.fau.COT4930;

import java.awt.*;
/**
 * card class for card values,images and suits
 * 
 * @author Daniel Leach
 */

public class Card {
	private int s;
	private int v;
	private Image im;
	
	/**
	 * The Card constructor sets the images, suits and values
	 * @param s		represents the suit in the card
	 * @param v		represents the value of the card
	 * @param im	represents the image of the card
	 * @see Image
	 */
	
	// card constructor
	public Card(int s, int v, Image im) {
		this.s = s;
		this.v = v;
		this.im = im;
	}
	
	/**
	 * getImage method gets the image from the card
	 * @return  the image of a card object
	 */
	public Image getImage() {
		return im;
	}
	/**
	 * getValue method gets value of card
	 * @return the integer value of the card
	 */
	public int getValue() {
		return v;
	}
	/**
	 * getSuit method gets the suit value of the card
	 * @return an integer value representing the suit
	 */
	public int getSuit() {
		return s;
	}
	
	
}
