package Default;

public class ClickCard {
	private int first = 0;
	
	private int firstPattern = -1;
	private int firstNum = -1;
	private int second = 0;
	private int secondPattern = -1;
	private int secondNum = -1;
	
	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getFirstNum() {
		return firstNum;
	}
	
	public int getSecondNum() {
		return secondNum;
	}

	public void click(int pattern, int num) {
		if (first == 0) { //카드 가 뒤집어져있는지 상태확인 0뒷면 1앞면
			first = 1;
			firstPattern = pattern;
			firstNum = num;
		}
		else if(first !=0 && num != firstNum && second ==0){
			second = 1;
			secondPattern = pattern;
			secondNum = num;
		}
	}
	
	public int checkCard() {//카드의 패턴이 같은지 아닌지 판별후 같으면1 다르면0 카드가 두장 오픈되어있지않으면 -1
		if(first ==1 && second ==1) {
			if(firstPattern == secondPattern) 
				return 1;
			else 
				return 0;
		}
		else
			return -1;
	}
	
	public void setAll() {
		first = 0;
		second = 0;
		firstPattern = -1;
		secondPattern = -1;
		firstNum =-1;
		secondNum =-1;
	}

}
