/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * Test if missing option input will trigger exception
 * =>
 *   Will skip the faulty part, throw an exception and 
 *   keep going
 */

package javasmartphone.p1u2.driver;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.util.FileParser;

public class DriverMissingOption {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automobile FordZTW;
		
		FordZTW = fileParser.buildAutoObject("./testData/FordZTW_MissingOption.txt");
		
		//Print
		System.out.println("Print Parsed model\n");
		FordZTW.print();
	}
}
