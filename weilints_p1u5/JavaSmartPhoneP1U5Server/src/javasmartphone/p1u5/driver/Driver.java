/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * This function is used to test if proxy Automobile is 
 * implemented correctly. First it build Wagon ZTW
 * and Civic via BuildAuto, then it update value of 
 * Wagon ZTW. Each iteration it print out the result 
 * for examination. 
 */

package javasmartphone.p1u5.driver;

import javasmartphone.p1u5.adapter.BuildAuto;
import javasmartphone.p1u5.adapter.ICreateAuto;
import javasmartphone.p1u5.adapter.IEditThread;
import javasmartphone.p1u5.scale.EditOptions;


public class Driver {

	public static void main(String[] args) {
		/* Build an Auto */
		System.out.println("Build Wagon ZTW ...");
		ICreateAuto c1 = new BuildAuto();        // c stands for creator 
		c1.buildAuto("./testData/FordZTW.txt", "definedType");
		// print out
		System.out.println("Print before Update ...");
		c1.printAuto("Wagon ZTW");
		
		/* Test For Multi-Threading part
		 * We have two threads: t1 and t2. 
		 * t1 try to modify the optionSet name from Color to T1Color while
		 * t2 try to modify the optionSet name from T1Color to T2Color 
		 * Then we start t1 following with t2. Because the the code 
		 * in run() method is synchronized so we know that 
		 * t1 will change the color first. As a result, t2 can find T1Color 
		 * and change it to T2Color. This is what we expected. 
		 * 
		 * However, if we remove the synchronization line, the result may be 
		 * unpredictable. To let the error case be triggered more easily, 
		 * I put a random sleep code in the run() method. Therefore, if 
		 * the critical codes were not protected. t2 can execute before 
		 * t1 by chance. In this case, the optionSet name is still Color 
		 * so t2 can not update the name to T2Color. This is a case to 
		 * demonstrate the data corruption without synchronization      
		 * The result is stored in ./testData/DriverOutoutWithoutSync and
		 * ./testData/DriverOutputWithSync  
		 *  */
		IEditThread auto = (IEditThread) c1;
		EditOptions t1 = new EditOptions("t1", "Wagon ZTW", 1/*Update name*/, auto);
		t1.setForUpdateOptionSetName("Color", "T1Color");
		
		EditOptions t2 = new EditOptions("t2", "Wagon ZTW", 1/*Update name*/, auto);
		t2.setForUpdateOptionSetName("T1Color", "T2Color");
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\nPrint after Update ...");
		c1.printAuto("Wagon ZTW");
	}
}
