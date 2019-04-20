package Default;

public class ChanceStack {
	ChanceOnOff chanceOnOff = new ChanceOnOff();
	
	static int chanceStack = 0;
	
	
	public void increaseStack() {
		this.chanceStack += 1;
		if(chanceStack == 3) {
			this.chanceStack = 0;
			chanceOnOff.chanceOn();	
		}
	}
	public void decreaseStack() {
		if(this.chanceStack == 0) {
		}
		else {
			this.chanceStack -= 1;
		}
	}

}
