/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 */
package javasmartphone.p1u5.server;

import java.util.Properties;
import java.util.Set;

import javasmartphone.p1u5.adapter.BuildAuto;
import javasmartphone.p1u5.model.Automobile;

public class BuildCarModelOptions implements IAutoServer {
	
	@Override
	public void buildAutoFromProperty (Properties props) {
		BuildAuto auto = new BuildAuto();
		auto.buildAutoFromProperty(props);
	}

	@Override
	public Set<String> listAuto() {
		BuildAuto auto = new BuildAuto();
		return auto.listAuto();
	}

	@Override
	public Automobile getAuto(String modelName) {
		BuildAuto auto = new BuildAuto();
		return auto.getAuto(modelName);
	}
}
