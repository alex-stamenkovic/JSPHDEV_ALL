/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */

package javasmartphone.p1u5.adapter;

import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.Set;

import javasmartphone.p1u5.model.Automobile;
import javasmartphone.p1u5.util.FileParser;


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
	
	// build auto and add to the hashMap according to the properties
	public void buildAutoFromProperty (Properties props) {
		Automobile auto = new Automobile();
		
		if ( props.getProperty("CarModel") != null) {
			auto.setModel(props.getProperty("CarModel"));
			auto.setMake(props.getProperty("CarMake"));
			auto.setBaseprice(Float.parseFloat(props.getProperty("BasePrice")));

			String OptionName = null;
			float price = (float)0.0;
			
			// get number of option sets
			int numberOfOptionSets = (Integer.parseInt(props.getProperty("OptionSetSize")));
			// for each optionSet
			for (int i = 1; i <= numberOfOptionSets; ++i) {
				// get options size first 
				int optionSize = Integer.parseInt(props.getProperty("OptionSet" + i + "_Size"));
				auto.addOptionSet(props.getProperty("OptionSet" + i), optionSize);  
				// for each option 
				for (int j = 1; j <= optionSize; ++j) {
					// option 1
					OptionName = props.getProperty("OptionSet" + i + "_Option" + j);
				    price = Float.parseFloat(props.getProperty("OptionSet" + i + "_Option" + j + "_Price"));
					auto.setOption(props.getProperty("OptionSet" + i), j-1, OptionName, price);						
				}
			}			
			mAutoMap.put(auto.getModel(), auto);
		}
	}

	// return the set of all available model name 
	public Set<String> listAuto() {
		return mAutoMap.keySet();
	}
		
	// return an auto mobile by given name 
	public Automobile getAuto(String modelName) {
		return mAutoMap.get(modelName);
	}
}
