/* 
	Author: Wei-Lin Tsai weilints@andrew.cmu.edu
*/
package javasmartphone.p1u5.adapter;

public interface IEditThread {
	
	// This function searches the Model for a given OptionSet and sets the
	// name of OptionSet to newName.
	// sync version
	public void syncUpdateOptionSetName(String Modelname, String OptionSetname,
			 String newName);
	
	//This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	// sync version
	public void syncUpdateOptionPrice(String Modelname, String OptionSetname,
			String Option, float newprice);
}
