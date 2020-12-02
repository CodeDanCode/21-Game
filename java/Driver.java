package edu.fau.COT4930;
/**
 * main driver to determine winner or loser
 * 
 * @author Daniel Leach
 */
public class Driver {
	static final int blackJack = 21;
	public enum Winner{PLAYER,DEALER,TIE}
	
	/**
	 * This method checks to see if there is a winner,
	 * loser, or tie
	 * @param player	the player object
	 * @param dealer	the dealer object
	 * @return the winner, loser or tie at the end of a round
	 */
	public Winner checkWinner(User player,User dealer) {
		if(player.getTotal() == dealer.getTotal()) {
			return Winner.TIE;
		}else if (player.getTotal() > dealer.getTotal()) {
			return Winner.PLAYER;
		}else {
			return Winner.DEALER;
		}
			
	}
	
	/**
	 * checks to see if player/dealer has 21
	 * @param player	THe Player/Dealer Object
	 * @return <Code>True</Code> if player/dealer has blackjack
	 * 			<Code>False</Code> otherwise.
	 */
	public boolean check_21(User player) {
		if(player.getTotal() == blackJack) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * checks if player or dealer busted
	 * @param player	The Player/Dealer object
	 * @return	<Code>True</Code> if player/dealer busted
	 * 			<Code>False</Code> otherwise.
	 */
	public boolean checkBust(User player) {
		if(player.getTotal() > blackJack) {
			return true;
		}else {
			return false;
		}
	}
	
}
