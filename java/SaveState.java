package edu.fau.COT4930;

import java.io.*;

/**
 * class for saving player states
 * this class saves name,wins,loses,ties
 * 
 * @author Daniel Leach
 */
public class SaveState implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int wins;
	private int loses;
	private int ties;
	private String name;
	
	
	/**
	 * save constructor
	 * @serialData allows the data to be saved
	 * @param w represents the number of wins
	 * @param l represents the number of loses
	 * @param t represents the number of ties
	 * @param n represents the players name
	 */
	public SaveState(int w,int l, int t, String n) {
		wins = w;
		loses = l;
		ties = t;
		name = n;
	}
	/**
	 * getName method retrieves players name
	 * @return name represents a string of the players name
	 */
	public String getName() {
		return name;
	}
	/**
	 * getWins method retrieves total wins
	 * @return wins represents the number of wins
	 */
	public int getWins() {
		return wins;
	}
	/**
	 * getLoses method retrieves total of losses
	 * @return loses represents the number of losses
	 */
	public int getLoses() {
		return loses;
	}
	/**
	 * getTies method retrieves total of ties
	 * @return ties represents the number of ties
	 */
	public int getTies() {
		return ties;
	}
}
