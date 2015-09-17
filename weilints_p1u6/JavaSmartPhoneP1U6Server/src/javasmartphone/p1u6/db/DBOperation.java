/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Put common database operation here 
 * ex:
 *   init connection 
 *   cloese connection
 *   get modelID by model name
 */
package javasmartphone.p1u6.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// Put some common variable/operation here here
public abstract class DBOperation {
	protected DBUtil dbUtil;
	protected Connection conn;
	protected Properties pros; 
	
	protected void initConnection() {
		dbUtil = new DBUtil();
		conn = dbUtil.getConnection();
		pros = dbUtil.loadPropertiesFile(); 
	}
	
	protected void closeConnection(){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	/* To check if a model is already existed or not 
	 * If not found, return 0. 
	 * Otherwise, return the model_id
	 * */
	protected int getModelID(String modelName) {
		String query = pros.getProperty("query_model_id_by_model_name");
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, modelName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}	
		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
