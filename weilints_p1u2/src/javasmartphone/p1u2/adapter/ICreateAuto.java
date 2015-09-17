/* 
	Author: Wei-Lin Tsai weilints@andrew.cmu.edu
*/
package javasmartphone.p1u2.adapter;

public interface ICreateAuto {
	// Given a text file name a method called BuildAuto can be written to
	// build an instance of Automobile. This method does not have to return
	// the Auto instance.
	public void buildAuto(String filename);
	
 	// This function searches and prints the properties of a given Automodel.
	public void printAuto(String Modelname);
}
