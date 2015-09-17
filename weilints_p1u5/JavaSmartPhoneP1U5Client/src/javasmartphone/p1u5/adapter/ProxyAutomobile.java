package javasmartphone.p1u5.adapter;

import java.util.LinkedHashMap;

import javasmartphone.p1u5.model.Automobile;
import javasmartphone.p1u5.util.FileParser;

/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */

public abstract class ProxyAutomobile {
	static private LinkedHashMap<String, Automobile> mAutoMap = new LinkedHashMap<String, Automobile>();  
	
	// This function searches the Model for a given OptionSet and sets the
	// name of OptionSet to newName.
	public void updateOptionSetName(String modelName, String optionSetname,
			 String newName) 
	{
		Automobile tmpAuto = mAutoMap.get(modelName);
		if (tmpAuto != null) {
			tmpAuto.updateOptionSet(optionSetname, newName);
		}
	}
	
	//This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void updateOptionPrice(String modelName, String optionName,
			String option, float newPrice)
	{
		Automobile tmpAuto = mAutoMap.get(modelName);
		if (tmpAuto != null) {
			tmpAuto.updateOption(optionName, option, newPrice); 
		}
	}
	
	// This function searches the Model for a given OptionSet and sets the
	// name of OptionSet to newName.
	public void syncUpdateOptionSetName(String modelName, String optionSetname,
			 String newName) 
	{
		Automobile tmpAuto = mAutoMap.get(modelName);
		synchronized(tmpAuto) { 
			if (tmpAuto != null) {
				tmpAuto.updateOptionSet(optionSetname, newName);
			}
		}
	}
	
	//This function searches the Model for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void syncUpdateOptionPrice(String modelName, String optionName,
			String option, float newPrice)
	{
		Automobile tmpAuto = mAutoMap.get(modelName);
		synchronized(tmpAuto) {
			if (tmpAuto != null) {
				tmpAuto.updateOption(optionName, option, newPrice); 
			}
		}
	}
	
	// Given a text file name a method called BuildAuto can be written to
	// build an instance of Automobile. This method does not have to return
	// the Auto instance.
	// The fileType can be "definedType"/"propertyFile" 
	public void buildAuto(String fileName, String fileType) {
		FileParser fileParser = new FileParser();
		Automobile newAuto;
		if (fileType == null || fileName == null) { return; }
		if (fileType.equals("definedType")) {
			newAuto = fileParser.buildAutoObject(fileName);	
		} else if (fileType.equals("propertyFile")) {
			newAuto = fileParser.buildAutoObjectFromProperty(fileName);	
		} else {
			return;
		}
		String modelName = newAuto.getModel();
		mAutoMap.put(modelName, newAuto);
	}
	
 	// This function searches and prints the properties of a given Automodel.
	public void printAuto(String modelName) {
		Automobile tmpAuto = mAutoMap.get(modelName);
		if (tmpAuto != null) {
			tmpAuto.print();
		} else {
			System.out.println("printAuto(): Error: Model Name <" +
					modelName + ">" + "is not existed");
		}
	}
	
	public void fix(int errNo) {
		// actually, do nothing here
	}
	
}
