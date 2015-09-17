/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * It's just a concrete class which expose to 
 * outer package, the implementations are all
 * in its supper class ProxyAutomobile
 */

package javasmartphone.p1u2.adapter;

public class BuildAuto extends ProxyAutomobile implements ICreateAuto, IUpdateAuto, IFixAuto {
	public BuildAuto() {
		super();
		// Just do nothing 
	}
}
