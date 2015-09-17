/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * It's just a concrete class which expose to 
 * outer package, the implementations are all
 * in its supper class ProxyAutomobile
 */

package javasmartphone.p1u4.adapter;

import javasmartphone.p1u4.server.IAutoServer;


public class BuildAuto extends ProxyAutomobile implements ICreateAuto, IUpdateAuto, IFixAuto, IEditThread, IAutoServer {
	public BuildAuto() {
		super();
		// Just do nothing 
	}
}
