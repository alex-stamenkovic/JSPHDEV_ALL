/* 
	Author: Wei-Lin Tsai weilints@andrew.cmu.edu
    
    From the data provided in project description you can try to find patterns in structuring information.
    notice that:
	a. Each model (Ford Wagon ZTW) definition has a set of properties (Color, Transmission etc.)
	b. Each property has a set of values (For color there are different values like Fort Knox Gold Clearcoat
	   Metallic etc.)
	c. Each property has a price.
	d. Model also has a price.
	e. Values for each property tend to be different in quantity.
	--
	1. Using the information in the last step (Analysis) you can start designing classes and how each might relate.
	2. We will certainly not create classes for each property as that will not be reusable design.
	3. We can create a Model class that can hold name of the model, base price and information about all the
	   properties (Let's call it OptionSet)
	4. Each OptionSet has a set of values. Each value can be defined in its own class (Let's call it Option). Each
	   Option can have name and price as properties.
	5. Each OptionSet will also have a name.
	--
	1. For each class (Automotive, OptionSet and Option) make sure you have applied the following criteria to
	   ensure the class definition is complete and properly coded.
	   1. Add constructors for every possible meaningful combination.
	   2. Write get and set methods for each property.
	   3. For each property in a class make sure you have added operations for Creating (generally done with
	      constructors), Reading (Generally done with find functions and/or getters), Updating (Update functions
          that will call find functions, Deleting values(Delete value of a property).
 */
package javasmartphone.p1u1.model;

import java.io.Serializable;

public class Automotive implements Serializable {
	//private static final long serialVersionUID = -8064443654888859694L;
	private String name; 	    // eg. Ford Wagon ZTW
	private float baseprice;
	private OptionSet optionSet[];
	
	public Automotive() {
		// do nothing here
	}
	
	public Automotive(String name, float baseprice, OptionSet[] optionSet) {
		this.name = name;
		this.baseprice = baseprice;
		this.optionSet = optionSet.clone();
	}
	
	public Automotive(String name, float baseprice, int size) {
		this.name = name;
		this.baseprice = baseprice;
		// create
		this.optionSet = new OptionSet[size];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getBaseprice() {
		return baseprice;
	}

	public void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	public OptionSet getOptionSet(int i) {
		if (optionSet == null || i >= optionSet.length) { return null; }
		return optionSet[i];
	}
	
	public void setOption(String optionSetName, int index, String OptionName, float price) {
		OptionSet tmp = findOptionSet(optionSetName);
		if (tmp != null) {
			tmp.updateOption(index, OptionName, price);
		}
	}
	
	public void setOptionSet(OptionSet[] optionSet) {
		this.optionSet = optionSet.clone();
	}
	
	public OptionSet findOptionSet(String nameOfOptionSet) {
		if (nameOfOptionSet == null) { return null; }
		for (OptionSet tmp : optionSet) {
			if (nameOfOptionSet.equals(tmp.getName())) {
				return tmp;
			}
		}
		return null; // not found 
	}

	public void deleteOptionSet(String nameOfOptionSet) {
		if (findOptionSet(nameOfOptionSet) == null) { return; }
		OptionSet[] newSet = new OptionSet[optionSet.length-1];
		int i = 0;
		for (OptionSet tmp : optionSet) {
			if (nameOfOptionSet.equals(tmp.getName())) {
				continue; // do not copy
			} else {
				newSet[i++] = tmp;  // reference copy so it's cheap
			}
		}
		optionSet = newSet;
	}
	
	public void addOptionSet(OptionSet newSet) {
		if (newSet == null) { return; }
		// we assume there's no duplication
		if (optionSet == null) {
			optionSet = new OptionSet[1];
			optionSet[0] = new OptionSet(newSet);
		} else {
			OptionSet[] tmp = new OptionSet[optionSet.length+1];
			for (int i = 0; i < optionSet.length; i++) {
				tmp[i] = optionSet[i];
			}
			tmp[optionSet.length] = new OptionSet(newSet);
			optionSet = tmp;
		}
	}
	
	public void addOptionSet(String name, int size) {
		if (optionSet == null) {
			optionSet = new OptionSet[1];
			optionSet[0] = new OptionSet(name, size);
		} else {
			OptionSet[] tmp = new OptionSet[optionSet.length+1];
			for (int i = 0; i < optionSet.length; i++) {
				tmp[i] = optionSet[i];
			}
			tmp[optionSet.length] = new OptionSet(name, size);
			optionSet = tmp;
		}
	}
	
	public void updateOptionSet(OptionSet newSet) {
		if (newSet == null) { return; }
		OptionSet optionSet = findOptionSet(newSet.getName());
		if (optionSet != null) {
			optionSet = new OptionSet(newSet);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(",");
		builder.append(baseprice);
		builder.append("\n");
		
		for (OptionSet i : optionSet) {
			builder.append(i.toString());
		}
		
		return builder.toString();
	}
	
	public void print(){
		System.out.print(this);
	}
}
