/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Let user to
 * 1. delete auto from data base
 */
package javasmartphone.p1u6.db;

import java.sql.PreparedStatement;

public class DBDeleteAuto extends DBOperation{
	
	public void dbDeleteAuto(String modelName) {
		if (modelName == null) { return; }
		
		initConnection();
		
		if (conn == null) { return; }
		
		// get model_id first 
		int modelID = getModelID(modelName);
		
		if (modelID != 0) {
			String query = pros.getProperty("delete_model");
			try {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, modelID);
				stmt.executeUpdate();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	
		closeConnection();
	}
}
