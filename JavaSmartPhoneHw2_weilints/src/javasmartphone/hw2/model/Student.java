package javasmartphone.hw2.model;

/* Note: we does not check if 
 * SID nor scores values are valid because we 
 * leave the responsibility in other classes 
 * */
public class Student extends AbstractExamTaker {
	final int QUIZ_NUM = 5;
	
	private int SID;
	
	//add methods to print values of instance variables.
	
	public Student() {
		scores = new int[QUIZ_NUM];
	}
	
	public Student(int sID, int[] scores) {
		SID = sID;
		this.scores = new int[QUIZ_NUM];
		setScores(scores);
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public void setScores(int[] scores) {
		int copySize = scores.length;
		if (copySize > QUIZ_NUM) { copySize = QUIZ_NUM; }
		for (int i = 0; i < QUIZ_NUM; ++i) {
			this.scores[i] = scores[i];
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(SID);
		builder.append("\t");
		for (int i = 0; i < scores.length; ++i) {
			builder.append(scores[i]);
			builder.append("\t");
		}

		return builder.toString();
	}
	
	public void print() {
		System.out.println(this.toString());
	} 
	
}
