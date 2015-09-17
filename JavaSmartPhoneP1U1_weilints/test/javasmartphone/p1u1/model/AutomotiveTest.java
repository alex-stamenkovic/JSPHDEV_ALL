package javasmartphone.p1u1.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutomotiveTest {
	Automotive auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Automotive();
	}

	@Test
	public void canSetModelName() {
		auto.setName("Accord");
		assertEquals("Accord", auto.getName());
	}

	@Test
	public void canSetBasePrice() {
		auto.setBaseprice((float)99.88);
		assertEquals((float)99.88, auto.getBaseprice(), 0.05);
	}
	
	@Test
	public void initOptionSetIsEmpty() {
		assertNull(auto.getOptionSet(0));
	}
	
	@Test 
	public void canAddOptionSet(){
		auto.addOptionSet("Color", 100);
		OptionSet curSet = auto.getOptionSet(0);
		assertNotNull(curSet);
		assertEquals(100, curSet.getOpt().length);
	}
	
	@Test 
	public void canSetOneOption(){
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		assertEquals("Sky Blue", auto.getOptionSet(0).getOpt()[44].getName());
		assertEquals((float)99.11, auto.getOptionSet(0).getOpt()[44].getPrice(), 0.01);
	}
}
