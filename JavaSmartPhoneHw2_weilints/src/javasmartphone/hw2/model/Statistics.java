package javasmartphone.hw2.model;

/* Note, we assume quiz number is 5 and Student number is 40. 
 * We do not handle error case because we leave the responsibility 
 * to other classes. 
 * */
public class Statistics implements IStatistics {
	final int QUIZ_NUM = 5;
	final int STUDENT_NUM = 40;
	private int [] lowScores; 
	private int [] highScores;
	private float [] avgScores;
	
	public Statistics() {
		lowScores = new int [QUIZ_NUM];
		highScores = new int [QUIZ_NUM];
		avgScores = new float [QUIZ_NUM];
	}

	// Note, we do not provide setter functions here because 
	// it does not make sense.
	public int[] getLowScores() {
		return lowScores;
	}

	public int[] getHighScores() {
		return highScores;
	}

	public float[] getAvgScores() {
		return avgScores;
	}

	//This method will find lowest score and store it in an array names low scores
	public void findlow(AbstractExamTaker [] a){
		for (int i = 0; i < lowScores.length; i++) {
			lowScores[i] = 100;
		}
		
		// go 
		for (int i = 0; (i < a.length) && (i < STUDENT_NUM); ++i) {
			int scoreOfAStudent[] = a[i].getScores();
			for (int j = 0; j < QUIZ_NUM; ++j) {
				int aScore = scoreOfAStudent[j];
				if (aScore > 100 || aScore < 0) { continue; } // do basic error handling here
				if (aScore < lowScores[j]) {
					lowScores[j] = aScore;
				}
			}
		}
	}
	
	//This method will find highest score and store it in an array names high scores
	public void findhigh(AbstractExamTaker [] a){
		for (int i = 0; i < highScores.length; i++) {
			highScores[i] = 0;
		}
		
		// go 
		for (int i = 0; (i < a.length) && (i < STUDENT_NUM); ++i) {
			int scoreOfAStudent[] = a[i].getScores();
			for (int j = 0; j < QUIZ_NUM; ++j) {
				int aScore = scoreOfAStudent[j];
				if (aScore > 100 || aScore < 0) { continue; } // do basic error handling here
				if (aScore > highScores[j]) {
					highScores[j] = aScore;
				}
			}
		}
	}
	
	//This method will find avg score for each quiz and store it in an array names avg scores
	public void findavg(AbstractExamTaker [] a){
		if (a.length == 0) { return; } // corner case
		
		for (int i = 0; i < avgScores.length; i++) {
			avgScores[i] = 0;
		}
		
		// go 
		for (int i = 0; (i < a.length) && (i < STUDENT_NUM); ++i) {
			int scoreOfAStudent[] = a[i].getScores();
			for (int j = 0; j < QUIZ_NUM; ++j) {
				int aScore = scoreOfAStudent[j];
				if (aScore > 100 || aScore < 0) { continue; } // do basic error handling here
				avgScores[j] += aScore;
			}
		}
		
		int realStudentNum = (a.length > STUDENT_NUM) ? STUDENT_NUM : a.length;
		for (int i = 0; i < QUIZ_NUM; ++i) {
			avgScores[i] = avgScores[i] / realStudentNum;
		}
	}
	
	//add methods to print values of instance variables.
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("High Score\t\t");
		for (int i = 0; i < QUIZ_NUM; ++i) {
			builder.append(highScores[i]);
			builder.append("\t");
		}
 		builder.append("\nLow Score\t\t");
		for (int i = 0; i < QUIZ_NUM; ++i) {
			builder.append(lowScores[i]);
			builder.append("\t");
		}
		builder.append("\nAverage  \t\t");
		for (int i = 0; i < QUIZ_NUM; ++i) {
			builder.append(String.format("%.2f", avgScores[i]));
			builder.append("\t");
		}
		return builder.toString();
	} 
	
	public void print() {
		System.out.println(this.toString());
	}
}
