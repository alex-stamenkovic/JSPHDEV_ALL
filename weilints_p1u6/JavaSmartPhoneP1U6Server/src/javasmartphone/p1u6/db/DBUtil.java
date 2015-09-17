/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * The most basic DB connection handle
 * 1. Connect to MySQL
 * 2. Read properties file 
 * 3. Create schema 
 * 4. Create table 
 */
package javasmartphone.p1u6.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class DBUtil {
	// Note, we can change the file name to change the program's behavior
	final String DB_PROPERTIES = "./db/db.properties";

	private Connection conn;
	private Properties props;

	public DBUtil() {
		conn = null;
		loadPropertiesFile();
	}

	/*
	 * 1. open connection to mySQL 
	 * 2. create database (if not existed)
	 * 3. open connection to database
	 * 4 create tables (if not existed)
	 */
	public void initialization() {
		/* Open connection to mySQL */
		getConnection(1);
		if (conn != null) {
			createSchema();
		}
		closeConnection();

		/* Open connection to target database */
		getConnection(2);
		if (conn != null) {
			createTable();
		}
		
		closeConnection();
	}

	/*
	 * Open and read properties file because we need it later
	 */
	protected Properties loadPropertiesFile() {
		props = new Properties();
		try {
			FileInputStream in = new FileInputStream(DB_PROPERTIES);
			props.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err
					.println("Can not load properties file: " + DB_PROPERTIES);
		}
		return props;
	}

	/* An public API to let user get the current connection to schema
	 */
	public Connection getConnection() {
		return getConnection(2);
	}
	
	/*
	 * get connection from db.properties file mode: 1: Connect to mySQL 2:
	 * Connect to database
	 */
	private Connection getConnection(int mode) {
		// close existing connection just in case
		closeConnection();

		String jdbc_driver = props.getProperty("jdbc_driver");  //  "com.mysql.jdbc.Driver";
		String ub_url = props.getProperty("ub_url");            //  "jdbc:mysql://localhost/";
		String db_user = props.getProperty("db_user");			//  "root";
		String db_pass = props.getProperty("db_pass");			//  "macbook";
		String db_name = props.getProperty("db_name");			//  "JavaSmartPhoneP1U6"

		// open connection
		try {
			Class.forName(jdbc_driver);

			if (mode == 1) {
				conn = DriverManager.getConnection(ub_url, db_user, db_pass);
			} else {
				conn = DriverManager.getConnection(ub_url + db_name, db_user,
						db_pass);
			}
		} catch (Exception e) {
			conn = null;
			e.printStackTrace();
		}
		return conn;
	}

	private void execCmdFile(String fileName) {
		Statement statement = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			br.close();
			String query = sb.toString();
			statement = (Statement) conn.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create database if not exited
	private void createSchema() {
		String cmd_path =  props.getProperty("create_schema_cmd_file");
		execCmdFile(cmd_path);
	}

	// Create table if not existed
	private void createTable() {
		// table for model 
		String cmd_path =  props.getProperty("create_tb_model_cmd_file");
		execCmdFile(cmd_path);
		
		// table for option_set 
		cmd_path = props.getProperty("create_tb_option_set_cmd_file");
		execCmdFile(cmd_path);
		
		// table for option
		cmd_path = props.getProperty("create_tb_option_cmd_file");
		execCmdFile(cmd_path);
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection");
			}
		}
	}
}
