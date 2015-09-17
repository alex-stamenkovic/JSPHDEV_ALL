/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * !! The driver part of the server side 
 * 
 * Setup a Java ServerSocket that will run an instance of BuildCarModelOption class
 * to build Automobile using the Properties file.
 * 
 * Note, the default port is 4444
 * 
 */
package javasmartphone.p1u5.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AutoSocketServer {
	public static void main(String[] args) throws IOException {
		final int PORT = 4444;
		final boolean DEBUG = true;
		ServerSocket serverSocket = null;
		
		// open a server socket 
		try {
			serverSocket = new ServerSocket(PORT);
			if (DEBUG) { 
				System.out.println("AutoSocket Server is binding on port" + PORT);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port:" + PORT);
			System.exit(1);
		}

		// listening and handle request 
		try {
			while (true) { // keep listening 
				// create a new thread and run it
				Socket socket =  serverSocket.accept();
				ServerSideDefaultSocketClient client
					= new ServerSideDefaultSocketClient("localHost", PORT, socket);
				client.start();
			}
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

	}
}
