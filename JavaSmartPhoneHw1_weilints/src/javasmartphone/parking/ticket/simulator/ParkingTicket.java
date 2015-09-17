package javasmartphone.parking.ticket.simulator;

public class ParkingTicket {
	// From police officer 
	private String name; // police name 
	private int badgeNum; 
	
	// From car 
	private String make;
	private String model; 
	private String color;
	private long licenseNumber;
	
	// Its own field 
	private int fine;
   
	public ParkingTicket(ParkedCar car, ParkingMeter meter, PoliceOfficer officer) {
		name = officer.getName();
		badgeNum = officer.getBadgeNum();
		
		make = car.getMake();
		model = car.getModel();
		color = car.getColor();
		licenseNumber = car.getLicenseNumber();
		
		int minParked = car.getNumberOfMinutesParked();
		int minPurchased = meter.getNumberOfMinutes();
		if ((minParked - minPurchased) <= 0) {
			fine = 0; // should not happened by spec, just do an error handling 
		} else if ((minParked - minPurchased) <= 60) {
			fine = 25;
		} else {
			fine = 25 + 10 * (int)((double)(minParked - minPurchased - 1)/60);
		}
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

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public long getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(long licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public int getFine() {
		return fine;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		return "ParkingTicket [Officer name=" + name + ", Officer badgeNum=" + badgeNum
				+ ", make=" + make + ", model=" + model + ", color=" + color
				+ ", licenseNumber=" + licenseNumber + ", fine=" + fine + "]";
	}
	
	public void print() {
		System.out.println(toString());
	} 
	
	// report all 
	public String Report() {
		return toString();
	}
	
	public String ReportCar() {
		return "ParkingTicket [make=" + make + ", model=" + model + ", color=" + color
				+ ", licenseNumber=" + licenseNumber + "]";
	}
	
	public String ReportFine() {
		return "ParkingTicket [fine=" + fine + "]";
	}
	
	public String ReportOfficer() {
		return "ParkingTicket [Officer name=" + name + ", Officer badgeNum=" + badgeNum + "]";
	}
}
