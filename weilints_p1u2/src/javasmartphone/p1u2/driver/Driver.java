/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * A test function to read a file, parse it and print out/
 * Then Serialize and DeSerialize to see if the content still 
 * match. 
 * Read a text input file, populate an instance of Automobile class and print
 * the OptionSet's and their respective options.
 */

package javasmartphone.p1u2.driver;

import javasmartphone.p1u2.model.Automobile;
import javasmartphone.p1u2.util.FileParser;

public class Driver {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automobile FordZTW;
		
		FordZTW = fileParser.buildAutoObject("./testData/FordZTW.txt");
		//Print attributes before choosing options
		System.out.println("Before Selcetion: Price 30000\n");
		FordZTW.print();
			
		System.out.println("After Selcetion: Price 29585\n");
		FordZTW.setOptionChoice("Color", "Gold Clearcoat Metallic");
		FordZTW.setOptionChoice("Color", "Non this color"); // should not take efftect
		FordZTW.setOptionChoice("Brakes/Traction Control", "ABS"); // +400
		FordZTW.setOptionChoice("Transmission", "standard"); // -815
		FordZTW.print();
	}
}
