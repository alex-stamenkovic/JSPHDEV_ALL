/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 */
package javasmartphone.p1u4.exception;

public enum EnumAutoErrorCode {
	MISSING_PRICE(1, "Missing base price for Automobile in Text file"),
	MISSING_OPTIONSET_DATA(2, "Missing OptionSet data (or part of it)"),
	MISSING_OPTION_DATA(3, "Missing option data"),
	MISSING_FILE_NAME(4, "Missing filename or wrong filename"),  // can be fixed
	HAVING_NEGATIVE_BASEPRICE(5, "Having negative base price");  // will be fixed in fileParser  

	private final int id; 
	private final String msg;
	
	EnumAutoErrorCode(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}
	
}
