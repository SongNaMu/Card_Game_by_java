 package Default;

public class Search {
	ChanceOnOff chanceOnOff = new ChanceOnOff();
	
	
	public int searchPattern(Card card[],int pattern) {
		int res = -1;
		
		for(int i = 0; i < card.length; i++) {
			if((card[i].getCardPattern() == card[pattern].getCardPattern())&&(card[i].getCardNum() != card[pattern].getCardNum())) {
				//System.out.println("클릭한 카드 패턴 = " + card[num].getCardPattern() + "찾은 카드패턴 = " + card[i].getCardPattern());
				//System.out.println("클릭한카드 주소 = " + card[num].getCardNum() + "찾은카드 주소 = " + card[i].getCardNum());
				res = card[i].getCardNum();
			}
				
		}
		chanceOnOff.chanceOff();
		return res;
	}
}
