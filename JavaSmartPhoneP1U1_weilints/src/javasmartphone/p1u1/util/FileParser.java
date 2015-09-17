/* Populating data in Model Package using a text file
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Read content from given fileName. Create an 
 * Automotive object, parse the data from input file 
 * and set the automotive object's content accordingly
 */
package javasmartphone.p1u1.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javasmartphone.p1u1.model.Automotive;

public class FileParser {
	
	public Automotive buildAutoObject(String filename) {
		Automotive auto = new Automotive();
		 
		try {
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			boolean isFirstLine = true;
			while (!eof) {
				String line = buff.readLine();
				if (line == null) {
					eof = true;
				} else {
					if (line.startsWith("//")) {
						continue;
					}
					if (isFirstLine) {
						ParseModel(line, auto);
						isFirstLine = false;
					} else {
						ParseOptions(line, auto);
					}
					//System.out.println(line);
				}
			}
			buff.close();
		} catch (IOException e) {
			 System.out.println("Error ­­ " + e);
		}
		
		return auto;
	}

	private void ParseModel(String aLine, Automotive auto) {
		String[] tmp = aLine.split(",");
		auto.setName(tmp[0]);
		auto.setBaseprice(Float.parseFloat(tmp[1]));
	}
	
	private void ParseOptions(String aLine, Automotive auto) {
		String[] tmp = aLine.split(",");
		int len = (tmp.length-1)/2;
		String optionSetName = tmp[0];
		auto.addOptionSet(optionSetName, len);
		for (int i = 0; i < len; i++) {
			String OptionName = tmp[i*2+1];
		    float price = Float.parseFloat(tmp[i*2+2]);
			auto.setOption(optionSetName, i, OptionName, price);
		}
	}
}
