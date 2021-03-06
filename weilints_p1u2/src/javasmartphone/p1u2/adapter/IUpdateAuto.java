/* 
	Author: Wei-Lin Tsai weilints@andrew.cmu.edu
*/
package javasmartphone.p1u2.adapter;

public interface IUpdateAuto {
	
	// This function searches the Model for a given OptionSet and sets the
	// name of OptionSet to newName.
	public void updateOptionSetName(String Modelname, String OptionSetname,
			 String newName);
	
	//This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void updateOptionPrice(String Modelname, String Optionname,
			String Option, float newprice);
}
