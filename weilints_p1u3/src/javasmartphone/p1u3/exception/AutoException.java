/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 */
package javasmartphone.p1u3.exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class AutoException extends Exception {
	private int errorCode;
	private String errorMsg;
	
	public AutoException(EnumAutoErrorCode code, String logFileName) {
		super();
		this.errorCode = code.getId();
		this.errorMsg = code.getMsg();
		logging(logFileName);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	private void logging(String logFileName) {
		try {
			FileWriter file = new FileWriter(logFileName, true);  // append mode
			BufferedWriter buff = new BufferedWriter(file);
			buff.write("[" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()) + "]: ");
			buff.write(toString());
			buff.close();
		} catch (IOException e) {
			 System.out.println("Error ­­ " + e);
		}
	}

	public String fix(int errNo) {
		AutoExceptionFix1to100 fix1to100 = new AutoExceptionFix1to100();
		// only fix MISSING_FILE_NAME(4, "Missing filename or wrong filename") only
		switch (errNo) {
		case 4: return fix1to100.fix4(errNo);

		default:
			return "";
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AutoException [errorCode=");
		builder.append(errorCode);
		builder.append(", errorMsg=");
		builder.append(errorMsg);
		builder.append("]\n");
		return builder.toString();
	}
	
}
