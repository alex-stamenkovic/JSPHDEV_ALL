/*
    @Author: Wei-Lin Tsai weilints@andrew.cmu.edu
   
    @Content
    Java Smart Phone Hw 2
	Write a program to keep records and perform statistical analysis for a class of students.
	The class may have up to 40 students. There are five quizzes during the term. Each
	student is identified by a four­digit student ID number.
	The program is to print the student scores and calculate and print the statistics for
	each quiz. The output is in the same order as the input; no sorting is needed. The input
	is to be read from a text file.
*/

package javasmartphone.hw2;

import javasmartphone.hw2.exception.InValidStudentInputException;
import javasmartphone.hw2.model.Statistics;
import javasmartphone.hw2.model.Student;
import javasmartphone.hw2.util.FileParser;

public class Hw2Main {

	public static void main(String[] args) {
		Student lab2 [];
		//Populate the student array
		try {
			lab2 = FileParser.readFile("./testData/SuperSmallInput");
			//lab2 = FileParser.readFile("./testData/41StudentsInput");
		} catch (InValidStudentInputException e) {
			System.out.println("The input format is invalid");
			e.printStackTrace();
			return;
		}
		Statistics statlab2 = new Statistics();
		statlab2.findlow(lab2);
		statlab2.findhigh(lab2);
		statlab2.findavg(lab2);
		
		// print out input data
		System.out.println("Here is the data parsed:"); 
		System.out.println("Stud\tQ1\tQ2\tQ3\tQ4\tQ5"); 
		for (int i = 0; i < lab2.length; ++i) {
			lab2[i].print();
		}
		System.out.println();
		
		// print our statistical result
		statlab2.print();
	}

}
