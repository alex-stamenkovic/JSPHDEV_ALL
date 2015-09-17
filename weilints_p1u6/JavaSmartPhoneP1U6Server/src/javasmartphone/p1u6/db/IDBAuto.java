/* 
	Author: Wei-Lin Tsai weilints@andrew.cmu.edu
*/
package javasmartphone.p1u6.db;

import java.util.List;

import javasmartphone.p1u6.model.Automobile;

public interface IDBAuto {
	// add an Automobile instance to database 
	public void dbBuildAuto(Automobile auto);
	
	// list all automobiles model name from database
	public List<String> dbListAutoName();
	
 	// This function searches and prints the properties of a given automobile
	// from data base.
	public void dbPrintAuto(String Modelname);
	
	// delete an Automobile from dataBase
	public void dbDeleteAuto(String modelName);
	
	// This function searches the Model from database for a given OptionSet and sets the
	// name of OptionSet to newName.
	public void dbUpdateOptionSetName(String Modelname, String OptionSetname,
			 String newName);
	
	//This function searches the Model from database for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void dbUpdateOptionPrice(String Modelname, String Optionname,
			String Option, float newprice);
	
}
