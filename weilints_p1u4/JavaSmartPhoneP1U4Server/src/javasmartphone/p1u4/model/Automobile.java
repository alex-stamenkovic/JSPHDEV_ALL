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
    -- 
    Project 1 Unit 3 
    	For thread saving purpose, we will add synchronized qualifier to 
    	all methods to ensure thread safeness
 */
package javasmartphone.p1u4.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable {
	//private static final long serialVersionUID = -8064443654888859694L;
	//private String name; 	    // eg. Ford Wagon ZTW  // TODO, will be breaked into Make and Model
	private String make;
	private String model;
	private float baseprice;
	private ArrayList<OptionSet> optionSet;
	
	public Automobile() {
		optionSet = new ArrayList<OptionSet> ();
	}
	
	public Automobile(String make, String model, float baseprice, ArrayList<OptionSet> optionSet) {
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		// do the clone 
		this.optionSet = new ArrayList<OptionSet>(optionSet.size());
		for (OptionSet opt : optionSet) {
			optionSet.add(new OptionSet(opt));
		}
	}
	
	public Automobile(String make, String model, float baseprice, int size) {
		this.make = make;
		this.model = model;
		this.baseprice = baseprice;
		// create
		this.optionSet = new ArrayList<OptionSet>(size);
	}

	public synchronized String getMake() {
		return make;
	}

	public synchronized void setMake(String make) {
		this.make = make;
	}

	public synchronized String getModel() {
		return model;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}

	public synchronized float getBaseprice() {
		return baseprice;
	}

	public synchronized void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}
	
	public synchronized OptionSet getOptionSet(int i) {
		if (optionSet == null || i >= optionSet.size()) { return null; }
		return optionSet.get(i);
	}
	
	public synchronized void setOption(String optionSetName, int index, String OptionName, float price) {
		OptionSet tmp = findOptionSet(optionSetName);
		if (tmp != null) {
			tmp.updateOption(index, OptionName, price);
		}
	}
	
	public synchronized void setOptionSet(ArrayList<OptionSet> optionSet) {
		// do the copy
		this.optionSet = new ArrayList<OptionSet>(optionSet.size());
		for (OptionSet opt : optionSet) {
			optionSet.add(new OptionSet(opt));
		}
	}
	
	public synchronized OptionSet findOptionSet(String nameOfOptionSet) {
		if (nameOfOptionSet == null) { return null; }
		for (OptionSet tmp : optionSet) {
			if (nameOfOptionSet.equals(tmp.getName())) {
				return tmp;
			}
		}
		return null; // not found 
	}

	public synchronized void deleteOptionSet(String nameOfOptionSet) {
		if (findOptionSet(nameOfOptionSet) == null) { return; }
		ArrayList<OptionSet> newSet = new ArrayList<OptionSet>(optionSet.size()-1);
		for (OptionSet tmp : optionSet) {
			if (nameOfOptionSet.equals(tmp.getName())) {
				continue; // do not copy
			} else {
				newSet.add(tmp);  // reference copy so it's cheap
			}
		}
		optionSet = newSet;
	}
	
	public synchronized void addOptionSet(OptionSet newSet) {
		if (newSet == null) { return; }
		// we assume there's no duplication
		if (optionSet == null) {
			optionSet = new ArrayList<OptionSet>();
		}
		optionSet.add(newSet);
	}
	
	public synchronized void addOptionSet(String name, int size) {
		if (optionSet == null) {
			optionSet = new ArrayList<OptionSet>();
		} 
		optionSet.add(new OptionSet(name, size));
	}
	
	public synchronized void updateOptionSet(String oldSetName, String newSetName) {
		OptionSet optionSet = findOptionSet(oldSetName);
		if (optionSet != null) {
			optionSet.setName(newSetName);
		}
	}
	
	// update an option's price 
	public synchronized void updateOption(String optionSetName, String OptionName, float price) {
		OptionSet tmp = findOptionSet(optionSetName);
		if (tmp != null) {
			for (int i = 0; i < tmp.getOpt().size(); ++i) {
				if (tmp.getOpt().get(i).getName().equals(OptionName)) {
					tmp.getOpt().get(i).setPrice(price);
				}
			}
		}
	}
	
	// get the option choice
	public synchronized String getOptionChoice(String setName) {
		OptionSet tmp = findOptionSet(setName);
		if (tmp != null && tmp.getOptionChoice() != null) {
			return tmp.getOptionChoice().getName();
		} else {
			return null;
		}
	}
	
	// if choice is not set, return 0
	public synchronized int getOptionChoicePrice(String setName) {
		OptionSet tmp = findOptionSet(setName);
		if (tmp != null && tmp.getOptionChoice() != null) {
			return (int)tmp.getOptionChoice().getPrice();
		} else {
			return 0;
		}
	}
	
	public synchronized void setOptionChoice(String setName, String optionName) {
		OptionSet tmp = findOptionSet(setName);
		if (tmp != null) {
			tmp.setOptionChoice(optionName);
		}
	}
	
	public synchronized int getTotalPrice() {
		int totalPrice = (int) this.baseprice;
		for (OptionSet tmpSet : optionSet) {
			if (tmpSet != null) {
				totalPrice += getOptionChoicePrice(tmpSet.getName());
			}
		}
		return totalPrice;
	}
	
	@Override
	public synchronized String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(make);
		builder.append(",");
		builder.append(model);
		builder.append(",");
		builder.append(baseprice);
		builder.append("\n");
		
		for (OptionSet i : optionSet) {
			builder.append(i.toString());
		}
		builder.append("-- > Option choice: // NULL means not select any one\n");
		for (OptionSet i : optionSet) {
			builder.append(i.getName());
			if (i.getOptionChoice() != null) {
				builder.append(": ");
				builder.append(i.getOptionChoice().getName());
			} else {
				builder.append(":");
				builder.append("NULL");
			}
			builder.append("\n");
		}
		builder.append("Total Price:");
		builder.append(getTotalPrice());
		builder.append("\n");
		
		return builder.toString();
	}
	
	public synchronized void print(){
		System.out.print(this);
	}
}
