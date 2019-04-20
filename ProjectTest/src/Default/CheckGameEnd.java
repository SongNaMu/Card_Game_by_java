package Default;

public class CheckGameEnd {
	private int cardState;

	public int checkgameend(Card card[], int num) {
		
		for(int i = 0; i < num*num; i++) 
			cardState += card[i].getCardState();
		if(cardState == (num*num)) {
			cardState = 0;
			return 1;
		}
		else {
			cardState =0;
			return 0;
		}
		
	}
}
