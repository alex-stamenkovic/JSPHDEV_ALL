/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Test if the missing price is missing 
 * => Will throw exception and the default price is 0.0 
 */

package javasmartphone.p1u2.driver;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.util.FileParser;

public class DriverMissingBasePrice {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automobile FordZTW;
	
		FordZTW = fileParser.buildAutoObject("./testData/FordZTW_MissingPrice.txt");
	
		//Print
		System.out.println("Print Parsed model\n");
		FordZTW.print();
	}
}
