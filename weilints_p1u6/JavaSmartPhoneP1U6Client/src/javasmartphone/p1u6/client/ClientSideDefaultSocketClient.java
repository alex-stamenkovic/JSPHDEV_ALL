/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Let user to load properties file and then upload 
 * to server side 
 * 
 * The modified version of DefaultSocketClient
 *  */

package javasmartphone.p1u6.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import javasmartphone.p1u6.model.Automobile;

public class ClientSideDefaultSocketClient extends Thread implements
		ISocketClient, ISocketClientConstants {
	// variables from original version of DefaultSocketetClient
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
		if (DEBUG) {
			System.out
					.println("Handling session with " + strHost + ":" + iPort);
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

	public void sendPropertiesObject(Properties props) {
		try {
			objectOutputStream.writeObject(props);
			objectOutputStream.flush();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String receiveInput() {
		try {
			//return reader.readLine();
			return (String)objectInputStream.readObject();
		} catch (Exception e) {
			if (DEBUG) { e.printStackTrace(); }
		} 
		return null;
	}
}
