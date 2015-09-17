/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */
package javasmartphone.p1u6.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/* Requirement: 
 *   1. the methods in Option and OptionSet class are protected and properties are private
 *      => class can be public but constructor should be protected
*/
public class OptionSet implements Serializable {
	//private static final long serialVersionUID = -2348322372570239828L;
	private ArrayList<Option> opt;
	private String name;
	private Option optionChoice;
	
	protected OptionSet(String name, int size) { 
		this.opt = new ArrayList<Option>(size);
		for (int i = 0; i < size; ++i) {
			opt.add(new Option());
		}
		this.name = name;
	}
	
	protected OptionSet(OptionSet newOptionSet) {
		this.opt = new ArrayList <Option> (newOptionSet.opt.size());
		for (Option tmp : newOptionSet.opt) {
			opt.add(new Option(tmp));
		}
		this.name = newOptionSet.name;
	}

	protected ArrayList<Option> getOpt() {
		return opt;
	}
	
	protected Option findOption(String nameOfOption) {
		if (nameOfOption == null) { return null; }
		for (Option tmp : opt) {
			if (nameOfOption.equals(tmp.getName())) {
				return tmp;
			}
 		}
		return null; // not found
	}

	protected void deleteOption(String nameOfOption) {
		Iterator<Option> it = opt.iterator();
		while (it.hasNext()) {
		    if (it.next().getName().equals(nameOfOption)) {
		        it.remove();
		        if (it == optionChoice) {
		        	optionChoice = null;
		        }
		        break; // we assume it's unique, so break here
		    }
		}
	}
	
	protected void addOption(String name, float price) {
		// we assume there's no duplication
		if (opt == null) {
			opt = new ArrayList<Option>();
		}
		opt.add(new Option(name, price));
	}
	
	protected void updateOption(String name, float price) {
		Option option = findOption(name);
		if (option != null) {
			option.setPrice(price);
		}
	}
	
	protected void updateOption(Option newOption) {
		Option option = findOption(newOption.getName());
		if (option != null) {
			option.setPrice(newOption.getPrice());
		}
	}
	
	protected void updateOption(int index, String name, float price) {
		if (index < opt.size() && index >= 0) {
			opt.get(index).setName(name);
			opt.get(index).setPrice(price);
		}		                          
	}
	
	protected void setOpt(ArrayList<Option> opt) {
		this.opt = new ArrayList<Option> (opt.size());
		for (Option tmp : opt) {
			this.opt.add(new Option(tmp));
		}
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	protected Option getOptionChoice() {
		return optionChoice;
	}

	protected void setOptionChoice(String optionName) {
		if (optionName == null) { return; }
		for (Option tmpOption : opt) {
			if (optionName.equals(tmpOption.getName())) {
				optionChoice = tmpOption;
			}
		}
	}
	
	protected List<String> getAllOptionListAndItsPrice() {
		List <String> res = new ArrayList<String>();
		for (int i = 0; i < opt.size(); i++){
			res.add(opt.get(i).name);
			res.add(Float.toString((opt.get(i).price)));
		}
		return res;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(name);
		builder.append(",");
		boolean first = true;
		for (Option i : opt) {
			if (first) {
				first = false;
			} else {
				builder.append(",");
			}
			builder.append(i.toString());
		}
		builder.append("\n");
		return builder.toString();
	}
	
	protected void print(){
		System.out.print(this);
	}

	// Inner class here
	protected class Option implements Serializable {
		private String name;
		private float price;
		
		protected Option() {
			//do not init string because string will consume extra space
			price = (float) 0.0; 
		}
		
		protected Option(Option opt) {
			this.name = opt.name;
			this.price = opt.price;
		}
		
		protected Option(String name, float price) {
			this.name = name;
			this.price = price;
		}
		protected String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		protected float getPrice() {
			return price;
		}
		protected void setPrice(float price) {
			this.price = price;
		}
		@Override  // note, toString can be public
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(name);
			builder.append(",");
			builder.append(price);
			return builder.toString();
		}	
		protected void print(){
			System.out.print(this);
		}
	} 
}
