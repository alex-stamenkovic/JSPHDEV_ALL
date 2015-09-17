package javasmartphone.hw2.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javasmartphone.hw2.exception.InValidStudentInputException;
import javasmartphone.hw2.exception.MoreThan40StudentException;
import javasmartphone.hw2.model.Student;

public class FileParser {
	public static Student [] readFile(String filename) throws InValidStudentInputException {
		final int STUDENT_NUM = 40;
		final int QUIZ_NUM = 5;
		Student[] students;  // = new Student[STUDENT_NUM];
		int lineNumber = -1; // because we need skip first line
		boolean exceed40 = false;
		
		// get line number first
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					lineNumber++;
				}
				if (lineNumber > STUDENT_NUM ) {
					lineNumber = STUDENT_NUM;
					exceed40 = true;
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("File IO Error ­­ " + e.toString());
		} 
		
		
		// new create with real size 
		students = new Student[lineNumber];
		int i = 0;
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			buff.readLine(); // skip first line
			int[] inScores = new int[QUIZ_NUM];
			int inSID;
			while (i < lineNumber) {
				StringTokenizer token = new StringTokenizer(buff.readLine());
				inSID = Integer.parseInt(token.nextToken());
				if (inSID < 0 || inSID > 9999) { 
					buff.close();
					throw new InValidStudentInputException();
				}
				for (int j = 0; j < QUIZ_NUM; ++j) {
					inScores[j] = Integer.parseInt(token.nextToken());
					if (inScores[j] < 0 || inScores[j] > 100) {
						buff.close();
						throw new InValidStudentInputException();
					}
				}
				students[i] = new Student(inSID, inScores);
				++i;
			}
			buff.close();
			// TODO throw exception here
			if (exceed40) {
				throw new MoreThan40StudentException();
			}
		} catch (IOException e) {
			System.out.println("File IO Error ­­ " + e.toString());
		} catch (MoreThan40StudentException e) {
			System.out.println("Get Exception: More than 40 students" + e.toString());
		} 

		return students;
	}
	
	
}
