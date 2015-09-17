/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */
package javasmartphone.p1u1.model;

import java.io.Serializable;


/* Requirement: 
 *   1. the methods in Option and OptionSet class are protected and properties are private
 *      => class can be public but constructor should be protected
*/
public class OptionSet implements Serializable {
	//private static final long serialVersionUID = -2348322372570239828L;
	private Option opt[];
	private String name;
	
	protected OptionSet(String name, int size) { 
		this.opt = new Option[size];
		for (int i = 0; i < opt.length; i++) {
			opt[i] = new Option();
		}
		this.name = name;
	}
	
	protected OptionSet(OptionSet newOptionSet) { 
		this.opt = newOptionSet.opt.clone();
		this.name = newOptionSet.name;
	}

	protected Option[] getOpt() {
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
		if (findOption(nameOfOption) == null) { return; }
		Option[] newOption = new Option[opt.length-1];
		int i = 0;
		for (Option tmp : opt) {
			if (nameOfOption.equals(tmp.getName())) {
				continue; // do not copy
			} else {
				newOption[i++] = tmp;  // reference copy so it's cheap
			}
 		}
	}
	
	protected void addOption(String name, float price) {
		// we assume there's no duplication
		if (opt == null) {
			opt = new Option[1];
			opt[0] = new Option(name, price);
		} else {
			Option[] tmp = new Option[opt.length+1];
			for (int i = 0; i < opt.length; i++) {
				tmp[i] = opt[i];
			}
			tmp[opt.length] = new Option(name, price);
			opt = tmp;
		}
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
		if (index < opt.length && index >= 0) {
			opt[index].setName(name);
			opt[index].setPrice(price);
		}		                          
	}
	
	protected void setOpt(Option[] opt) {
		this.opt = opt.clone();
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
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
