/**
 * @author Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * To let user configure a automobile 
 * 1. List all automobile we have 
 * 2. User select specific automobile
 * 3. Print out the default setting of the autobile 
 * 4. User can select option choice 
 * 5. Whenever the selection is completed, print out the 
 *    final result
 */
package javasmartphone.p1u4.client;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javasmartphone.p1u4.model.Automobile;

public class SelectCarOption {
	private Scanner scanner;
	private ClientSideDefaultSocketClient client;
	Set <String> modelNameSet;

	private String host;
	private int port;
	
	final static boolean DEBUG = true;
		
	public SelectCarOption(String host, int port) {
		this.host = host;
		this.port = port;
		modelNameSet = new HashSet<String>();
		scanner = new Scanner(System.in);
	}

	/* The major part
	 *    1. new a new ClientSideDefaultSocketClient
	 *    2. Connect to server to list available auto 
	 *    3. Let user customize an automobile
	 */
	public void configureModel() {
		client = new ClientSideDefaultSocketClient(host, port);
		if (!client.openConnection()) {
			System.out.println("Can not connect to " + host + " on port " + port);
			return;
		}
		
		// send to server 
		listAvailableModel();
		
		// receive the data from server 
		Automobile auto = retrieveAuto();	
		if (auto != null) {
			// Configure and output 
			System.out.println("Get auto" + auto.toString());
			configAuto(auto);
		}
		
		closeSession();
	}
	
	// Get the list of available models from server ans print 
	// to stdout
	public void listAvailableModel() {
		client.sendOutput("[Mode 2] : Configure Mode");
		System.out.println("Available models:");
		String res = client.receiveInput();
		modelNameSet.clear();
		while (res != null && res.length() > 0 ) {
			System.out.println("\t" + res);
			modelNameSet.add(res);
			res = client.receiveInput();
		}
	}
	
	// get automibile from server side 
	public Automobile retrieveAuto() {
		System.out.println("Please type model");
		String modelName = scanner.next();
		while(true) {
			if (modelNameSet.contains(modelName)) {
				System.out.println("Select model: " + modelName);
				return client.receiveAutomobileObject(modelName);
			} else {
				System.out.println("Model " + modelName + " is not valid");
				System.out.println("Please re-type model");
				modelName = scanner.next();
			}
		}
	}
	
	// let user select options and print out the final result
	public void configAuto(Automobile auto) {
		if (auto == null) { return; }
		while (true) {
			System.out.println("Type 1 to cofigure. Type 2 to print result and exit"); 
			if (scanner.nextInt() == 1) {
				System.out.println("Type OptionSet to confgiure");
				String optionSet = scanner.next();
				System.out.println("Type OptionChoice to confgiure");
				String optionChoice = scanner.next();
				System.out.println("Set " + optionSet + " to " +  optionChoice);
				auto.setOptionChoice(optionSet, optionChoice);
			} else {
				System.out.println("Final Result" + auto.toString());
				break;
			}
		}
	}
	
	private void closeSession() {
		client.closeSession();
		client = null;
	}

}
