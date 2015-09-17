/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * Test if the file name is missing 
 * =>
 * Will reset file name and open default file: FordZTW.txt
 */

package javasmartphone.p1u2.driver;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.util.FileParser;

public class DriverMissingFileName {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automobile FordZTW;
		
		FordZTW = fileParser.buildAutoObject("./testData/Ford.txt");
		//Print attributes
		System.out.println("Print parsed model\n");
		FordZTW.print();
	}
}
