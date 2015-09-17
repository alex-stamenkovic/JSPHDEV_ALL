/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Use ISyncUpdateAuto interface to update data 
 * in ProxyAuto in a thread safe way.
 * 
 * Please note although we have put the synchronized 
 * code in AutoMobile's API. We still need to put 
 * the synchronized the run method() because the purpose 
 * is different. To put synchronized code in AutoMobile 
 * it to ensure the data robotness of a single AutoMobile
 * because we are not sure if someone else would use 
 * AutoMobile directly in the future. On the contrary, 
 * to put synchronized here is to prevent data corruption
 * when two thread way to modify auto at the same time
 * 
 */
package javasmartphone.p1u3.scale;

import javasmartphone.p1u3.adapter.ISyncUpdateAuto;

public class EditOptions extends Thread {
	private String modelName; 
	private int opt; // option code to call witch function
	private ISyncUpdateAuto auto;

	// for syncUpdateOptionSetName()
	private String oldSetName; 
	private String newSetName;
	
	// for syncUpdateOptionPrice()
	private String optionSetName;
	private String optionName; 
	private float newPrice;
	
	public EditOptions(String name, String modelName, int opt, ISyncUpdateAuto auto) {
		super(name);
		this.modelName = modelName;
		this.opt = opt;
		this.auto = auto;
	}
	
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getOpt() {
		return opt;
	}
	
	public void setOpt(int opt) {
		this.opt = opt;
	}
	
	public ISyncUpdateAuto getAuto() {
		return auto;
	}
	
	public void setAuto (ISyncUpdateAuto auto) {
		this.auto = auto;
	}
	
	public String getOldSetName() {
		return oldSetName;
	}

	public String getNewSetName() {
		return newSetName;
	}

	public String getOptionStName() {
		return optionSetName;
	}

	public String getOptionName() {
		return optionName;
	}

	public float getNewPrice() {
		return newPrice;
	}

	public void setForUpdateOptionSetName(String oldName, String newName) {
		this.oldSetName = oldName;
		this.newSetName = newName;
	}
	
	public void setForUpdateOptionPrice(String optionSetName, String optionName,
			                            float newPrice) {
		this.optionSetName = optionSetName;
		this.optionName = optionName;
		this.newPrice = newPrice;
	}
	
	public void run(){
		// Please see the comment at the top of the file 
		// for the reason why we put synchronized code here
		synchronized (auto) { // if comment this line, than the data will corrupt
			randomSleep();
			switch (opt) {
			case 1:
				System.out.print("\n[Try Update]\n-->Try to update from old name: " + oldSetName);
				System.out.println(" To new name: " + newSetName);
				auto.syncUpdateOptionSetName(modelName, oldSetName, newSetName);
				System.out.println("Update done");
				break;
			case 2: 
				System.out.print("\n[Try Update]\n-->Try to update to new price : " + newPrice);
				System.out.println(" of option: " + optionSetName + "." + optionName);
				auto.syncUpdateOptionPrice(modelName, optionSetName, optionName, newPrice);
				System.out.println("Update done");
				break;
			}
		}
	}
	
	private void randomSleep() {
		try {
			//Thread.currentThread().sleep((long)(3000*Math.random()));
			Thread.currentThread().sleep((long)(3000*Math.random()));
		} catch(InterruptedException e) {
			System.out.println("Interrupted!");
		}
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EditOptions [name=");
		builder.append(this.getName());
		builder.append(", modelName=");
		builder.append(modelName);
		builder.append(", opt=");
		builder.append(opt);
		builder.append(", oldSetName=");
		builder.append(oldSetName);
		builder.append(", newSetName=");
		builder.append(newSetName);
		builder.append(", optionStName=");
		builder.append(optionSetName);
		builder.append(", optionName=");
		builder.append(optionName);
		builder.append(", newPrice=");
		builder.append(newPrice);
		builder.append("]");
		return builder.toString();
	}
}
