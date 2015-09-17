/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Let user to load properties file and then upload 
 * to server side 
 * 
 * The modified version of DefaultSocketClient
 *  */

package javasmartphone.p1u4.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;

import javasmartphone.p1u4.model.Automobile;

public class ClientSideDefaultSocketClient extends Thread implements
		ISocketClient, ISocketClientConstants {
	// variables from original version of DefaultSocketetClient
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock;
	private String strHost;
	private int iPort;

	// to upload properties object
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	public ClientSideDefaultSocketClient(String strHost, int iPort) {
		setPort(iPort);
		setHost(strHost);
	}// constructor

	public void setPort(int iPort) {
		this.iPort = iPort;
	}

	public void setHost(String strHost) {
		this.strHost = strHost;
	}

	public void run() {
		if (openConnection()) {
			handleSession();
			closeSession();
		}
	}// run

	@Override
	public boolean openConnection() {
		try {
			sock = new Socket(strHost, iPort);
		} catch (IOException socketError) {
			if (DEBUG)
				System.err.println("Unable to connect to " + strHost);
			return false;
		}
		try {
			writer = new PrintWriter(sock.getOutputStream(), true);
			objectOutputStream = new ObjectOutputStream(sock.getOutputStream());
			objectOutputStream.flush();

			reader = new BufferedReader(new InputStreamReader(
					sock.getInputStream()));
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
		if (DEBUG) {
			System.out
					.println("Handling session with " + strHost + ":" + iPort);
		}
	}

	@Override
	public void closeSession() {
		try {
			writer = null;
			objectOutputStream.close();
			reader = null;
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
			writer.println(strOutput);
		} catch (Exception e) {
			if (DEBUG)
				System.out.println("Error writing to " + strHost);
		}
	}

	public void sendPropertiesObject(Properties props) {
		try {
			objectOutputStream.writeObject(props);
		//	objectOutputStream.flush();
		} catch (IOException e) {
			if (DEBUG) {
				e.printStackTrace();
				System.out.println("Error writing to " + strHost);
			}
		}
	}
	
	public Automobile receiveAutomobileObject(String modelName) {
		sendOutput(modelName);
		try {
			return (Automobile)objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String receiveInput() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			if (DEBUG) { e.printStackTrace(); }
		} 
		return null;
	}
}
