/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Copy from the powerpoint provided by instructor
 * Rename from SocketClientInterface
 */
package javasmartphone.p1u6.server;

public interface ISocketClient {
	boolean openConnection();
	void handleSession();
	void closeSession();
}
