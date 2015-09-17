package javasmartphone.cointoss;

public class CoinToss {

	public static void main(String[] args) {
		System.out.println("Welcome toe CoinToss Game, this program will toss a coin 20 times and show the result");
		Simulation simulation = new Simulation();
		simulation.Run();
	}

	@Override
	public String toString() {
		return "CoinToss []";
	}
	public void print() {
		System.out.print(this.toString());
	} 
}
