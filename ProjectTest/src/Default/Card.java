package Default;

import javax.swing.ImageIcon;

public class Card implements Comparable<Card> {
	
	private int cardNum;
	private int cardPattern;
	private int cardState = 0;
	ImageIcon img;
	
	
	public int getCardState() {
		return cardState;
	}
	public void setCardState(int cardState) {
		this.cardState = cardState;
	}
	public int getCardPattern() {
		return cardPattern;
	}
	public void setCardPattern(int cardPattern) {
		this.cardPattern = cardPattern;
	}	
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	
	public String toString() {
		return "카드번호 :" + cardNum + " 카드패턴 : " + cardPattern;
	}
	
	public int compareTo(Card c) {
		if(this.cardNum< c.cardNum) {
			return -1;
		}
		else if(this.cardNum == c.cardNum) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
