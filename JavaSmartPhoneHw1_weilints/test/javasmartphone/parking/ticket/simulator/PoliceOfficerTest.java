package javasmartphone.parking.ticket.simulator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PoliceOfficerTest {
	ParkedCar car;
	ParkingMeter meter;
	PoliceOfficer officer;
	
	@Before
	public void setUp() throws Exception {
		car = new ParkedCar("Honda", "Accord", "Red", 12345, 0);
		meter = new ParkingMeter();
		officer = new PoliceOfficer("John McClane", 56789);
	}

	@Test
	public void testWhenTheParkedTimeIsLessThanPurchaseTimeThenNoTicketWillIssue() {
		car.setNumberOfMinutesParked(10);
		meter.setNumberOfMinutes(20);
		assertNull(officer.issueTicket(car, meter));
	}
	
	@Test
	public void testWhenTheParkedTimeIsEqualToPurchaseTimeThenNoTicketWillIssue() {
		car.setNumberOfMinutesParked(20);
		meter.setNumberOfMinutes(20);
		assertNull(officer.issueTicket(car, meter));
	}

	@Test
	public void testWhenTheParkedTimeIsLargerThanPurchaseTimeThenTicketWillIssue() {
		car.setNumberOfMinutesParked(21);
		meter.setNumberOfMinutes(20);
		assertNotNull(officer.issueTicket(car, meter));
	}
	
	@Test
	public void testFineIs25WhenNotExceedLessThan1hour() {
		car.setNumberOfMinutesParked(21);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertEquals(25, ticket.getFine());
	}
	
	@Test
	public void testFineIs25WhenExceed1hour() {
		car.setNumberOfMinutesParked(80);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertEquals(25, ticket.getFine());
	}
	
	@Test
	public void testFineIsMoreThan25WhenExceedMoreThan1hour() {
		car.setNumberOfMinutesParked(81);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertTrue(ticket.getFine()>25);
	}
	
	@Test
	public void testFineIs35WhenExceed80Min() {
		car.setNumberOfMinutesParked(100);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertEquals(35, ticket.getFine());
	}
	
	@Test
	public void testFineIs35WhenExceed120Min() {
		car.setNumberOfMinutesParked(140);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertEquals(35, ticket.getFine());
	}
	
	@Test
	public void testFineIs45WhenExceed121Min() {
		car.setNumberOfMinutesParked(141);
		meter.setNumberOfMinutes(20);
		ParkingTicket ticket = officer.issueTicket(car, meter);
		assertEquals(45, ticket.getFine());
	}
}

