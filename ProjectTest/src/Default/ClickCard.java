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
		if (first == 0) { //ī�� �� ���������ִ��� ����Ȯ�� 0�޸� 1�ո�
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
	
	public int checkCard() {//ī���� ������ ������ �ƴ��� �Ǻ��� ������1 �ٸ���0 ī�尡 ���� ���µǾ����������� -1
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
