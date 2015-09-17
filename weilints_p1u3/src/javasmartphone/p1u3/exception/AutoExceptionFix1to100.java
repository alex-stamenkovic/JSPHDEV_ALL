/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * Note: we only fix error = 4 now 
 */
package javasmartphone.p1u3.exception;

public class AutoExceptionFix1to100 {
	// now we only have fix 4 
	public String fix4(int errNo) {
		if (errNo != 4) { return null; }
		return "./testData/FordZTW.txt";  // default file name, can be user input in the future
	}
}
