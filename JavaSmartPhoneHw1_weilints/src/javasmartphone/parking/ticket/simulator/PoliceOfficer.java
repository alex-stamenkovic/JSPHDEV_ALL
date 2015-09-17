package javasmartphone.parking.ticket.simulator;

public class PoliceOfficer {
	private String name; // police name 
	private int badgeNum; 
	
	public PoliceOfficer() {
		// use default name 
		name = "defaultPolice";
		badgeNum = 0;
	}
	
	public PoliceOfficer(String name, int badgeNum) {
		super();
		this.name = name;
		this.badgeNum = badgeNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBadgeNum() {
		return badgeNum;
	}

	public void setBadgeNum(int badgeNum) {
		this.badgeNum = badgeNum;
	}
	
	public ParkingTicket issueTicket(ParkedCar car, ParkingMeter meter) {
		if (car.getNumberOfMinutesParked() <= meter.getNumberOfMinutes()) {
			return null;
		} else {
			return new ParkingTicket(car, meter, this);
		}
	}

	@Override
	public String toString() {
		return "PoliceOfficer [name=" + name + ", badgeNum=" + badgeNum + "]";
	}
	
	public void print() {
		System.out.println(toString());
	}
}
