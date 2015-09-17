package javasmartphone.hw2.util;

import javasmartphone.hw2.util.FileParser;
import static org.junit.Assert.*;
import javasmartphone.hw2.exception.InValidStudentInputException;
import javasmartphone.hw2.model.Student;

import org.junit.Test;

public class FileParserTest {
	Student[] s; 

	@Test
	public void testCanGetThreeStudents() throws InValidStudentInputException {
		s = FileParser.readFile("./testData/SuperSmallInput");
		assertEquals(3, s.length);
	}
	
	@Test
	public void testCanGet15Students() throws InValidStudentInputException {
		s = FileParser.readFile("./testData/SampleInputFromWriteUp");
		assertEquals(15, s.length);
	}
	
	@Test
	public void testCanGet40Students() throws InValidStudentInputException {
		s = FileParser.readFile("./testData/40StudentsInput");
		assertEquals(40, s.length);
	}
	
	@Test
	public void testCanGet41Students() throws InValidStudentInputException {
		s = FileParser.readFile("./testData/41StudentsInput");
		assertEquals(40, s.length);
	}
	
	@Test
	public void testIfUserIDIsNegtiveWillGetAnException()  {
		try {
			s = FileParser.readFile("./testData/InvalidStudentID");
			assertFalse(true);
		} catch (InValidStudentInputException e) {
			assertTrue(true);
		}	
		
	}

	@Test
	public void testIfSocreIsInvalideWillGetAnException()  {
		try {
			s = FileParser.readFile("./testData/InvalidScore");
			assertFalse(true);
		} catch (InValidStudentInputException e) {
			assertTrue(true);
		}	
	}
}
