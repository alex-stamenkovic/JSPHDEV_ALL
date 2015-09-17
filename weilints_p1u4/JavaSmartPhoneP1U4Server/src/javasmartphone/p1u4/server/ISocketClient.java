/* 
 * Author: Wei-Lin Tsai weilints@andrew.cmu.edu
 * 
 * Copy from the powerpoint provided by instructor
 * Rename from SocketClientInterface
 */
package javasmartphone.p1u4.server;

public interface ISocketClient {
	boolean openConnection();
	void handleSession();
	void closeSession();
}
