/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Copy and modify from power point
 * 
 * The core part of the server side. 
 * Whenever a request is accepted, the driver will 
 * create a thread of this class. And this class 
 * will do the following: 
 * 
 * 1. Call getMode() to see the mode 
 * 
 * 2.1 Mode 1 => Load Properties file from client 
 *    and then add it to database 
 * 2.2 Mode 2 => List available automobiles for user 
 *    to select. Pass the selected automobile to 
 *    client part.
 *  */

package javasmartphone.p1u5.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javasmartphone.p1u5.model.Automobile;

public class ServerSideDefaultSocketClient extends Thread implements
		ISocketClient, ISocketClientConstants {
	// variables from original version of DefaultSocketetClient
	private Socket sock;
	private String strHost;
	private int iPort;

	// to build auto
	private BuildCarModelOptions buildCarModelOptions;

	// to upload properties object
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public ServerSideDefaultSocketClient(String strHost, int iPort, Socket sock) {
		this.strHost = strHost;
		this.iPort = iPort;
		this.sock = sock;
		buildCarModelOptions = new BuildCarModelOptions();
	}// constructor

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	@Override
	public boolean openConnection() {
		try {
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
			objectOutputStream.flush();

			objectInputStream = new ObjectInputStream(sock.getInputStream());

		} catch (Exception e) {
			if (DEBUG)
				System.err
						.println("Unable to obtain stream to/from " + strHost);
			return false;
		}
		return true;
	}

	@Override
	public void handleSession() {
		if (DEBUG)
			System.out
					.println("Handling session with " + strHost + ":" + iPort);

		int mode = getMode();
		if (mode == 1) {
			handleProperties();
		} else if (mode == 2) {
			handleConfigureRequest();
		}
	}

	@Override
	public void closeSession() {
		try {
			objectOutputStream.close();
			objectInputStream.close();
			sock.close();
		} catch (IOException e) {
			if (DEBUG)
				System.err.println("Error closing socket to " + strHost);
		}
	}

	public void handleInput(String strInput) {
		System.out.println(strInput);
	}

	public void sendOutput(String strOutput) {
		try {
			objectOutputStream.writeObject(strOutput);
			objectOutputStream.flush();
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("Error writing to " + strHost);
		}
	}

	// Get Properties object from client and build auto
	public void handleProperties() {
		if (DEBUG) {
			System.out.println("Ready to receive property");
		}

		try {
			Properties pros = (Properties) objectInputStream.readObject();
			if (pros != null) {
				System.out.println("Receive " + pros.getProperty("CarModel"));
				buildCarModelOptions.buildAutoFromProperty(pros);
				sendOutput("Add model OK");
			} else {
				System.out.println("can not receive properties");
			}
		} catch (Exception e) {
			if (DEBUG) {
				System.out.println("Error: can not receive Properties from "
						+ "Client");
			}
		}
	}

	public void handleConfigureRequest() {
		if (DEBUG) {
			System.out.println("Ready to list models");
		}
		for (String model : buildCarModelOptions.listAuto()) {
			if (DEBUG) {
				System.out.println("sent list models" + model);
			}
			sendOutput(model);
		}
		sendOutput(""); // to end communication
		if (DEBUG) {
			System.out.println("End of list models");
		}
		try {
			String toBeConfigureModelName = (String)objectInputStream.readObject();
			Automobile auto = buildCarModelOptions
					.getAuto(toBeConfigureModelName);
			objectOutputStream.writeObject(auto);
			objectOutputStream.flush();
		} catch (Exception e) {
			if (DEBUG) {
				System.out.println("[Error] [handleConfigureRequest]"
						+ " Can not send autoMobile back");
			}
		}
	}

	public int getMode() {
		try {
			String inString = (String)objectInputStream.readObject();
			if (inString != null && inString.equals("[Mode 1] : Add model")) {
				return 1;
			} else if ((inString != null && inString
					.equals("[Mode 2] : Configure Mode"))) {
				return 2;
			}
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("[Get Mode] Error with " + strHost + ":"
						+ iPort);
		}
		return 3; // error condition
	}
}
