/**
 * @author Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Let user to load automobile from propertied file 
 * and then upload to server
 */
package javasmartphone.p1u5.client;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import javasmartphone.p1u5.adapter.BuildAuto;

public class CarModelOptionsIO {
	private Scanner scanner;
	private ClientSideDefaultSocketClient client;
	private String host;
	private int port;
	
	final static boolean DEBUG = true;
	
	public CarModelOptionsIO(String host, int port) {
		this.host = host;
		this.port = port;
		scanner = new Scanner(System.in);
	}
	

	public Properties askToGetPropertiesFromFile() {
		System.out.println("Please properties file name: ");
		return loadPropertiesFromFile(scanner.next());
	}

	/**
	 * @param fileName
	 * @return the properties object read from input file
	 */
	private Properties loadPropertiesFromFile(String fileName) {
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream(fileName);
			props.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	/* The major part:
	 *    create a new ClientSideDefaultSocketClient thread 
	 *    and connect to server
	 * 
	 * */
	public void addModel() {
		client = new ClientSideDefaultSocketClient(host, port);;
		if (!client.openConnection()) {
			System.out.println("Can not connect to " + host + " on port " + port);
			return;
		}
	
		Properties props = askToGetPropertiesFromFile();
		if (props != null) {
			if (DEBUG) {
				System.out.println("Ready for sending properties"
						+ props.toString());
			}

			client.sendOutput("[Mode 1] : Add model");
			client.sendPropertiesObject(props);
			String res = client.receiveInput();
			if (res != null && res.equals("Add model OK")) {
				System.out.println("Model build successfully "
						+ props.getProperty("CarModel"));
			} else {
				System.out.println("Model build failed: " + res);
			}
		}
		closeSession();
	}

	public void createAutoFromFile(String fileName) {
		BuildAuto auto = new BuildAuto();
		auto.buildAuto(fileName, "propertyFile");
	}
	
	private void closeSession() {
		client.closeSession();
		client = null;
	}
}
