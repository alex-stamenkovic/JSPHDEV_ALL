package javasmartphone.parking.ticket.simulator;

public class ParkedCar {
	private String make;
	private String model; 
	private String color;
	private long licenseNumber;
	private int numberOfMinutesParked;
	
	public ParkedCar() {
		numberOfMinutesParked = 0;
		// use default value;
		make = "Honda";
		model = "Civic";
		color = "Siliver";
		licenseNumber = 99999999;
	}

	public ParkedCar(String make, String model, String color,
			long licenseNumber, int numberOfMinutesParked) {
		super();
		this.make = make;
		this.model = model;
		this.color = color;
		this.licenseNumber = licenseNumber;
		this.numberOfMinutesParked = numberOfMinutesParked;
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

	public int getNumberOfMinutesParked() {
		return numberOfMinutesParked;
	}

	public void setNumberOfMinutesParked(int numberOfMinutesParked) {
		this.numberOfMinutesParked = numberOfMinutesParked;
	}

	@Override
	public String toString() {
		return "ParkedCar [make=" + make + ", model=" + model + ", color="
				+ color + ", licenseNumber=" + licenseNumber
				+ ", numberOfMinutesParked=" + numberOfMinutesParked + "]";
	}
    
	public void print() {
		System.out.println(toString());
	}
}
