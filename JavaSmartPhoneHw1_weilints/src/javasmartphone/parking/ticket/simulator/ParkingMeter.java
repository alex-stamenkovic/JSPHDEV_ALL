package javasmartphone.parking.ticket.simulator;

public class ParkingMeter {
	private int numberOfMinutes;  // number of minutes has been purchased
	
	public ParkingMeter() {
		numberOfMinutes = 0;
	}

	public ParkingMeter(int numberOfMinutes) {
		this.numberOfMinutes = numberOfMinutes;
	}

	public int getNumberOfMinutes() {
		return numberOfMinutes;
	}

	public void setNumberOfMinutes(int numberOfMinutes) {
		this.numberOfMinutes = numberOfMinutes;
	}

	@Override
	public String toString() {
		return "ParkingMeter [numberOfMinutes=" + numberOfMinutes + "]";
	}
	
	public void print() {
		System.out.println(toString());
	}
}
