/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * A test function to read a file, parse it and print out/
 * Then Serialize and DeSerialize to see if the content still 
 * match. 
 */

package javasmartphone.p1u1.driver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javasmartphone.p1u1.model.Automotive;
import javasmartphone.p1u1.util.FileParser;

public class Driver {
	public static void main(String [] args)
	{
		FileParser fileParser = new FileParser();
		//Build Automobile Object from a file.
		Automotive FordZTW = fileParser.buildAutoObject("./testData/FordZTW.txt");
		//Print attributes before serialization
		FordZTW.print();
		try {
			//Serialize the object
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./testData/FordZTW.ser_out"));
			out.writeObject(FordZTW);
			out.close();
			//Deserialize the object and read it into memory.
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("./testData/FordZTW.ser_out"));
			Automotive newFordZTW = (Automotive) in.readObject();
			//Print new attributes.
			newFordZTW.print();
		}catch (Exception e) {
			System.out.println("Get Exception: During Serialization   " + e);
		}
	}
}
