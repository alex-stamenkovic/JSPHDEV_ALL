/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * Test if the base price is negative 
 * => Will throw an exception and convert the 
 * negative number into positive number 
 * 
 */

package javasmartphone.p1u2.driver;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.util.FileParser;

public class DriverNegativeBasePrice {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automobile FordZTW;
		
		FordZTW = fileParser.buildAutoObject("./testData/FordZTW_NegativePrice.txt");
		
		//Print
		System.out.println("Print Parsed model\n");
		FordZTW.print();
	}
}
