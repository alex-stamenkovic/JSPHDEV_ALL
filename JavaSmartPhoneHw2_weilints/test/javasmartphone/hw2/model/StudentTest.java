package javasmartphone.hw2.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	Student student;
	int[] fiveElementsIntArr;
	int[] sixElementsIntArr;
	
	@Before
	public void setUp() throws Exception {
		student = new Student();
		fiveElementsIntArr = new int[5];
		sixElementsIntArr = new int[6];
		for (int i = 0; i < 5; i++) {
			fiveElementsIntArr[i] = i;
		}
		for (int i = 0; i < 6; i++) {
			sixElementsIntArr[i] = i;
		}
	}

	@Test
	public void testIfQuizNumberIsSetToFiveWhenCreated() {
		assertEquals(5, student.getScores().length);
	}

	@Test 
	public void testCanSetScores() {
		student.setScores(fiveElementsIntArr);
		assertArrayEquals(fiveElementsIntArr, student.getScores());
	}
	
	@Test 
	public void testCanSetMoreThanSixScores() {
		student.setScores(sixElementsIntArr);
		assertArrayEquals(fiveElementsIntArr, student.getScores());
	}
}
