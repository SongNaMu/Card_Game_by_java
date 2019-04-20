package Default;

import java.util.Arrays;
import java.util.Random;

public class CardShuffle {//카드생성후 순서 섞기

	Card[] card;
	Random generator = new Random();

	public CardShuffle(int num) {
		card = new Card[num*num];
		for(int i= 0; i < (num*num); i++) {
			card[i] = new Card();
			card[i].setCardNum(generator.nextInt(36));
			//card[i].setCardNum(i);
			
			card[i].setCardPattern(i/2);
		//	System.out.println(card[i]);
		}
		Arrays.sort(card);
		//System.out.println("정렬");
		for(int i= 0; i < (num*num); i++) {
			card[i].setCardNum(i);
			//System.out.println(card[i]);
		}
		for(int i = 0; i < (num*num); i++) {
			if(i%num == 0)
				System.out.println("");
			System.out.printf(" %2d ", card[i].getCardPattern());
		}
		System.out.println("");
	}
	
	
	
	

	
	
	
	

}
