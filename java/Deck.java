package edu.fau.COT4930;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;

/**
 * This class creates the deck of cards for the game
 * 
 * @author Daniel Leach
 */

public class Deck {
	private Component comp;
	private String fileCards;
	private ArrayList<Card> deck;
	private ArrayList<Card> backDeck;
	private Image image;
	private Card next;
	
	/**
	 * Deck Constructor sets the component and image file.
	 * the constructor also calls the CreateDeck method
	 * @param comp  This represents a Component object
	 * @param fileCards This represents a String for file location
	 * @see #CreateDeck()
	 */
	public Deck(Component comp, String fileCards) {
		this.comp = comp;
		this.fileCards = fileCards;
		CreateDeck();
		
	}
	/**
	 * The Create deck method
	 * Creates the deck by importing the file, 
	 * crop image by suit and rank 
	 * enlarge each new cropped image
	 * add new enlarged image to deck array
	 */
	public void CreateDeck() {
		deck = new ArrayList<Card>();	
		backDeck = new ArrayList<Card>();
		int ypos = 0;
		BufferedImage img = null;
		try {
			// use for Eclipse
			File f = new File(getClass().getResource("cards.gif").getFile());
			
			img = ImageIO.read(f);
			
			// Used for Netbeans or other compilers
			//File f = new File(fileCards);
			//img = ImageIO.read(new File(getClass().getResource(fileCards).getFile()));
			
			for(int i = 1; i <= 4;i++) {
				int xpos = 0;
				for(int j = 2; j <=14;j++) {
					
					ImageFilter crop;
					// crop a portion of the image
					crop = new CropImageFilter(xpos,ypos, 71, 96);
					image = comp.createImage(new FilteredImageSource(img.getSource(), crop));
					// enlarge image
					image = image.getScaledInstance(91, 116, Image.SCALE_SMOOTH);				
					deck.add(new Card(i,j,image));
					xpos = xpos+71;
				
				}
				ypos = ypos+96;
			
			}
			// creates an array for holding the back of the deck
			for(int i = 0; i < 13; i++) {
				int xpos = 0;
				ImageFilter cropBackground;
				cropBackground = new CropImageFilter(xpos,(96+96+96+96),71,96);
				image = comp.createImage(new FilteredImageSource(img.getSource(),cropBackground));
				// enlarge image
				image = image.getScaledInstance(91, 116, Image.SCALE_SMOOTH);
				backDeck.add(new Card(0,0,image));
				xpos = xpos+71;
			}
		}catch(IOException e) {
			System.out.println("Can not find the image file");
		}	
	}
	/**
	 * The shuffle method shuffles the deck and makes the deck whole
	 */
	public void shuffle(){
		CreateDeck();
		Collections.shuffle(deck);
	}
	/**
	 * getNextCardInDeck method gets next card in deck
	 * @return a card object from the list of card
	 * 		objects
	 */
	// get next card by removing it from the array list
	public Card getNextCardInDeck() {
		if(!deck.isEmpty()) {
			next = deck.remove(0);	
		}
		return  next;	
	}
	/** 
	 * The getNumberLeftInDeck method gets number of cards left in deck
	 * @return an integer of the number of card objects 
	 * 		left in the deck
	 */
	// get number of cards left in deck
	public int getNumberLeftInDeck() {
		return deck.size();
	}
	/**
	 * The getCardBack method gets the back card 
	 * image from the back deck list
	 * @return 	a card object with no value or suit
	 * 			with the back image
	 */
	// get the back card image
	public Card getCardBack() {
		return backDeck.remove(0);
	}
	
	
	
}
