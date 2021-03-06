/*
 * @Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * */
package javasmartphone.p1u6.util;

import static org.junit.Assert.assertEquals;
import javasmartphone.p1u6.model.Automobile;
import javasmartphone.p1u6.util.FileParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileParserTest {

	FileParser fileParser;
	
	@Before
	public void setUp() throws Exception {
		fileParser = new FileParser();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void canOpenPropertyFile() {
		fileParser.buildAutoObjectFromProperty("./testData/Prius.properties");
	}

	@Test
	public void canParsePropertyFileCorrectly() {
		Automobile auto = fileParser.buildAutoObjectFromProperty("./testData/Prius.properties");
		assertEquals((float)22000, auto.getBaseprice(), 0.05);
		assertEquals("Toyota", auto.getMake());
		assertEquals("Prius", auto.getModel());
		
		auto.print();
	}
}
