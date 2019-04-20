package Default;

public class ClickTime { //카드를 뒤집은 횟수
	static int clicktime = 0;

	public static int getClicktime() {
		return clicktime;
	}

	public static void setClicktime(int clicktime) {
		ClickTime.clicktime = clicktime;
	}

	public static void addClicktime() {
		clicktime +=1;
	}
	

}
