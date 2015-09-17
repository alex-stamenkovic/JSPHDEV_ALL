package javasmartphone.hw2.model;

public abstract class AbstractExamTaker {
	protected int[] scores;
	
	public int[] getScores() {
		return scores;
	}
	public abstract void setScores(int[] scores);
}
