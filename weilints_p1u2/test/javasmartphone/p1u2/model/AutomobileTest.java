package javasmartphone.p1u2.model;

import static org.junit.Assert.*;

import java.security.PublicKey;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.model.OptionSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutomobileTest {
	Automobile auto;
	
	@Before
	public void setUp() throws Exception {
		auto = new Automobile();
	}

	@Test
	public void canSetMake() {
		auto.setMake("Honda");
		assertEquals("Honda", auto.getMake());
	}
	
	@Test
	public void canSetModelName() {
		auto.setModel("Accord");
		assertEquals("Accord", auto.getModel());
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
		assertEquals(100, curSet.getOpt().size());
	}
	
	@Test 
	public void canSetOneOption(){
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		assertEquals("Sky Blue", auto.getOptionSet(0).getOpt().get(44).getName());
		assertEquals((float)99.11, auto.getOptionSet(0).getOpt().get(44).getPrice(), 0.01);
	}
	
	@Test 
	public void initialOptionChoiceIsNull() {
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		assertNull(auto.getOptionChoice("Color"));
	}
	
	@Test 
	public void canSetOptionChoicel() {
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		auto.setOption("Color", 15, "Red", (float) 10.11);
		auto.setOptionChoice("Color", "Sky Blue");
		assertEquals("Sky Blue", auto.getOptionChoice("Color"));
		assertEquals(99, auto.getOptionChoicePrice("Color"));
	}
	
	@Test 
	public void canSetOptionChoiceWithNotExistedOption() {
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		auto.setOption("Color", 15, "Red", (float) 10.11);
		auto.setOptionChoice("Color", "No this color");
		assertNull(auto.getOptionChoice("Color"));
	}
	
	@Test
	public void totalPriceIsTheSameAsBasePriceWithoutSelction() {
		auto.setBaseprice((float)1000.28);
		assertEquals(1000, auto.getTotalPrice());
	}
	
	@Test
	public void totalPriceWillUpdateWhenChoiceIsSelected() {
		auto.setBaseprice((float)1000.28);
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) 99.11);
		auto.setOption("Color", 15, "Red", (float) 10.11);
		auto.setOptionChoice("Color", "Sky Blue");
		assertEquals(1099, auto.getTotalPrice());
	}
	
	@Test
	public void totalPriceWillWorkWhenTheChoiceHasNegtiveValue() {
		auto.setBaseprice((float)1000.28);
		auto.addOptionSet("Color", 100);
		auto.setOption("Color", 44, "Sky Blue", (float) -99.11);
		auto.setOption("Color", 15, "Red", (float) 10.11);
		auto.setOptionChoice("Color", "Sky Blue");
		assertEquals(901, auto.getTotalPrice());
	}
	
}
