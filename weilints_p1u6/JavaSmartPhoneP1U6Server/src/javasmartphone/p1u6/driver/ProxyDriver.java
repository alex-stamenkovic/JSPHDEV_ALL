/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * This function is used to test if proxy Automobile is 
 * implemented correctly. First it build Wagon ZTW
 * and Civic via BuildAuto, then it update value of 
 * Wagon ZTW. Each iteration it print out the result 
 * for examination. 
 */

package javasmartphone.p1u6.driver;

import javasmartphone.p1u6.adapter.BuildAuto;
import javasmartphone.p1u6.adapter.ICreateAuto;
import javasmartphone.p1u6.adapter.IUpdateAuto;


public class ProxyDriver {

	public static void main(String[] args) {
		/* Build an Auto */
		ICreateAuto c1 = new BuildAuto();        // c stands for creator 
		c1.buildAuto("./testData/FordZTW.txt", "definedType");
		// print out
		c1.printAuto("Wagon ZTW");
		
		// build another car 
		System.out.println("\n--> Build Civic\n");
		c1.buildAuto("./testData/HondaCivic.txt", "definedType");
		c1.printAuto("Civic");
		
		/* Update an Auto */
		System.out.println("\n--> [Wagon ZTW] Change the option set name fron Color to NewColor\n");
		IUpdateAuto u1 = new BuildAuto();        // u stands for updator
		u1.updateOptionSetName("Wagon ZTW", "Color", "NewColor");
		c1.printAuto("Wagon ZTW");
		
		System.out.println("\n--> [Wagon ZTW] Update price of Power Moonroof from 595.0 to 300.0\n");
		u1.updateOptionPrice("Wagon ZTW", "Power Moonroof", "selected", (float) 300.0);
		c1.printAuto("Wagon ZTW");
	}
}
