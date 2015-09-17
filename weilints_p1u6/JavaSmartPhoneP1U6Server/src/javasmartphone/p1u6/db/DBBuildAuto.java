/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Let user to
 * 1. insert auto to database
 * 2. print given auto to screen
 * 3. list all auto name
 */

package javasmartphone.p1u6.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javasmartphone.p1u6.model.Automobile;

public class DBBuildAuto extends DBOperation{
	
	public void dbPrintAuto(String modelName) {
		if (modelName == null ) { return; }
		
		initConnection();
		int model_id = getModelID(modelName);
		
		if (model_id == 0) {
			System.out.println("The automobile named: " + modelName + " not found");
			return;
		}
	
		if (conn == null) { return; }
		
		Automobile auto = new Automobile();
		/*Get make and base price*/
		String query;
		try {
			/* Get make and base price */
			query = pros.getProperty("query_model_by_id");
			// SELECT model_name, make, base_price from model WHERE model_id = ?
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, model_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				auto.setModel(modelName);
				auto.setMake(rs.getString(2));
				auto.setBaseprice(rs.getFloat(3));
			} 
			
			/* Get option set */
			query = pros.getProperty("query_option_set_by_id"); 
			// SELECT set_name from option_set WHERE model_id = ?
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, model_id);
			rs = stmt.executeQuery();
			List<String> setNameList = new ArrayList<String>(); 
			while (rs.next()) {
				setNameList.add(rs.getString(1));
			} 
			
			/* for each optionSet */
			for (int i = 0; i < setNameList.size(); ++i) {
				query = pros.getProperty("query_option"); 
				// SELECT opt_name, price from opt RIGHT JOIN option_set ON opt.option_set_id = option_set.option_set_id
				// WHERE model_id = ? AND set_name = ?;
				stmt = conn.prepareStatement(query);
				stmt.setInt(1, model_id);
				stmt.setString(2, setNameList.get(i));
				rs = stmt.executeQuery();
				List<String> optName = new ArrayList<String>();
				List<Float> optPrice = new ArrayList<Float>();
				while (rs.next()) {
					optName.add(rs.getString(1));
					optPrice.add(rs.getFloat(2));
				} 
//				System.out.println(optName.toString());
//				System.out.println(optPrice.toString());
				
				auto.addOptionSet(setNameList.get(i), optName.size());  
				// for each option 
				for (int j = 0; j < optName.size(); ++j) {
					auto.setOption(setNameList.get(i), j, optName.get(j), optPrice.get(j));						
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		auto.print();
		
		closeConnection();
	}
	
	public void dbCreateAuto(Automobile auto) {
		if (auto == null) { return; }
		
		initConnection();
		
		if (conn == null ) { return; }
		
		if (getModelID(auto.getModel()) != 0 ) { return; }
		
		saveModel(auto);
		
		saveOptionSet(auto);
		
		saveOption(auto);
		
		closeConnection();
	}
	
	public List<String> dbListAutoName() {
		initConnection();
		List<String> resList = new ArrayList<String> ();
		
		if (conn == null) { return resList; }
		
		String query = pros.getProperty("query_model_names");
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				resList.add(rs.getString(1));
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		closeConnection();
		return resList;
	} 
	
	private void saveModel(Automobile auto) {
		String query = pros.getProperty("insert_model_cmd");
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, auto.getModel());
			stmt.setString(2, auto.getMake());
			stmt.setFloat(3, auto.getBaseprice());
			stmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void saveOptionSet(Automobile auto) {
		String query = pros.getProperty("insert_option_set_cmd");
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			List<String> setNameList = auto.getAllOptionSetName();
			if (setNameList != null) {
				for (String name : setNameList) {
					stmt.setString(1, auto.getModel());
					stmt.setString(2, name);
					
					stmt.executeUpdate();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void saveOption(Automobile auto) {
		String query = pros.getProperty("insert_option");
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			List<String> setNameList = auto.getAllOptionSetName();
			if (setNameList != null) {
				for (String setName : setNameList) {
					List<String> nameAndPriceList = auto.getOptionsAndPrices(setName);
					for (int i = 0; i < nameAndPriceList.size(); i+=2) {
						stmt.setString(1, auto.getModel());
						stmt.setString(2, setName);
						stmt.setString(3, nameAndPriceList.get(i));
						stmt.setFloat(4, Float.parseFloat(nameAndPriceList.get(i+1)));
						stmt.executeUpdate();
					}
				}
			}
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
