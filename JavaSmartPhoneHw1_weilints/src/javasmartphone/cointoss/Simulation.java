package javasmartphone.cointoss;

public class Simulation {
	static final int SIMCOUNT = 20;

	public Simulation() {
		// do nothing 
	}
	
	public void Run() {
		Coin coin = new Coin();
		System.out.printf("Init a coin with initail side up, %s\n", coin.getSideUp());
		for (int i = 0; i < SIMCOUNT; ++i) {
			coin.toss();
			System.out.printf("The %d times toss, the result is %s\n", i,  coin.getSideUp());
		}
		System.out.printf("Simulation ends, the number of heads is %d, the number of tails is %d\n", coin.getHeadCount(), coin.getTailCount());
	}
	
	@Override
	public String toString() {
		return "Simulation []";
	}
	
	public void print() {
		System.out.print(this.toString());
	} 
}
