 package Default;

public class Search {
	ChanceOnOff chanceOnOff = new ChanceOnOff();
	
	
	public int searchPattern(Card card[],int pattern) {
		int res = -1;
		
		for(int i = 0; i < card.length; i++) {
			if((card[i].getCardPattern() == card[pattern].getCardPattern())&&(card[i].getCardNum() != card[pattern].getCardNum())) {
				//System.out.println("Ŭ���� ī�� ���� = " + card[num].getCardPattern() + "ã�� ī������ = " + card[i].getCardPattern());
				//System.out.println("Ŭ����ī�� �ּ� = " + card[num].getCardNum() + "ã��ī�� �ּ� = " + card[i].getCardNum());
				res = card[i].getCardNum();
			}
				
		}
		chanceOnOff.chanceOff();
		return res;
	}
}
