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

import java.util.List;

import javasmartphone.p1u6.adapter.IUpdateAuto;
import javasmartphone.p1u6.adapter.BuildAuto;
import javasmartphone.p1u6.adapter.ICreateAuto;
import javasmartphone.p1u6.db.DBUtil;
import javasmartphone.p1u6.db.IDBAuto;


public class DBDriver {

	public static void main(String[] args) {
		// Init DB
		DBUtil dbUtil = new DBUtil();
		
		dbUtil.initialization();
		
		// Build Auto, and it also call DBBuildAuto
		System.out.println("Build Wagon ZTW ...");
		ICreateAuto c1 = new BuildAuto();        // c stands for creator 
		c1.buildAuto("./testData/FordZTW.txt", "definedType");
		
		// Build Auto, and it also call DBBuildAuto
		System.out.println("Build Civic ...");
		c1.buildAuto("./testData/HondaCivic.txt", "definedType");
		
		// List all models from database 
		System.out.println("List all models from database ...");
		IDBAuto dbAuto = (IDBAuto)c1;
		List<String> modelNameList = dbAuto.dbListAutoName();
		for (String name : modelNameList) {
			System.out.println("  " + name);
		}
		
		// Delete Wagon ZTE
		System.out.println("Delete Wagon ZTW ...");
		dbAuto.dbDeleteAuto("Wagon ZTW");
		
		// List all models after deletion
		System.out.println("List all models after deletion ...");
		modelNameList = dbAuto.dbListAutoName();
		for (String name : modelNameList) {
			System.out.println("  " + name);
		}
		
		// Build Auto again 
		System.out.println("Build Wagon ZTW ... again !!");
		c1.buildAuto("./testData/FordZTW.txt", "definedType");
		
		// List all models after building again
		System.out.println("List all models after building Wagon ZTW again ...");
		modelNameList = dbAuto.dbListAutoName();
		for (String name : modelNameList) {
			System.out.println("  " + name);
		}
		
		System.out.println("\n\n------------ Now for update -------------\n\n");
		
		// Print automobile before update 
		System.out.println("Print Wagon ZTW before updating !!");
		dbAuto.dbPrintAuto("Wagon ZTW");
		
		/* Update an Auto */
		System.out.println("\n--> [Wagon ZTW] Change the option set name fron Color to NewColor\n");
		IUpdateAuto u1 = (IUpdateAuto)c1;       // u stands for updator
		// will also update database
		u1.updateOptionSetName("Wagon ZTW", "Color", "NewColor");
		dbAuto.dbPrintAuto("Wagon ZTW");
		
		System.out.println("\n--> [Wagon ZTW] Update price of Power Moonroof from 595.0 to 300.0\n");
		u1.updateOptionPrice("Wagon ZTW", "Power Moonroof", "selected", (float) 300.0);
		dbAuto.dbPrintAuto("Wagon ZTW");
	}
}
