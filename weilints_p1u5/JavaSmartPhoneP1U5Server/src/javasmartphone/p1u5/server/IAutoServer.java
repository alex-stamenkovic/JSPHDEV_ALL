/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */
package javasmartphone.p1u5.server;

import java.util.Set;
import java.util.Properties;

import javasmartphone.p1u5.model.Automobile;

public interface IAutoServer {
	// build auto and add to the hashMap according to the properties 
	public void buildAutoFromProperty (Properties props);
	
	// return the set of all available model name 
	public Set<String> listAuto();
	
	// return an auto mobile by given name 
	public Automobile getAuto(String modelName);
}
