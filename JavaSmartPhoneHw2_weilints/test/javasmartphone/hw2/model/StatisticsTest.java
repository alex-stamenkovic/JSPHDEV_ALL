package javasmartphone.hw2.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

	Student[] s;
	int[] score;
	Statistics statistics;
	
	@Before
	public void setUp() throws Exception {
		statistics = new Statistics();
		score = new int[5];
		s = new Student[3]; 
		for (int i = 0; i < 3; i++) {
			s[i] = new Student();
		}
		
		// setup for first student 
		for (int i = 1; i <= 5; i++) { // 10 20 30 40 50
			score[i-1] = i * 10;
		}
		s[0].setScores(score);
		// setup for second student
		for (int i = 1; i <= 5; i++) { // 90 80 70 60 50
			score[i-1] = 100 - i * 10;
		}
		s[1].setScores(score);
		// setup for third student
		for (int i = 1; i <= 5; i++) { // 30 35 40 45 50
			score[i-1] = i * 5 + 25;
		}
		s[2].setScores(score);
	}

	@Test
	public void testLowIsCorrect() {
		statistics.findlow(s);
		assertEquals(10, statistics.getLowScores()[0]);
		assertEquals(20, statistics.getLowScores()[1]);
		assertEquals(30, statistics.getLowScores()[2]);
		assertEquals(40, statistics.getLowScores()[3]);
		assertEquals(50, statistics.getLowScores()[4]);
	}
	@Test
	public void testHighIsCorrect() {
		statistics.findhigh(s);
		assertEquals(90, statistics.getHighScores()[0]);
		assertEquals(80, statistics.getHighScores()[1]);
		assertEquals(70, statistics.getHighScores()[2]);
		assertEquals(60, statistics.getHighScores()[3]);
		assertEquals(50, statistics.getHighScores()[4]);
	}
	
	@Test
	public void testAvgIsCorrect() {
		statistics.findavg(s);
		assertEquals((float)130/3, statistics.getAvgScores()[0], 0.1);
		assertEquals((float)135/3, statistics.getAvgScores()[1], 0.1);
		assertEquals((float)140/3, statistics.getAvgScores()[2], 0.1);
		assertEquals((float)145/3, statistics.getAvgScores()[3], 0.1);
		assertEquals((float)150/3, statistics.getAvgScores()[4], 0.1);
	}
	
	
	
}
