/* Populating data in Model Package using a text file
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Read content from given fileName. Create an 
 * Automotive object, parse the data from input file 
 * and set the automotive object's content accordingly
 */
package javasmartphone.p1u2.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javasmartphone.p1u2.exception.AutoException;
import javasmartphone.p1u2.exception.EnumAutoErrorCode;
import javasmartphone.p1u2.model.Automobile;


// Note, all exceptions are caught and handled internally
public class FileParser {
	final String LOG_FILE_PATH = new String ("./loggingData/AutoLog.txt");
	
	public Automobile buildAutoObject(String filename) {
		Automobile auto = new Automobile();
		boolean problemFixed = false;
		do {
			try {
				problemFixed = openFile(filename, auto);
			} catch (AutoException e) {
				System.out.print("Catch exception" + e);
				System.out.println("Reset filename and reopen again ...");
				filename = e.fix(e.getErrorCode());
			}
		} while (problemFixed == false);
		
		return auto;
	}

	// if file is name is not existed, will throw an exception to fix 
	// for other error, just catch the exception and keep moving
	private boolean openFile(String filename, Automobile auto) throws AutoException {
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			boolean isFirstLine = true;
			boolean hasOptionSet = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					if (line.startsWith("//")) {
						continue;
					}
					if (isFirstLine) {
						parseModel(line, auto);
						isFirstLine = false;
					} else {
						parseOptions(line, auto);
						hasOptionSet = true;
					}
					//System.out.println(line);
				}
			}
			// check if is there any option set 
			if (!hasOptionSet) {
				try {
					buff.close();
					throw new AutoException(EnumAutoErrorCode.MISSING_OPTIONSET_DATA, LOG_FILE_PATH);
				} catch (AutoException e) {
					e.printStackTrace();
				}
			} 
			buff.close();
		} catch (FileNotFoundException e) {
			throw new AutoException(EnumAutoErrorCode.MISSING_FILE_NAME, LOG_FILE_PATH);
		} catch (Exception e) {
			 System.out.println("Error ­­ " + e); // all surprises
		}
		return true;
	}
	
	private void parseModel(String aLine, Automobile auto) {
		String[] tmp = aLine.split(",");
		auto.setMake(tmp[0]);
		auto.setModel(tmp[1]);
		if (tmp.length != 3) {   // if price is missing, throw exception 
			try {
				throw new AutoException(EnumAutoErrorCode.MISSING_PRICE, LOG_FILE_PATH);
			} catch (AutoException e ) {
				e.printStackTrace();
			} 
		} else {
			float basePrice = Float.parseFloat(tmp[2]);
			if (basePrice < 0) { // if price is negative, throw exception
				try {
					throw new AutoException(EnumAutoErrorCode.HAVING_NEGATIVE_BASEPRICE, LOG_FILE_PATH);
				} catch (AutoException e) {
					// convert to positive auto matically
					auto.setBaseprice(Float.parseFloat(tmp[2])*(-1));
					System.out.println(e);
					System.out.println("Will converted to positive number automatically");
				}
			} else {
				auto.setBaseprice(Float.parseFloat(tmp[2]));
			}
		}
	}
	
	private void parseOptions(String aLine, Automobile auto) {
		String[] tmp = aLine.split(",");
		int len = (tmp.length-1)/2;

		if (len == 0) { // check if missing option 
			try {
				throw new AutoException(EnumAutoErrorCode.MISSING_OPTION_DATA, LOG_FILE_PATH);
			} catch (AutoException e) {
				e.printStackTrace();
			}
			return;
		}

		// start parsing
		String optionSetName = tmp[0];
		auto.addOptionSet(optionSetName, len);
		for (int i = 0; i < len; i++) {
			String OptionName = tmp[i*2+1];
		    float price = Float.parseFloat(tmp[i*2+2]);
			auto.setOption(optionSetName, i, OptionName, price);
		}
	}
}
