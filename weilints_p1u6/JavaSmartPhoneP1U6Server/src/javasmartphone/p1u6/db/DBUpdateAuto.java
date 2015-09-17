/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 */
package javasmartphone.p1u6.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUpdateAuto extends DBOperation{
	
	// This function searches the Model from database for a given OptionSet and sets the
	// name of OptionSet to newName.
	public void dbUpdateOptionSetName(String modelName, String optionSetname,
			 String newName) {
		initConnection();
		
		if (conn == null) { return; }
		
		// get model_id first 
		int modelID = getModelID(modelName);
		
		if (modelID != 0) {
			String query = pros.getProperty("query_option_set_id_by_model_name_and_set_name");
			// SELECT option_set_id from option_set WHERE model_id = ? AND set_name = ?;
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, modelID);
				stmt.setString(2, optionSetname);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					int set_id = rs.getInt(1);
					query = pros.getProperty("update_set_name_by_id");
					//UPDATE option_set SET set_name = ? WHERE option_set_id = ?;
					stmt = conn.prepareStatement(query);
					stmt.setString(1, newName);
					stmt.setInt(2, set_id);
					stmt.executeUpdate();
				} else {
					System.out.println("Can not find " + optionSetname + "in " + modelName);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		closeConnection();
	}
	
	//This function searches the Model from database for a given OptionSet and Option name,
	// and sets the price to newPrice.
	public void dbUpdateOptionPrice(String modelName, String Optionname,
			String Option, float newprice) {
		initConnection();
		
		if (conn == null) { return; }
		
		// get model_id first 
		int modelID = getModelID(modelName);
		
		if (modelID != 0) {
			String query = pros.getProperty("query_option_set_id_by_model_name_and_set_name");
			// SELECT option_set_id from option_set WHERE model_id = ? AND set_name = ?;
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, modelID);
				stmt.setString(2, Optionname);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					int set_id = rs.getInt(1);
					query = pros.getProperty("update_opt_price_by_id_and_name");
					//UPDATE opt SET price = ? WHERE option_set_id = ? AND opt_name = ?;		
					stmt = conn.prepareStatement(query);
					stmt.setFloat(1, newprice);
					stmt.setInt(2, set_id);
					stmt.setString(3, Option);
					stmt.executeUpdate();
				} else {
					System.out.println("Can not find " + Optionname + "in " + modelName);
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
		closeConnection();
	}
}
